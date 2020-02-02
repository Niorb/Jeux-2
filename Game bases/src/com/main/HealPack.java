package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;

public class HealPack extends GameObject{
	
	private int WIDTHbasicEnemy = 20;
	private int HEIGHTbasicEnemy = 20;
	private Handler handler;
	

	public HealPack(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler= handler;
	
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x,(int)y, WIDTHbasicEnemy,HEIGHTbasicEnemy);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WIDTHbasicEnemy,HEIGHTbasicEnemy);
	}

	@Override
	public Area getBoundsArea() {
		// TODO Auto-generated method stub
		return null;
	}

}
