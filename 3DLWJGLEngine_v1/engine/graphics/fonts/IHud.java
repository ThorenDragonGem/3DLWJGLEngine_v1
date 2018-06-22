package graphics.fonts;

import core.display.*;

public interface IHud
{
	TextObject[] getObjects();

	void updateSize(GLFWDisplay display);

	default void cleanUp()
	{
		TextObject[] objects = getObjects();
		for(TextObject o : objects)
		{
			o.getModel().cleanUp();
		}
	}
}
