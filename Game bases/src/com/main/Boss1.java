package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boss1 extends GameObject{
	
	private int WidthBoss = 50;
	private int HEIGHTbasicEnemy = 50;
	

	public Boss1(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX=10;
		velY=10;
	
	}

	public void tick() {
		x+=velX;
		
		y+=velY;
		if(y<=0 || y+HEIGHTbasicEnemy+38>=Game.HEIGHT)
			velY*=-1;
		if(x<=0 || x+WidthBoss+18>=Game.WIDTH)
			velX*=-1;
	}

	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int)x,(int)y, WidthBoss,HEIGHTbasicEnemy);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WidthBoss,HEIGHTbasicEnemy);
	}

}
