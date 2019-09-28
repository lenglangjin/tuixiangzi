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
 *   ��Ϸ��ʼ����
 */
public class World extends JPanel{
	  //����ͼƬ
	  private BufferedImage bgImage;
	  //��ʼͼƬ
	  private BufferedImage startImage;
	  //����ͼƬ
	  private BufferedImage gameoverImage;
	  //�ж���Ϸ�Ƿ�ʼ��Ĭ��false
	  private boolean start;
	  //�ж���Ϸ�Ƿ������Ĭ��false
	  private boolean gameover;
	  private Ground ground;
	  //������������
	  private Column column1;
	  private Column column2;
	  private Bird bird;
	  //��Ϸ����
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
	  //���췽�������ڳ�ʼ����ͼ
	  public World () throws Exception{
		  //getClass()��ʾ��ǰ��,getResource()��ȡ�ļ�
		  bgImage = ImageIO.read(getClass().getResource("bg.png"));
		  startImage = ImageIO.read(getClass().getResource("start.png"));
		  gameoverImage = ImageIO.read(getClass().getResource("gameover.png"));
		  init();
	  }
	  
	public void paint(Graphics g) {
		//�ڴ��ڵ�0��0����ʾ����ͼƬ
		g.drawImage(bgImage, 0, 0, null);
		// û�п�ʼ��ʱ����ʾ��ʼͼƬ
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
		//ƴ��Ϊ�ַ�����ʾ
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(score+"", 30, 50);

	}
	//������Ļ����Ȳ����ķ���
	public void action(){
		//����Ϸ�������һ������¼�������
	   addMouseListener(new MouseAdapter() {	
		   //��갴�·���
		public void mousePressed(MouseEvent e) {
			if(gameover){
				try {
					init();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return;
			}
			//���������ʱ������startΪtrue
			   start = true;
			   //�����ٶ�
			   bird.flappy();
		}
	});
	   //���㼯�� ʹ����Ϸʧ�ܺ�۽�����
	   requestFocus();
	   //����repaint����������洰�ڵ������ص� 
	   //����ִ��repaint��ɴ��ڵ�����仯    ����ʱ����
	   while(true){
		   
		   if(start&&!gameover){
			   //����move����ʹ�ذ岻���ƶ�
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
		   //�ػ�
		   repaint();
		   //�ڼ�����У��������������̣���ÿһ�������ֻ��ɶ���߳����
		   //һ��������Լ����Ϊһ�����̣����ǵĳ���ִ�л�ռ��һ���߳�
		   //sleep()�������̳߳�˯һ��ʱ��
		   //���ڵ�λʱ���ڲ��Ṥ����ͣ�ͣ�
		   try {
			Thread.sleep(1000/70);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	   }
	}
	
      public static void main(String[] args) throws Exception {
    	  /*
    	   * ������Ϸ����
    	   */
		JFrame frame = new JFrame("������");
		//������Ϸ����  ���
		World world = new World();
		//����Ϸ��ӵ���������ʾ 
		frame.add(world);
		//���ô��ڴ�С
		frame.setSize(432+8,644+16);
		//��ʾ���������������ʾ
		frame.setLocationRelativeTo(null);
		//���ô��ڴ�С���ɸı�
		frame.setResizable(false);
		//��ʾframe����
		frame.setVisible(true);
		//������Ͻǹر�ʱ ������Ϸ����
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		world.action();
	}
}
