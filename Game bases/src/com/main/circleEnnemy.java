package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("unused")
public class circleEnnemy extends GameObject{
	
	private float MinRadius;
	private float MaxRadius;
	private float radius;
	private boolean Growing=true;

	private Handler handler;
	public Ellipse2D Circle;
	private Player player;
	
	public circleEnnemy(float x, float y, ID id, Handler handler, float MaxRadius, float MinRadius,Player player) {
		super(x, y, id);
		velX=3;
		velY=3;
		this.handler= handler;
		this.MaxRadius=MaxRadius;
		this.MinRadius=MinRadius;
		this.radius=MinRadius;
		this.player=player;
	}
	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		
		if(radius>MaxRadius) Growing=false;
		if(radius<MinRadius) Growing=true;
		if(Growing==false)
		{
			radius--;
		}else if(Growing==true)
		{
			radius++;
		}
		if(x<=0||x+radius+20>Game.WIDTH)
		{
			velX*=-1;
		}
		if(y<=0||y+radius+15>Game.HEIGHT)
		{
			velY*=-1;
		}
		
		Circle = new Ellipse2D.Float(this.x,this.y,this.radius,this.radius);
		if(Circle.intersects(this.player.getBounds()))
		{
				HUD.HEALTH-=2;
		}
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d= (Graphics2D) g;
		g2d.setColor(Color.magenta);
		g2d.fill(Circle);
	}

	public Rectangle getBounds() {
		return null;
	}

	@Override
	public Area getBoundsArea() {
		return null;
	}
//	public static Ellipse2D getCircle() {
//		return Circle;
//	}
//	public static void setCircle(Ellipse2D circle) {
//		Circle = circle;
//	}

}
