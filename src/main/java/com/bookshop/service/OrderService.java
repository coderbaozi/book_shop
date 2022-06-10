package com.bookshop.service;

import com.bookshop.pojo.Cart;
import com.bookshop.pojo.Order;

public interface OrderService {
    //生成订单
    public String createOrder(Cart cart, Integer userId) throws Exception;
}
