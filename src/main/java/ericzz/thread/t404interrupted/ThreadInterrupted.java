package ericzz.thread.t404interrupted;

import ericzz.thread.SleepUtils;
import lombok.SneakyThrows;

/**
 * @Author：huns
 * @Date：2023/1/25
 */
public class ThreadInterrupted {

	/**
	 * interrupt() 中断动作，对一个线程进行中断
	 * isInterrupted() 获取中断标识，判断线程是否中断过
	 * Thread.interrupted() 获取中断标识，并且对中断标识进行复位
	 */
	public static void main(String[] args) throws InterruptedException {
		// sleepThread 不停的尝试睡眠, 不捕获中断异常
		Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
		sleepThread.setDaemon(true);

		// sleep2Thread 不停的尝试睡眠, 捕获中断异常
		Thread sleep2Thread = new Thread(new Sleep2Runner(), "Sleep2Thread");
		sleep2Thread.setDaemon(true);

		// busyThread 不停的运行
		Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
		busyThread.setDaemon(true);

		sleepThread.start();
		sleep2Thread.start();
		busyThread.start();

		//休眠5秒，让sleepThread 和 busyThread 充分运行
		SleepUtils.second(5);

		sleepThread.interrupt();
		sleep2Thread.interrupt();
		busyThread.interrupt();

		System.out.println("interrupt after, SleepThread interrupted is " + sleepThread.isInterrupted());
		System.out.println("interrupt after, sleep2Thread interrupted is " + sleep2Thread.isInterrupted());
		System.out.println("interrupt after, BusyThread interrupted is " + busyThread.isInterrupted());

		// 方式sleepThread 和 busyThread 立刻退出
		SleepUtils.second(2);
	}

	// 线程被中断后抛出异常，在抛出InterruptedException之前，Java虚拟机会先将该线程的中断标识清除，然后抛出异常
	static class SleepRunner implements Runnable {

		@SneakyThrows
		@Override
		public void run() {
			while (true) {
				SleepUtils.second(10);
			}
		}
	}

	// 线程被中断后抛出异常，在抛出InterruptedException之前，Java虚拟机会先将该线程的中断标识清除，然后抛出异常
	static class Sleep2Runner implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					SleepUtils.second(10);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	static class BusyRunner implements Runnable {

		@Override
		public void run() {
			while (true) {
			}
		}
	}
}
