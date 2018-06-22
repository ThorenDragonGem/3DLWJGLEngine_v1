package utils;

import java.io.InputStream;
import java.util.Scanner;

public class Loader
{
	public static String loadResource(String fileName) throws Exception
	{
		String result = "";
		try(InputStream in = Loader.class.getClass().getResourceAsStream(fileName))
		{
			result = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
		}
		return result;
	}
}
