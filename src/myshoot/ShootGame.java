package myshoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShootGame extends JPanel {
	// 背景图片大小
	public static final int WEITH = 0;
	public static final int HEIGHT = 0;
	// 游戏界面固定大小336*607
	public static final int FRAME_WIDTH = 336;
	public static final int FRAME_HEIGHT = 607;

	/*
	 * 游戏启动第一件事是从硬盘加载所有要用到的图片到内存当中 而且仅在启动时加载一次——静态块 缓存在程序中的所有图片，都会反复使用，仅保存一份——静态变量
	 * 下面，为每张图片加载一个静态变量，然后在静态块加加载每张图片
	 */
	public static BufferedImage background; // 背景图片
	public static BufferedImage start; // 开始图片
	public static BufferedImage airplane; // 敌机图片
	public static BufferedImage airplane2;// 敌机图片2
	public static BufferedImage bigplane; // 大飞机
	public static BufferedImage hero0; // 英雄机状态0
	public static BufferedImage hero1; // 英雄机状态1
	public static BufferedImage bullet; // 子弹
	public static BufferedImage pause; // 暂停图片
	public static BufferedImage gameover; // 游戏结束
	public static BufferedImage nullgugu;// 鸽子
	public static BufferedImage gugu;// 鸽子离开
	public static BufferedImage bomb;// 子弹爆炸

	// 定义游戏状态：当前状态变量：默认为开始状态
	private int state = RUNNING;
	// 定义游戏状态的备选项常量：
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;

	// 游戏角色初始化
	public Hero hero = new Hero();
	// 子弹数组
	public Bullet[] bullets = {};
	// 敌人数组
	public Airplane[] airplanes = {};

	// 静态块，在类加载到方法区时执行一次，专门加载静态资源
	static {
		/*
		 * java从硬盘中加载图片到内存中： ImageIO.read方法：专门从硬盘中加载图片的静态方法 不用实例化，直接调用
		 * ShootGame.class:获得当前类的加载器所在路径 ShootGame.class.getRerource("文件名");
		 * 从当前类所在路径加载指定文件到程序中
		 */
		try {
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
			airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			airplane2 = ImageIO.read(ShootGame.class.getResource("airplane2.png"));
			bigplane = ImageIO.read(ShootGame.class.getResource("bigplane.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			gugu = ImageIO.read(ShootGame.class.getResource("gugu.png"));
			nullgugu = ImageIO.read(ShootGame.class.getResource("nullgugu.png"));
			bomb = ImageIO.read(ShootGame.class.getResource("bomb.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 500, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ShootGame game = new ShootGame();
		frame.add(game);
		frame.setVisible(true);
		game.action();
	}

	// 游戏开始要做的事情
	public void action() {
		/* 游戏开始时，要定义鼠标事件的监听 */
		// step1: 创建MouseAdapter匿名内部类——事件的响应程序
		MouseAdapter l = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// 只有在RUNNING状态下英雄机才跟随鼠标移动
				if (state == RUNNING) {
					// step3: 获得鼠标新位置
					int x = e.getX();
					int y = e.getY();
					// step4: 将鼠标位置传给英雄机的move方法
					hero.move(x, y);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// 如果在GAME_OVER状态下单机，会重新开始游戏
				if (state == GAME_OVER) {
					bullets = new Bullet[0];
					airplanes = new Airplane[0];
					hero = new Hero();
					state = RUNNING;
				} else if (state == RUNNING) {
					state = PAUSE;
				} else if (state == PAUSE) {
					state = RUNNING;
				}
			}

			@Override // 鼠标离开事件
			public void mouseExited(MouseEvent e) {
				if (state == RUNNING) {
					// 仅在处于RUNNING状态下，鼠标移出才暂停
					state = PAUSE;
				}
			}

			@Override // 鼠标进入事件
			public void mouseEntered(MouseEvent e) {
				if (state == PAUSE) {
					state = RUNNING;
				}
			}

		};
		this.addMouseMotionListener(l); // 支持鼠标的移动事件，不支持鼠标单击事件
		this.addMouseListener(l);
		; // 支持鼠标单击事件

		// step1: 创建定时器
		Timer timer = new Timer();
		// step2: 调用定时器对象的schedule方法，做计划
		// 第一个参数：TimerTask类型的匿名内部类
		// 必须重写run方法——核心——要做什么事
		timer.schedule(new TimerTask() {
			// 首先定义一个计时器变量index，记录run方法运行的次数
			private long runTimes = 0L;

			@Override
			public void run() {
				repaint();
				if (state == RUNNING) {

					runTimes++;

					// 限制五倍火力时间
					if (hero.fire == 5) {
						stopDog();
					}
					// 每300亳秒生成一次子弹
					if (runTimes % 20 == 0) {
						shoot(); // 创建一次子弹
					}
					// 子弹移动
					for (int i = 0; i < bullets.length; i++) {
						bullets[i].step();
					}

					// 生成敌机
					if (runTimes % 80 == 0) {
						nextOne(runTimes);
					}
					// 敌机移动
					for (int i = 0; i < airplanes.length; i++) {
						airplanes[i].step();
					}

					boom();// 子弹命中监测
					hit();// 机体碰撞监测
					outOf();// 飞行物越界监测
				}

			}
		}, 10, 10);
	}

	/**
	 * 游戏状态判断
	 */
	public void runGame(Graphics g) {
		if (state == GAME_OVER) {
			g.drawImage(gameover, 80, 80, null);
		}
		if (state == PAUSE) {
			g.drawImage(pause, 80, 80, null);
		}
	}

	public void paint(Graphics g) {
		// 绘制背景图片
		paintBackground(g);
		paintHero(g);
		paintBullets(g);
		paintAirplane(g);
		// 绘制分数和LIFE
		paintScoreAndLife(g);
		runGame(g);
	}

	/**
	 * 绘制背景图片
	 * 
	 * @param g
	 */
	int y = 0;
	int y2 = -background.getHeight();

	public void paintBackground(Graphics g) {
		g.drawImage(background, 0, y++, null);
		g.drawImage(background, 0, y2++, null);
		if (y >= background.getHeight()) {
			y = -background.getHeight();
		}
		if (y2 >= background.getHeight()) {
			y2 = -background.getHeight();
		}
	}

	public void paintHero(Graphics g) {
		g.drawImage(hero0, hero.x, hero.y, null);
	}

	/**
	 * 遍历子弹数组，批量绘制所有子弹的方法
	 * 
	 * @param g
	 */
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			g.drawImage(bullets[i].image, bullets[i].x, bullets[i].y, null);
		}
	}

	public void paintAirplane(Graphics g) {
		for (int i = 0; i < airplanes.length; i++) {
			g.drawImage(airplanes[i].image, airplanes[i].x, airplanes[i].y, null);
		}
	}

	/**
	 * 绘制分数
	 */
	public void paintScoreAndLife(Graphics g) {
		int x = 10; // 文字在左上角的x坐标
		int y = 30; // 文字在左上角的y坐标
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		Color color = new Color(255, 255, 255);
		g.setColor(color);
		g.setFont(font);
		g.drawString("SCORE:" + hero.getScore(), x, y);
		g.drawString("LIFE:" + hero.getLife(), x, y + 25);
	}

	/**
	 * 二狗五倍子弹取消的方法
	 */
	int i = 0;

	public void stopDog() {
		i++;
		if (hero.fire == 5 && i == 1000) {
			hero.fire = 1;
			i = 0;
		}
	}

	/**
	 * 获得英雄机对象发射的子弹对象 将新的子弹对象保存到子弹数组中，统一管理
	 */
	public void shoot() {
		Bullet[] newBullet = hero.createFire();
		bullets = Arrays.copyOf(bullets, newBullet.length + bullets.length);
		System.arraycopy(newBullet, 0, bullets, bullets.length - newBullet.length, newBullet.length);
	}

	/**
	 * 随机生成1个敌人对象 每生成一个新敌人， flyers数组就要扩容1 然后将新敌人放入数组最后一个元素
	 */
	public void nextOne(long runTimes) {
		Random r = new Random();
		Airplane f = null;
		if (r.nextInt(20) == 0) { // 只有随机数取0时才创建大飞机
			f = new Airplane();

		} else if (runTimes == 640) {
			f = new Airplane(3);
		} else { // 其余全部生成敌机
			f = new Airplane(2);
		}
		// 对flyers数组扩容1
		airplanes = Arrays.copyOf(airplanes, airplanes.length + 1);
		// 将新敌人放入数组末尾
		airplanes[airplanes.length - 1] = f;
	}

	/**
	 * 机体碰撞方法
	 */
	public void hit() {
		for (int i = 0; i < airplanes.length; i++) {
			if (Flyer.boom(hero, airplanes[i])) {
				hero.life -= 1;
				airplanes[i] = airplanes[airplanes.length - 1];
				Arrays.copyOf(airplanes, airplanes.length - 1);
			}
			if (hero.life <= 0) {
				state = GAME_OVER;
			}
		}
	}

	/**
	 * 子弹命中监测方法
	 */
	public void boom() {
		for (int i = 0; i < bullets.length; i++) {
			for (int j = 0; j < airplanes.length; j++) {
				if (Flyer.boom(bullets[i], airplanes[j])) {
					// 为英雄机获得分数和奖励
//					hero.getScore_Award(airplanes[j]);
					// 从敌人数组中删除被击中的敌机
					airplanes[j].life -= 1;
					if (airplanes[j].life <= 0) {
						if (airplanes[j].id == 0) {// 二狗+100
							hero.score += 100;
							hero.fire = 5;
						} else {// 普通+10
							hero.score += 10;
						}
						// step1： 使用敌人数组最后一个元素替换被击中的敌机
						airplanes[j] = airplanes[airplanes.length - 1];
						// step2: 压缩数组
						airplanes = Arrays.copyOf(airplanes, airplanes.length - 1);
					}
					// 从子弹数组中删除击中敌机的子弹
					bullets[i] = bullets[bullets.length - 1];
					bullets = Arrays.copyOf(bullets, bullets.length - 1);
					i--; // 第发现一次碰撞，子弹就要退一个元素，重新检测当前位置
					break; // 只要发现碰撞就退出当前敌人数组的循环
				}
			}
		}

	}

	/**
	 * 飞行物出界处理
	 */
	public void outOf() {
		for (int i = 0; i < airplanes.length; i++) {
			if (airplanes[i].outOfBounds()) {
				airplanes[i] = airplanes[airplanes.length - 1];
				// step2: 压缩数组
				airplanes = Arrays.copyOf(airplanes, airplanes.length - 1);
			}
		}
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i].outOfBounds()) {
				bullets[i] = bullets[bullets.length - 1];
				// step2: 压缩数组
				bullets = Arrays.copyOf(bullets, bullets.length - 1);
			}
		}
	}

}
