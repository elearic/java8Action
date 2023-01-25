package ericzz.thread.t02threadState;

/**
 * @Author：huns
 * @Date：2023/1/25
 */
public class Thread2Running {
	public static void main(String[] args) {
		//运行状态 创造一个新的线程并运行
		Thread threadNew = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("创造一个新的线程");
			}
		});
	}
}
