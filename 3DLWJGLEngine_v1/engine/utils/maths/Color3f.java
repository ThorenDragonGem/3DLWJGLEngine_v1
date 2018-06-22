package utils.maths;

public class Color3f
{
	private float r, g, b;
	
	public Color3f(float r, float g, float b)
	{
		this.r = r;
		this.g= g;
		this.b = b;
	}
	
	public Color3f()
	{
		this(0, 0, 0);
	}

	public Color3f getColor()
	{
		return this;
	}
	
	public float getR()
	{
		return r;
	}
	
	public float getG()
	{
		return g;
	}
	
	public float getB()
	{
		return b;
	}
	
	@Override
	public String toString()
	{
		return r + "  " + g + "  " + b;
	}
}
