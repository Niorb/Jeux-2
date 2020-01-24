package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();

	public Player(int x, int y, ID id) {
		super(x, y, id);
		
	}
	public void tick() {
		x+= velX;
		y+=velY;
		
		x=Game.limits(x,Game.WIDTH-35,0);
		y=Game.limits(y,Game.HEIGHT-59,0);
		
	}
	public void render(Graphics g) {
		if(id==ID.Player)
			g.setColor(Color.blue);
		else if (id==ID.Player2)
			g.setColor(Color.red);
		
		g.fillRect(x, y, 32, 32);
		
	}

}
