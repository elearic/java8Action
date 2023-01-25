package ericzz.thread.t02threadState;

/**
 * @Author：huns
 * @Date：2023/1/25
 */
public class Thread1New {

	public static void main(String[] args) {
		//初始状态 创造一个新的线程
		Thread threadNew = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("创造一个新的线程");
			}
		});
	}
}
