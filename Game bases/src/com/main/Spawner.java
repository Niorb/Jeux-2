package com.main;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.main.Game.STATE;
@SuppressWarnings("static-access")
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
		if(Limitor==3)
		{
			handler.addObject(new Player1(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
		}
		ScoreKeep++;
		Boss1();
		Boss2();
		Boss3();
		Limitor=ThreadLocalRandom.current().nextInt(4, 25 + 1);;
		basicSpawn();
		
	}

	private void Boss3()
	{
		if(hud.getLevel()==16)
		{	
				for(int i=0; i<handler.object.size();i++)
				{
					gameObject=handler.object.get(i);
					if(gameObject.getId()!=ID.Player&&gameObject.getId()!=ID.Boss3
							&&gameObject.getId()!=ID.Boss3Laser&&gameObject.getId()!=ID.Trail
							) {
						handler.removeObject(gameObject);
						i--;
						}
				}
			
			if(BossAlive==false) {
				ScoreKeep=0;
				handler.addObject(new Boss3(Game.WIDTH/3,100, ID.Boss3 , handler));	
				BossAlive=true;
			}
			if(ScoreKeep>=1000)
			{
				hud.setLevel(17);
				ScoreKeep=regulator+1;
				Slower=200;
				Limitor=12;
				BossAlive=false;
				for(int i=0; i<handler.object.size();i++)
				{
					gameObject=handler.object.get(i);
					if(gameObject.getId()==ID.Boss3||gameObject.getId()==ID.Boss3Laser) {
						handler.removeObject(gameObject);
						i--;
						WeirdRoom=true;
						BossAlive=false;
					}
				}
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
					i--;
				}
			}
			if(ScoreKeep>=500)
			{
				hud.setLevel(12);
				WeirdRoom=false;
				ScoreKeep=regulator+1;
				Slower=200;
				Limitor=12;
				for(int i=0; i<handler.object.size();i++)
				{
					gameObject=handler.object.get(i);
					if(gameObject.getId()==ID.Boss2||gameObject.getId()==ID.Boss2Bullet) {
						handler.removeObject(gameObject);
						i--;
						BossAlive=false;
					}
				}
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
		GameObject tempObject;
		for(int i =0; i<handler.object.size();i++)
		{
			tempObject=handler.object.get(i);
			if(tempObject.getId()==ID.MenuParticle)
			{
				handler.removeObject(tempObject);
				i--;
			}	
		}
		if(BossAlive==false)
		{	
			
		if(ScoreKeep>= regulator)
		{
			ScoreKeep=0;
			regulator=regulator*2;
			if(regulator>Slower)
				regulator=Slower/2;
			Slower+=50;
			hud.setLevel(hud.getLevel()+1);
			if((hud.getLevel()==7||hud.getLevel()==13||hud.getLevel()==18)&&WeirdRoom==false) {
				hud.setLevel(hud.getLevel()-1);
				WeirdRoom=true;
			}
				handler.addObject(new basicEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.basicEnnemy,handler));
				
			if(Limitor%4==0)
				handler.addObject(new fastEnemy(r.nextInt(Game.WIDTH-70), r.nextInt(Game.HEIGHT-70), ID.fastEnnemy,handler));
			if(Limitor%5==0) {
				handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH-70), r.nextInt(Game.HEIGHT-70), ID.smartEnnemy,handler));
				handler.addObject(new Healer(r.nextInt(Game.WIDTH-70), r.nextInt(Game.HEIGHT-70), ID.Healer,handler));
				handler.addObject(new HealPack(r.nextInt(Game.WIDTH-70), r.nextInt(Game.HEIGHT-70), ID.HealPack,handler));
		}
	}	
}
		if(HUD.HEALTH<=0)
		{
			for(int i=0; i<handler.object.size();i++)
			{
				tempObject = handler.object.get(i);
				handler.removeObject(tempObject);
				i--;
			}
			hud.setLevel(1);
			ScoreKeep=0;
			regulator=100;
			Slower=200;
			Limitor=3;
			HUD.HEALTH=200;
			Game.setGameState(STATE.Menu);
			BossAlive=false;
		}
	}
}
