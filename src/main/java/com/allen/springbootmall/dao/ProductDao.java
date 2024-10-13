package com.allen.springbootmall.dao;

import com.allen.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

}
