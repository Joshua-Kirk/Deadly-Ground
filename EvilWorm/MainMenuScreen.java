package kirk.EvilWorm;

import java.util.List;

import kirk.frameworkInterface.Game;
import kirk.frameworkInterface.Graphics;
import kirk.frameworkInterface.Input.TouchEvent;
import kirk.frameworkInterface.Screen;

public class MainMenuScreen extends Screen
{
	SettingsScreen settings;
	
	public MainMenuScreen(Game game)
	{
		super(game);
	}

	@Override
	public void update(float deltaTime) 
	{
		List<TouchEvent> TouchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = TouchEvents.size();
		
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = TouchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_UP)
			{
				if(inBounds(event, 23, 197, 125, 226))
				{
					game.setScreen(new GameScreen(game));
					return;
				}
				
				if(inBounds(event, 23, 249, 265, 278))
				{
					game.setScreen(new HighScoreScreen(game));
					return;
				}
				
				if(inBounds(event, 23, 300, 220, 331))
				{
					game.setScreen(new SettingsScreen(game));
					return;
				}
			}
		}
		
	}
	
	static boolean inBounds(TouchEvent event, int x, int y, int width, int height)
	{
		if(event.x > x && event.y > y && event.x < width && event.y < height)
		{
			return true;
		}
		
		return false;	
	}

	@Override
	public void present(float deltaTime)
	{
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.MainMenu, 0, 0);
	}

	@Override
	public void pause() 
	{
		Settings.Save(game.getFileIO());
	}

	@Override
	public void resume() 
	{
		
	}

	@Override
	public void dispose() 
	{
		
	}
}
