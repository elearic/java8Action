package com.ericzz.juc.threadtest;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: ConcurrentHashMapTest.java, v 0.1 2020/2/1 2:50 PM zz_huns Exp $$
 *
 */
public class ConcurrentHashMapTest extends SimpleDemo{

    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();


//        concurrentHashMap.put(null,null);
//        concurrentHashMap.put(null,null);
//        concurrentHashMap.put(null,null);
//        concurrentHashMap.put(null,null);


        HashMap hashMap = new HashMap();
        hashMap.put(null,1);
        hashMap.put(null,2);
        hashMap.put(null,3);
        hashMap.put(null,4);
        hashMap.put(null,5);



        System.out.println(hashMap.toString());
    }

    @Override
    public Integer getRandomInt(int i) {
        return super.getRandomInt(i);
    }
}
