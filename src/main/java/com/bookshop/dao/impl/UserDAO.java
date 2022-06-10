package com.bookshop.dao.impl;

import com.bookshop.dao.BasicDAO;
import com.bookshop.pojo.User;

import java.sql.SQLException;

public class UserDAO extends BasicDAO<User> {
    //查询用户通过名字
    public User queryByName(String username) throws Exception {
        String sql="select id,username,email from book_user where username=?";
        return querySingle(sql,User.class,username);
    }
    //查询名字和密码
    public User queryByNameAndPassword(String username,String password) throws Exception {
        String sql="select id,username,email from book_user where username=?and password=?";
        return querySingle(sql,User.class,username,password);
    }
    //保存用户
    public int saveUser(User us) throws Exception {
        String sql="insert into book_user(username,password,email) values(?,?,?)";
        return update(sql,us.getUsername(),us.getPassword(),us.getEmail());
    }
}
