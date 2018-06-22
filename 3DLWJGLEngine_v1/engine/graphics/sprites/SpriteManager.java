package graphics.sprites;

import java.util.*;

public class SpriteManager
{
	private List<Sprite> list;

	public SpriteManager()
	{
		list = new ArrayList<>();
	}

	public void add(Sprite e)
	{
		if(list.contains(e))
		{
			return;
		}
		list.add(e);
	}

	public void add(int index, Sprite e)
	{
		if(list.contains(e))
		{
			return;
		}
		list.add(index, e);
	}

	public void remove(Sprite e)
	{
		if(!list.contains(e))
		{
			return;
		}
		list.remove(e);
	}

	public void remove(int index)
	{
		if(list.get(index) == null)
		{
			return;
		}
		list.remove(index);
	}
	
	public void clear()
	{
		if(list.isEmpty())
		{
			return;
		}
		list.clear();
	}

	@Override
	public String toString()
	{
		String string = "";
		for(int i = 0; i < list.size(); i++)
		{
			string += list.get(i).toString() + " ";
		}
		System.out.println("[ " + string + "]");
		return string;
	}
	
	public List<Sprite> getList()
	{
		return list;
	}

	public void init()
	{
		list.forEach(e ->
		{
			try
			{
				e.init();
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		});
	}
	
	public void input(double delta)
	{
		list.forEach(e -> e.input(delta));
	}
	
	public void update(double delta)
	{
		list.forEach(e -> e.update(delta));
	}
	
	public void render(double alpha)
	{
		list.forEach(e -> e.render(alpha));
	}

	public void cleanUp()
	{
		list.forEach(e -> e.cleanUp());
	}
}
