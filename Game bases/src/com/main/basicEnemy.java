package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class basicEnemy extends GameObject{
	
	private int WIDTHbasicEnemy = 20;
	private int HEIGHTbasicEnemy = 20;
	private Handler handler;
	

	public basicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX=5;
		velY=5;
		this.handler= handler;
	
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		if(y<=0 || y+HEIGHTbasicEnemy+38>=Game.HEIGHT)
			velY*=-1;
		if(x<=0 || x+WIDTHbasicEnemy+18>=Game.WIDTH)
			velX*=-1;
		handler.addObject(new Trail(x, y, ID.Trail, WIDTHbasicEnemy, HEIGHTbasicEnemy, 0.02f, Color.red, handler));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y, WIDTHbasicEnemy,HEIGHTbasicEnemy);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WIDTHbasicEnemy,HEIGHTbasicEnemy);
	}

}
