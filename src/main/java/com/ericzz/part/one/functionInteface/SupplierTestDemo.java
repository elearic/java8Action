/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.part.one.functionInteface;


import com.ericzz.part.base.pojo.Apple;

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


