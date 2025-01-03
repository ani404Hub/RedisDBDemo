package org.redisDemo.repo;

import org.redisDemo.entity.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    public static final String HASH_KEY = "Product";
    @Autowired
    private RedisTemplate templ;                                            //encapsulating customed RedisTemplate bean within the class and not exposing it to the outside.
                                                                            //This ensures that other classes or components cannot directly access or modify the injected dependency.

    public ProductItem saveProd(ProductItem prodItem){
        templ.opsForHash().put(HASH_KEY, prodItem.getProdId(), prodItem);   //Parameters are Key(identifies hash name or UserId), UserName(FieldName) & UserValue(FieldValue)
        return prodItem;
    }

    public List<ProductItem> findAll(){
        return templ.opsForHash().values(HASH_KEY);                         //Fetch all the List of field names using the Key(HashName or UserId) but not in sorted order
        //return templ.opsForHash().entries(HASH_KEY);                      //Fetch all the map of records having field names and values
    }

    public ProductItem findProdById(int id){
        System.out.println("Called from DB ");                              //Since we are using caching mechanism, to check if called from db
        return (ProductItem) templ.opsForHash().get(HASH_KEY, id);          //Fetch the particular record using HashKey & FieldName
    }

    public String deleteProd(int id){
        templ.opsForHash().delete(HASH_KEY, id);
        return "Product Deleted";
    }

    public long generateNextId() {
        long genId = templ.opsForValue().increment("product:id:counter", 1001); //Generate sequence using redis template increment counter
        return genId;
    }

}
