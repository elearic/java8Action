package ericzz.thread.t4010ThreadJoin;

/**
 * @Author：huns
 * @Date：2023/2/4
 */
public class TheadJoin {

	public static void main(String[] args) throws InterruptedException {
		Thread previous = Thread.currentThread();
		for (int i = 0; i < 10; i++) {
			// 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
			Thread thread = new Thread(new Domino(previous), String.valueOf("thread-000"+i));
			thread.start();
			// thread.join 会导致死锁。
//			thread.join();
			previous = thread;
		}
	}


	static class Domino implements Runnable {
		private final Thread thread;

		public Domino(Thread thread) {
			this.thread = thread;

		}

		@Override
		public void run() {
			try {
				thread.join();

				//thread线程执行结束后，会调用线程自身的notifyAll()方法，会通知所有等待在该线程对象上的线程
				//join()方法的逻辑结构与 t06ThreadWaitNotify描述的机构 等待/通知经典范式一致，即加锁。循环、处理逻辑 3个步骤
			} catch (InterruptedException e) {
			}
			System.out.println(Thread.currentThread().getName() + " terminate.");
		}
	}
}
