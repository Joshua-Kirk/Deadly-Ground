package kirk.deadlyGound;

import java.util.List;

import kirk.frameworkInterface.Game;
import kirk.frameworkInterface.Graphics;
import kirk.frameworkInterface.Screen;
import kirk.frameworkInterface.Input.TouchEvent;

public class SettingsScreen extends Screen
{
	public static final int GREEN = 0;
	public static final int BLACK = 1;
	public static final int PINK = 2;
	public static final int BLUE = 3;
	
	public static int color = 0;

	public SettingsScreen(Game game)
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
				if(MainMenuScreen.inBounds(event, 77,  150, 162, 169))
				{
					color = GREEN;
					return;
				}
				
				if(MainMenuScreen.inBounds(event, 77,  183, 163, 202))
				{
					color = BLACK;
					return;
				}
				
				if(MainMenuScreen.inBounds(event, 77,  216, 145, 235))
				{
					color = PINK;
					return;
				}
				
				if(MainMenuScreen.inBounds(event, 77,  249, 145, 268))
				{
					color = BLUE;
					return;
				}
				
				if(MainMenuScreen.inBounds(event, 5, 453, 87, 474))
				{
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) 
	{
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.SettingsScreen, 0, 0);	
	}

	@Override
	public void pause()
	{
		
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
