package asd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPanel2 extends JPanel {
	
	private  JLabel label;//标签
	private JButton button;//按钮
	
	int count=1;
	
	/**
	 * 鼠标事件的移动方法
	 */
	public void addMouseAction(){
		//定义一个自定义的鼠标事件监听
		MouseAction action=new MouseAction();
		//为panel注册鼠标事件的监听
		this.addMouseListener(action);
		this.addMouseMotionListener(action);
		
		//为button添加鼠标点击事件
		button.addMouseListener(action);
		
	}
	/**
	 * 自定义Panel面板的初始化
	 */
	public void init(){
		//设置自定义布局
		this.setLayout(null);
		
		//初始化label
		label=new JLabel();
		//设置标签的位置和大小
		label.setBounds(200, 200,600, 50);
		//给面板panel中添加label标签
		this.add(label);
		
		//初始化button
		button=new JButton("点击");
		button.setBounds(300, 300, 80, 30);
		
		//添加button
		this.add(button);
		
	}
	
	
	
	public static void main(String[] args) {
		//创建一个窗体
		JFrame frame=new JFrame("这是一个窗体");
		frame.setVisible(true);//窗体设置为可见的
		frame.setSize(800, 600);//设置窗体的长宽
		frame.setLocationRelativeTo(null);//设在屏幕中央
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭方式
	
		MyPanel2 panel=new MyPanel2();
		//初始化
		panel.init();
		//添加事件
		panel.addMouseAction();
		frame.add(panel);
		
	}
	
	
	
	/**
	 * 这是一个自定义鼠标事件
	 * @author ZHM
	 *
	 */
	class MouseAction implements MouseListener,MouseMotionListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			/*
			 * 鼠标的点击事件
			 */
			String str="我被点了第"+count+"次";
			label.setText(str);
			//设置字体
			Font font=new Font("隶书", Font.BOLD, 30);
			label.setFont(font);
			//设置颜色
			Color c=new Color(0, 0,255);
			label.setForeground(c);
			//每点一次，count就加一次
			count++;
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

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			/*
			 * 这是鼠标移动事件
			 */
			//获取鼠标的XY值
			int x= e.getX();//获取鼠标移动X坐标值
			int y=e.getY();//获取鼠标的Y坐标值
			String str="坐标为("+x+","+y+")";
			label.setText(str);
			//设置字体
			Font font=new Font("隶书", Font.BOLD, 30);
			label.setFont(font);
			//设置颜色
			Color c=new Color(255, 0, 0);
			label.setForeground(c);
		}
		
	}
	
}



