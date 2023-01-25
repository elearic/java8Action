package ericzz.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author huns
 * @Date 2022/7/24 14:58
 */
public class thenRun {
	/**
	 * TODO thenRun 与 thenRunAsync  本质的区别
	 *
	 * @param args
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<Object> firstTask = CompletableFuture.supplyAsync(() -> {
			System.out.println("第一个任务执行结束!");
			return "第一个任务的返回值";
		});
		firstTask.thenRun(()->{
			System.out.println("第二个任务执行结果!");
		});
		System.out.println(firstTask.get());
		System.out.println("------------------------------------------------");
		CompletableFuture<Void> firstTask1 = CompletableFuture.runAsync(() -> {
			System.out.println("第一个任务执行结束!");
		});
		firstTask1.thenRun(()->{
			System.out.println("第二个任务执行结果!");
		});
		firstTask1.join();
		System.out.println("=================================================");
	}
}
