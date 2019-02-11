/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.java8.one.functionInteface;

/** 
 * Lambda 使用局部变量
  * @author zz_huns  
 * @version Id: LocalVariableTestDemo.java, v 0.1 2019/2/5 2:32 PM zz_huns Exp $$
  */
public class LocalVariableTestDemo {

    public static void main(String[] args) {

        //lambda表达式允许使用局部变量，但是默认为隐式的final,或者显示的final，否则编译不通过

        // 示例： num = 212 编译失败
//        int num = 21;
//        Runnable runnable1 = () -> System.out.println(num);
//        num = 212;

        //示例：正确
        final int num1 = 22;
        Runnable runnable2 = () -> System.out.println(num1);
    }
}
