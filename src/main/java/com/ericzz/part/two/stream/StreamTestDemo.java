/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.part.two.stream;

import com.ericzz.part.base.pojo.Banana;

import java.util.*;

import static java.util.stream.Collectors.toList;

/** 
 *
 * Stream  筛选举例
  * @author zz_huns  
 * @version Id: StreamTestDemo.java, v 0.1 2019/2/7 12:55 PM zz_huns Exp $$
  */
public class StreamTestDemo {

    public static void main(String[] args) {
        List<Banana> bananas = new ArrayList<>();

        List<Banana> result = new ArrayList<>();
        //java8 之前筛选：

        //方式一：用累加器筛选元素
        for (Banana banana : bananas){
            if (banana.getColor().equals("green")){
                result.add(banana);
            }
        }

        //方式二：用匿名类筛选
        Collections.sort(bananas, new Comparator<Banana>() {
            @Override
            public int compare(Banana o1, Banana o2) {
                return Integer.compare(o1.getWeight(),o2.getWeight());
            }
        });


        //缺点：1.使用了中间变量 result  //2.代码啰嗦
        //java8 实现筛选
        List<String> newResult = bananas.stream()
                .filter(banana -> banana.getWeight() > 300 )
                .sorted(Comparator.comparing(Banana::getColor))
                .map(Banana::getCountry)
                .collect(toList());

        //java8 多核架构并发执行
        List<String> pranalleNewResult = bananas.parallelStream()
                .filter(banana -> banana.getWeight() > 300 )
                .sorted(Comparator.comparing(Banana::getColor))
                .map(Banana::getCountry)
                .collect(toList());

        //---------------------筛选和切片---------------------------------

        //用谓词筛选：

        //Stream接口支持filter。该操作会接受一个谓词(一个返回boolean的函数)作为参数，并才返回
        //所有符合谓词的元素的流。
        List<Banana> bananas1 = bananas.stream()
                .filter(banana -> banana.getWeight() > 300 ) //谓词判断筛选出符合条件的元素
                .collect(toList());

        //筛选各异的元素

        //流还支持一个叫做distince的方法，它会
    }
}
