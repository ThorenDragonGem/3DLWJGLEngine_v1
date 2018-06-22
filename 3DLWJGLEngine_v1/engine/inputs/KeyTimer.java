package inputs;

import core.*;

public class KeyTimer
{
	private int initialTimer;
	private int timer;
	private int keyCode;
	private InputEngine input = Engine.inputs;

	public KeyTimer(int timer, int keyCode)
	{
		initialTimer = timer;
		this.timer = timer;
		this.keyCode = keyCode;
	}
	
	public boolean down()
	{
		if(input.isKeyDown(keyCode))
		{
			if(timer >= 0)
			{
				timer--;
				return false;
			}
			else if(timer <= 0)
			{
				timer = 0;
				return true;
			}
			return false;
		}
		reset();
		return false;
	}
	
	public void reset(int timer)
	{
		this.timer = timer;
	}

	public void reset()
	{
		timer = initialTimer;
	}

	public void setKeyCode(int keyCode)
	{
		this.keyCode = keyCode;
	}
}
