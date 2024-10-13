package com.allen.springbootmall.dao;

import com.allen.springbootmall.dto.ProductRequest;
import com.allen.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updataProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
