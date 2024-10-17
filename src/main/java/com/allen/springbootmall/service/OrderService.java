package com.allen.springbootmall.service;

import com.allen.springbootmall.dto.CreateOrderRequest;
import com.allen.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
