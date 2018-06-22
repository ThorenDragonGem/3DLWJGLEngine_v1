package graphics.datas;

import utils.maths.*;

public class CharDatas extends Datas
{
	private static final int VERTICES_PER_QUAD = 4;

	public CharDatas(float w, float h, float depth, Color3f color, float alpha, float tx, int index)
	{
		DIMENSIONS = new float[]
		{
				-w / 2, h / 2, depth, -w / 2, -h / 2, depth, w / 2, -h / 2, depth, w / 2, h / 2, depth
		};
		COLORS = new float[]
		{
				color.getR(), color.getG(), color.getB(), alpha, color.getR(), color.getG(), color.getB(), alpha,
				color.getR(), color.getG(), color.getB(), alpha, color.getR(), color.getG(), color.getB(), alpha
		};
		TEXTCOORDS = new float[]
		{
				tx, 0.0f, tx, 1.0f, tx, 1.0f, tx, 0.0f
		};
		INDICES = new int[] {};
	}
}
