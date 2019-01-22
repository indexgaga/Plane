package myshoot;

import java.util.Random;
/**
 *秋田犬 
 */
public class Airplane extends Flyer{
	//id  0---二狗   2--普通机   3--鸽子
	public int id = 0;
	public int life = 2;
	public int speed = 3;
	public int score = 10;
	public Airplane(){
		life = 15;
		speed = 1;
		id = 0;
		score = 100;
		image = ShootGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();
		x = rand.nextInt(ShootGame.FRAME_WIDTH-width);
		y = -height;
	}
	
	public Airplane(int i) {
		if(i==2) {
			id = 2;
			life = 2;
			speed = 4;
			image = ShootGame.airplane2;
			width = image.getWidth();
			height = image.getHeight();
			Random rand = new Random();
			x = rand.nextInt(ShootGame.FRAME_WIDTH-width);
			y = -height;
		}else if(i==3) {
			id = 3;
			life = 400;
			speed = 0;
			image = ShootGame.nullgugu;
			width = image.getWidth();
			height = image.getHeight();
			Random rand = new Random();
			x = 70;
			y = 0;
		}
	}
	
	
	public int getLife() {
		return life;
	}



	public void setLife(int life) {
		this.life = life;
	}



	@Override
	public void step() {
		// TODO Auto-generated method stub
		y +=speed;
	}

	@Override
	public boolean outOfBounds() {
		return y-image.getHeight()>ShootGame.FRAME_HEIGHT;
	}
	
}
