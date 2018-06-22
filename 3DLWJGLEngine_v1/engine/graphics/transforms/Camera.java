package graphics.transforms;

import java.lang.Math;

import org.joml.*;
import org.lwjgl.glfw.*;

import core.*;

public class Camera
{
	private static final float MOUSE_SENSITIVITY = 0.2f;
	private static final float CAMERA_POS_STEP = 0.05f;
	private final Vector3f position;
	private final Vector3f rotation;
	private Vector3f cameraInc;
	private int[] keys;
	
	public Camera()
	{
		this(new Vector3f(), new Vector3f(), new int[]
		{
				GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_LEFT_SHIFT,
				GLFW.GLFW_KEY_SPACE
		});
	}
	
	public Camera(Vector3f position, Vector3f rotation)
	{
		this(position, rotation, new int[]
		{
				GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_LEFT_SHIFT,
				GLFW.GLFW_KEY_SPACE
		});
	}
	
	public Camera(Vector3f position, Vector3f rotation, int... keys)
	{
		this.position = position;
		this.rotation = rotation;
		this.keys = keys;
		cameraInc = new Vector3f();
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
	
	public void movePosition(float offsetX, float offsetY, float offsetZ)
	{
		if(offsetZ != 0)
		{
			position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ;
			position.z += (float) Math.cos(Math.toRadians(rotation.y)) * offsetZ;
		}
		if(offsetX != 0)
		{
			position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offsetX;
			position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * offsetX;
		}
		position.y += offsetY;
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
	
	public void moveRotation(float offsetX, float offsetY, float offsetZ)
	{
		rotation.x += offsetX;
		rotation.y += offsetY;
		rotation.z += offsetZ;
		cameraInc.set(0, 0, 0);
	}
	
	public void input(double delta)
	{
		cameraInc.set(0, 0, 0);
		if(Config.perspective)
		{
			// if(input.getScrollY() < 0)
			if(Engine.inputs.isKeyDown(keys[0]))
			{
				cameraInc.z = -1;
			}
			// if(input.getScrollY() > 0)
			if(Engine.inputs.isKeyDown(keys[2]))
			{
				cameraInc.z = 1;
			}
		}
		if(Engine.inputs.isKeyDown(keys[1]))
		{
			cameraInc.x = -1;
		}
		if(Engine.inputs.isKeyDown(keys[3]))
		{
			cameraInc.x = 1;
		}
		if(Engine.inputs.isKeyDown(keys[4]))
		{
			cameraInc.y = -1;
		}
		if(Engine.inputs.isKeyDown(keys[5]))
		{
			cameraInc.y = 1;
		}
		movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);
		
		if(Engine.inputs.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_2))
		{
			Vector2f rotVec = Engine.inputs.getDisplayVec();
			moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
		}
	}
	
	public void update(double delta)
	{

	}
}
