/**
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.part.two.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** 
 * java8实战 5.4 归约
  * @author zz_huns  
 * @version Id: StreamMapReduceTestDemo.java, v 0.1 2019/2/10 1:09 AM zz_huns Exp $$
 *
 * 本章将学习如何将一个流中的元素组合起来，使用reduce操作来表达更复杂的查询
 * 比如"计算菜单中总卡路里"或者"菜单中卡路里最高的菜是哪个"，此类查询需要将
 * 流中所有元素反复结合起来，得到一个值。比如一个Integer.这样的查询可以被归类
 * 为归约操作(将流归约成一个值)。用函数式编程语言的术语来讲，这称为折叠
  */
public class StreamMapReduceTestDemo {

    public static void main(String[] args) {

        //------------------元素求和------------------
        //在研究如何使用reduce方法之前，先来看看如何使用for-each循环来对数字列表中的元素求和
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = 0;
        for (int x : numbers) {
            sum += x;
        }
        //numbers 中的每个元素都用加法运算符反复迭代来得到结果。通过反复使用加法，你把一个数字列表规约成一个数字。
        //这段代码中有两个参数
        //*** 总和变量的初始值，在这里是0
        //*** 将列表中所有元素结合在一起的操作，在这里是 +；
        //要是还能把所有的数字相乘，而不必去复制粘贴这段代码。那不是更好。这正是reduce的操作用武之地 。
        //它对这种重复应用的模式做了抽象。
        //
        // 举例如下:
        int sum1 = numbers.stream().reduce(0, (a, b) -> a + b);

        //reduce接受两个参数
        //*** 一个初始值，这里是0
        //*** 一个BinaryOperator<T> (继承了BiFunction<T,T,R>)来将两个元素结合起来产生一个新值，这里我们用的是lambda(a,b) -> a + b;
        //你也很容易把所有的元素相乘，只需要将另一个lambda(a,b) -> a * b 传递给reduce操作就可以了

        //方法一：
        int product = numbers.stream().reduce(1, (a, b) -> a * b);


        //方法二：
        int product1 = numbers.stream().reduce(0, Integer::sum);

        //reduce 无初始值
        //reduce还有一个重载的变体，它不接受初始值，但是会返回一个Optional对象
        //为什么它会返回一个Optional<Integer>呢？考虑流中没有任何元素的情况。reduce操作无法返回其和，因为它没有初始值
        //这就是为什么结果被包裹在一个Optional对象里，以表明和可能不存在。来看看reduce还能做什么

        //------------------最大值和最小值------------------

        //求最大值
        Optional<Integer> max = numbers.stream().reduce(Integer::max);

        //求最小值
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

    }
}
