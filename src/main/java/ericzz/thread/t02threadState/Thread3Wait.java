package ericzz.thread.t02threadState;

/**
 * @Author：huns
 * @Date：2023/1/25
 */
public class Thread3Wait {
	public static void main(String[] args) throws InterruptedException {
		// 等待状态
		/**
		 * wait/notify  用来解决临界资源的问题，必须带锁，否则没有实际意义
		 */
		new Thread(new Waiting1(), "Waiting1").start();
		new Thread(new Waiting2(), "Waiting2").start();
	}

	static class Waiting1 implements Runnable {
		@Override
		public void run() {
			while (true) {
				synchronized (Thread3Wait.class) {
					try {
						Thread3Wait.class.wait();
						System.out.println("Waiting1 等待并且释放锁");
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

	static class Waiting2 implements Runnable {
		@Override
		public void run() {
			while (true) {
				synchronized (Thread3Wait.class) {
					System.out.println("Waiting2 抢到 Thread3Wait 锁, 并通知其他等待线程并唤醒");
					Thread3Wait.class.notify();
				}
			}
		}
	}
}
