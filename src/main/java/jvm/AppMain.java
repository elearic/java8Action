package jvm;

/**
 * @Author huns
 * @Date 2022/9/17 1:30
 */
class OomObject{

	@Override
	public String toString() {
		return "OomObject{}";
	}
}
public class AppMain {
	public static void main(String[] args) throws InterruptedException {

		// 模拟堆内存溢出
//		List<OomObject> l1 =new LinkedList<>();
		while (true){
			Thread.sleep(100L);
			OomObject s =new OomObject();
			System.out.println(s.toString());
//			l1.add(s);
		}



	}
}
