package com.ericzz.juc.threadtest.executor;

import java.util.concurrent.*;

/**
 *  java 并发编程的艺术
 *
 *  executor 简介
 *  在 HotSpot VM的线程模型中，Java线程(Java.lang.Thread)被一对一映射为本地操作系统的线程。Java线程启动时
 *  会创建爱你一个本地操作的操作线程；当该java线程终止时候，这个操作系统线程也会被回收。操作系统会调度所有线程
 *  并将它们分配给可用的cpu
 *
 *
 *
 *
 *  Executor框架的三大组成部分
 *  1.任务 :包括被执行任务需要实现的接口 ：Runnable 接口或者 Callable 接口
 *  2.任务的执行：包括任务执行机制的核心接口 Executor,以及继承自Executor的ExecutorService接口。
 *    Executor框架有两个关键类实现了ExecutorService接口
 *      ThreadPoolExecutor
 *      ScheduledThreadPoolExecutor
 *  3.异步计算的结果： 包括接口Future 和实现Future接口的FutureTaks类
 *  
 *  * @author zz_huns  
 *  @version Id: ExecutorTest.java, v 0.1 2019/11/30 10:40 PM zz_huns Exp $$
 *
 */
public class ExecutorTest {

    public static void main(String[] args) {

        /**
         * 1 FixedThreadPool
         *  适用于为了满足资管管理的需求，创建使用固定线程数的FixedThreadPool
         *
         */
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);

        /**
         * 2.SingleThreadExecutor
         * 适用于需要保证顺序执行地执行各个任务，并且在任意时间点，不会有多个线程是活动的应用场景
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        /**
         * 3.CachedThreadPool
         * 是大小无界的线程池，适用于执行很多的短期异步任务的小程序，或者是负载比较轻的服务器
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


        /**
         * 4.ScheduledThreadPool
         *  适用于需要多个后台线程执行周期任务，同时为了满足资源管理的需求而需要限制后台线程的数量的应用场景
         */

        ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(1);

        /**
         * 5.SingleThreadScheduledExecutor
         *  适用于需要单个后台线程执行周期任务，同事需要保证顺序地执行各个任务的应用场景
         */
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    }


}
