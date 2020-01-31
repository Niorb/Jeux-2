package com.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.util.Random;
@SuppressWarnings("unused")
public class Boss3Laser3 extends GameObject{
	
	private int WidthLaser;
	private int HeightLaser;
	private double theta;
	public float xRotationCenter;
	public float yRotationCenter;
	private int counter=0;

	public static Rectangle Boss3Laser;
	
	private static Shape Line;
	
	public static Shape Boss3LaserShape;
	
	public static float xLaser;
	public static float yLaser;
	
	public static Line2D FinalLine;
;	public static float getxLaser() {
		return xLaser;
	}
	public static float getyLaser() {
		return yLaser;
	}
	
	public static Rectangle getBoss3Laser() {
		return Boss3Laser;
	}
	public static void setBoss3Laser(Rectangle boss3Laser) {
		Boss3Laser = boss3Laser;
	}

	private Handler handler;
	
	private Random r=new Random();
	

	public Boss3Laser3(float x, float y, ID id, Handler handler, int WidthLaser, int HeightLaser, double theta, float xRotationCenter, float yRotationCenter) {
		super(x, y, id);
		this.WidthLaser=WidthLaser;
		this.HeightLaser=HeightLaser;
		this.handler= handler;
		this.theta=theta;
		this.xRotationCenter=xRotationCenter;
		this.yRotationCenter=yRotationCenter;
	}
	public void tick() 
	{
		
	}
	public AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}
	
	public void render(Graphics g) {
		
		try {
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform old = g2d.getTransform();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(theta), xRotationCenter, yRotationCenter);
			g2d.setComposite(makeTransparent(0.5f));
			g2d.setColor(Color.red);
			Shape Boss3LaserShape = new Rectangle((int)x,(int)y, WidthLaser,HeightLaser);
			Boss3Laser = new Rectangle((int)x,(int)y, WidthLaser,HeightLaser);
			Boss3LaserShape = at.createTransformedShape(Boss3LaserShape);
			g2d.fill(Boss3LaserShape);
			Line = new Line2D.Double((int)x,(int)y, Boss3Laser.getMaxX(),Boss3Laser.getMaxY());
			Line = at.createTransformedShape(Line);
			g2d.setTransform(old);
			g2d.draw(Line);
			g2d.setComposite(makeTransparent(1f));
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}
	
	public static double getMaxXLaser()
	{
		return Boss3Laser.getMaxX();
	}
	public static double getMaxYLaser()
	{
		return Boss3Laser.getMaxY();
	}
	

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, WidthLaser,HeightLaser);
	}
//	public boolean IsPolygonsIntersecting(Polygon, Polygon b)
//	{
//	    foreach (var polygon in new[] { a, b })
//	    {
//	        for (int i1 = 0; i1 < polygon.Points.Count; i1++)
//	        {
//	            int i2 = (i1 + 1) % polygon.Points.Count;
//	            var p1 = polygon.Points[i1];
//	            var p2 = polygon.Points[i2];
//
//	            var normal = new Point(p2.Y - p1.Y, p1.X - p2.X);
//
//	            double? minA = null, maxA = null;
//	            foreach (var p in a.Points)
//	            {
//	                var projected = normal.X * p.X + normal.Y * p.Y;
//	                if (minA == null || projected < minA)
//	                    minA = projected;
//	                if (maxA == null || projected > maxA)
//	                    maxA = projected;
//	            }
//
//	            double? minB = null, maxB = null;
//	            foreach (var p in b.Points)
//	            {
//	                var projected = normal.X * p.X + normal.Y * p.Y;
//	                if (minB == null || projected < minB)
//	                    minB = projected;
//	                if (maxB == null || projected > maxB)
//	                    maxB = projected;
//	            }
//
//	            if (maxA < minB || maxB < minA)
//	                return false;
//	        }
//	    }
//	    return true;
//	}
	
	public static Shape getLine() {
		return Line;
	}
	
	public Area getBoundsArea()
	{
		return null;
	}

}
