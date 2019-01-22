package myshoot;

import java.awt.image.BufferedImage;


public abstract class Flyer {
	public int x;
	public int y;
	public int width;
	public int height;
	public BufferedImage image;
	/**
	 * 飞行物移动
	 */
	public abstract void step(); 
	
	/**
	 *检查是否越界方法
	 *@return 是否越界
	 */
	public abstract boolean outOfBounds();
	
	/**
	 * 专门检测两个矩形飞行物是否碰撞的工具方法
	 * 和具体对象无关，所以定义为静态方法
	 * @param f1 飞行对象1
	 * @param f2 飞行对象2
	 * @return 是否碰撞
	 */
	public static boolean boom(Flyer f1,Flyer f2){
		//step1: 求出两个矩形的中心点
		int f1x = f1.x + f1.width/2;
		int f1y = f1.y + f1.height/2;
		int f2x = f2.x + f2.width/2;
		int f2y = f2.y + f2.height/2;
		//step2: 横向和纵向碰撞检测
		boolean H = Math.abs(f1x - f2x) < (f1.width + f2.width)/2;
		boolean V = Math.abs(f1y -f2y) < (f1.height + f2.height)/2;
		//step3: 必须两个方向同时碰撞
		return H&V;
	}
}
