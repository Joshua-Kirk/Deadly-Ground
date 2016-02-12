package kirk.deadlyGound;

import java.util.List;

import kirk.frameworkInterface.Game;
import kirk.frameworkInterface.Graphics;
import kirk.frameworkInterface.Input.TouchEvent;
import kirk.frameworkInterface.Pixmap;
import kirk.frameworkInterface.Screen;

public class GameScreen extends Screen
{
	enum GameState
	{
		Ready,
		Running,
		Pause,
		GameOver
	}
	
	GameState state = GameState.Ready;
	World world;
	SettingsScreen settings;
	boolean aboveGround = true;
	int type = 1;
	int oldScore = 0;
	String score = "0";
	
	public GameScreen(Game game)
	{
		super(game);
		world = new World();
	}

	@Override
	public void update(float deltaTime) 
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		if(state == GameState.Ready)
			updateReady(touchEvents);
		
		if(state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		
		if(state == GameState.Pause)
			updatePause(touchEvents);
		
		if(state == GameState.GameOver)
			updateGameOver(touchEvents);
	}
	
	public void updateReady(List<TouchEvent> touchEvents)
	{
		int len = touchEvents.size();
		
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_DRAGGED)
			{
				state = GameState.Running;
				world.score = 0;
				World.tick = World.INITIAL_TIME;
			}
		}
	}
	
	public void updateRunning(List<TouchEvent> touchEvents, float deltaTime)
	{
		int len = touchEvents.size();
		
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				if(inBounds(event, 189, 430, 250, 463))
				{
					world.worm.turnRight();
					return;
				}
				
				if(inBounds(event, 70, 430, 131, 463))
				{
					world.worm.turnLeft();
					return;
				}
				
				if(inBounds(event, 132, 413, 188, 446))
				{
					world.worm.turnUp();
					return;
				}
				
				if(inBounds(event, 132, 448, 188, 478))
				{
					world.worm.turnDown();
					return;
				}
				
				if(inBounds(event, 277, 431, 320, 480) || inBounds(event, 0, 431, 43, 480))
				{
					if(aboveGround)
					{
						Worm.AboveGround();
						aboveGround = false;
					}
					
					else
					{
						Worm.BelowGround();
						aboveGround = true;
					}
					
					return;
				}
			}
			
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				if(touchEvents.size() >= 2)
				{
					if(inBounds(event, 0, 0, 320, 390))				
					state = GameState.Pause;					
				}
			}
			
		}
		world.update(deltaTime);
		
		if(world.gameOver)
		{
			state = GameState.GameOver;
		}
		
		if(oldScore != world.score)
		{
			oldScore = world.score;
			score = "" + oldScore;
		}
		
		
	}
	
	public void updatePause(List<TouchEvent> touchEvents)
	{
		int len = touchEvents.size();
		
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_UP)
			{
				if(MainMenuScreen.inBounds(event, 96, 150, 211, 170))
				{
					state = GameState.Running;
					return;
				}
			
				
				if(MainMenuScreen.inBounds(event, 118, 184, 187, 204))
				{
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
			
		}
	}
	
	public void updateGameOver(List<TouchEvent> touchEvents)
	{
		int len = touchEvents.size();
		
		for(int i = 0; i < len; i++)
		{
			TouchEvent event =  touchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_UP)
			{
				if(inBounds(event, 0, 0, 320, 480))
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
		g.drawPixmap(Assets.GameScreen, 0, 0);	
		drawWorld(world);
		
		//if(state == GameState.Ready)
			//drawReadyUI();
		
		if(state == GameState.Running)
			drawRunningUI();	
		
		if(state == GameState.Pause)
			drawPauseUI();
		
		if(state == GameState.GameOver)
			drawGameOverUI();
		
		HighScoreScreen.drawText(g, score, g.getWidth() / 2 - score.length() * 9, g.getHeight() - 100);
	}
	
	public void drawWorld(World world)
	{
		Graphics g = game.getGraphics();
		
		Worm worm = world.worm;
		WormPiece head = worm.parts.get(0);
		People people = world.people;
		
		
		Pixmap personPixmap = null;
		
		if(people.type == People.TYPE_1)
			personPixmap = Assets.ScaredFarmer; 
		
		if(people.type == People.TYPE_2)
			personPixmap = Assets.ScaredFarmer;
		
		if(people.type == People.TYPE_3)
			personPixmap = Assets.ScaredFarmer; 
			
					
		int x = people.x * 32;
		int y = people.y * 32;
		g.drawPixmap(personPixmap, x, y);
		
		
		int len = worm.parts.size();
		for(int i = 0; i < len; i++)
		{
			Pixmap option = null;
			WormPiece part = worm.parts.get(i); 
			x = part.x * 32;
			y = part.y * 32; 
			if(part.type == 0)
				option = Assets.Dirt;
			
			else if(part.type == 1)
			{
				if(part.direction == Worm.LEFT || part.direction == Worm.RIGHT)
					option = Assets.TremorBodyPiece;
			
				else if(part.direction == Worm.UP || part.direction == Worm.DOWN)
					option = Assets.TremorBodyPieceUp;
			}
			
				g.drawPixmap(option, x, y);
		}
		

		Pixmap headPixmap = null; 
		
		if(worm.direction == Worm.UP) 
		{
			
			if(Worm.AboveOrBelow == Worm.Above)
				headPixmap = Assets.Dirt;
			
			else
				headPixmap = Assets.TremorHeadUp;
		}

		
		if(worm.direction == Worm.LEFT) 
		{
			
			if(Worm.AboveOrBelow == Worm.Above)
				headPixmap = Assets.Dirt;
			
			else
				headPixmap = Assets.TremorHeadLeft;
				
		}
		
		if(worm.direction == Worm.DOWN) 
		{
			
			if(Worm.AboveOrBelow == Worm.Above)
				headPixmap = Assets.Dirt;
			
			else
				headPixmap = Assets.TremorHeadDown;
		}
		
		if(worm.direction == Worm.RIGHT) 
		{
			
			if(Worm.AboveOrBelow == Worm.Above)
				headPixmap = Assets.Dirt;
			
			else
				headPixmap = Assets.TremorHeadRight;
		}
		
		x = head.x * 32 + 16;
		y = head.y * 32 + 16;
		g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);
	}
	
	private void drawRunningUI()
	{
		Graphics g = game.getGraphics();
		
		Pixmap color = null;
		
		if(SettingsScreen.color == SettingsScreen.BLACK)
			color = Assets.GameControlsBlack;
		
		else if(SettingsScreen.color == SettingsScreen.PINK)
			color = Assets.GameControlsPink;
		
		else if(SettingsScreen.color == SettingsScreen.GREEN)
			color = Assets.GameControlsGreen;
		
		else if(SettingsScreen.color == SettingsScreen.BLUE)
			color = Assets.GameControlsBlue;
		
		g.drawPixmap(color, 0, 411);
	}
	
	private void drawPauseUI()
	{
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.PauseMenu, 79, 131);
	}
	
	private void drawGameOverUI()
	{
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.GameOver, 85, 131);
	}
	
	public boolean inBounds(TouchEvent event, int x, int y, int width, int height)
	{
		if(event.x > x && event.y > y && event.x < width && event.y < height)
		{
			return true;
		}
		
		return false;	
	}

	@Override
	public void pause() 
	{
		if(world.gameOver)
		{
			Settings.addScore(oldScore);
			Settings.Save(game.getFileIO());
		}
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
