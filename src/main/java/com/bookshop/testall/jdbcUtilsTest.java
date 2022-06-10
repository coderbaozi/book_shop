package com.bookshop.testall;

import com.bookshop.dao.impl.UserDAO;
import com.bookshop.pojo.User;
import com.bookshop.utils.JdbcUtils;


import java.sql.Connection;
import java.sql.SQLException;

public class jdbcUtilsTest {

    public void testjdbc() throws Exception {
        for (int i = 0; i < 100; i++) {
            Connection connection=JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(null,connection,null);
        }

    }

    public void testlogin() throws Exception {
        User user = new User(5, "meiyitian", "ziyang", "333030@qq.com");
        new UserDAO().saveUser(user);
    }

    public void queryuser() throws Exception {
        String username="nihoa";
        System.out.println(new UserDAO().queryByName(username));
    }

}
