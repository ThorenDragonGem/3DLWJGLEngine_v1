package graphics.transforms;

import java.lang.Math;

import org.joml.*;

import graphics.*;

public class Transformation
{
	private final Matrix4f projectionMatrix;
	private final Matrix4f modelViewMatrix;
	private final Matrix4f viewMatrix;
	private float scaleLeft = 1;
	private float scaleRight = 1;
	private float scaleBottom = 1;
	private float scaleTop = 1;
	
	public Transformation()
	{
		projectionMatrix = new Matrix4f();
		modelViewMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
	}
	
	public final Matrix4f getPerspectiveProjectionMatrix(float fov, float width, float height, float zNear, float zFar)
	{
		float aspectRatio = width / height;
		return projectionMatrix.identity().perspective(fov, aspectRatio, zNear, zFar);
	}
	
	public final Matrix4f getOrthographicProjectionMatrix(float left, float right, float bottom, float top, float zNear,
			float zFar)
	{
		return projectionMatrix.identity().ortho(left * scaleLeft, right * scaleRight, bottom * scaleBottom,
				top * scaleTop, zNear, zFar);
	}

	public final Matrix4f getOrtho2D(float left, float right, float bottom, float top)
	{
		return new Matrix4f().identity().ortho2D(left * scaleLeft, right * scaleRight, bottom * scaleBottom,
				top * scaleTop);
	}
	
	public void zoom(float scaleLeft, float scaleRight, float scaleBottom, float scaleTop)
	{
		this.scaleLeft += scaleLeft;
		this.scaleRight += scaleRight;
		this.scaleBottom += scaleBottom;
		this.scaleTop += scaleTop;
		if(this.scaleBottom < 0)
		{
			this.scaleBottom = 0;
		}
		if(this.scaleTop < 0)
		{
			this.scaleTop = 0;
		}
		if(this.scaleLeft < 0)
		{
			this.scaleLeft = 0;
		}
		if(this.scaleRight < 0)
		{
			this.scaleRight = 0;
		}
	}
	
	public void zoom(float leftRight, float bottomTop)
	{
		zoom(leftRight, leftRight, bottomTop, bottomTop);
	}
	
	public void zoom(float value)
	{
		zoom(value, value, value, value);
	}
	
	public Matrix4f getModelViewMatrix(LwjglObject object, Matrix4f viewMatrix)
	{
		Vector3f rotation = object.getRotation();
		modelViewMatrix.identity().translate(object.getPosition()).rotateX((float) Math.toRadians(-rotation.x))
				.rotateY((float) Math.toRadians(-rotation.y)).rotateZ((float) Math.toRadians(-rotation.z))
				.scale(object.getScale());
		Matrix4f viewCurr = new Matrix4f(viewMatrix);
		return viewCurr.mul(modelViewMatrix);
	}
	
	public Matrix4f getViewMatrix(Camera camera)
	{
		Vector3f cameraPos = camera.getPosition();
		Vector3f rotation = camera.getRotation();
		return viewMatrix.identity().rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
				.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0))
				.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
	}
	
	public Matrix4f getOrthProjModelMatrix(LwjglObject o, Matrix4f ortho)
	{
		Vector3f rotation = o.getRotation();
		Matrix4f modelMatrix = new Matrix4f();
		modelMatrix.identity().translate(o.getPosition()).rotateX((float) Math.toRadians(-rotation.x))
				.rotateY((float) Math.toRadians(-rotation.y)).rotateZ((float) Math.toRadians(-rotation.z))
				.scale(o.getScale());
		Matrix4f orthoMatrixCurr = new Matrix4f(ortho);
		orthoMatrixCurr.mul(modelMatrix);
		return orthoMatrixCurr;
	}
}