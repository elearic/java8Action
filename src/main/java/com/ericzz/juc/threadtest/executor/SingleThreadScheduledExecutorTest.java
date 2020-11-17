package com.ericzz.juc.threadtest.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.*;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: SingleThreadScheduledExecutorTest.java, v 0.1 2019/11/30 11:37 PM zz_huns Exp $$
 *
 */
public class SingleThreadScheduledExecutorTest {


    public static void main(String[] args) {


        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        Executors.newSingleThreadScheduledExecutor();

        Map map = new ConcurrentHashMap<>();

        /**
         * scheduledThreadPoolExecutor.execute方法 无返回
         */
        scheduledThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行 scheduledThreadPoolExecutor.execute方法");
            }
        });


        /**
         * scheduledThreadPoolExecutor.submit(new Runnable(){})
         * 此种方式执行线程，返回Future<?>.get 返回的是一个Null
         */
//        Future<?> future = scheduledThreadPoolExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("执行 scheduledThreadPoolExecutor.submit(new Runnable() 方法");
//            }
//        });
//
//        try {
//            Object o = future.get();
////            System.out.println("o --"+o.toString());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


        /**
         * 固定周期定时任务：
         * 周期：指一个任务的开始到下一个任务开始的时间
         * period 为自定义的饿周期间隔
         * 若任务的执行时间 < period ，则任务结束后，需等待，直到达到period间隔时间才会执行下一个任务
         * 若任务的执行时间 > preiod，则直接执行下一个任务
         *
         * 注：任务的执行周期是根据任务时间的执行耗时计算的，没个任务执行耗时基本是固定的。
         *     在此，就会出现上面两种情况。所以
         *     scheduleAtFixedRate 是一种以任务具体的执行耗时为周期的定时任务
         */
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            int period = 1;

            @Override
            public void run() {
                    try {
                        System.out.println("---------------第 " + period + " 周期-------------");
                        System.out.println("begin = " + System.currentTimeMillis() / 1000);
                        //任务执行时间
                        Thread.sleep(2000);
                        System.out.println("end =   " + System.currentTimeMillis() / 1000);
                        period++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }, 50,2000, TimeUnit.MILLISECONDS);


        /**
         * 固定间隔定时任务
         * 此任务不受任务的执行耗时(周期)影响限制
         * 若任务的执行耗时(周期) < delay || 若任务的执行耗时(周期)  > delay,
         * 则任务结束后，需等待，直到达到period间隔时间才会执行下一个任务
         *
         */
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            int period = 1;

            @Override
            public void run() {
                try {
                    System.out.println("---------------第 " + period + " 周期-------------");
                    System.out.println("begin = " + System.currentTimeMillis() / 1000);
                    //任务执行时间
                    Thread.sleep(2000);
                    System.out.println("end =   " + System.currentTimeMillis() / 1000);
                    period++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 50,2000, TimeUnit.MILLISECONDS);



    }
}


