/**
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.ericzz.threadtest.waitingtime;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/** 
  * @author zz_huns  
 * @version Id: ConnectionDriver.java, v 0.1 2019/2/11 10:08 PM zz_huns Exp $$
 *
 * 由于java.sql.Connection是一个接口，最终的实现是由数据库驱动方来实现的，考虑到只是个示例，
 * 我们通过动态代理构造了一个Connection，该Connection的代理实现仅仅是在commit()方法调用
 * 时休闲100毫秒
 *
 */
public class ConnectionDriver {

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{Connection.class},
                new ConnectionHandler());
    }


    //静态内部类
    static class ConnectionHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                Thread.currentThread().sleep(100);
            }
            return null;
        }
    }


}
