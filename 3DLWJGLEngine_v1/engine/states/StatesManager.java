package states;

public class StatesManager
{
	private State state = null;

	public StatesManager()
	{

	}

	public StatesManager(State state)
	{
		setState(state);
	}

	public void setState(State state)
	{
		if(this.state != null)
		{
			this.state.onExit();
		}
		this.state = state;
		this.state.onEnter();
	}

	public State getState()
	{
		return state;
	}
}
