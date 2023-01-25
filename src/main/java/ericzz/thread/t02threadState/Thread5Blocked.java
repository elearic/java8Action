package ericzz.thread.t02threadState;

import java.util.concurrent.TimeUnit;

/**
 * @Author：huns
 * @Date：2023/1/25
 */
public class Thread5Blocked {

	public static void main(String[] args) throws InterruptedException {
		// 阻塞状态, 使用两个Blocked线程，一个获取到锁，另外一个被阻塞
		new Thread(new Blocked(), "BlockedThread-1").start();
		new Thread(new Blocked(), "BlockedThread-2").start();
	}

	static class Blocked implements Runnable {

		@Override
		public void run() {
			synchronized (Blocked.class) {
				while (true) {
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}
}
