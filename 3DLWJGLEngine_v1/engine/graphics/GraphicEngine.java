package graphics;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import core.display.*;
import graphics.shaders.*;
import graphics.transforms.*;

public class GraphicEngine
{
	List<ShaderProgram> programs;
	private Camera camera;

	public GraphicEngine(Camera camera)
	{
		programs = new ArrayList<>();
		this.camera = camera;
	}
	
	public GraphicEngine(ShaderProgram program, Camera camera)
	{
		this(camera);
		programs.add(program);
	}
	
	public GraphicEngine(List<ShaderProgram> programs, Camera camera)
	{
		this(camera);
		this.programs = programs;
	}
	
	public void init() throws Exception
	{
		for(ShaderProgram program : programs)
		{
			program.init();
		}
	}
	
	public void input(double delta)
	{
		camera.input(delta);
		for(ShaderProgram program : programs)
		{
			program.input(delta);
		}
	}
	
	public void update(double delta)
	{
		camera.update(delta);
		for(ShaderProgram program : programs)
		{
			program.update(delta);
		}
	}
	
	public void render(GLFWDisplay display, double alpha)
	{
		clear();
		display.setClearColor(1, 0, 0, 1);
		if(display.isResized())
		{
			glViewport(0, 0, display.getWidth(), display.getHeight());
			display.setResized(false);
		}
		for(ShaderProgram program : programs)
		{
			if(!program.isBinded())
			{
				program.bind();
			}

			program.render(display, alpha);

			if(program.isBinded())
			{
				program.unbind();
			}
		}
	}
	
	public void cleanup()
	{
		for(ShaderProgram program : programs)
		{
			if(program != null)
			{
				program.cleanup();
			}
		}
	}

	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public Camera getCamera()
	{
		return camera;
	}

	public List<ShaderProgram> getPrograms()
	{
		return programs;
	}
}
