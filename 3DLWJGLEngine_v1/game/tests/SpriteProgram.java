package tests;

import java.lang.Math;

import org.joml.*;

import core.*;
import core.display.*;
import graphics.shaders.*;
import graphics.sprites.*;
import graphics.transforms.*;
import utils.*;

public class SpriteProgram extends ShaderProgram
{
	private static final float FOV = (float) Math.toRadians(60.0f);
	private static final float Z_NEAR = 0.01f;
	private static final float Z_FAR = 1000f;
	Transformation transformation;
	public SpriteManager sprites;
	
	public SpriteProgram() throws Exception
	{
		super();
		sprites = new SpriteManager();
		transformation = new Transformation();
	}

	@Override
	public void init() throws Exception
	{
		super.init();
		createVertexShader(Loader.loadResource("/assets/shaders/shader.vsh"));
		createFragmentShader(Loader.loadResource("/assets/shaders/shader.fsh"));
		link();

		createUniform("projectionMatrix");
		createUniform("modelViewMatrix");
		createUniform("isTextured");
		createUniform("texture_sampler");
		sprites.init();
	}

	@Override
	public void input(double delta)
	{
		super.input(delta);
		sprites.input(delta);
		if(Engine.inputs.getScrollY() > 0)
		{
			transformation.zoom(1);
		}
		if(Engine.inputs.getScrollY() < 0)
		{
			transformation.zoom(-1);
		}
	}

	@Override
	public void update(double delta)
	{
		super.update(delta);
		sprites.update(delta);
	}

	@Override
	public void render(GLFWDisplay display, double alpha)
	{
		super.render(display, alpha);
		display.setClearColor(1, 1, 1, 1);
		bind();
		Matrix4f projectionMatrix = Config.perspective
				? transformation.getPerspectiveProjectionMatrix(FOV, Config.width, Config.height, Z_NEAR, Z_FAR)
				: transformation.getOrthographicProjectionMatrix(-1, 1, -1, 1, Z_NEAR, Z_FAR);
		setUniform("projectionMatrix", projectionMatrix);
		Matrix4f viewMatrix = transformation.getViewMatrix(Engine.graphics.getCamera());
		sprites.getList().forEach(sprite ->
		{
			Matrix4f modelViewMatrix = transformation.getModelViewMatrix(sprite, viewMatrix);
			setUniform("modelViewMatrix", modelViewMatrix);

			setUniform("isTextured", sprite.getModel().getTexture() != null);
			setUniform("texture_sampler", 0);

			sprite.getModel().render();
		});
		unbind();
	}

	@Override
	public void cleanup()
	{
		super.cleanup();
		sprites.cleanUp();
		sprites.clear();
	}
}
