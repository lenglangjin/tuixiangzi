package com.haina.bird.day02;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.haina.bird.day03.Bird;

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
	  

	  
	  public void init() throws Exception{

		   ground = new Ground();
		   column1 = new Column(1);
		   column2 = new Column(2);   

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
        	 return;
         }
		column1.paint(g);
		column2.paint(g);
		ground.paint(g);

	}
	//������Ļ����Ȳ����ķ���
	public void action(){
		//����Ϸ��������һ������¼�������
	   addMouseListener(new MouseAdapter() {	
		   //��갴�·���
		public void mousePressed(MouseEvent e) {

			//���������ʱ������startΪtrue
			   start = true;

			   
		}
	});

	   //����repaint����������洰�ڵ������ص� 
	   //����ִ��repaint��ɴ��ڵ�����仯    ����ʱ����
	   while(true){
		   //����move����ʹ�ذ岻���ƶ�
		   column1.move();
		   column2.move();
		   ground.move();

		   //�ػ�
		   repaint();
		   //�ڼ�����У��������������̣���ÿһ�������ֻ��ɶ���߳����
		   //һ���������Լ�����Ϊһ�����̣����ǵĳ���ִ�л�ռ��һ���߳�
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
		//����Ϸ���ӵ���������ʾ 
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