package core.i;

import graphics.*;

public interface Instance
{
	void init() throws Exception;
	
	void input(double delta);
	
	void update(double delta);

	void render(GraphicEngine g);

	void cleanUp() throws Throwable;
}

// default void init() throws Exception
// {
// if((Engine.states != null) && (Engine.states.getState() != null))
// {
// Engine.states.getState().init();
// }
// }
//
// default void input(double delta)
// {
// if((Engine.states != null) && (Engine.states.getState() != null))
// {
// Engine.states.getState().input(delta);
// }
// }
//
// default void update(double delta)
// {
// if((Engine.states != null) && (Engine.states.getState() != null))
// {
// Engine.states.getState().update(delta);
// }
// }
//
// default void render(GraphicEngine graphics)
// {
// if((Engine.states != null) && (Engine.states.getState() != null))
// {
// Engine.states.getState().render(graphics);
// }
// }
//
// default void cleanUp() throws Throwable
// {
// if((Engine.states != null) && (Engine.states.getState() != null))
// {
// Engine.states.getState().cleanUp();
// }
// }
