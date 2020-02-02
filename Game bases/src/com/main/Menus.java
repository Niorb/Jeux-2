package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.main.Game.STATE;

public class Menus extends MouseAdapter{
	
	private int WidthMenu=200;
	private int HeightMenu=64;
	private String play = "Play";
	private String pause = "Pause";
	private	String help = "Help";
	private	String quit = "Quit";
	private String resume= "Resume";
	private String menu = "Menu";
	private String PlayerEpl = "You...";
	private Handler handler;
	private Random r = new Random();
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		//PAUSE
		if(clickedOnWindow(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4-HeightMenu/2, WidthMenu, HeightMenu, mx, my)&&Game.getGameState()==STATE.Pause)
		{
			Game.setGameState(Game.STATE.Game);
		}
		//MENU DE BASE
		else if(clickedOnWindow(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4-HeightMenu/2, WidthMenu, HeightMenu, mx, my)&&Game.getGameState()==STATE.Menu)
		{
			Game.setGameState(Game.STATE.Game);
			HUD.HEALTH=200;
		}
		//QUIT
		else if(clickedOnWindow(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4*3-HeightMenu/2, WidthMenu, HeightMenu, mx, my))
		{
			System.exit(1);
		}
		if(clickedOnWindow(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/2-HeightMenu/2, WidthMenu, HeightMenu, mx, my))
		{
			Game.setGameState(STATE.Help);
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
		Font fnt3 = new Font("arial", 1, 20);
		
		if(Game.getGameState()==STATE.Help)
		{
			g.setColor(Color.gray);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawString(help, Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4-HeightMenu);
			
			g.setFont(fnt3);
			g.setColor(Color.blue);
			g.fillRect(Game.WIDTH/4, Game.HEIGHT/6, 32, 32);
			g.drawString(PlayerEpl, Game.WIDTH/4+40, Game.HEIGHT/6+32);
			
		}else {
			g.setColor(Color.white);
			g.setFont(fnt3);
			g.drawString("Last score : "+HUD.getLastScore(), 5, 20);
			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4-HeightMenu/2, WidthMenu, HeightMenu);
			//PAUSE
			if(Game.getGameState()==STATE.Pause)
			{
				g.setFont(fnt);
				g.drawString(pause, Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4-HeightMenu);
				g.setFont(fnt2);
				g.drawString(resume,Game.WIDTH/2-resume.length()*10, Game.HEIGHT/4+resume.length()*2);
			}
			//MENU DE BASE
			else if(Game.getGameState()==STATE.Menu)
			{
				g.setFont(fnt);
				g.drawString(menu, Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4-HeightMenu);
				g.setFont(fnt2);
				g.drawString(play,Game.WIDTH/2-play.length()*10, Game.HEIGHT/4+play.length()*2);
				
			}
			//HELP
			g.setColor(Color.yellow);
			g.drawRect(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/2-HeightMenu/2, WidthMenu, HeightMenu);
			g.drawString(help,Game.WIDTH/2-help.length()*10, Game.HEIGHT/2+help.length()*2);
			
			//QUIT
			g.setColor(Color.cyan);
			g.drawRect(Game.WIDTH/2-WidthMenu/2, Game.HEIGHT/4*3-HeightMenu/2, WidthMenu, HeightMenu);
			g.drawString(quit,Game.WIDTH/2-quit.length()*10, Game.HEIGHT/4*3+quit.length()*2);
			
		}
		
	}
	
	

}
