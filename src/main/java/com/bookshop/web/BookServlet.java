package com.bookshop.web;

import com.bookshop.pojo.Page;
import com.bookshop.pojo.Book;
import com.bookshop.service.BookService;
import com.bookshop.service.impl.BookServiceImpl;
import com.bookshop.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();
    //添加图书
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取请求参数，封装为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用service层保存图书
        bookService.addBook(book);
        //跳到图书列表页面,请求转发会导致用户没刷新一次都会发送一次请求
/*        req.getRequestDispatcher("/manager/bookServlet?action=page").forward(req,resp);*/
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo")+1);
    }
    //删除图书
    protected void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取请求参数
        Integer id = Integer.valueOf(req.getParameter("id"));
        //删除图书
        bookService.deleteBook(id);
        //重定向图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    //修改图书
    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取请求的参数--封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用service层，修改图书
        bookService.updateBook(book);
        //重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    //列表查询图书
    protected void queryBookList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //通过BookService查询全部图书
        List<Book> books = bookService.queryAllBooks();
        //把全部图书保存到request域中
        req.setAttribute("books",books);
        //请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    //用于修改图书时，填入数据
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取请求的参数图书编号
        Integer id = Integer.valueOf(req.getParameter("id"));
        //调用bookService层来查询图书
        Book book = bookService.queryBookById(id);
        //保存图书到request域中
        req.setAttribute("book",book);
        //请求转发到pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
    //处理分页
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求的参数pageNo，和pageSize
        int pageNo= Integer.parseInt(req.getParameter("pageNo")==null?"1":req.getParameter("pageNo"));
        int pageSize= Integer.parseInt(req.getParameter("pageSize")==null? Page.PAGE_SIZE.toString() :req.getParameter("pageNo"));
        //2.调用bookservic.page（pageNo，pageSize）方法返回Page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        //设置后台分页条的访问路径
        page.setUrl("manager/bookServlet?action=page");
        //3.保存Page对象到request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
