/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.java8.two.stream;

import com.ericzz.java8.base.pojo.Banana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** 
 * java8实战 5.3 查找与匹配
  * @author zz_huns  
 * @version Id: StreamMatchTestDemo.java, v 0.1 2019/2/10 12:07 AM zz_huns Exp $$
 *
 *
 * 常见数据处理操作:看看数据集中的某些元素是否匹配一个给定的属性
 * StreamAPI 通过allMatch, anyMatch, noneMath, findFirst, findAny 方法提供这样的操作
  */
public class StreamMatchTestDemo {

    public static void main(String[] args) {
        //----------------anyMatch----------------
        //检查谓词是否至少匹配一个元素

        List<Banana> bananas = new ArrayList<>();

        if (bananas.stream().anyMatch(banana -> banana.getWeight() > 300)){
            System.out.println("banana:weight");
        }

        //----------------allMatch----------------
        //检查谓词是否匹配所有元素
        boolean flag = bananas.stream().allMatch(banana -> banana.getWeight() > 300);

        //----------------noneMatch----------------
        //和allMatch相对的是noneMatch,它可以确保流中没有任何元素与给定的谓词匹配

        boolean isNotGreen = bananas.stream().noneMatch(banana -> !banana.getColor().equals("green"));

        //注意：andMatch、allMatch、noneMatch这三个操作都用到了我们所谓的短路，这就是大家熟悉的java
        //中的&& 和 || 运算符短路在流中的版本


        //----------------findAny----------------
        //findAny方法将返回当前流中的任意元素，它可以与其他操作结合使用
        Optional<Banana> banana = bananas.stream().findAny();
        //返回泛型Optional<T> 这里不做介绍

        //----------------findFirst----------------
        //有些流有一个出现顺序来指定流中项目出现的逻辑顺序(比如由List或排序好的数据列生成的流)，对于这种流，你可能想找到第一个元素
        //为此有一个findFirst方法，它的工作方式类似于findAny。例如：给定一个数字列表，下面的代码能找出第一个平方被3整除的数

        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisbleByThree =
                someNumbers.stream()
                            .map(x -> x * 3)
                            .filter(x -> x % 3  ==0)
                            .findFirst();




    }


}
