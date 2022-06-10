package com.bookshop.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    /*
    利用反射来封装减少，if else if的使用，动态获取更加方便。
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
