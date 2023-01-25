package ericzz.java8.map;

import ericzz.java8.base.pojo.Apple;
import com.google.common.base.Objects;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  * @author zz_huns  
 *  @version Id: MapDemo.java, v 0.1 2019/12/8 12:29 AM zz_huns Exp $$
 */
public class MapDemo {
    public static void main1(String[] args) {
        Map map = new HashMap<>();
//        map.put("k1","v1");
//        map.put("k1","vv1");
//        System.out.println("==="+map.toString());

        MyMap m1 = new MyMap();
        m1.setAge(1);
        m1.setName("k");

        MyMap m2 = new MyMap();
        m2.setAge(1);
        m2.setName("k");

        map.put(m1, 1);
        map.put(m2, 2);
        System.out.println("===" + map.toString());

    }

    /**
     * java 8 map 新特性
     * Key 实现 Comparable 接口  提升性能验证
     *
     * @param args
     */
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        Map<Apple, String> map = new HashMap();
        for (int i = 0; i < 500000; i++) {
            Apple apple = new Apple();
            apple.setColor(i);
            map.put(apple, apple.getColor().toString());
        }
        System.out.println(System.currentTimeMillis() - start);

        System.out.println(map.size());
    }

    @Data
    static class MyMap {

        private String name;

        private Integer age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyMap)) return false;
            if (!super.equals(o)) return false;
            MyMap myMap = (MyMap) o;
            return Objects.equal(name, myMap.name) && Objects.equal(age, myMap.age);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(super.hashCode(), name, age);
        }
    }
}
