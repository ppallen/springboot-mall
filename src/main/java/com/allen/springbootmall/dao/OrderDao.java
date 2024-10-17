package com.allen.springbootmall.dao;

import com.allen.springbootmall.dto.OrderQueryParams;
import com.allen.springbootmall.model.Order;
import com.allen.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId,Integer totalAmount);
    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);


}
