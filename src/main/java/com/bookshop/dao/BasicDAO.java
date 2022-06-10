package com.bookshop.dao;

import com.bookshop.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//添加泛型可以操作domain
public class BasicDAO<T> {
    private QueryRunner qr=new QueryRunner();

    //开发通用的dml方法
    public int update(String sql,Object...args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        int affectedRows = qr.update(connection, sql, args);
        JdbcUtils.close(null,connection,null);
        return affectedRows;
    }

    //开发查询多行
    public List<T> queryMulity(String sql,Class<T> clazz,Object...args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        List<T> list = qr.query(connection, sql, new BeanListHandler<>(clazz), args);
        JdbcUtils.close(null,connection,null);
        return list;
    }
    //查询单行
    public T querySingle(String sql,Class<T> clazz,Object...args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        T rs = qr.query(connection, sql, new BeanHandler<>(clazz), args);
        JdbcUtils.close(null,connection,null);
        return rs;
    }
    //查询单个对象
    public Object queryScalar(String sql,Object...args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        Object rs = qr.query(connection, sql, new ScalarHandler<>(), args);
        JdbcUtils.close(null,connection,null);
        return rs;
    }
}
