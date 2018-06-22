package tests;

import java.io.*;
import java.util.*;

import org.lwjgl.glfw.*;

import core.*;
import core.i.*;
import graphics.*;
import graphics.datas.*;
import graphics.models.*;
import graphics.sprites.*;
import graphics.textures.*;

public class GameLauncher implements Instance
{
	private SpriteProgram program;
	private Sprite sprite;

	public static void main(String[] args)
	{
		new Engine(new GameLauncher());
	}
	
	public GameLauncher()
	{

	}

	@Override
	public void init() throws Exception
	{
		System.out.println("Game Launcher instance");
		program = new SpriteProgram();
		Engine.graphics.getPrograms().add(program);
		program.init();
		System.out.println(Engine.assets);
		HashMap<String, Texture> textures = new HashMap<>();
		System.out.println(System.currentTimeMillis());
		for(File f : getFilesImages(new File(System.getProperty("user.dir") + "/assets/textures/"), 0, 100))
		{
			textures.put(f.getName().substring(0, f.getName().lastIndexOf(".")),
					new Texture(f.getName().substring(0, f.getName().lastIndexOf(".")), f));
		}
		System.out.println(System.currentTimeMillis());
		QuadDatas datas = new QuadDatas(1, 1, 0.5f, 0.5f, 0, 0, 0, 1);
		sprite = new Sprite(new QuadModel(datas, textures.get("texture")));
		sprite.setPosition(0, 0, -2);
		program.sprites.add(sprite);
	}
	
	public List<File> getFilesImages(File directory, double progressStart, double progressEnd)
	{
		List<File> res = new ArrayList<>();
		File[] files = directory.listFiles();
		int nb = 0;
		if(files.length > 0)
		{
			double step = (progressEnd - progressStart) / files.length;

			for(int i = 0; i < files.length; i++)
			{
				File f = files[i];
				double progress = progressStart + (i * step);

				if(f.isDirectory())
				{
					getFilesImages(f, progress, progress + step);
				}
				else
				{
					for(String s : new String[]
					{
							"png"
					})
					{
						if(f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase().contains(s))
						{
							try
							{
								// publish(new Texture(f.getName().substring(0, f.getName().lastIndexOf(".")),
								// ImageIO.read(f),
								// f.getName().substring(f.getName().lastIndexOf(".") + 1,
								// f.getName().length())));
								// publish(new Texture(f.getName().substring(0, f.getName().lastIndexOf(".")),
								// f));
								res.add(f);
								
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return res;
	}

	@Override
	public void input(double delta)
	{
		if(Engine.inputs.isKeyPressed(GLFW.GLFW_KEY_ESCAPE))
		{
			Engine.cleanUp();
		}
	}

	@Override
	public void update(double delta)
	{
		
	}

	@Override
	public void render(GraphicEngine g)
	{
	}

	@Override
	public void cleanUp() throws Throwable
	{
	}
}
