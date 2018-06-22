package graphics.datas;

import utils.maths.*;

public class QuadDatas extends Datas
{
	/**
	 * Creates datas for a cooloured quad
	 *
	 * @param w the width of the quad
	 * @param h the height of the quad
	 * @param x4VecColours the 4 edges Vector3f colours
	 * @param x4Depth the 4 depths of the quad
	 */
	public QuadDatas(float w, float h, X4Color4f colors, float... x4Depth)
	{
		DIMENSIONS = new float[]
		{
				-w / 2, h / 2, x4Depth[0], -w / 2, -h / 2, x4Depth[1], w / 2, -h / 2, x4Depth[2], w / 2, h / 2,
				x4Depth[3]
		};
		COLORS = new float[]
		{
				colors.getC0().getR(), colors.getC0().getG(), colors.getC0().getB(), colors.getAlpha(),
				colors.getC1().getR(), colors.getC1().getG(), colors.getC1().getB(), colors.getAlpha(),
				colors.getC2().getR(), colors.getC2().getG(), colors.getC2().getB(), colors.getAlpha(),
				colors.getC3().getR(), colors.getC3().getG(), colors.getC3().getB(), colors.getAlpha()
		};
		INDICES = new int[]
		{
				0, 1, 3, 3, 1, 2
		};
		width = w;
		height = h;
	}

	/**
	 * Creates datas for a textured quad
	 *
	 * @param w the width of the quad
	 * @param h the height of the quad
	 * @param tw the width of the texture region (0 <= tw <= 1)
	 * @param th the height of the texture region (0 <= th <= 1)
	 * @param x4Depth the 4 depth of the quad
	 */
	public QuadDatas(float w, float h, float tw, float th, float... x4Depth)
	{
		DIMENSIONS = new float[]
		{
				-w / 2, h / 2, x4Depth[0], -w / 2, -h / 2, x4Depth[1], w / 2, -h / 2, x4Depth[2], w / 2, h / 2,
				x4Depth[3]
		};
		TEXTCOORDS = new float[]
		{
				0f, 0f, 0f, th, tw, th, tw, 0f
		};
		INDICES = new int[]
		{
				0, 1, 3, 3, 1, 2
		};
		width = w;
		height = h;
	}
	
}