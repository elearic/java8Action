package ericzz.juc.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 多线程信号量demo
 *
 * 信号量为多线程协作提供了更为强大的控制方法。从广义上说，信号量是对锁的扩展。
 * 无论是内部锁synchronized还是冲入锁ReentrantLock，一次都只允许一个线程访问一个资源，而信号量却可以指定多个线程，
 * 同时访问某一个资源。
 *
 * @Author: eric
 * @Date: 2021/7/28 10:43 下午
 */
public class SemaphoreDemo implements Runnable {

    final Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        final SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            threadPool.submit(demo);
        }
    }

    @Override public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.printf(Thread.currentThread().getId() + ":done!" + "\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

}
