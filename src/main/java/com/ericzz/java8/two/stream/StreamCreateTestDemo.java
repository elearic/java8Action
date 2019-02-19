/**
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.java8.two.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *  java8实战 5.7 构建流
 *   @author zz_huns  
 *  @version Id: StreamCreateTestDemo.java, v 0.1 2019/2/11 1:03 AM zz_huns Exp $$
 * <p>
 * 如何从值序列、数组、文件来创建流，生成函数创建无限流
 */
public class StreamCreateTestDemo {

    public static void main(String[] args) {

        //---------------由值来创建流---------------

        //静态方法：Stream.of 显示的创建流，它可以接受任意数量的参数。
        //举例：
        Stream<String> stream = Stream.of("java8", "action", "hello", "world");
        stream.map(String::toUpperCase).forEach(System.out::print);

        //---------------由数组来创建流---------------

        //静态方法：Arrays.stream.它接受一个数组作为参数
        int[] numbers = {1, 2, 3, 4, 5, 6};
        int sum = Arrays.stream(numbers).sum();


        //---------------由文件来创建流---------------

        long uniquerWords = 0;
        try (Stream<String> lines =
                     Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {    //流会自动关闭
            uniquerWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))       //生成单词流
                    .distinct()  //去重
                    .count();  //计算有多少不相同的单词
        } catch (IOException e) {
            e.printStackTrace();
        }
        //说明：可以使用Files.lines得到一个流，其中的每个元素都是给定文件中的一行。

        //---------------由函数生成流---------------
        //Stream API提供了两个静态方法来从函数生成流
        //Stream.iterate 和 Stream.generate 这两个操作可以创建所谓的无限流，不像从固定集合创建的流,那样有固定大小的流。
        //由iterate 和 generate 产生的流会用给定的函数按需创建值，因此可以无穷无尽的计算下去。一般来说，应该使用limit(n)
        //来对这种流加以限制，以避免打印无穷多个值

        //迭代：Stream.iterate
        Stream.iterate(0, n -> n + 2);     //无限流

        //生成：Stream.generate, 与iterate方法类似，generate方法也可以生成一个无限流。但generate不是依次对
        //每个新生成的值应用函数的。它接受一个Supplier<T>类型的Lambda提供的新的值。
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::print);


    }
}
