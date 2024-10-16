package com.allen.springbootmall.service.impl;

import com.allen.springbootmall.dao.OrderDao;
import com.allen.springbootmall.dao.ProductDao;
import com.allen.springbootmall.dto.BuyItem;
import com.allen.springbootmall.dto.CreateOrderRequest;
import com.allen.springbootmall.model.OrderItem;
import com.allen.springbootmall.model.Product;
import com.allen.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            //計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

            //轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);

        }

        //創建訂單
        Integer orderId = orderDao.createOrder(userId,totalAmount);
        orderDao.createOrderItem(orderId, orderItemList);

        return orderId;
    }
}
