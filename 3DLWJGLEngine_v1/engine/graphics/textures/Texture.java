package graphics.textures;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.*;
import java.nio.*;

import org.lwjgl.*;

import core.*;
import de.matthiasmann.twl.utils.*;
import de.matthiasmann.twl.utils.PNGDecoder.*;

public class Texture
{
	private String name;
	private final int id;
	private final int width;
	private final int height;

	public Texture(String name, File fileName) throws Exception
	{
		this(name, new FileInputStream(fileName));
	}

	public Texture(String name, InputStream is) throws Exception
	{
		this.name = name;

		PNGDecoder decoder = new PNGDecoder(is);
		
		width = decoder.getWidth();
		height = decoder.getHeight();
		
		ByteBuffer buf = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
		decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
		buf.flip();

		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		
		glTexImage2D(GL_TEXTURE_2D, Config.mipmapLevel, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_LINEAR);
		
		// GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, 2);
		// GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_GENERATE_MIPMAP,
		// GL11.GL_TRUE);
		// GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.width,
		// this.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
		
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void bind()
	{
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void cleanUp()
	{
		glDeleteTextures(id);
	}
	
	public String getName()
	{
		return name;
	}
}
