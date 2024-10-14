package com.allen.springbootmall.service;

import com.allen.springbootmall.dto.ProdcutQueryParams;
import com.allen.springbootmall.dto.ProductRequest;
import com.allen.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProducts(ProdcutQueryParams prodcutQueryParams );

    List<Product> getProducts(ProdcutQueryParams prodcutQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updataProduct(Integer productId,ProductRequest productRequest);

    void deleteProduct(Integer productId);


}
