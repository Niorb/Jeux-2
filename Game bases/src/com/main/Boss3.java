package com.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
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

	private int WidthTurretPart1;
	private  int HeightTurretPart1;
	private int WidthTurretPart2;
	private  int HeightTurretPart2;
	private Handler handler;
	public static double RotationSpeed = 1.5;
	private int timer=50,timer2=50;
	public static int theta=1;
	
	private Random r = new Random();

	public Boss3(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		WidthTurretPart1=WidthBoss3/4;
		HeightTurretPart1=HeightBoss3/4;
		
		WidthTurretPart2=WidthBoss3/8;
		HeightTurretPart2=HeightBoss3/8;
		
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
		
		if(y-HeightTurretPart1-HeightTurretPart2<=0 || y+HeightBoss3+HeightTurretPart1+38>=Game.HEIGHT)
			velY*=-1;
		if(x-WidthTurretPart1-WidthTurretPart2<=0 || x+WidthBoss3+WidthTurretPart1+18>=Game.WIDTH)
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
		//lasers
			//North
			handler.addObject(new Boss3Laser(x+WidthBoss3/2-HeightTurretPart2/4, y-HeightTurretPart1-WidthTurretPart2-Game.HEIGHT,
					ID.Boss3Laser, handler,WidthTurretPart2/2,Game.HEIGHT,
					theta,x+WidthBoss3/2, y+HeightBoss3/2));
			//East
			handler.addObject(new Boss3Laser(x+WidthBoss3+WidthTurretPart1+WidthTurretPart2, y+HeightBoss3/2-HeightTurretPart2/4,
					ID.Boss3Laser, handler, Game.WIDTH, HeightTurretPart2/2,
					theta,x+WidthBoss3/2, y+HeightBoss3/2));
			//South
			handler.addObject(new Boss3Laser(x+WidthBoss3/2-WidthTurretPart2/4, y+HeightBoss3+HeightTurretPart1+HeightTurretPart2,
					ID.Boss3Laser, handler, WidthTurretPart2/2, Game.HEIGHT,
					theta,x+WidthBoss3/2, y+HeightBoss3/2));
			//West
			handler.addObject(new Boss3Laser(x-WidthTurretPart1-WidthTurretPart2-Game.WIDTH, y+HeightBoss3/2-HeightTurretPart2/4,
					ID.Boss3Laser, handler, Game.WIDTH, HeightTurretPart2/2,
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
		g2d.fillRect((int)x+WidthBoss3/2-HeightTurretPart2/2,(int)y-HeightTurretPart1-WidthTurretPart2, WidthTurretPart2,HeightTurretPart2);
		//East
		g2d.fillRect((int)x+WidthBoss3+WidthTurretPart1,(int)y+HeightBoss3/2-HeightTurretPart2/2, WidthTurretPart2,HeightTurretPart2);
		//South
		g2d.fillRect((int)x+WidthBoss3/2-WidthTurretPart2/2,(int)y+HeightBoss3+HeightTurretPart1, WidthTurretPart2,HeightTurretPart2);
		//West
		g2d.fillRect((int)x-WidthTurretPart1-WidthTurretPart2,(int)y+HeightBoss3/2-HeightTurretPart2/2, WidthTurretPart2,HeightTurretPart2);
		//Parts 1
		//North
		g2d.fillRect((int)x+WidthBoss3/2-WidthTurretPart1/2,(int)y-HeightTurretPart1, WidthTurretPart1,HeightTurretPart1);
		//East
		g2d.fillRect((int)x+WidthBoss3,(int)y+HeightBoss3/2-HeightTurretPart1/2, WidthTurretPart1,HeightTurretPart1);
		//South
		g2d.fillRect((int)x+WidthBoss3/2-WidthTurretPart1/2,(int)y+HeightBoss3, WidthTurretPart1,HeightTurretPart1);
		//West
		g2d.fillRect((int)x-WidthTurretPart1,(int)y+HeightBoss3/2-HeightTurretPart1/2, WidthTurretPart1,HeightTurretPart1);
		//Lasers
		g2d.setTransform(old);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WidthBoss3,HeightBoss3);
	}
	public Area getBoundsArea() {
		return null;
	}

}
