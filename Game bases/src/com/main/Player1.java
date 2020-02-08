package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player1 extends GameObject{
	
	Random r = new Random();
	Handler handler;
	private int CircleSpawner=0;
	public static int bonusVel=0;
	public static int sizePlayer=32;
	public static int getSizePlayer() {
		return sizePlayer;
	}
	public static void setSizePlayer(int sizePlayer) {
		Player1.sizePlayer = sizePlayer;
	}
	public static int getBonusVel() {
		return bonusVel;
	}
	public static void setBonusVel(int bonusVel) {
		Player1.bonusVel = bonusVel;
	}

	public static BufferedImage player_image;
	public static BufferedImage getPlayer_image() {
		return player_image;
	}
	public Player1(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		player_image = ss.grabImage(1, 1, 32, 32);
		
	}
	public void tick() {
		x+=velX;
		y+=velY;
		
		x=Game.limits(x,Game.WIDTH-50,0);
		y=Game.limits(y,Game.HEIGHT-70,0);
		
		CircleSpawner++;
		if(HUD.getLevel()%3==0&&CircleSpawner>=300)
		{	
			handler.addObject(new circleEnnemy(r.nextInt(Game.WIDTH-200), r.nextInt(Game.HEIGHT-200), ID.CircleEnnemy,handler, 60, 10, this));
			CircleSpawner=0;
		}
		handler.addObject(new Trail(x, y, ID.Trail, sizePlayer, sizePlayer, 0.05f, Color.blue, handler));
		
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
			}
			if(tempObject.getId()==ID.Healer&&getBounds().intersects(tempObject.getBounds()))
			{
				HUD.HEALTH+=2;	
			}
			if(tempObject.getId()==ID.Boss1&& getBounds().intersects(tempObject.getBounds()) )
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
			if(tempObject.getId()==ID.HealPack&&getBounds().intersects(tempObject.getBounds()))
			{
				HUD.HEALTH+=25;
				handler.removeObject(tempObject);
			}
				
			
			}
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.blue);
//		g.drawImage(player_image,(int) x, (int)y, null);
		g.fillRect((int) x, (int)y, sizePlayer, sizePlayer);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,sizePlayer, sizePlayer);
}	
	public Area getBoundsArea() {
		return null;
	}
}
