package utils.maths;

public class X4Color4f
{
	private Color3f c0, c1, c2, c3;
	private float alpha;
	
	public X4Color4f(Color3f c0, Color3f c1, Color3f c2, Color3f c3, float alpha)
	{
		this.c0 = c0;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.alpha = alpha;
	}
	
	/**
	 * @param LRorBT true: V0 and V1 same colors, V2 and V3 too ; false: V0 and V3 same colors, V1 and V2 too.
	 */
	public X4Color4f(Color3f c0, Color3f c1, float alpha, boolean LRorBT)
	{
		if(LRorBT)
		{
			this.c0 = c0;
			this.c1 = c0;
			this.c2 = c1;
			this.c3 = c1;
		}
		else
		{
			this.c0 = c0;
			this.c1 = c1;
			this.c2 = c1;
			this.c3 = c0;
		}
		this.alpha = alpha;
	}
	
	public X4Color4f(Color3f c0, Color3f c1, boolean LRorBT)
	{
		this(c0, c1, 1f, LRorBT);
	}
	
	public X4Color4f(Color3f c, float alpha)
	{
		this(c, c, c, c, alpha);
	}
	
	public X4Color4f(Color3f c)
	{
		this(c, c, c, c, 1f);
	}
	
	public X4Color4f(float alpha)
	{
		this(new Color3f(), new Color3f(), new Color3f(), new Color3f(), alpha);
	}
	
	public X4Color4f()
	{
		this(new Color3f(), new Color3f(), new Color3f(), new Color3f(), 1f);
	}
	
	public X4Color4f(float r, float g, float b, float a)
	{
		this(new Color3f(r, g, b), a);
	}
	
	public X4Color4f(float r, float g, float b)
	{
		this(new Color3f(r, g, b), 1f);
	}

	public Color3f getC0()
	{
		return c0;
	}

	public Color3f getC1()
	{
		return c1;
	}
	
	public Color3f getC2()
	{
		return c2;
	}
	
	public Color3f getC3()
	{
		return c3;
	}
	
	public float getAlpha()
	{
		return alpha;
	}
}
