package ericzz.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author huns
 * @Date 2022/7/24 16:32
 */
public class exceptionally {

    /**
     * exceptionally 方法表示，某个任务执行异常时，执行的回调方法并且有抛出异常作为参数，传递到回调方法。
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Object> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行失败了!");
            throw new RuntimeException();
        });
        CompletableFuture<Object> exceptionally = firstTask.exceptionally((e) -> {
            e.printStackTrace();
            return "程序异常了!";
        });
        System.out.println(exceptionally.get());
    }
}
