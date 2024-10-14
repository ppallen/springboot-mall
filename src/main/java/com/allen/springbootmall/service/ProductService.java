package com.allen.springbootmall.service;

import com.allen.springbootmall.constant.ProductCategory;
import com.allen.springbootmall.dto.ProductRequest;
import com.allen.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category,String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updataProduct(Integer productId,ProductRequest productRequest);

    void deleteProduct(Integer productId);


}
