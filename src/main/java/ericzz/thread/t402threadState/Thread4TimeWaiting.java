package ericzz.thread.t402threadState;

/**
 * @Author：huns
 * @Date：2023/1/25
 */
public class Thread4TimeWaiting {

	public static void main(String[] args) {
		// 超时等待状态
		new Thread(new Waiting(), "Waiting").start();
	}

	static class Waiting implements Runnable {
		@Override
		public void run() {
			while (true) {
				synchronized (Thread4TimeWaiting.class) {
					try {
						Thread4TimeWaiting.class.wait(1000);
						System.out.println("Waiting 等待时间到期，自动唤醒线程");
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}
}
