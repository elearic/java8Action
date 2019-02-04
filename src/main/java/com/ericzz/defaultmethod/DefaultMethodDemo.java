/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.defaultmethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** 
 * 
  * @author zz_huns  
 * @version Id: DefaultMethodDemo.java, v 0.1 2019/1/27 11:22 PM zz_huns Exp $$
  */
public class DefaultMethodDemo {

    public static void main(String[] args){
        List list = new ArrayList<Integer>();
        list.add("0");
        list.add("1");
        list.add("2");

        list.sort(new Comparator() {
            public int compare(Object o1, Object o2) {
                Integer int1 = Integer.parseInt(o1.toString());
                Integer int2 = Integer.parseInt(o2.toString());
                if (int1 <= int2){
                    return int1;
                }
                return int2;
            }
        });

        Thread thread = new Thread(() -> {
            System.out.println("hello world");
        });


    }
}
