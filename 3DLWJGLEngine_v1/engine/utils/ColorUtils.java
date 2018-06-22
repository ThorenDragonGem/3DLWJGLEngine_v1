package utils;

import java.awt.*;

import utils.maths.*;

public class ColorUtils
{
	public static Color3f awtColorToColor3f(Color color)
	{
		return new Color3f(color.getRed() / 256f, color.getGreen() / 256f, color.getBlue() / 256f);
	}

	public static Color color3fToAwtColor(Color3f color)
	{
		return new Color(color.getR(), color.getG(), color.getB());
	}
}
