package com.bookshop.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/*
* 购物车对象
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    //key是商品编号，value是商品对象
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();

    //添加商品项
    public void addItem(CartItem cartItem){
        //不能直接添加，先查看购物车中是否已添加此商品
        //已添加，则数量累加
        //没有添加，则直接放入集合
        CartItem item = items.get(cartItem.getId());
        if(item==null){
            //之前没有添加过此商品
            items.put(cartItem.getId(),cartItem);
        }else{
            //已经添加过此商品
            item.setCount(item.getCount()+1);//数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }

    }
    //删除商品项
    public void deleteItem(Integer id){
        items.remove(id);
    }
    //清空购物车
    public void clearAll(){
        items.clear();
    }
    //修改商品数量
    public void updateCount(Integer id,Integer count){
        //先查看购物车中是否有此商品，如果有，修改商品金额，更新总金额
        CartItem item = items.get(id);
        if(item==null){
            //购物车中没有这个商品
        }else{
            if(count>0){
                item.setCount(count);//数量修改
                item.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));//总金额修改
            }else{
                //修改为count小于0，则直接删除
                items.remove(id);
            }
        }
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
/*        for (Map.Entry<Integer, CartItem> entry:items.entrySet()) {
            totalCount+=entry.getValue().getCount();
        }*/
        for (CartItem item:items.values()) {
            totalCount+=item.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem item:items.values()) {
            totalPrice=totalPrice.add(item.getTotalPrice());
        }

        return totalPrice;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
