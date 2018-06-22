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

public class TextModel extends Model
{
	public TextModel(CharDatas datas)
	{
		super(datas);
	}

	public TextModel(float[] positions, float[] textCoords, float[] normals, int[] indices, Texture texture)
	{
		super(new Datas()
		{
		});
		this.texture = texture;
		vertexCount = indices.length;
		vboIdList = new ArrayList<>();
		
		vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);
		
		// Position VBO
		int vboId = glGenBuffers();
		vboIdList.add(vboId);
		FloatBuffer posBuffer = BufferUtils.createFloatBuffer(positions.length);
		posBuffer.put(positions).flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		// Texture coordinates VBO
		vboId = glGenBuffers();
		vboIdList.add(vboId);
		FloatBuffer textCoordsBuffer = BufferUtils.createFloatBuffer(textCoords.length);
		textCoordsBuffer.put(textCoords).flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		
		// Vertex normals VBO
		vboId = glGenBuffers();
		vboIdList.add(vboId);
		FloatBuffer vecNormalsBuffer = BufferUtils.createFloatBuffer(normals.length);
		vecNormalsBuffer.put(normals).flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, vecNormalsBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
		
		// Index VBO
		vboId = glGenBuffers();
		vboIdList.add(vboId);
		IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
		indicesBuffer.put(indices).flip();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
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
	public void render()
	{
		Texture texture = getTexture();
		if(texture != null)
		{
			// Activate first texture bank
			glActiveTexture(GL_TEXTURE0);
			// Bind the texture
			glBindTexture(GL_TEXTURE_2D, texture.getId());
		}
		
		// Draw the mesh
		glBindVertexArray(getVaoId());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);
		
		// Restore state
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	@Override
	public void cleanUp()
	{
		glDisableVertexAttribArray(0);
		
		// Delete the VBOs
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		for(int vboId : vboIdList)
		{
			glDeleteBuffers(vboId);
		}
		
		// Delete the texture
		Texture texture = getTexture();
		if(texture != null)
		{
			texture.cleanUp();
		}
		
		// Delete the VAO
		glBindVertexArray(0);
		glDeleteVertexArrays(vaoId);
	}
	
	@Override
	public void deleteBuffers()
	{
		glDisableVertexAttribArray(0);
		
		// Delete the VBOs
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		for(int vboId : vboIdList)
		{
			glDeleteBuffers(vboId);
		}
		
		// Delete the VAO
		glBindVertexArray(0);
		glDeleteVertexArrays(vaoId);
	}
}
