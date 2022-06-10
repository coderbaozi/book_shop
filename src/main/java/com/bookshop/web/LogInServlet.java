package com.bookshop.web;

import com.bookshop.pojo.User;
import com.bookshop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInServlet extends HttpServlet {
    private UserServiceImpl userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.调用service处理业务
        boolean loginuser= false;
        try {
            loginuser = userService.userLogIn(new User(null,username,password,null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(loginuser==false){
            //把错误的信息，和回显的表单项信息，保存到requeat域中
            req.setAttribute("msg","用户名或者密码错误");
            req.setAttribute("username",username);
            //登录失败跳回登录页面
            System.out.println("用户名或密码错误！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
