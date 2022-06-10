package com.bookshop.testall;

import com.bookshop.pojo.Cart;
import com.bookshop.pojo.CartItem;
import com.bookshop.pojo.Order;
import com.bookshop.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {
    @Test
    public void test01() throws Exception {
        OrderServiceImpl orderService = new OrderServiceImpl();
        Cart cart = new Cart();
        cart.addItem(new CartItem(4,"一本小书书",1,new BigDecimal(20)));
        cart.addItem(new CartItem(5,"一本max书书",2,new BigDecimal(40)));
        String ordernumber = orderService.createOrder(cart, 1);
        System.out.println("订单号码为"+ordernumber);
    }
}
