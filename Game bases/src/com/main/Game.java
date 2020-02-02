package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 4088146271165387233L;
	
	public static final int WIDTH = 640, HEIGHT= WIDTH /12*9;
	
	private Thread thread;
	private Handler handler;
	private HUD hud;
	private Menus menus;
	private int i = 0;
	
	private Spawner spawner;

	private Random r;
	
	private boolean running = false;
	
	enum STATE {
		Game,
		Pause,
		Help,
		Menu;
	}
	public static STATE gameState = STATE.Menu;
	public static STATE getGameState() {
		return gameState;
	}
	public static void setGameState(STATE gameState) {
		Game.gameState = gameState;
	}
	
	public Game()
	{
		handler= new Handler();
		menus = new Menus();
		
		hud = new HUD();
		spawner = new Spawner(handler, hud);
		this.addMouseListener(menus);
		this.addKeyListener(new KeyInput(handler) );
		
		new Window(WIDTH, HEIGHT, "Let's build a Game !", this);
		r = new Random();
	}
	private void tick()
	{
		if(gameState==STATE.Game)
		{
			hud.tick();
			spawner.tick();
			handler.tick();
		}else if(gameState==STATE.Menu||gameState==STATE.Help)
		{
			handler.tick();
			menus.tick();
			for(;i<15;i++)
			{
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
			}
		}else
		{
			i=0;
			menus.tick();
		}
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,0, WIDTH,HEIGHT);
		handler.render(g);
		if(gameState==STATE.Game) {
			hud.render(g);
			}
		else
			{
			menus.render(g);
			}
		g.dispose();
		bs.show();
	}

	public static float limits(float var, int max, int min)
	{
		if(var<=min)
			return min;
		else if(var>= max)
			return max;
		else return var;
	}
	public static void main(String[] args) {
		new Game();

	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running=true;
		
	}
	public synchronized void stop()
	{
		try {
			thread.join();
			running=false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime= System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta+= ( now-lastTime)/ns;
			lastTime=now;
			while(delta>=1)
			{
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis()-timer > 1000) 
			{
				timer+=1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
	}

}
