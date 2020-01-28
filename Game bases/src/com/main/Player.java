package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	
	Handler handler;
	

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
	}
	public void tick() {
		x+= velX;
		y+=velY;
		
		x=Game.limits(x,Game.WIDTH-50,0);
		y=Game.limits(y,Game.HEIGHT-70,0);
		
		handler.addObject(new Trail(x, y, ID.Trail, 32, 32, 0.08f, Color.blue, handler));
		
		collision();
		
	}
	public void collision()
	{
		for(int i=0; i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.basicEnnemy||tempObject.getId()==ID.fastEnnemy
					||tempObject.getId()==ID.smartEnnemy||tempObject.getId()==ID.Boss2Bullet
					||tempObject.getId()==ID.Boss3Laser)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH-=2;
				}
			}else if(tempObject.getId()==ID.Healer)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH+=2;
				}
			}else if(tempObject.getId()==ID.Boss1)
				if(getBounds().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH=0;
				}
		}
	}
	public void render(Graphics g) {
		if(id==ID.Player)
			g.setColor(Color.blue);
		
		g.fillRect((int)x,(int)y, 32, 32);
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

}
