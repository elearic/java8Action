package ericzz.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author：huns
 * @Date：2023/1/25
 */
public class SleepUtils {

	public static void second(long seconds) throws InterruptedException {
		TimeUnit.SECONDS.sleep(seconds);
	}
}
