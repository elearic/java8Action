package ericzz.juc.threadtest;

/**
 * @Author huns
 * @Date 2022/10/18 21:32
 */
public class ThreadJoinTest {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				for (int i = 0; i < 5; i++) {
					System.out.println("this thread t1!");
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

				for (int i = 0; i < 5; i++) {
					System.out.println("this thread t2!");
				}
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();
		System.out.println("hello world!");
	}
}
