package graphics.fonts;

import org.joml.*;

import core.display.*;
import graphics.models.*;
import graphics.shaders.*;
import graphics.transforms.*;
import utils.*;

public class TextShaderProgram extends ShaderProgram
{
	private Transformation transformation;
	private TextObject[] objects;
	private IHud hud;

	public TextShaderProgram(TextObject[] objects, IHud hud) throws Exception
	{
		super();
		this.objects = objects;
		this.hud = hud;
		transformation = new Transformation();
	}

	@Override
	public void init() throws Exception
	{
		super.init();
		createVertexShader(Loader.loadResource("/res/shaders/fontShader.vs"));
		createFragmentShader(Loader.loadResource("/res/shaders/fontShader.fs"));
		link();
		
		createUniform("projModelMatrix");
		createUniform("color");
	}

	@Override
	public void render(GLFWDisplay display, double alpha)
	{
		super.render(display, alpha);
		hud.updateSize(display);
		Matrix4f ortho = transformation.getOrthographicProjectionMatrix(0, display.getWidth(), display.getHeight(), 0,
				0.001f, 1000f);
		for(TextObject o : objects)
		{
			TextModel model = (TextModel) o.getModel();
			Matrix4f projModelMatrix = transformation.getOrthProjModelMatrix(o, ortho);
			setUniform("projModelMatrix", projModelMatrix);
			setUniform("color", new Vector3f(1, 0, 0));
			model.render();
		}
	}
	
	@Override
	public void cleanup()
	{
		super.cleanup();
	}
}
