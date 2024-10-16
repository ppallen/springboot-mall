package com.allen.springbootmall.dao.impl;

import com.allen.springbootmall.dao.OrderDao;
import com.allen.springbootmall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `order`(user_id, total_amount, created_date, last_modified_date)" +
                " VALUES(:userId, :totalAmount, :createdDate, :lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        map.put("createdDate", timestamp);
        map.put("lastModifiedDate", timestamp);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int orderId = keyHolder.getKey().intValue();
        return orderId;
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "INSERT INTO order_item(order_id, product_id, quantity, amount)" +
                " VALUES(:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] paramsSource = new MapSqlParameterSource[orderItemList.size()];

        for(int i =0; i<orderItemList.size(); i++){
            OrderItem orderItem = orderItemList.get(i);

            paramsSource[i] = new MapSqlParameterSource();
            paramsSource[i].addValue("orderId", orderId);
            paramsSource[i].addValue("productId", orderItem.getProductId());
            paramsSource[i].addValue("quantity", orderItem.getQuantity());
            paramsSource[i].addValue("amount", orderItem.getAmount());
        }
        //批量插入
        namedParameterJdbcTemplate.batchUpdate(sql, paramsSource);


    }
}
