package com.bookshop.testall;

import com.bookshop.pojo.Cart;
import com.bookshop.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {
    @Test
    public void test01(){
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java二笔",10,new BigDecimal(20)));
        cart.addItem(new CartItem(1,"java二笔",1,new BigDecimal(20)));
        cart.addItem(new CartItem(2,"java二笔1",2,new BigDecimal(20)));
        System.out.println(cart);
    }
    @Test
    public void test02(){
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java二笔",1,new BigDecimal(20)));
        cart.addItem(new CartItem(1,"java二笔",1,new BigDecimal(20)));
        cart.addItem(new CartItem(2,"java二笔1",2,new BigDecimal(20)));
        cart.deleteItem(1);
        System.out.println(cart);
    }
    @Test
    public void test03(){
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java二笔",1,new BigDecimal(20)));
        cart.addItem(new CartItem(1,"java二笔",1,new BigDecimal(20)));
        cart.addItem(new CartItem(2,"java二笔1",2,new BigDecimal(20)));
        cart.clearAll();
        System.out.println(cart);
    }
    @Test
    public void test04(){
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java二笔",1,new BigDecimal(20)));
        cart.addItem(new CartItem(1,"java二笔",1,new BigDecimal(20)));
        cart.addItem(new CartItem(2,"java二笔1",2,new BigDecimal(20)));
        cart.updateCount(1,5);
        System.out.println(cart);
    }
}
