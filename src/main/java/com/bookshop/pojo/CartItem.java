package com.bookshop.pojo;

import java.math.BigDecimal;

public class CartItem {
    /*
    *   购物车的商品项
     */
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String orderId;

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice, String orderId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = price.multiply(new BigDecimal(count));
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

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
        return totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
