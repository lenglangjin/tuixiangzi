package com.haina.bird.day04;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/*
 * 游戏世界的鸟类
 */
public class Bird {
	//鸟的横纵坐标
        int x;
        int y;
       //鸟飞行时的角度
        double angle;
       //当前小鸟样式图片
       BufferedImage image;
       // 图片动画
       BufferedImage[] images;
       //当前展示的图片的序号
       int index = 0;
       //重力加速度
       final double g;
       //飞行时间
       final double t;
       //飞行时初始向上的速度
       final double v0;
       //当前飞行时的瞬时速度
       double speed;
       //移动的总距离
       double s;
       //小鸟的尺寸大小
       int size = 40;
       //初始化变量和图片的构造方法
       public Bird() throws Exception{
    	   this.g = 4;
    	   this.t = 0.25;
    	   this.v0 = 20;
    	   this.x = 132;
    	   this.y = 275;
    	   //初始化图片数组，长度为8 ，装小鸟图片
    	   images = new BufferedImage[8];
    	   for(int i=0;i<8;i++){
    		   images[i] = ImageIO.read(getClass().getResource(i+".png"));
    	   }
    	   //把第一张照片作为小鸟的初始照片
    	   image = images[0];
       }
       //小鸟的移动方法
       public void move(){
    	   //小鸟的初始速度
    	   double v0 = speed;
    	   //丄抛时 t时间后的速度
    	   double v = v0-g*t;
    	   //作为下一次计算时使用的初始速度
    	   speed = v;
    	   //计算位移的距离
    	   s = v0*t-0.5*g*t*t;
    	   //计算丄抛一次后位置的变化
    	   y = y-(int)s;
    	   //计算小鸟朝向前的角度,8表示8张图片
    	   angle = Math.atan(s/8);
       }
       /**
        * 切换小鸟分行时的样式
        */
       public void fly(){
    	   index ++;
    	   //(index/8)%images.length  可以保证取值在0到7之间
    	   image = images[(index/8)%images.length];
       }
       public void flappy(){
    	   //速度回归初始值
    	   speed = v0;
       }
       //synchronized 同步
       public synchronized void paint(Graphics g){
    	   //涉及到图像旋转需要使用2d图像设置
    	   Graphics2D g2 = (Graphics2D)g;
    	   //根据小鸟x y 值进行旋转
    	   g2.rotate(angle,this.x,this.y);
    	   //获取小鸟的中心位置
    	   int x= this.x-image.getWidth()/2;
    	   int y = this.y-image.getHeight()/2;
    	   //根据小鸟中心位置展示小鸟
    	   g.drawImage(image, x, y, null);
    	   //将小鸟调回初始状态
    	   g2.rotate(-angle, this.x, this.y);
       }
	   //判断小鸟是否通过柱子可以加分 
       public boolean pass(Column c1,Column c2){
    	   return c1.x ==x||c2.x == x;
       }
       public boolean hit(Column c1,Column c2,Ground ground){
    	  //判断是否撞击地面
    	   if(y+size/2>=ground.y){
    		   return true;
    	   }
    	   return hit(c1)||hit(c2);
       }
       public boolean hit(Column col) {
    	   //计算小鸟撞柱子时x坐标的取值范围
           if(x>col.x-col.width/2-size/2&&x<col.x+col.width/2+size/2){
        	   
        	   if(y>col.y-72-size/2&&y<col.y+72-size/2){
        		   return false;
        	   }
               return true;
           }
           return false;

	}
       
}















