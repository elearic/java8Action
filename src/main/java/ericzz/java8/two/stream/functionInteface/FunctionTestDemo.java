package ericzz.java8.two.stream.functionInteface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Function 函数描述符 T -> R
 */
public class FunctionTestDemo {
    public static void main(String[] args) {
        //java.util.function.Function<T,R> 接口定义了一个apply()方法，接受一个泛型T的对象，返回一个R类型的对象。

        //示例1：接收一个String，返回一个Integer
        List<Integer> list1 = map(Arrays.asList("lambdas","in","action"), (String s) -> s.length());

        //示例2：接收一个String,返回一个Boolean
        List<Boolean> list2 = map(Arrays.asList("lambdas","in","action"), (String s) -> s.isEmpty());

        //示例3：接收一个String,返回一个String
        List<String>  list3 = map(Arrays.asList("lambdas","in","action"), (String s) -> s.intern());

    }

    public static <T, R> List<R> map(List<T> list,
                                     Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }
}
