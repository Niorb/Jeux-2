package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class basicEnemy extends GameObject{
	
	private int WIDTHbasicEnemy = 20;
	private int HEIGHTbasicEnemy = 20;
	

	public basicEnemy(int x, int y, ID id) {
		super(x, y, id);
		velX=5;
		velY=5;
	
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		if(y<=0 || y+HEIGHTbasicEnemy+25>=Game.HEIGHT)
			velY*=-1;
		if(x<=0 || x+WIDTHbasicEnemy>=Game.WIDTH)
			velX*=-1;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x,y, WIDTHbasicEnemy,HEIGHTbasicEnemy);
		
	}

}
