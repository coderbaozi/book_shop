package com.bookshop.web;

import com.bookshop.pojo.Book;
import com.bookshop.pojo.Cart;
import com.bookshop.pojo.CartItem;
import com.bookshop.service.BookService;
import com.bookshop.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();
    //加入购物车
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取请求的参数 商品编号
        int id= Integer.parseInt(req.getParameter("id")==null?"0": req.getParameter("id"));
        //调用bookService.queryBookById(id)得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化位CartItem商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice());
        //调用Cart.addItem（CartItem）;添加商品
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastAddBookName",book.getName());
        //重定向回商品列表页面--即页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //获取请求的参数
        int id= Integer.parseInt(req.getParameter("id"));
        //调用Cart中的delete方法删除
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        //重定向到购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    //清空购物车
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clearAll();
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //得到购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int id= Integer.parseInt(req.getParameter("id"));
        int count= Integer.parseInt(req.getParameter("count"));
        //开始修改
        cart.updateCount(id,count);
        //重定向回当前页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
