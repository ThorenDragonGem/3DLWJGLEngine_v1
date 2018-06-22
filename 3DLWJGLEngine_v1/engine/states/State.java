package states;

import graphics.*;

public interface State
{
	void onEnter();
	
	void onExit();

	void init() throws Exception;

	void input(double delta);

	void update(double delta);

	void render(GraphicEngine graphics);

	void cleanUp() throws Throwable;
}
