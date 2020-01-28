package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	private int speed=5;
	private int finalXSpeed;
	private int finalYSpeed;
	
	public KeyInput(Handler handler) {
		this.handler= handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key== KeyEvent.VK_ESCAPE)
			System.exit(1);
		
		for (int i=0; i<handler.object.size(); i++)
		{
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ID.Player)
			{
				if(key==KeyEvent.VK_D) {
					tempObject.setVelX(finalXSpeed=speed);}
				if(key==KeyEvent.VK_Q) {
					tempObject.setVelX(finalXSpeed=-speed);}
				if(key==KeyEvent.VK_S) {
					tempObject.setVelY(finalYSpeed=speed);}
				if(key==KeyEvent.VK_Z) {
					tempObject.setVelY(finalYSpeed=-speed);}
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
