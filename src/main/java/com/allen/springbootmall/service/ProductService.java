package com.allen.springbootmall.service;

import com.allen.springbootmall.dto.ProductRequest;
import com.allen.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

}
