package com.allen.springbootmall.dao.impl;

import com.allen.springbootmall.dao.OrderDao;
import com.allen.springbootmall.dto.OrderQueryParams;
import com.allen.springbootmall.dto.ProdcutQueryParams;
import com.allen.springbootmall.model.Order;
import com.allen.springbootmall.model.OrderItem;
import com.allen.springbootmall.rowmapper.OrderItemMapper;
import com.allen.springbootmall.rowmapper.OrderMapper;
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
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "SELECT count(*) FROM `order` WHERE 1=1 ";
        Map<String,Object> map = new HashMap<>();

        sql = addFilterSql(sql,map,orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "SELECT order_id, user_id, total_amount,created_date, last_modified_date " +
                "FROM `order` WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        //查詢條件
        sql = addFilterSql(sql,map,orderQueryParams);

        //排序
        sql = sql + " ORDER BY created_date DESC";

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",orderQueryParams.getLimit());
        map.put("offset",orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderMapper());

        return orderList;
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, " +
                "oi.amount, p.product_name, p.image_url FROM order_item as oi " +
                "LEFT JOIN product as p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id = :orderId";
        Map<String,Object>map = new HashMap<>();
        map.put("orderId",orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemMapper());

        return orderItemList;

    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT order_id, user_id, total_amount, created_date, last_modified_date " +
                "FROM `order` WHERE order_id = :orderId";
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderMapper());

        return orderList.size() > 0? orderList.get(0) : null;

    }

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

    private String addFilterSql(String sql,Map<String, Object> map,
                                OrderQueryParams orderQueryParams) {
        //查詢條件 search
        if(orderQueryParams.getUserId() != null) {
            sql = sql + " AND user_id = :userId";
            map.put("userId",orderQueryParams.getUserId());
        }
        return sql;
    }

}
