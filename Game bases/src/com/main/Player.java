package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	private int CircleSpawner=0;
	
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
		
		CircleSpawner++;
		if(HUD.getLevel()%3==0&&CircleSpawner>=300)
		{	
			handler.addObject(new circleEnnemy(r.nextInt(Game.WIDTH-200), r.nextInt(Game.HEIGHT-200), ID.CircleEnnemy,handler, 60, 10, this));
			CircleSpawner=0;
		}
		
		collision();		
	}
	public void collision()
	{
		for(int i=0; i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			if((tempObject.getId()==ID.basicEnnemy||tempObject.getId()==ID.fastEnnemy
					||tempObject.getId()==ID.smartEnnemy||tempObject.getId()==ID.Boss2Bullet
					||tempObject.getId()==ID.Boss3      )
					&&getBounds().intersects(tempObject.getBounds())
					)
			{
					HUD.HEALTH-=2;
			}else if(tempObject.getId()==ID.Healer&&getBounds().intersects(tempObject.getBounds()))
			{
				HUD.HEALTH+=2;	
			}else if(tempObject.getId()==ID.Boss1&& getBounds().intersects(tempObject.getBounds()) )
				{
					HUD.HEALTH-=10;
				}
			try {
				if((tempObject.getId()==ID.Boss3Laser&&(Boss3Laser.getLine().intersects(getBounds())||Boss3Laser2.getLine().intersects(getBounds())||
						Boss3Laser3.getLine().intersects(getBounds())||Boss3Laser4.getLine().intersects(getBounds())
					)))
				{
					 	HUD.HEALTH-=1;
				}
			} catch (Exception e) {
			}
		}
	}
	
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(id==ID.Player)
			g2d.setColor(Color.blue);
		g2d.fillRect((int)x,(int)y, 32, 32);
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
}

	public Rectangle getBounds2() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public Area getBoundsArea() {
		return null;
	}
}
