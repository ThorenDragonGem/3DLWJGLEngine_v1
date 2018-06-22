package utils.maths;

import org.joml.Vector3f;

public class X4Vector3f
{
	private Vector3f v0, v1, v2, v3;
	
	public X4Vector3f(Vector3f v0, Vector3f v1, Vector3f v2, Vector3f v3)
	{
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	/**
	 * V0--------V3
	 * |		  |
	 * |		  |
	 * |		  |
	 * |		  |
	 * V1--------V2
	 * @param v0
	 * @param v1
	 * @param LRorBT true: V0 and V1 same colors, V2 and V3 too ; false: V0 and V3 same colors, V1 and V2 too.
	 */
	public X4Vector3f(Vector3f v0, Vector3f v1, boolean LRorBT)
	{
		if(LRorBT)
		{
			this.v0 = v0;
			this.v1 = v0;
			this.v2 = v1;
			this.v3 = v1;
		}
		else
		{
			this.v0 = v0;
			this.v1 = v1;
			this.v2 = v1;
			this.v3 = v0;
		}
	}
	
	public X4Vector3f(Vector3f v)
	{
		this(v, v, v, v);
	}
	
	public X4Vector3f()
	{
		this(new Vector3f(), new Vector3f(), new Vector3f(), new Vector3f());
	}
	
	public Vector3f getV0()
	{
		return v0;
	}

	public Vector3f getV1()
	{
		return v1;
	}
	
	public Vector3f getV2()
	{
		return v2;
	}
	
	public Vector3f getV3()
	{
		return v3;
	}
}
