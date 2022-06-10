package com.bookshop.service.impl;

import com.bookshop.dao.impl.UserDAO;
import com.bookshop.pojo.User;
import com.bookshop.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userdao=new UserDAO();
    @Override
    public void registUser(User us) throws Exception {
        userdao.saveUser(us);
    }

    @Override
    public boolean userLogIn(User us) throws Exception {
        if(userdao.queryByNameAndPassword(us.getUsername(),us.getPassword())==null){
            return false;
        }
        //返回登陆后用户的完整信息
        us=userdao.queryByName(us.getUsername());
            return true;
    }

    @Override
    public boolean existUserName(String username) throws Exception {
        if(userdao.queryByName(username)!=null){
            return true;
        }
        return false;
    }

    @Override
    public User buQuanUser(User user) throws Exception {
        user=userdao.queryByName(user.getUsername());
        return user;
    }
}
