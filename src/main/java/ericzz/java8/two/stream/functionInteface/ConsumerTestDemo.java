package ericzz.java8.two.stream.functionInteface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer 函数描述符 T -> void
 */
public class ConsumerTestDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        //java.util.function.Consumer 函数式接口的唯一抽象抽象方法accept(T t) 接收一个泛型T的对象，返回一个void值
        //对于方法入参为Consumer<T> 时，可直接传入Comsumer.accept的lambda表达式
        ConsumerTestDemo.forEach(Arrays.asList(1,2,3,4,5),(Integer integer) -> System.out.printf(integer.toString()));
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T i: list){
            c.accept(i);
        }
    }

}
