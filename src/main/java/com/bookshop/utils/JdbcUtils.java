package com.bookshop.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource ds=null;
    //在静态代码块完成ds初始化
    static{
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\QZY\\Desktop\\bookshop\\src\\druid.properties"));
            ds= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("有异常");
        }
    }
    //得到连接方法
    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }
    //释放资源，Connection放回连接池,此是的close是数据库连接池实现的close方法。
    public static void close(ResultSet rs, Connection conn, Statement st) throws SQLException {
        if(rs!=null){
            rs.close();
        }
        if (conn!=null){
            conn.close();
        }
        if(st!=null){
            st.close();
        }
    }
}
