package com.allen.springbootmall.service;

import com.allen.springbootmall.dto.CreateOrderRequest;
import com.allen.springbootmall.dto.OrderQueryParams;
import com.allen.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
