package core;

import assets.*;
import core.display.*;
import core.i.*;
import graphics.*;
import graphics.transforms.*;
import inputs.*;
import particles.*;
import physics.*;
import sounds.*;
import states.*;

public class Engine
{
	public static Instance assetsInstance, gameInstance, instance;
	
	public static GraphicEngine graphics;
	public static PhysicEngine physics;
	public static InputEngine inputs;
	public static SoundEngine sounds;
	public static ParticlesEngine particles;
	public static Assets assets;
	public static StatesManager states;
	
	private static GLFWDisplay display;
	private Camera camera;
	
	private double tickTime, renderTime, delta, alpha;
	private Timer timer;
	private Thread thread;
	
	public Engine(Instance instance)
	{
		// TODO
		assets = new Assets();
		
		// TODO
		states = new StatesManager();
		display = new GLFWDisplay();
		physics = new PhysicEngine();
		camera = new Camera();
		graphics = new GraphicEngine(camera);
		inputs = new InputEngine();
		sounds = new SoundEngine();
		particles = new ParticlesEngine();
		
		timer = new Timer();
		
		gameInstance = instance;
		Engine.instance = gameInstance;
		// Engine.instance = new AssetsInstance(assets);
		
		thread = new Thread(() ->
		{
			try
			{
				init();
				loop();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					cleanUp();
				}
				catch(Throwable e)
				{
					e.printStackTrace();
				}
			}
		});
		if(System.getProperty("os.name").contains("Mac"))
		{
			thread.run();
		}
		else
		{
			thread.start();
		}
	}
	
	public void init() throws Exception
	{
		display.init(true);
		inputs.init(display.getDisplayId());
		physics.init();
		graphics.init();
		if(instance != null)
		{
			instance.init();
		}
	}
	
	public void input()
	{
		physics.input(delta);
		graphics.input(delta);
		if(instance != null)
		{
			instance.input(delta);
		}
	}
	
	public void update()
	{
		inputs.update();
		physics.update(delta);
		graphics.update(delta);
		if(instance != null)
		{
			instance.update(delta);
		}
	}
	
	public void render()
	{
		physics.render(graphics);
		graphics.render(display, alpha);
		if(instance != null)
		{
			instance.render(graphics);
		}
	}
	
	public static void cleanUp()
	{
		try
		{
			physics.cleanUp();
			graphics.cleanup();
			if(instance != null)
			{
				instance.cleanUp();
			}
		}
		catch(Throwable e1)
		{
			e1.printStackTrace();
		}
		try
		{
			display.destroy();
			System.exit(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loop()
	{
		tickTime = 1000000000.0 / Config.maxUps;
		renderTime = 1000000000.0 / Config.maxFps;
		double updatedTime = 0.0;
		double renderedTime = 0.0;
		delta = 0;
		alpha = 0;
		
		int secondTime = 0;
		boolean second;
		int frames = 0;
		int ticks = 0;
		while(!display.shouldClose())
		{
			second = false;
			if(display.isVSync())
			{
				renderTime = 0;
			}
			delta = timer.getElapsed() - updatedTime;
			alpha = timer.getElapsed() - renderedTime;
			
			if(delta >= tickTime)
			{
				input();
				
				update();
				ticks++;
				
				secondTime++;
				if((secondTime % Config.maxUps) == 0)
				{
					second = true;
					secondTime = 0;
				}
				updatedTime += tickTime;
			}
			else if(alpha >= renderTime)
			{
				render();
				frames++;
				display.update();
				renderedTime += renderTime;
			}
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			if(second)
			{
				display.displayTitle(display.getTitle() + " | " + ticks + " tps, " + frames + " fps");
				// display.displayTitle(display.getTitle() + " | " + ticks + " tps, " + frames +
				// " fps | delta: "
				// + (delta / 1000000000) + ", alpha: " + (alpha / 1000000000));
				frames = 0;
				ticks = 0;
			}
		}
	}
	
}
