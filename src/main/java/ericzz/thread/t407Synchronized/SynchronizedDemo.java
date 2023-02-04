package ericzz.thread.t407Synchronized;

/**
 * 关键字Synchronized 可以修饰方法或者以同步块的形式来进行使用，它主要确保多个线程在同一时刻，只能有一个线程处于方法或者同步
 * 块中，它保证了线程对变量访问的可见性和排他性
 *
 * @Author：huns
 * @Date：2023/1/27
 */
public class SynchronizedDemo {

	public static void main(String[] args) {

		synchronized (SynchronizedDemo.class){
		}
		m();
	}

	public static synchronized void m(){

	}

	/**
	 * 对于同步代码块的实现使用了monitorenter 和 monitorexit 指令，而同步方法则是依靠方法修饰符上的ACC_SYNCHRONIZED 来完成的
	 * 无论采用哪种方式，其本质是对一个对象的监视器(monitor)进行获取，而这个获取的过程是排他的，也就是一同一时刻只能有一个线程获取到由
	 * Synchronized 所保护对象的监视器。
	 *
	 * 任意一个对象都拥有自己的监视器，当这个对象由同步块或者这个对象的同步方法调用时，执行方法的线程必须先获取到该对象的监视器才能进入同步块
	 * 或者同步方法，而没有获取到监视器(执行该方法)的线程会被阻塞在同步块和同步方法的入口处，进入BLOCKED状态
	 */

}
