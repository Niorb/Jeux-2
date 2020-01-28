package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{
	
	//Score monte en pause
	
	private int WidthMenu=200;
	private int HeightMenu=64;
	private String play = "Play";
	private	String help = "Help";
	private	String quit = "Quit";
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(clickedOnWindow(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4-HeightMenu/2, WidthMenu, HeightMenu, mx, my))
		{
			Game.setGameState(Game.STATE.Game);
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}
	public boolean clickedOnWindow(int x, int y, int widthFrame, int heightFrame, int xMouse, int yMouse)
	{
		if(xMouse>x&&xMouse<x+widthFrame&&yMouse>y&&yMouse<y+heightFrame)
			return true;
		else 
			return false;
	}

	
	public void tick()
	{
		
	}
	public void render(Graphics g)
	{
		
		Font fnt = new Font("arial", 1, 40);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setColor(Color.white);
		g.setFont(fnt);
		g.drawString("Menu", Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4-HeightMenu);
		g.setFont(fnt2);
		g.drawRect(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4-HeightMenu/2, WidthMenu, HeightMenu);
		g.drawString(play,Game.WIDTH/2-play.length()*10, Game.HEIGHT/4+play.length()*2);
		
		g.setColor(Color.yellow);
		g.drawRect(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/2-HeightMenu/2, WidthMenu, HeightMenu);
		g.drawString(help,Game.WIDTH/2-help.length()*10, Game.HEIGHT/2+help.length()*2);
		
		g.setColor(Color.cyan);
		g.drawRect(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4*3-HeightMenu/2, WidthMenu, HeightMenu);
		g.drawString(quit,Game.WIDTH/2-quit.length()*10, Game.HEIGHT/4*3+quit.length()*2);
	}
	
	

}
