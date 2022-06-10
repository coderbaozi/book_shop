package com.bookshop.service.impl;

import com.bookshop.dao.impl.BookDAO;
import com.bookshop.dao.impl.OrderDAO;
import com.bookshop.dao.impl.OrderItemDAO;
import com.bookshop.pojo.*;
import com.bookshop.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO=new OrderDAO();
    private OrderItemDAO orderItemDAO=new OrderItemDAO();
    private BookDAO bookDAO=new BookDAO();
    @Override
    public String createOrder(Cart cart,Integer userId) throws Exception {
        //订单号的唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDAO.saveOrder(order);
        //遍历购物车中每一个商品项转换成为订单项保存到数据库
        for(Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
            //获取每一个购物车中的商品项
            CartItem cartItem=entry.getValue();
            //转换为每一个订单项
            OrderItem orderItem=new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),orderId);
            //保存订单项到数据库
            orderItemDAO.saveOrderItem(orderItem);

            //更新的库存和销量也要修改
            Book book = bookDAO.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+ cartItem.getCount());
            book.setStock(book.getStock()- cartItem.getCount());
            bookDAO.updateBook(book);
        }
        //生成订单后清空购物车
        cart.clearAll();
        return orderId;
    }
}
