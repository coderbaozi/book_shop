package com.bookshop.web;

import com.bookshop.pojo.Cart;
import com.bookshop.pojo.User;
import com.bookshop.service.OrderService;
import com.bookshop.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{
    private OrderService orderService=new OrderServiceImpl();
    //生成订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //先获取Cart对象，获取userId
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        //用户登录后保存到session中
        User loginUser = (User) req.getSession().getAttribute("user");
        Integer userId= loginUser.getId();
        //调用orderService方法生成订单
        String orderNumber = orderService.createOrder(cart, userId);
        //将订单编号放入session域中
        req.getSession().setAttribute("orderNumber",orderNumber);
        //重定向到/pages/cart/checkout.jsp页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
