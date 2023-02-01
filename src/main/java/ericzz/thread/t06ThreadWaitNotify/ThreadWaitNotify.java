package ericzz.thread.t06ThreadWaitNotify;

import ericzz.thread.SleepUtils;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 多线程间的等待通知
 *
 * @Author：huns
 * @Date：2023/1/29
 */
public class ThreadWaitNotify {

	/**
	 * 等待/通知的相关方法是任意Java对象都具备的，因为这些方法被定义在所有对象的超类 java.lang.Object
	 * <p>
	 * notify: 通知一个在对象上等待的线程，使其从wait()方法返回，而返回的前提是该线程获取到了对象的锁
	 * notifyAll(): 通知所有等待在该对象上的线程
	 * wait(): 调用该方法的线程进入WAITING状态，只有等待另外线程的通知或被中断才会返回，需要注意，调用wait（）方法后，会释放对象的锁
	 * wait(long): 超时等待一段时间，这里的参数时间是毫秒，也就是等待长达n毫秒，如果没有通知就超时返回
	 * wait(long,int): 对于超时时间更细粒度的控制，可以达到纳秒
	 * <p>
	 * 等待/通知，是指一个线程A 调用了对象O 的wait() 方法进入等待状态，而另一个线程B调用了对象O 的notify() 或者notifyAll() 方法，
	 * 线程A 收到通知后从对象O 的wait() 返回返回，进而执行后续操作。上述两个线程通过对象O来完成交互，而对象上的wait()和notify()/notifyAll（）
	 * 的关系就如同开关信号一样，用来完成等待方和通知方之间的交互工作。
	 */

	static boolean flag = true;
	static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		Thread waitThread = new Thread(new Wait(), "WaitThread");
		waitThread.start();
		SleepUtils.second(1);
		Thread notifyThread = new Thread(new Notify(), "NotifyThread");
		notifyThread.start();
	}

	static class Wait implements Runnable {

		@Override
		public void run() {
			// 加锁, 拥有lock的monitor
			synchronized (lock) {
				while (flag) {
					try {
						System.out.println(Thread.currentThread() + "flag is true. wait @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
						lock.wait();
					} catch (InterruptedException e) {

					}
				}

				//条件满足时，完成工作
				System.out.println(Thread.currentThread() + "flag is false. wait @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
	}

	static class Notify implements Runnable {

		@SneakyThrows
		@Override
		public void run() {
			//加锁, 拥有lock的Monitor
			synchronized (lock) {
				//获取lock的锁，然后进行通知，通知时不会释放lock的锁
				//直到当前线程释放了lock后，WaitThread才能从Wait方法中返回
				System.out.println(Thread.currentThread() + "hold lock. notify @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				lock.notifyAll();
				flag = false;
				SleepUtils.second(5);
			}

			//再次加锁
			synchronized (lock) {
				System.out.println(Thread.currentThread() + "hold lock again.sleep @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
	}
	/**
	 * 结果输出
	 * Thread[WaitThread,5,main]flag is true. wait @22:46:52
	 * Thread[NotifyThread,5,main]hold lock. notify @22:46:53
	 * Thread[NotifyThread,5,main]hold lock again.sleep @22:46:58
	 * Thread[WaitThread,5,main]flag is false. wait @22:46:58
	 *
	 * 第3行和第4行的输出顺序可能会互换，此例主要说明了调用wait()、notify()、以及notifyAll() 时需要注意的细节
	 *
	 * 1> 使用wait()、notify()、notifyAll()时需要先对调用对象加锁 -- 否则会抛出IllegalMonitorStateException 异常
	 * 2> 调用wait()方法后，线程状态由RUNNING变为WAITING,并将当前线程放置到对象的等待队列
	 * 3> notify() 或者 notifyAll()方法调用后，等待线程才有机会从wait()返回。
	 * 4> notify() 方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll()方法则是将等待队列中所有线程全部移到
	 *    同步队列，被移动的线程状态由WAITING变为BLOCKED。
	 * 5> 从wait()方法返回的前提是获得了调用对象的锁
	 *
	 * 以上可以看出，等待/通知机制依托于同步机制，其目的就是确保等待线程从wait()方法返回时能够感知到通知线程对变量做出的修改。
	 *
	 */
}
