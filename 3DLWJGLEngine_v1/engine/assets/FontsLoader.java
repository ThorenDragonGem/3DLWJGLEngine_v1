package assets;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import assets.FontsLoader.*;
import core.i.*;

public class FontsLoader extends SwingWorker<Integer, JFont> implements Loader
{
	private HashMap<String, Font> fonts;
	private final String[] EXTENSIONS = new String[]
	{
			"otf", "ttf", "otc", "ttc"
	};
	private boolean done, executed;
	
	public FontsLoader()
	{
		fonts = new HashMap<>();
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
		File userDir = new File(System.getProperty("user.dir") + "/assets/fonts/");
		return getFilesFonts(userDir, 0, 100);
	}
	
	public int getFilesFonts(File directory, double progressStart, double progressEnd)
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
					nb += getFilesFonts(f, progress, progress + step);
				}
				else
				{
					for(String s : EXTENSIONS)
					{
						if(f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase().contains(s))
						{
							try
							{
								Font font16 = Font.createFont(Font.TRUETYPE_FONT, f).deriveFont(16f);
								Font font20 = Font.createFont(Font.TRUETYPE_FONT, f).deriveFont(20f);
								Font font26 = Font.createFont(Font.TRUETYPE_FONT, f).deriveFont(26f);
								Font font30 = Font.createFont(Font.TRUETYPE_FONT, f).deriveFont(30f);
								publish(new JFont(f.getName().substring(0, f.getName().lastIndexOf(".")) + "16",
										font16));
								publish(new JFont(f.getName().substring(0, f.getName().lastIndexOf(".")) + "20",
										font20));
								publish(new JFont(f.getName().substring(0, f.getName().lastIndexOf(".")) + "26",
										font26));
								publish(new JFont(f.getName().substring(0, f.getName().lastIndexOf(".")) + "30",
										font30));
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
	protected void process(List<JFont> chunks)
	{
		for(JFont font : chunks)
		{
			fonts.put(font.getName(), font.getFont());
			font.clear();
		}
	}

	@Override
	protected void done()
	{
		setProgress(100);
		Assets.fonts = fonts;
		done = true;
	}

	public HashMap<String, Font> getFonts()
	{
		return fonts;
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
		finalize();
	}
	
	class JFont
	{
		private String name;
		private Font font;

		public JFont(String name, Font font)
		{
			this.name = name;
			this.font = font;
		}
		
		public String getName()
		{
			return name;
		}
		
		public Font getFont()
		{
			return font;
		}

		public void clear()
		{
			try
			{
				finalize();
			}
			catch(Throwable e)
			{
				e.printStackTrace();
			}
		}
	}
}
