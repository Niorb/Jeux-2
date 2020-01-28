package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class fastEnemy extends GameObject{
	
	private int WIDTHfastEnemy = 15;
	private int HEIGHTfastEnemy = 15;
	private Handler handler;
	

	public fastEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX=2;
		velY=9;
		this.handler= handler;
	
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		if(y<=0 || y+HEIGHTfastEnemy+38>=Game.HEIGHT)
			velY*=-1;
		if(x<=0 || x+WIDTHfastEnemy+18>=Game.WIDTH)
			velX*=-1;
		handler.addObject(new Trail(x, y, ID.Trail, WIDTHfastEnemy, HEIGHTfastEnemy, 0.02f, Color.yellow, handler));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x,(int)y, WIDTHfastEnemy,HEIGHTfastEnemy);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,WIDTHfastEnemy,HEIGHTfastEnemy);
	}

}
