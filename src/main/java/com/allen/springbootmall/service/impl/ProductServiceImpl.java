package com.allen.springbootmall.service.impl;

import com.allen.springbootmall.dao.ProductDao;
import com.allen.springbootmall.dto.ProductRequest;
import com.allen.springbootmall.model.Product;
import com.allen.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

}
