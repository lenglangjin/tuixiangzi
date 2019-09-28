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
    	  //地板的横坐标，纵坐标，宽度，高度；
    	  x = 0;
    	  y = 498;
    	  width = ground.getWidth();
    	  height = ground.getHeight();
      }
      public void paint(Graphics g) {
            g.drawImage(ground, x, y, null);
	}
      //地板的移动方法
      public void move(){
    	  //当调用move方法时x坐标减一
    	  x--;
    	  //循环移动
    	  if(x<-300)
    		  x=0;
    	  System.out.println(x);
      }
}

