package org.redisDemo.service;

import org.redisDemo.entity.ProductItem;
import org.redisDemo.repo.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductItemService {
    @Autowired
    private ProductDao dao;

    public ProductItem saveProductItem(ProductItem prodItem){
        prodItem.setProdId(dao.generateNextId());
        return dao.saveProd(prodItem);
    }
}
