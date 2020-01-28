package com.main;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Spawner {
	
	private Handler handler;
	private HUD hud;
	private int regulator=100;
	private int Slower=200;
	private int Limitor=3;
	private GameObject gameObject;
	private boolean BossAlive=false;
	private boolean WeirdRoom=false;

	private Random r=new Random();
	
	private int ScoreKeep=0;
	public Spawner(Handler handler, HUD hud)
	{
		this.handler= handler;
		this.hud=hud;
	}
	
	public void tick()
	{
		
		ScoreKeep++;
		basicSpawn();
		Limitor=ThreadLocalRandom.current().nextInt(4, 25 + 1);;
		Boss1();
		Boss2();
		Boss3();
	}
	private void Boss3()
	{
		if(hud.getLevel()==3)
		{
			
			//faire un clearAll de liste 
			
				for(int i=0; i<handler.object.size();i++)
				{
					gameObject=handler.object.get(i);
					if(gameObject.getId()!=ID.Player&&gameObject.getId()!=ID.Boss3
							&&gameObject.getId()!=ID.Boss3Laser
							) {
						handler.removeObject(gameObject);
								}
				}
			
			if(BossAlive==false) {
				ScoreKeep=0;
				handler.addObject(new Boss3(Game.WIDTH/3,100, ID.Boss3 , handler));	
				BossAlive=true;
			}
			if(ScoreKeep>=10000)
			{
				hud.setLevel(12);
				ScoreKeep=regulator+1;
				Slower=200;
				Limitor=12;
				float xPlayer=0;
				float yPlayer=0;
				for(int i=0;i<handler.object.size();i++)
				{
					gameObject=handler.object.get(i);
					if(gameObject.getId()==ID.Player) 
					{
						 xPlayer= gameObject.x;
						 yPlayer= gameObject.y;
					}
				}
				handler.object.clear();
				handler.addObject(new Player(xPlayer, yPlayer, ID.Player, handler));
				BossAlive=false;
			}	
			}
	}
	
	private void Boss2()
	{
		if(hud.getLevel()==11)
		{
			if(BossAlive==false) {
				ScoreKeep=0;
				handler.addObject(new Boss2(Game.WIDTH/2,0, ID.Boss2 , handler));	
				BossAlive=true;
			}
			for(int i=0; i<handler.object.size();i++)
			{
				gameObject=handler.object.get(i);
				if(gameObject.getId()!=ID.Player&&gameObject.getId()!=ID.Boss2
						&&gameObject.getId()!=ID.Trail&&gameObject.getId()!=ID.Boss2Bullet) {
					handler.removeObject(gameObject);
				}
			}
			if(ScoreKeep>=500)
			{
				hud.setLevel(12);
				ScoreKeep=regulator+1;
				Slower=200;
				Limitor=12;
				float xPlayer=0;
				float yPlayer=0;
				for(int i=0;i<handler.object.size();i++)
				{
					gameObject=handler.object.get(i);
					if(gameObject.getId()==ID.Player) 
					{
						 xPlayer= gameObject.x;
						 yPlayer= gameObject.y;
					}
				}
				handler.object.clear();
				handler.addObject(new Player(xPlayer, yPlayer, ID.Player, handler));
				BossAlive=false;
			}	
			}
	}

	private void Boss1()

	{
		if(hud.getLevel()==5)
		{
			if(BossAlive==false) {
				ScoreKeep=0;
				handler.addObject(new Boss1(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Boss1,handler));	
				BossAlive=true;
			}
			
			for(int i=0; i<handler.object.size();i++)
			{
//				u = i;
				gameObject=handler.object.get(i);
				if(gameObject.getId()!=ID.Player&&gameObject.getId()!=ID.Boss1&&gameObject.getId()!=ID.Trail) {
					handler.removeObject(gameObject);
				}
			}
			if(ScoreKeep>=300)
			{
				hud.setLevel(6);
				ScoreKeep=regulator+1;
				Slower=200;
				Limitor=60;
				for(int i=0; i<handler.object.size();i++)
				{
					gameObject=handler.object.get(i);
					if(gameObject.getId()==ID.Boss1) {
						handler.removeObject(gameObject);
						BossAlive=false;
					}
				}
			}
			}
	}
	
	private void basicSpawn()
	{
		if(BossAlive==false)
		{	
			if(Limitor==3)
			{
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			}
		if(ScoreKeep>= regulator)
		{
			ScoreKeep=0;
			regulator=regulator*2;
			if(regulator>Slower)
				regulator=Slower/2;
			Slower+=50;
			hud.setLevel(hud.getLevel()+1);
			if(hud.getLevel()==7&&WeirdRoom==false) {
				hud.setLevel(6);
				WeirdRoom=true;
				}
//			if(Limitor%3==0)
				handler.addObject(new basicEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.basicEnnemy,handler));
			if(Limitor%4==0)
				handler.addObject(new fastEnemy(r.nextInt(Game.WIDTH-70), r.nextInt(Game.HEIGHT-70), ID.fastEnnemy,handler));
			if(Limitor%5==0) {
				handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH-70), r.nextInt(Game.HEIGHT-70), ID.smartEnnemy,handler));
				handler.addObject(new Healer(r.nextInt(Game.WIDTH-70), r.nextInt(Game.HEIGHT-70), ID.Healer,handler));
		}
			
		
	}
	
	
}
	}
}
