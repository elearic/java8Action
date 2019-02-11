/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.java8.two.stream;

import com.ericzz.java8.base.pojo.Banana;

import java.util.*;
import java.util.stream.Stream;

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
        //流支持map，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素
        //(使用映射一词),是因为它和转换类似，但其中的细微差别在于它是"创建一个新版本"，而不是去修改。
        //例如，下面的代码把方法Banana:getCountry 传给了map,来提取流中国家的名称
        List<String> bananas3 = bananas.stream()
                                        .map(Banana::getCountry)
                                        .collect(toList());
        //因为getColor方法返回一个String,所以map方法输出的流的类型就是Stream(String)
        //让我们看一个一个稍微不同的例子来巩固一下对map的理解。给定一个单词的列表，你想要返回另一个列表，
        //显示每个单词中有几个字母，怎么做呢?
        //你需要对列表中的每个元素应用一个函数，这听起来正好该用map方法去做！！！
        //应用的函数应该接受一个单词，并返回其长度。
        //例:
        List<String> words = Arrays.asList("java","c++","python","go");
        List<Integer> wordLengths = words.stream()
                                            .map(String::length)
                                            .collect(toList());

        //现在让我们回到提取国家名称的例子。如果你要找出每个国家的名称有多长，怎么做？
        //你可以像下面，再链接上一个map
        List<Integer> bananaCountryLength = bananas.stream()
                                                    .map(Banana::getCountry)
                                                    .map(String::length)
                                                    .collect(toList());

        //---------------------流的扁平化---------------------------------
        //场景：对于一张单词表，如何返回一张列表，列出里面各不相同的字符呢？
        //例如，给定单词列表["hello","world"],你想要返回列表['h','e','l','o',','w','r','d']
        List<String> words1 = Arrays.asList("hello","word");
        List<String[]> words2 = words1.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        //这么写存在一个问题，map函数返回的是一个String[]，场景需要返回的是String

        //尝试使用map和Arrays.stream()

        //Arrays.stream()方法接受一个数组，返回一个字符流
        String[] arraysOfWords = {"google","facebook"};
        Stream<String> streamOfWords = Arrays.stream(arraysOfWords);
        //把它应用到上面的场景中，看看发生了什么
        words1.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());
        //这么写依旧存在一个问题：Arrays::stream 把map(word -> word.split(""))返回一个数组流编程了一个独立的流。
        //不符合以上场景提出的需求

        // flatMap (扁平map)

        List<String> uniqueCharacters = words1.stream()
                .map(word -> word.split(""))    //将各个单词转换为由其字母构成的数组
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        //使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。
        //所有使用map(Arrays:stream)时生成的单个流都被合并起来，即扁平化为一个流。


    }
}
