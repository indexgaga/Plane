package shoot;
 
import java.awt.image.BufferedImage;

import myshoot.Bullet;
 
/**
 * 封装所有飞行物的公共属性和功能的父类
 */
public abstract class Flyer {
	public int x; //飞行物左上角x坐标
	public int y; //飞行物左上角y坐标
	public int height; //飞行物的高度
	public int width; //飞行物的宽度
	public BufferedImage image; //飞行物的图片
	
	/**
	 * 要求所有飞行物必须都能移动
	 * 但移动的方式由子类自己实现
	 */
	public abstract void step();
	
	/**
	 * 检查越界的方法
	 * @return 是否越界
	 */
	public abstract boolean outOfBounds();
	
	/**
	 * 专门检测两个矩形飞行物是否碰撞的工具方法
	 * 和具体对象无关，所以定义为静态方法
	 * @param bullets 飞行对象1
	 * @param f2 飞行对象2
	 * @return 是否碰撞
	 */
	public static boolean boom(Flyer bullets,Flyer f2){
		//step1: 求出两个矩形的中心点
		int f1x = bullets.x + bullets.width/2;
		int f1y = bullets.y + bullets.height/2;
		int f2x = f2.x + f2.width/2;
		int f2y = f2.y + f2.height/2;
		//step2: 横向和纵向碰撞检测
		boolean H = Math.abs(f1x - f2x) < (bullets.width + f2.width)/2;
		boolean V = Math.abs(f1y -f2y) < (bullets.height + f2.height)/2;
		//step3: 必须两个方向同时碰撞
		return H&V;
	}
	
}
