package com.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.util.Random;

public class Boss3 extends GameObject{
	
	public static int WidthBoss3 = 81;
	public static int HeightBoss3 = 81;
	public static int getWidthBoss3() {
		return WidthBoss3;
	}
	public static int getHeightBoss3() {
		return HeightBoss3;
	}

	private static int WidthTurretPart1;
	private  static int HeightTurretPart1;
	private static int WidthTurretPart2;
	private  static int HeightTurretPart2;
	
	private static double xLineNorth;
	private static double xMaxLineNorth;
	private static double yLineNorth;
	private static double yMaxLineNorth;
	
	
	public static double getxLineNorth() {
		return xLineNorth;
	}
	public static double getxMaxLineNorth() {
		return xMaxLineNorth;
	}
	public static double getyLineNorth() {
		return yLineNorth;
	}
	public static double getyMaxLineNorth() {
		return yMaxLineNorth;
	}
	
	public static Shape Line1;
	public static Shape Line2;
	public static Shape Line3;
	public static Shape Line4;
	
	public static Shape getLine1() {
		return Line1;
	}
	public static Shape getLine2() {
		return Line2;
	}
	public static Shape getLine3() {
		return Line3;
	}
	public static Shape getLine4() {
		return Line4;
	}

	private Handler handler;
	public static double RotationSpeed = 1.9;
	private int timer=50,timer2=50;
	public static double theta=1;
	
	private Random r = new Random();

	public Boss3(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		WidthTurretPart1=WidthBoss3/4;
		setHeightTurretPart1(HeightBoss3/4);
		
		setWidthTurretPart2(WidthBoss3/8);
		setHeightTurretPart2(HeightBoss3/8);
		
		velX=0;
		velY=2;
		this.handler= handler;
	
	}
	
	public AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	public void tick() {
		
		theta+=RotationSpeed;
		
		if(y-getHeightTurretPart1()-getHeightTurretPart2()+32<=0 || y+HeightBoss3+getHeightTurretPart1()+38+32>=Game.HEIGHT)
			velY*=-1;
		if(x-WidthTurretPart1-getWidthTurretPart2()<=0 || x+WidthBoss3+WidthTurretPart1+18>=Game.WIDTH)
			velX*=-1;
		x+=velX;
		y+=velY;
		
		if(timer<=0) 
		{
			timer2--;
			if(timer2==0) 
				velX=2;
			for(int i = 0; i<handler.object.size();i++)
			{
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId()==ID.Boss3Laser)
				{
					i--;
					handler.removeObject(tempObject);
				}
			}
			//North
			handler.addObject(new Boss3Laser(x+WidthBoss3/2-getHeightTurretPart2()/4, y-getHeightTurretPart1()-getWidthTurretPart2()-Game.HEIGHT,
					ID.Boss3Laser, handler,getWidthTurretPart2()/2,Game.HEIGHT,
					theta,x+WidthBoss3/2, y+HeightBoss3/2));
			//East
			handler.addObject(new Boss3Laser2(x+WidthBoss3+WidthTurretPart1+getWidthTurretPart2(), y+HeightBoss3/2-getHeightTurretPart2()/4,
					ID.Boss3Laser, handler, Game.WIDTH, getHeightTurretPart2()/2,
					theta,x+WidthBoss3/2, y+HeightBoss3/2));
			//South
			handler.addObject(new Boss3Laser3(x+WidthBoss3/2-getWidthTurretPart2()/4, y+HeightBoss3+getHeightTurretPart1()+getHeightTurretPart2(),
					ID.Boss3Laser, handler, getWidthTurretPart2()/2, Game.HEIGHT,
					theta,x+WidthBoss3/2, y+HeightBoss3/2));
			//West
			handler.addObject(new Boss3Laser4(x-WidthTurretPart1-getWidthTurretPart2()-Game.WIDTH, y+HeightBoss3/2-getHeightTurretPart2()/4,
					ID.Boss3Laser, handler, Game.WIDTH, getHeightTurretPart2()/2,
					theta,x+WidthBoss3/2, y+HeightBoss3/2));
		}
		else timer--;
	}
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform old = g2d.getTransform();
		
		g2d.rotate(Math.toRadians(theta), x+WidthBoss3/2, y+HeightBoss3/2);
		g2d.setColor(Color.yellow);
		//Boss
		g2d.fillRect((int)x,(int)y, WidthBoss3,HeightBoss3);
		//Parts 2
		//North
		Rectangle North=new Rectangle((int)x+WidthBoss3/2-getHeightTurretPart2()/2,(int)y-getHeightTurretPart1()-getWidthTurretPart2(), getWidthTurretPart2(),getHeightTurretPart2());
		g2d.fill(North);
		//East
		g2d.fillRect((int)x+WidthBoss3+WidthTurretPart1,(int)y+HeightBoss3/2-getHeightTurretPart2()/2, getWidthTurretPart2(),getHeightTurretPart2());
		//South
		g2d.fillRect((int)x+WidthBoss3/2-getWidthTurretPart2()/2,(int)y+HeightBoss3+getHeightTurretPart1(), getWidthTurretPart2(),getHeightTurretPart2());
		//West
		g2d.fillRect((int)x-WidthTurretPart1-getWidthTurretPart2(),(int)y+HeightBoss3/2-getHeightTurretPart2()/2, getWidthTurretPart2(),getHeightTurretPart2());
		//Parts 1
		//North
		g2d.fillRect((int)x+WidthBoss3/2-WidthTurretPart1/2,(int)y-getHeightTurretPart1(), WidthTurretPart1,getHeightTurretPart1());
		//East
		g2d.fillRect((int)x+WidthBoss3,(int)y+HeightBoss3/2-getHeightTurretPart1()/2, WidthTurretPart1,getHeightTurretPart1());
		//South
		g2d.fillRect((int)x+WidthBoss3/2-WidthTurretPart1/2,(int)y+HeightBoss3, WidthTurretPart1,getHeightTurretPart1());
		//West
		g2d.fillRect((int)x-WidthTurretPart1,(int)y+HeightBoss3/2-getHeightTurretPart1()/2, WidthTurretPart1,getHeightTurretPart1());
		
		g2d.setTransform(old);
	}
	
	
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WidthBoss3,HeightBoss3);
	}
	public Area getBoundsArea() {
		return null;
	}
	public static int getHeightTurretPart2() {
		return HeightTurretPart2;
	}
	public static void setHeightTurretPart2(int heightTurretPart2) {
		HeightTurretPart2 = heightTurretPart2;
	}
	public static int getHeightTurretPart1() {
		return HeightTurretPart1;
	}
	public static void setHeightTurretPart1(int heightTurretPart1) {
		HeightTurretPart1 = heightTurretPart1;
	}
	public static int getWidthTurretPart2() {
		return WidthTurretPart2;
	}
	public static void setWidthTurretPart2(int widthTurretPart2) {
		WidthTurretPart2 = widthTurretPart2;
	}

}
