package com.main;

import java.awt.Color;
import java.awt.Graphics;

import com.main.Game.STATE;

public class HUD {
	
	public static float HEALTH= 100;
	
	private float greenValue=255;
	
	private int score=0;
	private int lastScoreInt=0;
	public static String lastScore="0";
	public static String getLastScore() {
		return lastScore;
	}
	public static void setLastScore(String lastScore) {
		HUD.lastScore = lastScore;
	}
	private int level=1;
	
	public void tick()
	{
		HEALTH= Game.limits((int)HEALTH,100, 0);
		greenValue= Game.limits((int)greenValue, 255, 0);
		greenValue=HEALTH*2;
		score++;
		if(HEALTH<=0)
		{
			lastScore=String.valueOf(score);
			score=0;
		}
	
	}
	public void render(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75,(int)greenValue,0));
		g.fillRect(15, 15, (int)HEALTH*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " +score,10,65);
		g.drawString("Level: " +level,10,85);
		
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
