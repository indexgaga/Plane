package myshoot;



public class Hero extends Flyer{

	public int fire=1;//同时发射的子弹数量
	public int life = 0;//生命值
	public int score = 0;//得分
	
	
	//读取生命值的方法
	public int getLife() {
		return life;
	}
	
	//读取得分的方法
	public int getScore() {
		return score;
	}
	
	public Hero() {
		image = ShootGame.hero0;
		height = image.getHeight();
		width = image.getWidth();
		fire = 1;
		life = 3;
		score = 0;
		x = 300;
		y = 400;
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * move的作用是让英雄机的中心位置和鼠标位置一致
	 * @param x
	 * @param y
	 */
	public void move(int x,int y) {
		this.x = x - width / 2;
		this.y = y - height / 2;
	}
	
	public Bullet[] createFire() {
		Bullet[] bullet = null;
		if(fire==1) {
			bullet = new Bullet[1];
			bullet[0] = new Bullet(x+this.width/2-ShootGame.bullet.getWidth()/2,y-ShootGame.bullet.getHeight());
			
		}else if(fire == 5) {
			bullet = new Bullet[5];
			bullet[0] = new Bullet(x+this.width/2-ShootGame.bullet.getWidth()/2,y-ShootGame.bullet.getHeight());
			bullet[1] = new Bullet(x+this.width/2-ShootGame.bullet.getWidth()/2+30,y-ShootGame.bullet.getHeight()+30);
			bullet[2] = new Bullet(x+this.width/2-ShootGame.bullet.getWidth()/2+60,y-ShootGame.bullet.getHeight()+80);
			bullet[3] = new Bullet(x+this.width/2-ShootGame.bullet.getWidth()/2-30,y-ShootGame.bullet.getHeight()+30);
			bullet[4] = new Bullet(x+this.width/2-ShootGame.bullet.getWidth()/2-60,y-ShootGame.bullet.getHeight()+80);
		}
		return bullet;
	}

}
