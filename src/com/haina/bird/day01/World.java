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
 * ��Ϸ��ʼ����
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
	  
	  //���췽�������ڳ�ʼ����ͼ
	  public World () throws IOException{
		  //getClass()��ʾ��ǰ��,getResource()��ȡ�ļ�
		  bgImage = ImageIO.read(getClass().getResource("bg.png"));
		  startImage = ImageIO.read(getClass().getResource("start.png"));
		  gameoverImage = ImageIO.read(getClass().getResource("gameover.png"));
		  
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
		}
	}
	//������Ļ����Ȳ����ķ���
	public void action(){
		//����Ϸ�������һ������¼�������
	   addMouseListener(new MouseAdapter() {	
		   //��갴�·���
		public void mousePressed(MouseEvent e) {
			//���������ʱ������startΪtrue
			   start = true;
			   System.out.println("��Ϸ��ʼ��");
		}
	});
	   //����repaint����������洰�ڵ������ص� 
	   //����ִ��repaint��ɴ��ڵ�����仯    ����ʱ����
	   while(true){
		   repaint();
	   }
	}
	
      public static void main(String[] args) throws IOException {
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
