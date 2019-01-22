package asd;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * 创建一个窗体对象
		 */
		JFrame frame =new JFrame("这是窗体的标题");
		//title是表示设置窗体的标题
		
		//默认创建的窗体是不可见的
		//设置为可见的
		frame.setVisible(true);//窗体设置为可见的
		frame.setSize(400, 500);//设置窗体的长宽
		frame.setLocationRelativeTo(null);//设在屏幕中央
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//这个设置的目的就是为了，为了关闭窗口的同时，顺便关闭虚拟机
		
		//JPanel这是一个组件容器，可以用来装载组件(按钮，标签，输入框)
		JPanel panel=new JPanel();
		//panel是默认可见的，panel之间可以嵌套
		//frame窗体之间不可以嵌套
		
		//容器需要被装载在窗体之内才有效果
		frame.add(panel);//在窗体中添加panel组件
		
		//设置自定义布局
		panel.setLayout(null);

		//组件
		JButton button=new JButton("这是一个按钮");
		//在创建按钮组件的同时可以，给按钮设置名称
		//设置组件的位置和大小
		button.setBounds(100, 20, 120, 50);
		/*
		 * 此处X和Y设置的是坐标
		 * x:设置横坐标
		 * Y：设置纵坐标
		 * width：设置组件的宽
		 * height:设置组件的高
		 */
		//组件也要添加才能使用
		panel.add(button);
		
		//标签组件
		JLabel label=new JLabel("这是一个标签");
		//标签设置大小和位置
		label.setBounds(100, 150, 600, 60);
		
		//给label标签设置字体
		Font font=new Font("隶书", Font.BOLD, 35); 
		label.setFont(font);
		//给Label标签字体设置颜色
		Color color=new Color(120, 25,255);
		/*
		 * r--red  g--green b--blue
		 * 颜色的度都是从0~255
		 */
		
		label.setForeground(color);
		//添加标签组件
		panel.add(label);
		
		//设置一个单行文本框组件
		JTextField textField=new JTextField(8);
		//设置输入文本框的内容长度为8列(8个字符)
		//设置长宽和位置
		textField.setBounds(150, 200, 120, 40);
		//给单行文本框设置值
		textField.setText("这是一个单行输入文本框");
		textField.setFocusable(true);
		//添加单行文本框组件
		panel.add(textField);
		
		
	}
}
