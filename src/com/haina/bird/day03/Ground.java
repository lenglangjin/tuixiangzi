package com.haina.bird.day03;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ground {
       BufferedImage ground;
       int x,y,width,height;
      
      public Ground() throws IOException{
    	  ground = ImageIO.read(getClass().getResource("ground.png"));
    	  //�ذ�ĺ����꣬�����꣬��ȣ��߶ȣ�
    	  x = 0;
    	  y = 498;
    	  width = ground.getWidth();
    	  height = ground.getHeight();
      }
      public void paint(Graphics g) {
            g.drawImage(ground, x, y, null);
	}
      //�ذ���ƶ�����
      public void move(){
    	  //������move����ʱx�����һ
    	  x--;
    	  //ѭ���ƶ�
    	  if(x<-300)
    		  x=0;
    	  System.out.println(x);
      }
}

