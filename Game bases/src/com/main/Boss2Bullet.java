package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss2Bullet extends GameObject{
	
	private int WidthBullet = 15;
	private int HeightBullet = 15;
	private Handler handler;
	
	private Random r=new Random();
	

	public Boss2Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX=r.nextInt(5 - -5) -5;
		velY=r.nextInt(5 - -5) -5;
		this.handler= handler;
	
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		if(y<=0)
			velY*=-1;
		else if(y+HeightBullet>=Game.HEIGHT)
			handler.removeObject(this);
		if(x<=0) 
			velX*=-1;
		else if(x+WidthBullet>= Game.WIDTH)
			handler.removeObject(this);

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect((int)x,(int)y, WidthBullet,HeightBullet);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WidthBullet,HeightBullet);
	}

}
