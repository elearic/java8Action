package ericzz.thread.t405stopThread;

import ericzz.thread.SleepUtils;

/**
 * 安全的终止线程
 *
 * @Author：huns
 * @Date：2023/1/26
 */
public class ThreadStop {

	public static void main(String[] args) throws InterruptedException {
		Runner one = new Runner();
		Thread countThread = new Thread(one, "CountThread");
		countThread.start();

		//睡眠1秒，main线程对countThread进行中断，使countThread能够感知 中断而结束
		SleepUtils.second(1);
		countThread.interrupt();

		Runner two = new Runner();
		countThread = new Thread(two, "CountThread");
		countThread.start();
		//睡眠1秒, main线程堆Runner two 进行取消，使CountThread能够感知on为false 而结束
		SleepUtils.second(1);
		two.cancel();
	}


	private static class Runner implements Runnable {
		private long i;

		private volatile boolean on = true;

		@Override
		public void run() {
			while (on && !Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println("Count i= " + i);
		}

		private void cancel() {
			on = false;
		}
	}
}
