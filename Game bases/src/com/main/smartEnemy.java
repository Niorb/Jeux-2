package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class smartEnemy extends GameObject{
	
	private int WIDTHsmartEnemy = 12;
	private int HEIGHTsmartEnemy = 12;
	private Handler handler;
	private GameObject Player;
	private float speed=2.0f;
	

	public smartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		for(int i =0; i<handler.object.size(); i++) 
			if(handler.object.get(i).getId()==ID.Player) 
				Player = handler.object.get(i);
		
		this.handler= handler;
	
	}
	public void tick() {
		
		x+=velX;
		y+=velY;
		
		float diffX= x - Player.getX() - 12;
		float diffY= y - Player.getY() -12;
		
		float distance= (float) (Math.sqrt((x-Player.getX()) * (x-Player.getX()) + (y-Player.getY()) * (y-Player.getY())));
		
		velX=(-1/distance)*speed*diffX;
		velY=(-1/distance)*speed*diffY;
		
		if(velX>speed*5/4)
			velX= speed*5/4;
		else if (velX<-speed*5/4)
			velX=-speed*5/4;
		if(velY>speed*5/4)
			velY= speed*5/4;
		else if (velY<-speed*5/4)
			velY=-speed*5/4;
		
		handler.addObject(new Trail(x, y, ID.Trail, WIDTHsmartEnemy, HEIGHTsmartEnemy, 0.06f, Color.cyan, handler));
		
	}
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int)x,(int)y, WIDTHsmartEnemy,HEIGHTsmartEnemy);
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,WIDTHsmartEnemy,HEIGHTsmartEnemy);
	}

}
