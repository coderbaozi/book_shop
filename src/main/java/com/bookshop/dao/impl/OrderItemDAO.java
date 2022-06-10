package com.bookshop.dao.impl;

import com.bookshop.dao.BasicDAO;
import com.bookshop.pojo.Order;
import com.bookshop.pojo.OrderItem;

public class OrderItemDAO extends BasicDAO<OrderItem> {

    public int saveOrderItem(OrderItem orderItem) throws Exception {
        String sql="insert into order_item(name,count,price,totalPrice,orderId) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
