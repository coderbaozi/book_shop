package com.bookshop.testall;

import com.bookshop.dao.impl.BookDAO;
import com.bookshop.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class bookDAOTest {
    private BookDAO bookDAO = new BookDAO();
    @Test
    public void testBookDao() throws Exception {

        bookDAO.addBook(new Book(null,"测试小书","小钱钱",new BigDecimal(8888),1000,7888,null));
    }
    @Test
    public void testbookdao() throws Exception {
        Book book = bookDAO.queryBookById((Integer) 5);
        System.out.println(book);
    }
    @Test
    public void testbookdao01() throws Exception {
        List<Book> books = bookDAO.queryBooks();
        System.out.println(books);
    }
    @Test
    public void testbookdao02() throws Exception {
        int i = bookDAO.updateBook(new Book(21,"测试小书","小钱钱",new BigDecimal(8888),5000,7888,null));
    }
    @Test
    public void testbookdao03() throws Exception {
        int i = bookDAO.deleteBookById(21);
    }
    @Test
    public void testsb() throws Exception {
        List<Book> list=bookDAO.queryBookItems(1,4);
        System.out.println(list);
    }
    @Test
    public void testsb01() throws Exception {
        Integer count=bookDAO.queryBooksCount();
        System.out.println(count);
    }
    @Test
    public void testsb02() throws Exception {
        List<Book> books = bookDAO.queryBookItemsByPrice(1, 4, 10, 20);
        System.out.println(books);
        System.out.println(books.size());
    }
    @Test
    public void testsb03() throws Exception {
        System.out.println(bookDAO.queryBooksCountByPrice(10,50));
    }
}
