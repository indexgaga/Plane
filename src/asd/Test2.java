package asd;

import java.util.Timer;
import java.util.TimerTask;

public class Test2 {
	public static void main(String[] args) {
		Timer time=new Timer();
		//指定在多久之后去执行
//		time.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				System.out.println("我被执行中。。。");
//			}
//		}, 3000);
		//1秒=1000毫秒
		//3000表示3000毫秒之后执行
		time.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("我被执行。。。");
				
			}
		}, 0, 1000);
		//0-是代表0毫秒之后执行
		//1000是表示1000毫秒之后再运行一次，每隔1秒执行一次
	}
}
