package graphics.textures;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

import javax.imageio.*;

import graphics.fonts.*;

public class FontTexture
{
	private static final String IMAGE_FORMAT = "png";
	private final Font font;
	private final String charSetName;
	private final HashMap<Character, CharInfo> charMap;
	private Texture texture;
	private int width;
	private int height;

	public FontTexture(Font font, String charSetName) throws Exception
	{
		this.font = font;
		this.charSetName = charSetName;
		charMap = new HashMap<>();

		buildTexture();
	}

	private String getAllAvailableChars(String charsetName)
	{
		CharsetEncoder ce = Charset.forName(charsetName).newEncoder();
		StringBuilder result = new StringBuilder();
		for(char c = 0; c < Character.MAX_VALUE; c++)
		{
			if(ce.canEncode(c))
			{
				result.append(c);
			}
		}
		return result.toString();
	}

	private void buildTexture() throws Exception
	{
		// Get the font metrics for each character for the selected font by
		// using image
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = img.createGraphics();
		g2D.setFont(font);
		FontMetrics fontMetrics = g2D.getFontMetrics();

		String allChars = getAllAvailableChars(charSetName);
		width = 0;
		height = 0;
		for(char c : allChars.toCharArray())
		{
			if(c == 'i')
			{
				width -= 1;
			}
			// Get the size for each character and update global image size
			CharInfo charInfo = new CharInfo(width, fontMetrics.charWidth(c));
			charMap.put(c, charInfo);
			width += charInfo.getWidth();
			height = Math.max(height, fontMetrics.getHeight());
		}
		g2D.dispose();

		// Create the image associated to the charset
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2D = img.createGraphics();
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setFont(font);
		fontMetrics = g2D.getFontMetrics();
		g2D.setColor(Color.WHITE);
		g2D.drawString(allChars, 0, fontMetrics.getAscent());
		g2D.dispose();

		// Dump image to a byte buffer
		InputStream is;
		try(ByteArrayOutputStream out = new ByteArrayOutputStream())
		{
			ImageIO.write(img, IMAGE_FORMAT, out);
			out.flush();
			is = new ByteArrayInputStream(out.toByteArray());
		}
		// TODO ?
		texture = new Texture(font.getFontName() + "_" + font.getSize(), is);
	}

	public Texture getTexture()
	{
		return texture;
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}

	public CharInfo getCharInfo(char c)
	{
		return charMap.get(c);
	}
	
	public Font getFont()
	{
		return font;
	}
}
