/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.java8.two.stream.exercise;

/** 
 * 领域BaseDO: 交易类
  * @author zz_huns  
 * @version Id: Transaction.java, v 0.1 2019/2/10 1:43 AM zz_huns Exp $$
  */
public class Transaction {

    /**
     * 交易员
     */
    private final Trader trader;

    /**
     * 交易员工作年份
     */
    private final int year;

    /**
     * 交易额
     */
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
