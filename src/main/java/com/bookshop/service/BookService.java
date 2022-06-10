package com.bookshop.service;

import com.bookshop.pojo.Page;
import com.bookshop.pojo.Book;

import java.util.List;

public interface BookService {
    //添加图书
    public void addBook(Book book) throws Exception;
    //删除图书根据id
    public void deleteBook(Integer id) throws Exception;
    //查询全部图书
    public List<Book> queryAllBooks() throws Exception;
    //查找某个图书
    public Book queryBookById(Integer id) throws Exception;
    //修改图书
    public void updateBook(Book book) throws Exception;
    //分页操作
    public Page<Book> page(Integer pageNo, Integer pageSize) throws Exception;
    //价格查询的分页操作
    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize,Integer min,Integer max) throws Exception;
}
