package graphics.fonts;

import java.util.*;

import org.lwjgl.glfw.*;

import com.sun.javafx.geom.*;

import core.*;
import graphics.*;
import graphics.models.*;
import graphics.textures.*;
import inputs.*;
import utils.*;

public class TextObject extends LwjglObject
{
	private static final float ZPOS = 0.0f;
	private static final int VERTICES_PER_QUAD = 4;
	private final FontTexture fontTexture;
	private String text;
	private KeyTimer keyTimer;
	private Rectangle textZone;
	private int textWidth;
	
	public TextObject(String text, FontTexture fontTexture) throws Exception
	{
		super();
		this.text = text;
		this.fontTexture = fontTexture;
		model = buildModel();
		textZone = new Rectangle((int) getPosition().x, (int) getPosition().y, (int) getWidth(), (int) getHeight());
	}
	
	private TextModel buildModel()
	{
		List<Float> positions = new ArrayList<>();
		List<Float> textCoords = new ArrayList<>();
		float[] normals = new float[0];
		List<Integer> indices = new ArrayList<>();
		char[] characters = text.toCharArray();
		int numChars = characters.length;
		
		float startx = 0;
		for(int i = 0; i < numChars; i++)
		{
			CharInfo charInfo = fontTexture.getCharInfo(characters[i]);
			
			// Build a character tile composed by two triangles
			
			// Left Top vertex
			positions.add(startx); // x
			positions.add(0.0f); // y
			positions.add(ZPOS); // z
			textCoords.add((float) charInfo.getStartX() / fontTexture.getWidth());
			textCoords.add(0.0f);
			indices.add(i * VERTICES_PER_QUAD);
			
			// Left Bottom vertex
			positions.add(startx); // x
			positions.add((float) fontTexture.getHeight()); // y
			positions.add(ZPOS); // z
			textCoords.add((float) charInfo.getStartX() / fontTexture.getWidth());
			textCoords.add(1.0f);
			indices.add((i * VERTICES_PER_QUAD) + 1);
			
			// Right Bottom vertex
			positions.add(startx + charInfo.getWidth()); // x
			positions.add((float) fontTexture.getHeight()); // y
			positions.add(ZPOS); // z
			textCoords.add((float) (charInfo.getStartX() + charInfo.getWidth()) / fontTexture.getWidth());
			textCoords.add(1.0f);
			indices.add((i * VERTICES_PER_QUAD) + 2);
			
			// Right Top vertex
			positions.add(startx + charInfo.getWidth()); // x
			positions.add(0.0f); // y
			positions.add(ZPOS); // z
			textCoords.add((float) (charInfo.getStartX() + charInfo.getWidth()) / fontTexture.getWidth());
			textCoords.add(0.0f);
			indices.add((i * VERTICES_PER_QUAD) + 3);
			
			// Add indices por left top and bottom right vertices
			indices.add(i * VERTICES_PER_QUAD);
			indices.add((i * VERTICES_PER_QUAD) + 2);
			
			startx += charInfo.getWidth();
		}
		
		float[] posArr = Utils.listToArray(positions);
		float[] textCoordsArr = Utils.listToArray(textCoords);
		int[] indicesArr = indices.stream().mapToInt(i -> i).toArray();
		TextModel model = new TextModel(posArr, textCoordsArr, normals, indicesArr, fontTexture.getTexture());
		return model;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
		getModel().deleteBuffers();
		setModel(buildModel());
	}

	public void addText(String text)
	{
		this.text += text;
		getModel().deleteBuffers();
		setModel(buildModel());
	}
	
	public void removeText(int lengthToRemove)
	{
		text = text.substring(0, text.length() - lengthToRemove);
		getModel().deleteBuffers();
		setModel(buildModel());
	}

	// TODO
	// private boolean formatTextInZone()
	// {
	// textWidth = (int)(text.length() * fontTexture.getFont().getSize2D());
	// if(textWidth > textZone.width)
	// {
	// int dw = textZone.width - textWidth;
	//
	// }
	// }

	@Override
	public void init() throws Exception
	{
		keyTimer = new KeyTimer(30, 0);
	}
	
	@Override
	public void input(double delta)
	{
		keyTimer.setKeyCode(Engine.inputs.getCurrentKeyDown());
		// key writing mechanics
		if(Engine.inputs.isKeyPressed())
		{
			if((Engine.inputs.getCurrentKeyDown() == GLFW.GLFW_KEY_BACKSPACE) && (text.length() > 0))
			{
				removeText(1);
			}
			else
			{
				addText(String.valueOf(Engine.inputs.getCurrentCharDown()));
			}
		}
		if(keyTimer.down())
		{
			if((Engine.inputs.getCurrentKeyDown() == GLFW.GLFW_KEY_BACKSPACE) && (text.length() > 0))
			{
				removeText(1);
			}
			else
			{
				addText(String.valueOf(Engine.inputs.getCurrentCharDown()));
			}
		}
		// if(input.getCurrentKeyDown() == GLFW.GLFW_KEY_BACKSPACE && text.length() !=
		// 0)
		// removeText(1);
	}
	
	@Override
	public void update(double delta)
	{
	}
	
	@Override
	public void render(double alpha)
	{
	}
	
	@Override
	public void cleanUp()
	{
	}
}