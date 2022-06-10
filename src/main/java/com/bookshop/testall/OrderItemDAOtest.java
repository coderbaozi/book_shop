package com.bookshop.testall;

import com.bookshop.dao.impl.OrderItemDAO;
import com.bookshop.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDAOtest {
    @Test
    public void test01() throws Exception {
        OrderItem orderItem = new OrderItem(null,"一本测试小书",1,new BigDecimal(20),"123456");
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        orderItemDAO.saveOrderItem(orderItem);
    }
}
