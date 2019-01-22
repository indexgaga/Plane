package asd;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	/**
	 *重写绘图工具
	 *是自动运行的，不需要手动调用即可运行
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//设置颜色
		Color c=new Color(255, 0, 0);//颜色为红色
		g.setColor(c);
		//绘制矩形
		g.drawRect(100,100, 100, 50);
		
		//画个圆
		c=new Color(0, 0, 255);//设置为蓝色
		g.setColor(c);
		g.drawOval(150, 150, 200, 100);
		
		//画一条线
		c=new Color(120, 156, 198);
		g.setColor(c);
		g.drawLine(250, 250,350, 350);
	}
	
	public static void main(String[] args) {
		//创建一个窗体
		JFrame frame=new JFrame("这是一个窗体");
		frame.setVisible(true);//窗体设置为可见的
		frame.setSize(800, 600);//设置窗体的长宽
		frame.setLocationRelativeTo(null);//设在屏幕中央
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭方式
	
		JPanel panel=new MyPanel();
		frame.add(panel);
	
	}
	
}
