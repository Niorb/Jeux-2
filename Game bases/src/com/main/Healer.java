package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Healer extends GameObject{
	
	private int WidthHealer = 10;
	private int HeightHealer = 10;
	private Handler handler;
	

	public Healer(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX=8;
		velY=8;
		this.handler= handler;
	
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		if(y<=0 || y+HeightHealer+38>=Game.HEIGHT)
			velY*=-1;
		if(x<=0 || x+WidthHealer+18>=Game.WIDTH)
			velX*=-1;
		handler.addObject(new Trail(x, y, ID.Trail, WidthHealer, HeightHealer, 0.07f, Color.green, handler));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x,(int)y, WidthHealer,HeightHealer);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WidthHealer,HeightHealer);
	}

}
