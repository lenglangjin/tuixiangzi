package com.haina.bird.day04;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/*
 * ��Ϸ���������
 */
public class Bird {
	//��ĺ�������
        int x;
        int y;
       //�����ʱ�ĽǶ�
        double angle;
       //��ǰС����ʽͼƬ
       BufferedImage image;
       // ͼƬ����
       BufferedImage[] images;
       //��ǰչʾ��ͼƬ�����
       int index = 0;
       //�������ٶ�
       final double g;
       //����ʱ��
       final double t;
       //����ʱ��ʼ���ϵ��ٶ�
       final double v0;
       //��ǰ����ʱ��˲ʱ�ٶ�
       double speed;
       //�ƶ����ܾ���
       double s;
       //С��ĳߴ��С
       int size = 40;
       //��ʼ��������ͼƬ�Ĺ��췽��
       public Bird() throws Exception{
    	   this.g = 4;
    	   this.t = 0.25;
    	   this.v0 = 20;
    	   this.x = 132;
    	   this.y = 275;
    	   //��ʼ��ͼƬ���飬����Ϊ8 ��װС��ͼƬ
    	   images = new BufferedImage[8];
    	   for(int i=0;i<8;i++){
    		   images[i] = ImageIO.read(getClass().getResource(i+".png"));
    	   }
    	   //�ѵ�һ����Ƭ��ΪС��ĳ�ʼ��Ƭ
    	   image = images[0];
       }
       //С����ƶ�����
       public void move(){
    	   //С��ĳ�ʼ�ٶ�
    	   double v0 = speed;
    	   //�A��ʱ tʱ�����ٶ�
    	   double v = v0-g*t;
    	   //��Ϊ��һ�μ���ʱʹ�õĳ�ʼ�ٶ�
    	   speed = v;
    	   //����λ�Ƶľ���
    	   s = v0*t-0.5*g*t*t;
    	   //����A��һ�κ�λ�õı仯
    	   y = y-(int)s;
    	   //����С����ǰ�ĽǶ�,8��ʾ8��ͼƬ
    	   angle = Math.atan(s/8);
       }
       /**
        * �л�С�����ʱ����ʽ
        */
       public void fly(){
    	   index ++;
    	   //(index/8)%images.length  ���Ա�֤ȡֵ��0��7֮��
    	   image = images[(index/8)%images.length];
       }
       public void flappy(){
    	   //�ٶȻع��ʼֵ
    	   speed = v0;
       }
       //synchronized ͬ��
       public synchronized void paint(Graphics g){
    	   //�漰��ͼ����ת��Ҫʹ��2dͼ������
    	   Graphics2D g2 = (Graphics2D)g;
    	   //����С��x y ֵ������ת
    	   g2.rotate(angle,this.x,this.y);
    	   //��ȡС�������λ��
    	   int x= this.x-image.getWidth()/2;
    	   int y = this.y-image.getHeight()/2;
    	   //����С������λ��չʾС��
    	   g.drawImage(image, x, y, null);
    	   //��С����س�ʼ״̬
    	   g2.rotate(-angle, this.x, this.y);
       }
	   //�ж�С���Ƿ�ͨ�����ӿ��Լӷ� 
       public boolean pass(Column c1,Column c2){
    	   return c1.x ==x||c2.x == x;
       }
       public boolean hit(Column c1,Column c2,Ground ground){
    	  //�ж��Ƿ�ײ������
    	   if(y+size/2>=ground.y){
    		   return true;
    	   }
    	   return hit(c1)||hit(c2);
       }
       public boolean hit(Column col) {
    	   //����С��ײ����ʱx�����ȡֵ��Χ
           if(x>col.x-col.width/2-size/2&&x<col.x+col.width/2+size/2){
        	   
        	   if(y>col.y-72-size/2&&y<col.y+72-size/2){
        		   return false;
        	   }
               return true;
           }
           return false;

	}
       
}















