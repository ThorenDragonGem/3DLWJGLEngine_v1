package assets;

import java.io.*;
import java.util.*;

import javax.swing.*;

import core.i.*;
import graphics.textures.*;

public class ImagesLoader extends SwingWorker<Integer, Texture> implements Loader
{
	private HashMap<String, Texture> textures;
	private final String[] EXTENSIONS = new String[]
	{
			"png", "jpg", "bmp"
	};
	private boolean done, executed;

	public ImagesLoader()
	{
		textures = new HashMap<>();
		executed = false;
		done = false;
		
		// addPropertyChangeListener(evt ->
		// {
		// if(evt.getPropertyName().equals("progress"))
		// {
		// progress = (int) evt.getNewValue();
		// }
		// });
	}

	@Override
	protected Integer doInBackground() throws Exception
	{
		File userDir = new File(System.getProperty("user.dir") + "/assets/textures/");
		System.err.println(true);
		return getFilesImages(userDir, 0, 100);
	}

	public int getFilesImages(File directory, double progressStart, double progressEnd)
	{
		File[] files = directory.listFiles();
		int nb = 0;
		if(files.length > 0)
		{
			double step = (progressEnd - progressStart) / files.length;

			for(int i = 0; i < files.length; i++)
			{
				File f = files[i];
				double progress = progressStart + (i * step);
				setProgress(Math.min((int) progress, 100));

				if(f.isDirectory())
				{
					nb += getFilesImages(f, progress, progress + step);
				}
				else
				{
					for(String s : EXTENSIONS)
					{
						if(f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase().contains(s))
						{
							try
							{
								// publish(new Texture(f.getName().substring(0, f.getName().lastIndexOf(".")),
								// ImageIO.read(f),
								// f.getName().substring(f.getName().lastIndexOf(".") + 1,
								// f.getName().length())));
								publish(new Texture(f.getName().substring(0, f.getName().lastIndexOf(".")), f));
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
		return nb;
	}
	
	@Override
	protected void process(List<Texture> chunks)
	{
		for(Texture t : chunks)
		{
			textures.put(t.getName(), t);
		}
	}

	@Override
	protected void done()
	{
		setProgress(100);
		Assets.textures = textures;
		done = true;
	}
	
	public HashMap<String, Texture> getTextures()
	{
		return textures;
	}
	
	@Override
	public boolean isFinished()
	{
		return done;
	}
	
	@Override
	public boolean isExecuted()
	{
		return executed;
	}
	
	@Override
	public void executeLoader()
	{
		execute();
		executed = true;
	}

	@Override
	public void cleanUp() throws Throwable
	{
		for(Texture t : textures.values())
		{
			t.cleanUp();
		}
		finalize();
	}
}
