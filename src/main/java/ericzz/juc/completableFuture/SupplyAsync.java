package ericzz.juc.completableFuture;

import java.util.concurrent.*;

/**
 * Java8 提供的异步处理工具类
 *  supplyAsync(Supplier) 带返回值的异步调用
 *  supplyAsync(Supplier,ExecutorPool) 带返回值且支持自定义线程池的异步调用
 *
 *
 * @Author huns
 * @Date 2022/7/23 21:41
 */
public class SupplyAsync {

	public static ExecutorService executorPool = new ThreadPoolExecutor(100, 100, 100L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return "hello world!";
		});
        String s = stringCompletableFuture.get();
        System.out.println(s);
		CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return "小鱼儿";
		}, executorPool);
		String now_return = stringCompletableFuture1.getNow("now return");
		System.out.println(now_return);
		executorPool.shutdown();

	}
}

