package ericzz.thread.t406Volatile;

import ericzz.thread.SleepUtils;

/**
 * Volatile
 * <p>
 * java支持多个线程同时访问一个对象或者对象的成员变量，由于每个线程可以拥有这个变量的拷贝(虽然对象以及成员变量分配的内存是共享内存中，但是
 * 每个执行的线程还是可以拥有一份拷贝，这样做的目的是加速程序的执行，这是现代多核处理器的一个显著特性)，所以程序在执行过程中，一个线程
 * 看到的变量并不一定是最新的
 * <p>
 * 关键字 volatile可以用来修饰字段(成员变量),就是告知程序任何对任何该变量的访问均需要从 共享内存中获取，而对它的改变必须同步刷新回共享内存，
 * 它能保证所有线程堆变量访问的可见性。
 *
 * @Author：huns
 * @Date：2023/1/27
 */
public class ThreadVolatile {

	// volatile 只能保证线程间的可见性，不能保证安全性
	// 缺点：它会降低程序执行的效率
	private static volatile int index = 0;

	public static void main(String[] args) throws InterruptedException {


		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < 10000; i++) {
					index++;
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < 10000; i++) {
					index++;
				}
			}
		});

		t1.start();
		t2.start();

		// 睡眠10秒，让 t1,t2线程充分运行
		SleepUtils.second(3);

		System.out.println("index....:" + index);
	}
}
