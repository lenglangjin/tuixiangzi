package com.haina.bird.day01;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * 游戏初始世界
 */
public class World extends JPanel{
	  //背景图片
	  private BufferedImage bgImage;
	  //开始图片
	  private BufferedImage startImage;
	  //结束图片
	  private BufferedImage gameoverImage;
	  //判断游戏是否开始，默认false
	  private boolean start;
	  //判断游戏是否结束，默认false
	  private boolean gameover;
	  
	  //构造方法，用于初始化视图
	  public World () throws IOException{
		  //getClass()表示当前包,getResource()读取文件
		  bgImage = ImageIO.read(getClass().getResource("bg.png"));
		  startImage = ImageIO.read(getClass().getResource("start.png"));
		  gameoverImage = ImageIO.read(getClass().getResource("gameover.png"));
		  
	  }
	  
	public void paint(Graphics g) {
		//在窗口的0，0出显示背景图片
		g.drawImage(bgImage, 0, 0, null);
		// 没有开始的时候显示开始图片
		if(!start){
			g.drawImage(startImage, 0,0,null);
		}
		if(gameover){
			g.drawImage(gameoverImage, 0, 0, null);
		}
	}
	//处理屏幕点击等操作的方法
	public void action(){
		//给游戏窗口添加一个点击事件监听器
	   addMouseListener(new MouseAdapter() {	
		   //鼠标按下方法
		public void mousePressed(MouseEvent e) {
			//当按下鼠标时，设置start为true
			   start = true;
			   System.out.println("游戏开始了");
		}
	});
	   //利用repaint可以重新描绘窗口的事物特点 
	   //不断执行repaint完成窗口的事物变化    ，及时更新
	   while(true){
		   repaint();
	   }
	}
	
      public static void main(String[] args) throws IOException {
    	  /*
    	   * 创建游戏窗口
    	   */
		JFrame frame = new JFrame("像素鸟");
		//创建游戏世界  面板
		World world = new World();
		//将游戏添加到窗口中显示 
		frame.add(world);
		//设置窗口大小
		frame.setSize(432+8,644+16);
		//显示窗口在桌面居中显示
		frame.setLocationRelativeTo(null);
		//设置窗口大小不可改变
		frame.setResizable(false);
		//显示frame窗口
		frame.setVisible(true);
		//点击右上角关闭时 结束游戏运行
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		world.action();
	}
}
