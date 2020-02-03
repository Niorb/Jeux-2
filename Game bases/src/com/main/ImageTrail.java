package com.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class ImageTrail extends GameObject{
	
	
	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int width, height;
	private float life;
	private BufferedImage Image;
	
	//life = 0.001 0.1

	public ImageTrail(float x, float y, ID id,int width, int height,float life, Handler handler,BufferedImage Image) {
		super(x, y, id);
		this.handler=handler;
		this.width=width;
		this.height=height;
		this.life=life;
		this.Image=Image;
	}

	public Rectangle getBounds() {
		return null;
	}

	public void tick() {
		if(alpha>life)
		{
			alpha-=life-0.001f;
		}else handler.removeObject(this);
	}
	
	public AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));	
		g.setColor(color);
		g2d.drawImage(Image,(int) x,(int) y , null );
		g2d.setComposite(makeTransparent(1));
	}
	@Override
	public Area getBoundsArea() {
		// TODO Auto-generated method stub
		return null;
	}

}
