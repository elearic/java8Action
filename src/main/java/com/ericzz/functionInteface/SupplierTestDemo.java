/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.functionInteface;


import java.util.function.Supplier;

/** 
 * Supplier
  * @author zz_huns  
 * @version Id: SupplierTestDemo.java, v 0.1 2019/2/4 11:49 PM zz_huns Exp $$
  */
public class SupplierTestDemo {

    //java.util.Function.Supplier<T> 具有唯一一个抽象方法叫做get,
    //代表的函数描述符是 () -> T,
    Supplier<Apple> supplier = () -> new Apple();



}


class Apple {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}