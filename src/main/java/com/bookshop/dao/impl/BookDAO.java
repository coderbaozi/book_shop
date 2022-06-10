package com.bookshop.dao.impl;

import com.bookshop.dao.BasicDAO;
import com.bookshop.pojo.Book;

import java.util.List;

public class BookDAO extends BasicDAO<Book> {
        //添加图书
        public int addBook(Book book) throws Exception {
            String sql="insert into book_book(name,price,author,sales,stock,imgPath) values(?,?,?,?,?,?)";
            return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath());
        }
        //删除图书通过id
        public int deleteBookById(Integer id) throws Exception {
            String sql="delete from book_book where id=?";
            return update(sql,id);
        }
        //更新/修改图书
        public int updateBook(Book book) throws Exception {
            String sql="update book_book set name=?,price=?,author=?,sales=?,stock=?,imgPath=? where id=?";
            return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
        }
        //查询图书通过id
        public Book queryBookById(Integer id) throws Exception {
            String sql="select * from book_book where id=?";
            return querySingle(sql,Book.class,id);
        }
        //查询全部图书
        public List<Book> queryBooks() throws Exception {
            String sql="select * from book_book";
            return queryMulity(sql,Book.class);
        }
        //查询全部图书的总数
        public Integer queryBooksCount() throws Exception {
            String sql="select count(*) from book_book";
            Number number=(Number) queryScalar(sql);
            return number.intValue();
        }
        //分页查询，查询某一页的数据
        public List<Book> queryBookItems(Integer pageNo,Integer pageSize) throws Exception {
            String sql="select * from book_book limit ?,?";
            return queryMulity(sql,Book.class,(pageNo-1)*pageSize,pageSize);
        }
        //查询价格区间内的图书个数
        public Integer queryBooksCountByPrice(Integer min,Integer max) throws Exception {
            String sql="select count(*) from book_book where price between ? and ?";
            Number number=(Number) queryScalar(sql,min,max);
            return number.intValue();
        }

        public List<Book> queryBookItemsByPrice(Integer pageNo, Integer pageSize,Integer min,Integer max) throws Exception {
            String sql="select * from book_book where price between ? and ? order by price limit ?,?";
            return queryMulity(sql,Book.class,min,max,pageNo,pageSize);
        }
}
