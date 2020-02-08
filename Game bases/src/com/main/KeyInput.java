package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.main.Game.STATE;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	private int speed=5;
	private int finalXSpeed;
	public boolean inMenu=true;
	private int finalYSpeed;

	public KeyInput(Handler handler) {
		this.handler= handler;
		
	}

	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(Game.getGameState()== STATE.Menu) inMenu=true;
		
		if (key==KeyEvent.VK_ESCAPE)
			if(Game.getGameState()== STATE.Menu)
			{
				System.exit(1);
			}else if(Game.getGameState()==STATE.Game)
			{
				Game.setGameState(STATE.Pause);
				inMenu=false;
			}else if(Game.getGameState()==STATE.Pause||Game.getGameState()==STATE.Shop)
			{
				System.exit(1);
			}else if(Game.getGameState()==STATE.Help)
				if(inMenu)
					Game.setGameState(STATE.Menu);
				else 
					Game.setGameState(STATE.Pause);
		
		for (int i=0; i<handler.object.size(); i++)
		{
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ID.Player)
			{
				if(key==KeyEvent.VK_D) {
					tempObject.setVelX(finalXSpeed=speed+Player1.getBonusVel());}
				if(key==KeyEvent.VK_Q) {
					tempObject.setVelX(finalXSpeed=-(speed+Player1.getBonusVel()));}
				if(key==KeyEvent.VK_S) {
					tempObject.setVelY(finalYSpeed=speed+Player1.getBonusVel());}
				if(key==KeyEvent.VK_Z) {
					tempObject.setVelY(finalYSpeed=-(speed+Player1.getBonusVel()));}
				if(key==KeyEvent.VK_ENTER)
				{
					HUD.HEALTH=200;
				}
			}
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for (int i=0; i<handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ID.Player)
			{
				if(key==KeyEvent.VK_D) {
					if(finalXSpeed>0)
						tempObject.setVelX(0);}
				if(key==KeyEvent.VK_Q) {
					if(finalXSpeed<0)
						tempObject.setVelX(0);}
				if(key==KeyEvent.VK_S) {
					if(finalYSpeed>0)
						tempObject.setVelY(0);}
				if(key==KeyEvent.VK_Z) {
					if(finalYSpeed<0)
						tempObject.setVelY(0);}
				
			}
		}
	}

}
