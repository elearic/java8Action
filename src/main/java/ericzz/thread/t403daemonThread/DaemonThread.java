package ericzz.thread.t403daemonThread;

import ericzz.thread.SleepUtils;

/**
 * @Author：huns
 * @Date：2023/1/25
 */
public class DaemonThread {

	/**
	 * Daemon线程是一种支持型线程，因为它主要被用作程序后台调度及支持性工作。
	 * 这意味着，当一个Java虚拟机中不存在非Daemon线程的时候，Java虚拟机将会退出。
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
		thread.setDaemon(true);
		thread.start();
//		thread.join();
	}

	static class DaemonRunner implements Runnable {

		@Override
		public void run() {
			try {
				SleepUtils.second(10);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} finally {
				System.out.println("DaemonThread finally run.");
			}

		}
	}
}
