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
	
	private Random r;
	
	private boolean running = false;
	
	public Game()
	{
		handler= new Handler();
		
		this.addKeyListener(new KeyInput(handler) );
		new Window(WIDTH, HEIGHT, "Let's build a Game !", this);
		r = new Random();
		handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player));
		handler.addObject(new Player(WIDTH/2-20, HEIGHT/2-32, ID.Player2));
		for(int u =0; u<10 ; u++)
			handler.addObject(new basicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.basicEnemy));
	}
	
	private void tick()
	{
		handler.tick();
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
		
		g.setColor(Color.green);
		g.fillRect(0,0, WIDTH,HEIGHT);
		handler.render(g);
		g.dispose();
		bs.show();
	}

	public static int limits(int var, int max, int min)
	{
		System.out.println(var);
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
