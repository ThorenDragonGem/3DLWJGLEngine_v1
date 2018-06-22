package assets;

import java.awt.*;
import java.util.*;

import graphics.textures.*;

public class Assets
{
	protected static HashMap<String, Texture> textures;
	protected static HashMap<String, Font> fonts;
	
	private ImagesLoader imagesLoader;
	private FontsLoader fontsLoader;
	
	// TODO: sounds...
	public Assets()
	{
		imagesLoader = new ImagesLoader();
		textures = new HashMap<>();
		fontsLoader = new FontsLoader();
		fonts = new HashMap<>();
	}
	
	public static Texture getTexture(String name)
	{
		return textures.get(name);
	}
	
	public static Font getFont(String name)
	{
		return fonts.get(name);
	}
	
	public boolean isDone()
	{
		return imagesLoader.isDone() && fontsLoader.isDone();
	}
	
	public void cleanUp() throws Throwable
	{
		imagesLoader.cleanUp();
		fontsLoader.cleanUp();
	}
	
	public int getProgress()
	{
		return imagesLoader.getProgress();
	}
	
	public boolean isAllExecuted()
	{
		return imagesLoader.isExecuted() && fontsLoader.isExecuted();
	}
	
	public ImagesLoader getImagesLoader()
	{
		return imagesLoader;
	}
	
	public FontsLoader getFontsLoader()
	{
		return fontsLoader;
	}
}
