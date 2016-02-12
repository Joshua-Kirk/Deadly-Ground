package kirk.deadlyGound;

import java.util.List;

import kirk.frameworkInterface.Game;
import kirk.frameworkInterface.Graphics;
import kirk.frameworkInterface.Screen;
import kirk.frameworkInterface.Input.TouchEvent;

public class HighScoreScreen extends Screen
{
	String lines[] = new String[5];
	
	public HighScoreScreen(Game game) 
	{
		super(game);
		
		for(int i = 0; i < 5; i++)
		{
			lines[i] = "" + (i + 1) + ". " + Settings.highscore[i];
		}
		
	}

	@Override
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				if(MainMenuScreen.inBounds(event, 5, 453, 87, 474))
					game.setScreen(new MainMenuScreen(game));
					return;
			}
		}
	}

	@Override
	public void present(float deltaTime) 
	{
		int y = 100;
		
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.HighScoreScreen, 0, 0);
		
		for(int i = 0; i < 5; i++)
		{
			drawText(g, lines[i], 20, y);
			
			y += 50;
		}
	}

	public static void drawText(Graphics g, String line, int x, int y)
	{
		int len = line.length();
		
		for (int i = 0; i < len; i++)
		{
			int srcX = 0;
			int srcWidth = 0;
			char character = line.charAt(i);
			
			if (character == ' ') 
			{ 
				x += 13;
				continue;
			}
			
			if (character == '.')
			{
				srcX = 200;
				srcWidth = 10; 
			}
			
			else 
			{
				srcX = (character - '0') * 20;
			    srcWidth = 20;
		    }
			
		g.drawPixmap(Assets.Numbers, x, y, srcX, 0, srcWidth, 32);
		            x += srcWidth;
		}
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
