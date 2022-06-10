package com.bookshop.pojo;

import java.math.BigDecimal;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String imgPath="static/img/default.jpg";

    public Book() {
    }

    public Book(Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        //要求给出的图片路径不能为空
        if(imgPath!=null&&"".equals(imgPath)){
            this.imgPath = imgPath;
        }

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setImgPath(String imgPath) {
        if(imgPath!=null&&"".equals(imgPath)){
            this.imgPath = imgPath;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getSales() {
        return sales;
    }

    public Integer getStock() {
        return stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
