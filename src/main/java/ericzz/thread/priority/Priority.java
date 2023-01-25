package ericzz.thread.priority;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程优先级
 * 线程优先级最大 10  最小1 平均 5
 * 实验结果：高优先级的计数和低优先级的计数结果无明显区别，线程优先级不能作为程序正确性的依赖
 * @Author：huns
 * @Date：2023/1/25
 */
public class Priority {

	private static volatile boolean notStart = true;

	private static volatile boolean notEnd = true;

	public static void main(String[] args) throws InterruptedException {
		List<Job> jobs = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
			Job job = new Job(priority);
			jobs.add(job);
			Thread thread = new Thread(job, "Thread:" + i);
			thread.setPriority(priority);
			thread.start();
		}
		notStart = false;
		TimeUnit.SECONDS.sleep(100);
		notEnd = false;
		for (Job job : jobs) {
			System.out.println("Job Priority：" + job.priority + ", count : " + job.jobCount);
		}
	}

	static class Job implements Runnable {
		private final int priority;
		private long jobCount;

		public Job(int priority) {
			this.priority = priority;
		}

		@Override
		public void run() {
			while (notStart) {
				Thread.yield();
			}
			while (notEnd) {
				Thread.yield();
				jobCount++;
			}
		}
	}
}
