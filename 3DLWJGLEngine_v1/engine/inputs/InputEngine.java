package inputs;

import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCharCallback;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;

public class InputEngine
{
	private Keyboard keyboard;
	private Mouse mouse;

	public InputEngine()
	{

	}

	public void init(long displayId) throws Exception
	{
		keyboard = new Keyboard().init(displayId);
		mouse = new Mouse().init(displayId);
	}

	public void update()
	{
		keyboard.update();
		mouse.update();
	}

	/**
	 * @return true if any key is down
	 */
	public boolean isKeyDown()
	{
		return keyboard.isKeyDown(getCurrentKeyDown());
	}

	/**
	 * @param keyCode
	 *            the id of the key checked
	 * @return true only if the key of id keyCode is pressed
	 */
	public boolean isKeyDown(int keyCode)
	{
		return keyboard.isKeyDown(keyCode);
	}

	/**
	 * @return true if any key is pressed
	 */
	public boolean isKeyPressed()
	{
		return isKeyPressed(getCurrentKeyDown());
	}

	/**
	 * @param keyCode
	 *            the id of the key checked
	 * @return true only if the key of id keyCode is pressed
	 */
	public boolean isKeyPressed(int keyCode)
	{
		return keyboard.isKeyPressed(keyCode);
	}

	public int getCurrentKeyDown()
	{
		return keyboard.getCurrentKeyDown();
	}

	public String getCurrentCharDown()
	{
		return keyboard.getCurrentCharDown();
	}

	public boolean isButtonDown(int button)
	{
		return mouse.isButtonDown(button);
	}

	public boolean isButtonPressed(int button)
	{
		return mouse.isButtonPressed(button);
	}

	public float getX()
	{
		return mouse.getX();
	}

	public float getY()
	{
		return mouse.getY();
	}

	public float getScrollX()
	{
		return mouse.getScrollX();
	}

	public float getScrollY()
	{
		return mouse.getScrollY();
	}

	public boolean isInDisplay()
	{
		return mouse.isInDisplay();
	}

	public Vector2f getDisplayVec()
	{
		return mouse.getDisplayVec();
	}
}

class Keyboard
{
	private GLFWKeyCallback keyCallback;
	private GLFWCharCallback charCallback;
	private Map<Integer, Boolean> keyMap = new HashMap<>(), prevKeyMap = new HashMap<>();
	private int key;
	private String keyChar = "";

	public Keyboard init(long displayId) throws Exception
	{
		glfwSetKeyCallback(displayId, keyCallback = new GLFWKeyCallback()
		{
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods)
			{
				keyMap.put(key, action != GLFW_RELEASE);
				setCurrentKeyDown(key);
			}
		});

		glfwSetCharCallback(displayId, charCallback = new GLFWCharCallback()
		{
			private String keyString;

			@Override
			public void invoke(long window, int codepoint)
			{
				keyChar = String.valueOf((char)codepoint);
			}
		});
		return this;
	}

	private void setCurrentKeyDown(int key)
	{
		this.key = key;
	}

	public int getCurrentKeyDown()
	{
		return key;
	}

	private boolean isFonctionKey(int key)
	{
		switch(key)
		{
			case 259:
				return true;
			case 260:
				return true;
			case 261:
				return true;
			case 262:
				return true;
			case 263:
				return true;
			case 264:
				return true;
			case 265:
				return true;
			case 266:
				return true;
			case 267:
				return true;
			case 268:
				return true;
			case 269:
				return true;
			case 280:
				return true;
			case 281:
				return true;
			case 282:
				return true;
			case 284:
				return true;
			case 290:
				return true;
			case 291:
				return true;
			case 292:
				return true;
			case 293:
				return true;
			case 294:
				return true;
			case 295:
				return true;
			case 296:
				return true;
			case 297:
				return true;
			case 298:
				return true;
			case 299:
				return true;
			case 300:
				return true;
			case 301:
				return true;
			case 320:
				return true;
			case 321:
				return true;
			case 322:
				return true;
			case 323:
				return true;
			case 324:
				return true;
			case 325:
				return true;
			case 326:
				return true;
			case 327:
				return true;
			case 328:
				return true;
			case 329:
				return true;
			case 340:
				return true;
			case 341:
				return true;
			case 342:
				return true;
			case 343:
				return true;
			case 344:
				return true;
			case 345:
				return true;
			case 346:
				return true;
			case 348:
				return true;
		}
		return false;
	}
	
	//TODO
	@Deprecated
	private boolean isNumpad()
	{
			if(keyChar == "0")
				return true;
			else if(keyChar == "1")
				return true;
			else if(keyChar  == "2")
				return true;
			else if(keyChar == "3")
				return true;
			else if(keyChar == "4")
				return true;
			else if(keyChar == "5")
				return true;
			else if(keyChar == "6")
				return true;
			else if(keyChar == "7")
				return true;
			else if(keyChar == "8")
				return true;
			else if(keyChar == "9")
				return true;
			else 
				return false;
	}

	public void update()
	{
		for(Integer i : keyMap.keySet())
			prevKeyMap.put(i, keyMap.get(i));
	}

	public boolean isKeyDown(int keyCode)
	{
		if(keyMap.containsKey(keyCode))
			return keyMap.get(keyCode);
		else
			return false;
	}

	public boolean isKeyPressed(int keyCode)
	{
		if(isKeyDown(keyCode) && !wasKeyDown(keyCode))
			return true;
		else
			return false;
	}

	public String getCurrentCharDown()
	{
		if(isFonctionKey(key))
			return ""; 
		//implement enter "exec" command
		if(key == 257 || key == 335)
			return "\n";//Doesn't work. //!TODO
		return keyChar;
	}

	private boolean wasKeyDown(int keyCode)
	{
		if(prevKeyMap.containsKey(keyCode))
			return prevKeyMap.get(keyCode);
		else
			return false;
	}
}

