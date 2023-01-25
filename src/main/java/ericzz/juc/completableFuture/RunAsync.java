package ericzz.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 *
 * @Author huns
 * @Date 2022/7/24 14:23
 */
public class RunAsync {

	/**
	 * get 和 join 都会阻塞，区别是get抛出的是受检异常，join抛出的是非受检异常
	 * @param args
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println("线程执行了");
		});
		voidCompletableFuture.join();
	}
}
