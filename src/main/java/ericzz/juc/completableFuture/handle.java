package ericzz.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author huns
 * @Date 2022/7/24 20:01
 */
public class handle {

    /**
     * handle方法表示
     * 某个任务执行完成后，执行回调方法，并且是有返回值的。
     * 并且handle方法返回的CompletableFuture的result是回调方法执行的结果
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread.currentThread().interrupt();
        CompletableFuture<String> firstFutureTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行结束!");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "第一个任务执行结果!";
        });
        CompletableFuture<Object> resultFutureTask = firstFutureTask.handle((a, e) -> {
            System.out.println("上个任务执行结束了，还把  " + a + "  传过来了!");
            System.out.println("第二个任务执行结束了");
            return null;
        });
        System.out.println(resultFutureTask.get());
    }
}