class Mouse
{
	private GLFWCursorPosCallback posCallback;
	private GLFWScrollCallback scrollCallback;
	private GLFWMouseButtonCallback buttonCallback;
	private GLFWCursorEnterCallback enterCallback;

	private Vector2f displayVec;
	private Vector2f currentPos, previousPos;

	private float scrollX, scrollY;
	private Map<Integer, Boolean> buttonMap = new HashMap<>(), prevButtonMap = new HashMap<>();
	private boolean entered;

	public Mouse init(long displayId) throws Exception
	{
		displayVec = new Vector2f();
		currentPos = new Vector2f();
		previousPos = new Vector2f();

		glfwSetCursorPosCallback(displayId, posCallback = new GLFWCursorPosCallback()
		{

			@Override
			public void invoke(long window, double xpos, double ypos)
			{
				Mouse.this.currentPos.x = (int)xpos;
				Mouse.this.currentPos.y = (int)ypos;
			}
		});
		glfwSetScrollCallback(displayId, scrollCallback = new GLFWScrollCallback()
		{
			@Override
			public void invoke(long window, double xoffset, double yoffset)
			{
				Mouse.this.scrollX = (float)xoffset;
				Mouse.this.scrollY = (float)yoffset;
			}
		});
		glfwSetMouseButtonCallback(displayId, buttonCallback = new GLFWMouseButtonCallback()
		{

			@Override
			public void invoke(long window, int button, int action, int mods)
			{
				buttonMap.put(button, action != GLFW_RELEASE);
			}
		});
		glfwSetCursorEnterCallback(displayId, enterCallback = new GLFWCursorEnterCallback()
		{

			@Override
			public void invoke(long window, boolean entered)
			{
				Mouse.this.entered = entered;
			}
		});
		return this;
	}

	public void update()
	{
		updateDisplayVec();
		for(Integer i : buttonMap.keySet())
			prevButtonMap.put(i, buttonMap.get(i));
	}

	public boolean isButtonDown(int button)
	{
		if(buttonMap.containsKey(button))
			return buttonMap.get(button);
		else
			return false;
	}

	public boolean isButtonPressed(int button)
	{
		if(isButtonDown(button) && !wasButtonDown(button))
			return true;
		else
			return false;
	}

	private boolean wasButtonDown(int button)
	{
		if(prevButtonMap.containsKey(button))
			return prevButtonMap.get(button);
		else
			return false;
	}

	public float getX()
	{
		return currentPos.x;
	}

	public float getY()
	{
		return currentPos.y;
	}

	public boolean isInDisplay()
	{
		return entered;
	}

	int j = 0;

	public float getScrollX()
	{
		float scrollx = scrollX;
		j++;
		if(j % 2 == 0)
			scrollX = 0;
		return scrollx;
	}

	int i = 0;

	public float getScrollY()
	{
		float scrolly = scrollY;
		i++;
		if(i % 2 == 0)
			scrollY = 0;
		return scrolly;
	}

	public void updateDisplayVec()
	{
		displayVec.x = 0;
		displayVec.y = 0;
		if(previousPos.x > 0 && previousPos.y > 0 && entered)
		{
			double deltax = currentPos.x - previousPos.x;
			double deltay = currentPos.y - previousPos.y;
			boolean rotateX = deltax != 0;
			boolean rotateY = deltay != 0;
			if(rotateX)
			{
				displayVec.y = (float)deltax;
			}
			if(rotateY)
			{
				displayVec.x = (float)deltay;
			}
		}
		previousPos.x = currentPos.x;
		previousPos.y = currentPos.y;
	}

	public Vector2f getDisplayVec()
	{
		return displayVec;
	}
}
