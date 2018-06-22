package physics;

import org.joml.*;

public class Paved
{
	protected Vector3f position;
	protected float width, height;
	protected float[] depths = new float[4];

	public Paved(Vector3f position, float width, float height, float depth1, float depth2, float depth3, float depth4)
	{
		this.position = position;
		this.width = width;
		this.height = height;
		depths[0] = depth1;
		depths[1] = depth2;
		depths[2] = depth3;
		depths[3] = depth4;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public float getWidth()
	{
		return width;
	}

	public float getHeight()
	{
		return height;
	}

	public float[] getDepths()
	{
		return depths;
	}

	public Paved get3DBounds()
	{
		return this;
	}

	@Override
	public String toString()
	{
		return "{" + width + " ; " + height + " ; " + depths[0] + " ; " + depths[1] + " ; " + depths[2] + " ; "
				+ depths[3] + " ; " + position.toString() + "}";
	}
}
