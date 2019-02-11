/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.java8.one.functionInteface;

import com.ericzz.java8.base.pojo.Banana;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/** 
 * lambda方法引用
  * @author zz_huns  
 * @version Id: LambdaQuoteTestDemo.java, v 0.1 2019/2/5 2:40 PM zz_huns Exp $$
 *
 *                       Lambda 及其等效方法引用的例子
 *          Lambda                                          等效的方法引用
 * (Apple a) -> a.getWeight()                           Apple::getWeight
 * () -> Thread.currentThread().dumpStack()             Thread.currentThread()::dumpStack
 * (str,i) -> str.substring(i)                          String::subString
 * (String,s) -> System.out.printLn(s)                  System.out::pringln
  */
public class LambdaQuoteTestDemo {

    public static void main(String[] args) {
        List<String> inventory = Arrays.asList("alix","bob");

        //方法引用主要有三类：
        //
        // 第一类：指向 静态方法 的方法引用
        //BiFunction 函数描述符为 (T,U) -> R
        //Integer.parseInt 的函数签名为 (String ,Integer ) -> Integer
        BiFunction<String, Integer, Integer> stringIntegerIntegerBiFunction = Integer::parseInt;
        Integer aaa = stringIntegerIntegerBiFunction.apply("101010", 8);
        System.out.println("result--->"+aaa);

        // 第二类：指向 任意类型实例方法 的方法引用
        String str = "hello world";
        inventory.stream().mapToInt(String::length).max();

        // 第三类：指向现有对象的实例方法的方法引用
        Supplier<Banana> supplier = Banana::new;
        Banana banana = supplier.get();
        //lambda    (args) -> ClassName.staticMethod(args)
        //方法引用   ClassName:staticMethod

        //lambda    (arg0,rest) -> arg0.instanceMethod(rest)
        //方法引用   ClassName::instanceMethod

        //lambda    (args) -> expr.instanceMethod(args)
        //方法引用   expr::instanceMethod

    }
}


/**
 *  构造函数引用
 *  对于一个现有构造函数，可以利用它的名称和关键字new来创建它的一个引用  ClassName::new
 *  它的功能与指向静态方法的引用类似。例如，假设有一个构造函数没有参数。
 *  它适合Supplier的签名 ()->Apple
 */
class QuteConstruction{
    public static void main(String[] args) {
        //构造器函数引用指向默认的Banana()构造函数
        Supplier<Banana> supplier = Banana::new;
        //调用Supplier的get方法将产生一个新的banana;
        Banana banana = supplier.get();

        //等价于
        //利用默认构造函数创建Banana的lambda表达式
        Supplier<Banana> supplier1 = () -> new Banana();
        //调用Supplier的get方法将产生一个新的banana;
        Banana banana1 = supplier1.get();

        //--------------------------------------------------------

        //如果你的构造函数的签名是Banana(Integer weight),那么它就适合Function接口的签名，于是可以这么写
        //指向Banana(Integer weight)的构造函数引用
        Function<Integer,Banana> function = Banana::new;
        //调用该Function函数的apply方法，并给出要求的质量，将产生一个Banana
        Banana banana2 = function.apply(110);

        //等价于

        //指向Banane(Integer weight)的构造函数引用
        Function<Integer,Banana> function1 = (weight) -> new Banana(weight);
        //调用该Function函数的apply方法，并给出要求的质量，将产生一个Banana
        Banana banana3 = function1.apply(200);


        //--------------------------------------------------------

        //如果你有一个具有两个参数的构造函数Banana(String color, Integer weight)
        //那么它就适合BiFunction接口的签名，于是就可以这样写
        //指向Banana(Strng color,Integer weight)构造函数的引用
        BiFunction<String,Integer,Banana> biFunction = Banana::new;
        //调用该BiFunction函数的apply方法，并给出要求的颜色和质量，将产生一个新的Banana质量
        Banana banana4 = biFunction.apply("green",110);

    }
}