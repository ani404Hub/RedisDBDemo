package org.redisDemo;

import org.redisDemo.entity.ProductItem;
import org.redisDemo.repo.ProductDao;
import org.redisDemo.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/productItem")
@EnableCaching                                                                          //Enable caching mechanism present in spring framework
class RedisDemoController {

    @Autowired
    private ProductDao dao;
    @Autowired
    private ProductItemService prodService;

    @PostMapping
    public ProductItem save(@RequestBody ProductItem prodItem){
        return prodService.saveProductItem(prodItem);                                   //calling service layer is good coding standard instead calling dao layer to save data
    }

    @GetMapping
    public List<ProductItem> getAllProdItems(){
        return dao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Product", unless = "#result.prodPrice > 2000")     //Enable caching based on FieldName, set condition on result of field-values & provide hashKey as value
    public ProductItem findProdItem(@PathVariable int id){
        if(dao.findProdById(id) != null)
            return dao.findProdById(id);
        else
            return new ProductItem();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id")                                                            //Delete cached data based on userName
    public String deleteProdItem(@PathVariable int id){
        if(dao.deleteProd(id) != null)
            return dao.deleteProd(id);
        else
            return "No item deleted";
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoController.class, args);
    }
}
