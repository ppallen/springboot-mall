package com.allen.springbootmall.service.impl;

import com.allen.springbootmall.dao.ProductDao;
import com.allen.springbootmall.dto.ProdcutQueryParams;
import com.allen.springbootmall.dto.ProductRequest;
import com.allen.springbootmall.model.Product;
import com.allen.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer countProducts(ProdcutQueryParams prodcutQueryParams) {
        return productDao.countProducts(prodcutQueryParams);
    }

    @Override
    public List<Product> getProducts(ProdcutQueryParams prodcutQueryParams) {
        return productDao.getProducts(prodcutQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updataProduct(Integer productId, ProductRequest productRequest) {
        productDao.updataProduct(productId, productRequest);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }

}
