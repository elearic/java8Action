/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.java8.two.stream;

import com.ericzz.java8.base.pojo.Banana;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** 
 * java8实战 5.6数值流
  * @author zz_huns  
 * @version Id: StreamOriginalTypeTestDemo.java, v 0.1 2019/2/11 12:35 AM zz_huns Exp $$
 *
 * Stream API 提供了原始类型流特化，专门支持处理数值流的方法
 * java8 引入了三个原始类型特化流接口来解决这个问题
 * IntStream  <--->  int
 * DoubleStream  <--->  double
 * LongStream  <--->  long
 * 从而避免了暗含的装箱成本。每个接口都带来了进行常用数值规约的新方法，比如对数值流求和的sum,找到最大元素的max
 * 此外还有在必要时再把它们转换回对象流的方法。
 * 要记住的是，这些特化的原因并不在于流的复杂性，而是装箱造成的复杂性
 *
 *
  */
public class StreamOriginalTypeTestDemo {
    public static void main(String[] args) {
        List<Banana> bananas = new ArrayList<>();


        //-----------------映射到数据流---------------
        int priceSum = bananas.stream()         //返回一个Stream(Banana)
                .mapToInt(Banana::getPrice)     //返回一个IntStream
                .sum();
        //注意：如果流是空的，sum默认返回0。

        //IntStream 求max
        OptionalInt priceMax = bananas.stream()         //返回一个Stream(Banana)
                .mapToInt(Banana::getPrice)     //返回一个IntStream
                .max();

        //IntStream 求min
        OptionalInt priceMin = bananas.stream()         //返回一个Stream(Banana)
                .mapToInt(Banana::getPrice)     //返回一个IntStream
                .min();

        //IntStream 求平均数
        OptionalDouble priceAverage = bananas.stream()         //返回一个Stream(Banana)
                .mapToInt(Banana::getPrice)     //返回一个IntStream
                .average();

        //-----------------转换回对象流---------------
        IntStream intStream = bananas.stream().mapToInt(Banana::getPrice);
        Stream<Integer> integerStream = intStream.boxed();


        //-----------------默认值 OptionalInt---------------
        //-----------------默认值 OptionalDouble---------------
        //-----------------默认值 OptionalLong---------------

        //TODO 数值流处理DEMO

    }
}
