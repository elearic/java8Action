/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.part.two.stream.exercise;

/** 
 * 领域BaseDO: 交易员类
  * @author zz_huns  
 * @version Id: Trader.java, v 0.1 2019/2/10 1:40 AM zz_huns Exp $$
  */
public class Trader {

    /**
     * 交易员姓名
     */
    private final String name;

    /**
     * 交易员所在城市名称
     */
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
