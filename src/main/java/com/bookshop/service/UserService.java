package com.bookshop.service;

import com.bookshop.pojo.User;

//业务层
public interface UserService {
    //用户注册
    public void registUser(User us) throws Exception;
    //用户登录
    public boolean userLogIn(User us) throws Exception;
    //检查用户名
    public boolean existUserName(String username) throws Exception;
    //登陆后将用户信息补全
    public User buQuanUser(User user) throws Exception;
}
