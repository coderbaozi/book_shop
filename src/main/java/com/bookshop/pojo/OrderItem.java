package com.bookshop.pojo;

import java.math.BigDecimal;

public class OrderItem {
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(Integer id, String name, Integer count, BigDecimal price, String orderId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.orderId = orderId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalPrice() {
        totalPrice=price.multiply(BigDecimal.valueOf(count));
        return totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
