package com.haina.bird.day04;

import java.awt.Color;
import java.awt.Font;
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
 *   游戏初始世界
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
	  private Ground ground;
	  //创建两个柱子
	  private Column column1;
	  private Column column2;
	  private Bird bird;
	  //游戏分数
	  private int score;
	  public void init() throws Exception{
		  score = 0;
		  start = false;
		  gameover = false;
		   ground = new Ground();
		   column1 = new Column(1);
		   column2 = new Column(2);
		   bird = new Bird();
	  }
	  //构造方法，用于初始化视图
	  public World () throws Exception{
		  //getClass()表示当前包,getResource()读取文件
		  bgImage = ImageIO.read(getClass().getResource("bg.png"));
		  startImage = ImageIO.read(getClass().getResource("start.png"));
		  gameoverImage = ImageIO.read(getClass().getResource("gameover.png"));
		  init();
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
			start = true;
			return;
		}
		column1.paint(g);
		column2.paint(g);
		ground.paint(g);
		bird.paint(g);
		Font font = new Font(Font.MONOSPACED,Font.BOLD,45);
		//拼接为字符串显示
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(score+"", 30, 50);

	}
	//处理屏幕点击等操作的方法
	public void action(){
		//给游戏窗口添加一个点击事件监听器
	   addMouseListener(new MouseAdapter() {	
		   //鼠标按下方法
		public void mousePressed(MouseEvent e) {
			if(gameover){
				try {
					init();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return;
			}
			//当按下鼠标时，设置start为true
			   start = true;
			   //调整速度
			   bird.flappy();
		}
	});
	   //焦点集中 使得游戏失败后聚焦窗口
	   requestFocus();
	   //利用repaint可以重新描绘窗口的事物特点 
	   //不断执行repaint完成窗口的事物变化    ，及时更新
	   while(true){
		   
		   if(start&&!gameover){
			   //调用move方法使地板不断移动
			   column1.move();
			   column2.move();
			   bird.move();
			   if(bird.pass(column1, column2)){
				  score ++;
				  System.out.println(score);
			   }
			   if(bird.hit(column1,column2,ground)){
				   start = false;
				   gameover = true;
			   }
		   }

		   ground.move();
		   if(!gameover){
			   bird.fly(); 
		   }
		   //重绘
		   repaint();
		   //在计算机中，存在无数个进程，而每一个进程又会由多个线程组成
		   //一个软件可以简单理解为一个进程，我们的程序执行会占用一个线程
		   //sleep()可以让线程沉睡一段时间
		   //即在单位时间内不会工作，停滞；
		   try {
			Thread.sleep(1000/70);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	   }
	}
	
      public static void main(String[] args) throws Exception {
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
