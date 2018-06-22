package assets;

import core.*;
import core.i.*;
import graphics.*;

public class AssetsInstance implements Instance
{
	private Assets assets;

	public AssetsInstance(Assets assets)
	{
		this.assets = assets;
		System.err.println(true);
	}
	
	@Override
	public void input(double delta)
	{

	}

	@Override
	public void init() throws Exception
	{

	}

	@Override
	public void update(double delta)
	{
		// if(true)
		// {
		// System.err.println(false);
		// Engine.instance = Engine.gameInstance;
		// try
		// {
		// Engine.instance.init();
		// }
		// catch(Exception e)
		// {
		// e.printStackTrace();
		// }
		// return;
		// }
		if(!assets.isAllExecuted())
		{
			if(!assets.getImagesLoader().isExecuted() && !assets.getImagesLoader().isDone())
			{
				executeImagesLoader();
				return;
			}
			if(assets.getImagesLoader().isDone() && !assets.getFontsLoader().isExecuted()
					&& !assets.getFontsLoader().isDone())
			{
				executeFontsLoader();
			}
		}
		if(assets.isDone())
		{
			try
			{
				assets.cleanUp();
				Thread.sleep(500);
			}
			catch(Throwable e)
			{
				e.printStackTrace();
			}
			Engine.instance = Engine.gameInstance;
			try
			{
				Engine.instance.init();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void executeImagesLoader()
	{
		if(!assets.getImagesLoader().isExecuted() && !assets.getImagesLoader().isDone())
		{
			assets.getImagesLoader().executeLoader();
		}
	}

	public void executeFontsLoader()
	{
		if(!assets.getFontsLoader().isExecuted() && !assets.getFontsLoader().isDone())
		{
			assets.getFontsLoader().executeLoader();
		}
	}

	@Override
	public void render(GraphicEngine graphics)
	{
		// graphics.getGraphics().setColor(Color.green);
		// graphics.getGraphics().fillRect(40, 50, (720 * assets.getProgress()) / 100,
		// 50);
		// TextUtils.drawString("Loading Assets...", graphics, Color.black, 800 / 2, 85,
		// 20, true);
		// y = rect.x + rect.height / 2 + font.height / 2 = 85
	}

	@Override
	public void cleanUp() throws Throwable
	{
		try
		{
			finalize();
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
}