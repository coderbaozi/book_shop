package com.bookshop.dao.impl;

import com.bookshop.dao.BasicDAO;
import com.bookshop.pojo.Order;

public class OrderDAO extends BasicDAO<Order> {
    //订单保存
    public int saveOrder(Order order) throws Exception {
        String sql="insert into book_order(orderId,createTime,price,status,userId) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
