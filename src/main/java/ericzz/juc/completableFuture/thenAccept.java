package ericzz.juc.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @Author huns
 * @Date 2022/7/24 15:43
 */
public class thenAccept {

    /**
     * thenAccept 回调任务方法
     * 第一个任务执行完成后，执行第二个回调方法任务，会将第一个任务的执行结果作为入参，传入第二个回调任务方法中，回调方法是无返回值的
     * @param args
     */
    public static void main(String[] args) {
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务执行结束!");
            return "第一个任务执行结束!";
        });
        firstTask.thenAccept((firstTaskResult) -> {
            System.out.println("第二个任务执行了!");
            System.out.println("第一个任务的响应结果:" + firstTaskResult);
        });
    }
}
