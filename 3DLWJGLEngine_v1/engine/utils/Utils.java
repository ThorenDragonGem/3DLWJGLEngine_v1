package utils;

import java.awt.image.*;
import java.io.*;
import java.nio.*;
import java.util.*;

import javax.imageio.*;

public class Utils
{
	public static float[] orderFloatArray(float[] array)
	{
		Arrays.sort(array);
		return array;
	}
	
	public static int[] orderIntArray(int[] array)
	{
		Arrays.sort(array);
		return array;
	}
	
	public static double[] orderDoubleArray(double[] array)
	{
		Arrays.sort(array);
		return array;
	}
	
	public static List<String> readAllLines(String fileName) throws Exception
	{
		List<String> list = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(Utils.class.getClass().getResourceAsStream(fileName))))
		{
			String line;
			while((line = br.readLine()) != null)
			{
				list.add(line);
			}
		}
		return list;
	}
	
	public static float[] listToArray(List<Float> list)
	{
		int size = list != null ? list.size() : 0;
		float[] floatArr = new float[size];
		for(int i = 0; i < size; i++)
		{
			floatArr[i] = list.get(i);
		}
		return floatArr;
	}
	
	public static ByteBuffer convertImageData(BufferedImage image, String imageFormatName)
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try
		{
			ImageIO.write(image, imageFormatName, out);
			return ByteBuffer.wrap(out.toByteArray());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
