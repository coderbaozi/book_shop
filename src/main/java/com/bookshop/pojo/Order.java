package com.bookshop.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    //0表示未发货，1表示已发货，2表示已签收
    private Integer status=0;
    private Integer userId;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getUserId() {
        return userId;
    }
}
