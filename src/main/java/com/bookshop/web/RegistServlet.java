package com.bookshop.web;

import com.bookshop.pojo.User;
import com.bookshop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserServiceImpl userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2.检查验证码是否正确----验证码写死
        if("abcde".equalsIgnoreCase(code)){
            //1.检查用户名是否正确
            try {
                if(userService.existUserName(username)){
                    System.out.println("用户名["+username+"]已存在");
                    //把回显信息保存到request域中
                    req.setAttribute("msg","用户名已存在！");
                    req.setAttribute("username",username);
                    req.setAttribute("email",email);
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                }else{
                    userService.registUser(new User(null,username,password,email));
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //把回显信息保存到request域中
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            //不正确，跳回注册页面
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
