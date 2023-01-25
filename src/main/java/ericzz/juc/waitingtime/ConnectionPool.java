/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package ericzz.juc.waitingtime;

/** 
 * java并发编程的艺术 4.4.2
  * @author zz_huns  
 * @version Id: ConnectionPool.java, v 0.1 2019/2/11 10:00 PM zz_huns Exp $$
 *
 * 使用java 线程等待超时wait(long)模式来构造一个简单的数据库连接池，在示例中模拟从连接池中
 * 获取、使用和释放连接的过程，而客户端获取连接的过程被设定为等待超时的模式，也就是在1000毫秒
 * 内如果无法获取到可用连接，将会返回给客户端一个null。设定连接池的大小为10个，然后通过调节
 * 客户端的线程的线程数来模拟无法获取连接的场景
  */

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 首先看一下连接池的定义。它通过构造函数初始化连接的最大上限，通过一个双向队列来维护连接，调用方
 * 需要先调用fetchConnection(long) 方法来指定在多少毫秒内超时获取连接，当连接使用完成后，需要
 * 调用releaseConnection(Connection) 方法将连接池放回线程池。
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    /**
     * 初始化连接池
     * @param initialSize
     */
    public ConnectionPool(int initialSize) {
        if (initialSize > 0){
            for (int i=0;i<initialSize;i++){
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 释放数据库连接
     * @param connection
     */
    public void releaseConnection(Connection connection){
        if (null != connection){
            synchronized (pool){
                //连接释放后需要进行通知，这样其他消费者能够感知到连接池中已经癸亥了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取数据库连接
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool){
            //完全超时
//            if (mills <= 0){
//                while (pool.isEmpty()){
//                    pool.wait();
//                }
//                return pool.removeFirst();
//            }else {
//                long future = System.currentTimeMillis() + mills;
//                long remaining = mills;
//                while (pool.isEmpty() && remaining > 0){
//                    pool.wait(remaining);
//                    remaining = future - System.currentTimeMillis();
//                }
//                Connection connection = null;
//                if (!pool.isEmpty()){
//                    connection = pool.removeFirst();
//                }
//                return connection;
//            }

            long future = System.currentTimeMillis() + mills;
            long remaining = mills;
            while (pool.isEmpty() && remaining > 0){
                pool.wait(remaining);
                remaining = future - System.currentTimeMillis();
            }
            Connection connection = null;
            if (!pool.isEmpty()){
                connection = pool.removeFirst();
            }
            return connection;
        }
    }
}
