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

        //1.用谓词筛选：

        //Stream接口支持filter。该操作会接受一个谓词(一个返回boolean的函数)作为参数，并才返回
        //所有符合谓词的元素的流。
        List<Banana> bananas1 = bananas.stream()
                                       .filter(banana -> banana.getWeight() > 300 ) //谓词判断筛选出符合条件的元素
                                       .collect(toList());

        //2.筛选各异的元素

        //流还支持一个叫做distinct的方法，它会返回一个元素各异(根据流所生成元素的hashCode和equals方法实现)
        //的流
        //例如，以下代码会筛选出列表中所有的偶数，并确保没有重复
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,1,2,4,5);
        numbers.stream()
                .filter(integer -> integer % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        //3.截断流

        //流支持limit(n)方法，该方法会返回一个不超过给定长度的流。所需的长度作为参数传递给limit
        //如果留是有序的，则最多会返回前n个元素。
        //例如，建立一个list,选出重量超过300的前3个
        List<Banana> list2 = bananas.stream()
                                    .filter(banana -> banana.getWeight() > 300)
                                    .limit(3)
                                    .collect(toList());

        //4.跳过元素
        //流还支持skip(n)方法，返回一个扔掉了前N个元素的流。如果流中元素不足n个，则返回一个空流。
        //注意: limit(n)和skip(n)是互补的
        //例如，建立一个list,选出重量超过300的前3个剩下的
        List<Banana> bananas2 = bananas.stream()
                                       .filter(banana -> banana.getWeight() > 300)
                                       .skip(3)
                                       .collect(toList());

        //---------------------映射---------------------------------
        


    }
}
