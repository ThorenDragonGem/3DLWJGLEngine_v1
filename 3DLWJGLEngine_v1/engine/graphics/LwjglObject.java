package graphics;

import org.joml.*;

import graphics.models.*;
import utils.maths.*;

public abstract class LwjglObject
{
	protected Model model;
	private Vector3f position;
	private Vector3f rotation;
	private float scale;

	public LwjglObject(Model model, Vector3f position, Vector3f rotation, float scale)
	{
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public LwjglObject(Model model)
	{
		this(model, new Vector3f(), new Vector3f(), 1);
	}
	
	public LwjglObject()
	{
		this(null);
	}

	public abstract void init() throws Exception;

	public abstract void input(double delta);

	public abstract void update(double delta);

	public abstract void render(double alpha);

	public abstract void cleanUp();

	public Model getModel()
	{
		return model;
	}
	
	public void setModel(Model model)
	{
		this.model = model;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public void setPosition(float x, float y, float z)
	{
		position.x = x;
		position.y = y;
		position.z = z;
	}

	public float getScale()
	{
		return scale;
	}

	public void setScale(float scale)
	{
		this.scale = scale;
	}

	public Vector3f getRotation()
	{
		return rotation;
	}

	public void setRotation(float x, float y, float z)
	{
		rotation.x = x;
		rotation.y = y;
		rotation.z = z;
	}

	public float getWidth()
	{
		return model.getDatas().width;
	}
	
	public float getHeight()
	{
		return model.getDatas().height;
	}
	
	public void setDimension(float width, float height, float... x4Depth)
	{
		model.getDatas().DIMENSIONS = new float[]
		{
				-width / 2, height / 2, x4Depth[0], -width / 2, -height / 2, x4Depth[1], width / 2, -height / 2,
				x4Depth[2], width / 2, height / 2, x4Depth[3]
		};
		model.getDatas().width = width;
		model.getDatas().height = height;
	}

	public void setColor(X4Color4f colors)
	{
		model.getDatas().COLORS = new float[]
		{
				colors.getC0().getR(), colors.getC0().getG(), colors.getC0().getB(), colors.getAlpha(),
				colors.getC1().getR(), colors.getC1().getG(), colors.getC1().getB(), colors.getAlpha(),
				colors.getC2().getR(), colors.getC2().getG(), colors.getC2().getB(), colors.getAlpha(),
				colors.getC3().getR(), colors.getC3().getG(), colors.getC3().getB(), colors.getAlpha()
		};
	}
}
