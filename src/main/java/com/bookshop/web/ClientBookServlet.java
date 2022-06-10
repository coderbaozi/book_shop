package com.bookshop.web;

import com.bookshop.pojo.Book;
import com.bookshop.pojo.Page;
import com.bookshop.service.BookService;
import com.bookshop.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientBookServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求的参数pageNo，和pageSize
        int pageNo= Integer.parseInt(req.getParameter("pageNo")==null?"1":req.getParameter("pageNo"));
        int pageSize= Integer.parseInt(req.getParameter("pageSize")==null? Page.PAGE_SIZE.toString() :req.getParameter("pageNo"));
        //2.调用bookservic.page（pageNo，pageSize）方法返回Page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        //设置前台分页条的访问路径
        page.setUrl("client/clientBookServlet?action=page");
        //3.保存Page对象到request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求的参数pageNo，和pageSize
        int pageNo= Integer.parseInt(req.getParameter("pageNo")==null?"1":req.getParameter("pageNo"));
        int pageSize= Integer.parseInt(req.getParameter("pageSize")==null? Page.PAGE_SIZE.toString() :req.getParameter("pageNo"));
        int min= Integer.parseInt(req.getParameter("min").equals("")==true?"0":req.getParameter("min"));
        int max= Integer.parseInt(req.getParameter("max").equals("")==true?"999999999":req.getParameter("max"));
        //2.调用bookservic.page（pageNo，pageSize）方法返回Page对象
        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);
        //设置前台分页条的访问路径
        page.setUrl("client/clientBookServlet?action=pageByPrice&min="+min+"&max="+max);
        //3.保存Page对象到request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}
