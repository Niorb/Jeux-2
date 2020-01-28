package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss2 extends GameObject{
	
	private int WidthBoss2 = 70;
	public  int HeigthBoss2 = 70;
	private Handler handler;
	private int timer=50,timer2=50;
	private Random r = new Random();

	public Boss2(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX=0;
		velY=2;
		this.handler= handler;
	
	}

	public void tick() {
		if(timer<=0) 
		{
			velY=0;
			timer2--;
			if(timer2==0) 
				velX=2;
			else if(timer2<0&&r.nextInt(5)==0) 
			{
				handler.addObject(new Boss2Bullet(x+WidthBoss2/2, y+HeigthBoss2/2, ID.Boss2Bullet, handler));
			}
		}
		else timer--;
		
		if(x<=0 || x+WidthBoss2+18>=Game.WIDTH)
			velX*=-1;
		
		x+=velX;
		y+=velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect((int)x,(int)y, WidthBoss2,HeigthBoss2);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WidthBoss2,HeigthBoss2);
	}

}
