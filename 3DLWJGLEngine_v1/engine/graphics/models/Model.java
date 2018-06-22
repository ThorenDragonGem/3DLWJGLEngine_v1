package graphics.models;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.*;

import graphics.datas.*;
import graphics.textures.*;

public abstract class Model
{
	protected int vaoId;
	protected List<Integer> vboIdList;
	protected int vertexCount;
	private Datas datas;
	protected Texture texture;
	
	public Model(Datas datas, Texture texture)
	{
		setDatas(datas);
		this.texture = texture;
	}
	
	public Model(Datas datas)
	{
		this(datas, null);
	}
	
	public abstract void render();
	
	public abstract void cleanUp();
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public Datas getDatas()
	{
		return datas;
	}
	
	public void setDatas(Datas datas)
	{
		this.datas = datas;
	}
	
	public void deleteBuffers()
	{
		glDisableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		for(int vboId : vboIdList)
		{
			glDeleteBuffers(vboId);
		}
		
		glBindVertexArray(0);
		glDeleteVertexArrays(vaoId);
	}
}
