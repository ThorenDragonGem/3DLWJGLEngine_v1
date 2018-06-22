package graphics;

import org.joml.*;

import graphics.datas.*;
import graphics.models.*;
import physics.*;
import utils.maths.*;

public class LwjglRectangle extends LwjglObject
{
	private Paved bounds;

	public LwjglRectangle(Model model, Vector3f position, Vector3f rotation, float scale, float width, float height,
			X4Color4f colors, float... depths)
	{
		super(new QuadModel(new QuadDatas(width, height, colors, depths)), position, rotation, scale);
	}
	
	@Override
	public void init() throws Exception
	{

	}
	
	@Override
	public void input(double delta)
	{

	}
	
	@Override
	public void update(double delta)
	{

	}
	
	@Override
	public void render(double alpha)
	{

	}
	
	@Override
	public void cleanUp()
	{

	}
}
