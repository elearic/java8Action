package com.ericzz.functionInteface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate 函数描述符 T -> boolean
 */
public class PredicateTestDemo {

    public static void main(String[] args){

        //java.utils.function.Predicate 函数式接口唯一的抽象方法 接收一个泛型T对象，返回一个boolean类型的值
        //定义Predicate 时，实现可直接内联lambda
        Predicate<String> predicate = (String str) -> !str.isEmpty();

        List list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        //方式一：filter 函数入参可直接接收一个函数式接口的实现
        PredicateTestDemo.filter(list,predicate);

        //方式二：filter 函数入参可直接接收一个函数式接口的lambda表达式
        PredicateTestDemo.filter(list,(String str) -> !str.isEmpty());

    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T s: list){
            if(p.test(s)){
                results.add(s);
            }
        }
        return results;
    }
}
