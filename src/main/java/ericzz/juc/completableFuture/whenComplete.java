package ericzz.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author huns
 * @Date 2022/7/24 16:50
 */
public class whenComplete {

    /**
     * whenComplete 表示
     * 某个任务执行完成后，执行的回调方法，方法无返回值。
     * 并且whenComplete方法返回的CompletableFuture的result是上个任务的结果
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行结束! 当前线程名称:" + Thread.currentThread().getName());
            System.out.println("----------------");
            return "第一个任务执行结束!";
        });
        CompletableFuture<String> future = firstTask.whenComplete((a, b) -> {
            System.out.println("第二个任务执行结束! 当前线程名称:" + Thread.currentThread().getName());
            System.out.println("第一个任务的执行结果:" + a);
            System.out.println("-----------------");
        });
        System.out.println(firstTask.get());
    }
}
