package ericzz.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author huns
 * @Date 2022/7/24 15:11
 */
public class thenRunAsync {

	/**
	 * TODO thenRun 与 thenRunAsync  本质的区别
	 *
	 * @param args
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(100L);
				System.out.println("第一个任务执行结束!");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return "第一个任务执行结束!";
		});
		firstTask.thenRunAsync(() ->{
			System.out.println("第二个任务执行结束");
		});
		firstTask.get();
	}
}
