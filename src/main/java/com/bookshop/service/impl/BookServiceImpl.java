package com.bookshop.service.impl;

import com.bookshop.dao.impl.BookDAO;
import com.bookshop.pojo.Page;
import com.bookshop.pojo.Book;
import com.bookshop.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDAO bookDAO=new BookDAO();
    @Override
    public void addBook(Book book) throws Exception {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) throws Exception {
        bookDAO.deleteBookById(id);
    }

    @Override
    public List<Book> queryAllBooks() throws Exception {
        List<Book> books = bookDAO.queryBooks();
        return books;
    }

    @Override
    public Book queryBookById(Integer id) throws Exception {
        Book book = bookDAO.queryBookById(id);
        return book;
    }

    @Override
    public void updateBook(Book book) throws Exception {
        bookDAO.updateBook(book);
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) throws Exception {
        Page<Book> page=new Page<Book>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount=bookDAO.queryBooksCount();
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal=pageTotalCount%pageSize==0?pageTotalCount/pageSize:pageTotalCount/pageSize+1;
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //得到当前页数据
        List<Book> items=bookDAO.queryBookItems(page.getPageNo(),pageSize);
        //保存当前页的数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max) throws Exception {
        Page<Book> page=new Page<Book>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount=bookDAO.queryBooksCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal=pageTotalCount%pageSize==0?pageTotalCount/pageSize:pageTotalCount/pageSize+1;
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //得到当前页数据
        List<Book> items=bookDAO.queryBookItemsByPrice(page.getPageNo(),pageSize,min,max);
        //保存当前页的数据
        page.setItems(items);
        return page;
    }


}
