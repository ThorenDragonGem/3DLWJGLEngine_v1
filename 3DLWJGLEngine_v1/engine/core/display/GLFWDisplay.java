package core.display;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import core.*;

public class GLFWDisplay
{
	public static final int DEFAULT_WIDTH = Config.width;
	public static final int DEFAULT_HEIGHT = Config.height;
	// private GLFWErrorCallback errorCallback;
	private GLFWWindowSizeCallback windowSizeCallback;
	private GLFWWindowFocusCallback windowFocusCallback;
	private boolean resized;
	private boolean focused;
	private long displayId;
	private long newDisplayId;
	
	public GLFWDisplay()
	{
		resized = false;
	}
	
	public void init(boolean baseWindow)
	{
		if(!glfwInit())
		{
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		initGL();
		if(baseWindow)
		{
			initDisplay();
		}
		else
		{
			initLauncherDisplay();
		}
		syncronize();
		initCallbacks();
	}
	
	public void toggleFullscreen(boolean fullscreen)
	{
		if(fullscreen == Config.fullscreen)
		{
			return;
		}
		
		Config.fullscreen = fullscreen;
		
		// destroy();
		init(true);
	}
	
	private void syncronize()
	{
		if(isVSync())
		{
			// if(getFocused())
			glfwSwapInterval(1);
			// else
			// {
			// if(getFullscreen())
			// glfwSwapInterval(1);
			// else
			// glfwSwapInterval(4);
			// }
		}
	}
	
	private void initGL()
	{
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		if(Config.resizable)
		{
			glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		}
		else
		{
			glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		}
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, Config.majorOpenGL);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, Config.minorOpenGL);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
	}
	
	private void initDisplay()
	{
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		if(Config.fullscreen)
		{
			Config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
			Config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
		}
		else
		{
			Config.width = DEFAULT_WIDTH;
			Config.height = DEFAULT_HEIGHT;
		}
		displayId = glfwCreateWindow(Config.width, Config.height, Config.title,
				Config.fullscreen ? glfwGetPrimaryMonitor() : NULL, newDisplayId);
		if(Config.cursor)
		{
			long cursorId = glfwCreateStandardCursor(GLFW_CROSSHAIR_CURSOR);
			glfwSetCursor(displayId, cursorId);
		}
		
		glfwSetWindowPos(displayId, (vidmode.width() - Config.width) / 2, (vidmode.height() - Config.height) / 2);
		glfwMakeContextCurrent(displayId);
		glfwShowWindow(displayId);
		GL.createCapabilities();
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glEnable(GL_DEPTH_TEST);
		
		if(Config.polygonMode == Config.FILL)
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		}
		else if((Config.polygonMode == Config.LINE))
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		}
		else if(Config.polygonMode == Config.POINT)
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_POINT);
		}
		
		if(Config.blend)
		{
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		}
		if(Config.cullFace)
		{
			glEnable(GL_CULL_FACE);
			glCullFace(GL_BACK);
		}
	}
	
	private void initLauncherDisplay()
	{
		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		displayId = glfwCreateWindow(Config.launcherWidth, Config.launcherHeight, Config.launcherTitle, NULL, NULL);
		glfwSetWindowPos(displayId, (vidMode.width() - Config.launcherWidth) / 2,
				(vidMode.height() - Config.launcherHeight) / 2);
		glfwMakeContextCurrent(displayId);
		glfwShowWindow(displayId);
		GL.createCapabilities();
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glEnable(GL_DEPTH_TEST);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}
	
	private void initCallbacks()
	{
		// glfwSetErrorCallback(errorCallback =
		// GLFWErrorCallback.createPrint(System.err));
		glfwSetWindowSizeCallback(displayId, windowSizeCallback = new GLFWWindowSizeCallback()
		{
			@Override
			public void invoke(long window, int width, int height)
			{
				Config.width = width;
				Config.height = height;
				GLFWDisplay.this.setResized(true);
			}
		});
		
		glfwSetWindowFocusCallback(displayId, windowFocusCallback = new GLFWWindowFocusCallback()
		{
			@Override
			public void invoke(long window, boolean focused)
			{
				// GLFWDisplay.this.setFocused(focused);
				// syncronize();
			}
		});
	}
	
	public boolean getFullscreen()
	{
		return Config.fullscreen;
	}
	
	public void setFocused(boolean focused)
	{
		this.focused = focused;
		// glfwFocusWindow(displayId);
	}
	
	public boolean getFocused()
	{
		return focused;
	}
	
	public void setMaximized(boolean maximized)
	{
		Config.maximized = maximized;
		if(maximized)
		{
			glfwMaximizeWindow(displayId);
		}
		else
		{
			glfwRestoreWindow(displayId);
			// glfwIconifyWindow(displayId);
		}
	}
	
	public void update()
	{
		glfwSwapBuffers(displayId);
		glfwPollEvents();
	}
	
	public void displayTitle(String title)
	{
		glfwSetWindowTitle(displayId, title);
	}
	
	public long getDisplayId()
	{
		return displayId;
	}
	
	public void setClearColor(float r, float g, float b, float alpha)
	{
		glClearColor(r, g, b, alpha);
	}
	
	public boolean shouldClose()
	{
		return glfwWindowShouldClose(displayId);
	}
	
	public String getTitle()
	{
		return Config.title;
	}
	
	public int getWidth()
	{
		return Config.width;
	}
	
	public int getHeight()
	{
		return Config.height;
	}
	
	public boolean isResized()
	{
		return resized;
	}
	
	public void setResized(boolean resized)
	{
		this.resized = resized;
	}
	
	public boolean isVSync()
	{
		return Config.vSync;
	}
	
	public void setVSync(boolean vSync)
	{
		Config.vSync = vSync;
	}
	
	public void destroy()
	{
		glfwFreeCallbacks(displayId);
		glfwDestroyWindow(displayId);
		glfwTerminate();
		windowSizeCallback.free();
		windowFocusCallback.free();
	}
}