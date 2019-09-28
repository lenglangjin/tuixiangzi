package com.haina.bird.day03;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 * ������
 * ͬһ��Ļ����������������
 * ��������֮���й̶��ľ���
 */
public class Column {
      BufferedImage columnImage;
      int x,y,width,height;
     //��������֮��ľ���
      int distance = 300;
     //�����������
      Random random = new Random();
     /*
      * ������Ĺ��췽��
      * number ���ӵı�ţ�����������
      * ��һ�����ӻ��ǵڶ�������
      */
     public  Column(int number) throws IOException{
			columnImage = ImageIO.read(getClass().getResource("column.png"));
			width = columnImage.getWidth();
			height = columnImage.getHeight();
			x = distance*number+344;//����һ�����ӵľ���
			y = random.nextInt(120)+200;
     }
     public void paint(Graphics g) {
    	 g.drawImage(columnImage, x, y-height/2, null);
	}
     
     public void move(){
    	 x--;
    	 if(x<-width){
    		 y = random.nextInt(120)+200;
    		 x = distance+244;
    	 }
     }
}












