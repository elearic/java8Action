package ericzz.juc.demo;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: eric
 * @Date: 2021/7/28 11:12 下午
 */
public class ReadWriteLockDemo {

    private static Lock lock = new ReentrantLock();

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();

    private static Lock writeLock = readWriteLock.writeLock();

    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            // 模拟读操作
            lock.lock();
            // 读操作的耗时越多，读写锁的优势就越明显
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId() + "：begin sleep");
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override public void run() {
                try {
//                    demo.handleRead(readLock);
                    demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override public void run() {
                try {
                    demo.handleWrite(writeLock, new Random().nextInt());
//                    demo.handleWrite(lock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 18; i++) {
            Thread thread = new Thread(readRunnable);
            thread.start();
            // join 会阻塞当前主线程，当子线程thread没有执行完，主线程的for循环是不会继续往下执行的
            thread.join();
        }

//        for (int i = 18; i < 20; i++) {
//            Thread thread = new Thread(writeRunnable);
//            thread.start();
//            thread.join();
//        }
        System.out.println("耗时："+ (System.currentTimeMillis() - start));
    }
}
