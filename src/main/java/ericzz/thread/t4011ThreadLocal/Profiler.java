package ericzz.thread.t4011ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal  即线程变量，是一个以ThreadLocal对象为键。任意对象为值的存储结构。
 * 这个结构被附带在线程上，也就是说一个线程可以根据一个ThreadLocal对象查询到绑定在这个线程上的一个值。
 * @Author：huns
 * @Date：2023/2/4
 */
public class Profiler {

	private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return System.currentTimeMillis();
		}
	};

	public static final void begin() {
		TIME_THREADLOCAL.set(System.currentTimeMillis());
	}

	public static final long end() {
		return System.currentTimeMillis() - TIME_THREADLOCAL.get();
	}

	public static void main(String[] args) throws InterruptedException {
		Profiler.begin();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Cost: " + Profiler.end() + " mills");
	}

	/**
	 * Profiler 可以被服用在方法调用耗时统计的功能上，在方法的入口前执行begin()方法，在方法调用后执行end()方法。
	 * 好处是两个方法的调用不在一个方法或者类中，比如在AOP(面向切面编程)，可以在方法调用前的切入点执行begin()方法，
	 * 而在方法调用后的切入点执行end()方法，这样依旧可以获得方法的执行耗时。
	 */
}
