package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.Random;

public class MenuParticle extends GameObject{
	
	private int WIDTHfastEnemy = 18;
	private int HEIGHTfastEnemy = 18;
	private Handler handler;
	private Color color;
	private Random r = new Random();
	

	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX=r.nextInt(9);
		velY=r.nextInt(9);
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
		
		
		color= new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		
		handler.addObject(new Trail(x, y, ID.Trail, WIDTHfastEnemy, HEIGHTfastEnemy, 0.05f, color, handler));
		
	}
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x,(int)y, WIDTHfastEnemy,HEIGHTfastEnemy);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,WIDTHfastEnemy,HEIGHTfastEnemy);
	}

	@Override
	public Area getBoundsArea() {
		// TODO Auto-generated method stub
		return null;
	}

}
