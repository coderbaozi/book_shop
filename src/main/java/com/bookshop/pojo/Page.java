package com.bookshop.pojo;


import java.util.List;

/*
* 分页模块
* */
public class Page <T>{
    public static final Integer PAGE_SIZE=4;
    //当前页码
    private Integer pageNo;
    //总页码
    private Integer pageTotal;
    //当前页显示的数量
    private Integer pageSize=PAGE_SIZE;
    //得到总记录数
    private Integer pageTotalCount;
    //当前页数据
    private List<T> items;
    //分页条中的url
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }


    public void setPageNo(Integer pageNo) {
        //判断如果用户输入的跳转参数小于1则返回第一页，大于一则返回最后一页
        pageNo=pageNo<1?1:pageNo;
        pageNo=pageNo>pageTotal?pageTotal:pageNo;
        this.pageNo = pageNo;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
