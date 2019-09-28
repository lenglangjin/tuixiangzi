package com.haina.bird.day03;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 * 柱子类
 * 同一屏幕下最多出现两个柱子
 * 两个柱子之间有固定的距离
 */
public class Column {
      BufferedImage columnImage;
      int x,y,width,height;
     //两个柱子之间的距离
      int distance = 300;
     //随机数的生成
      Random random = new Random();
     /*
      * 柱子类的构造方法
      * number 柱子的编号，用来区分是
      * 第一根柱子还是第二根柱子
      */
     public  Column(int number) throws IOException{
			columnImage = ImageIO.read(getClass().getResource("column.png"));
			width = columnImage.getWidth();
			height = columnImage.getHeight();
			x = distance*number+344;//到第一根柱子的距离
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












