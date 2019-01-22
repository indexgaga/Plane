package asd;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class hero {
	static int x = 127;
	static int y = 388;
}

public class Frame extends JPanel {

	public void addMouseAction() {
		// 定义一个自定义的鼠标事件监听
		MouseAction action = new MouseAction();
		// 为panel注册鼠标事件的监听
		this.addMouseListener(action);
		this.addMouseMotionListener(action);
		Timer time = new Timer();
		time.schedule(new TimerTask() {

			@Override
			public void run() {
				repaint();
			}

		}, 0, 1);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(400, 100, 336, 607);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Frame f = new Frame();
		frame.add(f);
		frame.setVisible(true);
		f.addMouseAction();


	}
	
	public void paint(Graphics g) {
		//背景图片
		Image imageBG = new ImageIcon("src/backage.png").getImage();
		g.drawImage(imageBG, 0, 0, null);
		Image imageBG2 = new ImageIcon("src/asdd.png").getImage();
		int width=imageBG2.getWidth(null);
		int height=imageBG2.getHeight(null);
		g.drawImage(imageBG2, hero.x,hero.y , null);
		
	}

}

class MouseAction implements MouseListener, MouseMotionListener {
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		hero.x = e.getX();
		hero.y = e.getY();
		System.out.println(hero.x + ":" + hero.y);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
