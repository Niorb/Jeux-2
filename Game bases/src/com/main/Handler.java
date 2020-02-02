package com.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick()
	{
		for(int i = 0 ; i<object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
		
	}

	public void render(Graphics g)
	{
		for(int i = 0 ; i<object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
		
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	public void clearEnnemies()
	{
		for(int i =0; i<this.object.size();i++)
		{
			GameObject tempObject = this.object.get(i);
			if(tempObject.getId()!=ID.Player)
			{
				this.removeObject(tempObject);
			}
		}
	}
}
