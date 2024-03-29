/**
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package ericzz.java8.two.stream.exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 *  练习
 *  * @author zz_huns  
 *  @version Id: TradeExercise.java, v 0.1 2019/2/10 1:47 AM zz_huns Exp $$
 */
public class TradeExercise {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        /**
         * 问题：
         * 1.找出2011年发生的所有交易，并交易额排序(从低排序)
         * 2.交易员都在哪些不同的城市工作过
         * 3.查找所有来自于剑桥的交易员，并按姓名排序
         * 4.返回所有交易员的姓名字符串，按字母顺序排序
         * 5.有没有交易员是在米兰工作的
         * 6.打印生活在剑桥的交易员的所有交易额
         * 7.所有交易中，最高的交易额是多少
         * 8.找到交易额最小的交易
         */


        /**
         * 1.找出2011年发生的所有交易，并交易额排序(从低排序)
         */
        List<Transaction> list1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());


        /**
         * 2.交易员都在哪些不同的城市工作过
         */
        List<String> list2 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toList());

        //官方答案：
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());

        //对比：少了一个map，减少了流的性能消耗

        /**
         * 3.查找所有来自于剑桥的交易员，并按姓名排序
         */
        List<Trader> list3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());

        /**
         * 4.返回所有交易员的姓名字符串，按字母顺序排序
         */
        List<String> list4 = transactions.stream()
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .distinct()
                .collect(toList());
        //写的有问题，理解题意出错，需要返回所有交易员的姓名字符串
        //官方答案：
        String nameStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + b);


        /**
         * 5.有没有交易员是在米兰工作的
         */
        boolean isWordMilan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));


        /**
         * 6.打印生活在剑桥的交易员的所有交易额
         */
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::print);


        /**
         * 7.所有交易中，最高的交易额是多少
         */
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        /**
         * 8.找到交易额最小的交易
         */
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

        //官方答案一：
        Optional<Transaction> min1 = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        //官方答案二：
        Optional<Transaction> min2 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
    }
}
