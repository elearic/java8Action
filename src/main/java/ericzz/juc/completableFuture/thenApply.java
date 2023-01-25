package ericzz.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author huns
 * @Date 2022/7/24 16:13
 */
public class thenApply {

    /**
     * thenApply 方法表示
     *
     * 第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果，作为入参，
     * 传递到回调方法中，并且回调方法是有返回值的
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行结束!");
            System.out.println("----------------");
            return "第一个任务执行结束!";
        });
        CompletableFuture<String> secondTask = firstTask.thenApply((firstTaskResult) -> {
            System.out.println("第二个任务执行结束!");
            System.out.println("第一个任务的执行结果:" + firstTaskResult);
            return "第二个任务执行结束!";
        });
        System.out.println(firstTask.get());
        System.out.println(secondTask.get());
    }


}
