package myshoot;

public class Bullet extends Flyer{
	private int speed = 7;
	
	/**
	 * 子弹类的带参构造方法
	 * 因为子弹对象创造的位置要根据英雄机的位置决定
	 * 所以子弹对名的x和y要从外界传入
	 * @param x 英雄机指定子弹创造位置的x坐标
	 * @param y 英雄机指定子弹创造位置的y坐标
	 */
	public Bullet(int x,int y) {
		image = ShootGame.bullet;
		int width = image.getWidth();
		int height = image.getHeight();
		this.x = x;
		this.y = y;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		y -=speed;
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return (y+height)<0;
	}
}
