package ericzz.juc.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @Author huns
 * @Date 2022/7/24 16:09
 */
public class thenAcceptAsync {

    /**
     * thenAcceptAsync
     * 和 thenAccept  区别 TODO
     *
     * @param args
     */
    public static void main(String[] args) {
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行结束!");
            return "第一个任务执行结束!";
        });

        firstTask.thenAcceptAsync((firstTaskResult) -> {
            System.out.println("第二个任务执行了!");
            System.out.println("第一个任务执行的结果:" + firstTaskResult);
        });

    }
}
