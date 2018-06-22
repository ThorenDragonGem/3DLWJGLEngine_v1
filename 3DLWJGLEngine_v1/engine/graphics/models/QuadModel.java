package graphics.models;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.*;
import java.util.*;

import org.lwjgl.*;

import graphics.datas.*;
import graphics.textures.*;

public class QuadModel extends Model
{
	private final int vaoId;
	private final List<Integer> vboIdList;
	private final int vertexCount;
	private Texture texture;
	
	public QuadModel(QuadDatas datas)
	{
		super(datas);
		vertexCount = datas.INDICES.length;
		vboIdList = new ArrayList<>();

		vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		int vboId = glGenBuffers();
		vboIdList.add(vboId);
		FloatBuffer posBuffer = BufferUtils.createFloatBuffer(datas.DIMENSIONS.length);
		posBuffer.put(datas.DIMENSIONS).flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

		vboId = glGenBuffers();
		vboIdList.add(vboId);
		FloatBuffer coloursBuffer = BufferUtils.createFloatBuffer(datas.COLORS.length);
		coloursBuffer.put(datas.COLORS).flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, coloursBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

		vboId = glGenBuffers();
		vboIdList.add(vboId);
		IntBuffer indicesBuffer = BufferUtils.createIntBuffer(datas.INDICES.length);
		indicesBuffer.put(datas.INDICES).flip();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	public QuadModel(QuadDatas datas, Texture texture)
	{
		super(datas);
		this.texture = texture;
		vertexCount = datas.INDICES.length;
		vboIdList = new ArrayList<>();

		vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		int vboId = glGenBuffers();
		vboIdList.add(vboId);
		FloatBuffer posBuffer = BufferUtils.createFloatBuffer(datas.DIMENSIONS.length);
		posBuffer.put(datas.DIMENSIONS).flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

		vboId = glGenBuffers();
		vboIdList.add(vboId);
		FloatBuffer textCoordsBuffer = BufferUtils.createFloatBuffer(datas.TEXTCOORDS.length);
		textCoordsBuffer.put(datas.TEXTCOORDS).flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);

		vboId = glGenBuffers();
		vboIdList.add(vboId);
		IntBuffer indicesBuffer = BufferUtils.createIntBuffer(datas.INDICES.length);
		indicesBuffer.put(datas.INDICES).flip();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	@Override
	public void render()
	{
		if(texture != null)
		{
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, texture.getId());
		}

		glBindVertexArray(getVaoId());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);

		glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);

		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
	}

	public int getVaoId()
	{
		return vaoId;
	}

	public int getVertexCount()
	{
		return vertexCount;
	}

	@Override
	public void cleanUp()
	{
		deleteBuffers();
		texture.cleanUp();
	}

	@Override
	public Texture getTexture()
	{
		return texture;
	}
}
