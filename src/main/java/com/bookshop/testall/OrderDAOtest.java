package com.bookshop.testall;

import com.bookshop.dao.impl.OrderDAO;
import com.bookshop.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDAOtest {

    @Test
    public void test01() throws Exception {
        OrderDAO orderDAO=new OrderDAO();
        orderDAO.saveOrder(new Order("123456",new Date(),new BigDecimal(100),0,1));
    }
}
