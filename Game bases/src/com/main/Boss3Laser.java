package com.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
@SuppressWarnings("unused")
public class Boss3Laser extends GameObject{
	
	private int WidthLaser;
	private int HeightLaser;
	
	private Handler handler;
	
	private Random r=new Random();
	

	public Boss3Laser(float x, float y, ID id, Handler handler, int WidthLaser, int HeightLaser) {
		super(x, y, id);
		this.WidthLaser=WidthLaser;
		this.HeightLaser=HeightLaser;
//		velX=0;
//		velY=0;
		this.handler= handler;
	}
	public void tick() {
//		x+=velX;
//		y+=velY;
//		if(y<=0)
//			velY*=-1;
//		else if(y+HeightLaser>=Game.HEIGHT)
//			handler.removeObject(this);
//		if(x<=0) 
//			velX*=-1;
//		else if(x+WidthLaser>= Game.WIDTH)
//			handler.removeObject(this);

	}
	public AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(0.5f));
		g2d.setColor(Color.red);
		g2d.fillRect((int)x,(int)y, WidthLaser,HeightLaser);
		g2d.setComposite(makeTransparent(1f));
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WidthLaser,HeightLaser);
	}

}
