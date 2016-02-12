package kirk.EvilWorm;

import java.util.Random;

public class World
{
	static final int SCORE_INCREMENT = 10;
	static final int WORLD_HEIGHT = 11;
	static final int WORLD_WIDTH = 9;
	static final float INITIAL_TIME = 0.5f;
	static final float TICK_DECREMENT = 0.05f;
	
	public Worm worm;
	public People people;
	public int score = 0;
	public boolean gameOver = false;
	
	boolean fields[][] = new boolean [WORLD_WIDTH][WORLD_HEIGHT];
	Random random = new Random();
	float tickTime = 0;
	static float tick = INITIAL_TIME;
	
	public World()
	{
		worm = new Worm();
		placePerson();
	}
	
	
	private void placePerson()
	{
		int len = worm.parts.size();
		/*
		for(int x = 0; x < WORLD_WIDTH; x++)
		{
			for(int y = 0; y < WORLD_HEIGHT; y++)
			{
				fields[x][y] = false;
			}
		}
		
		//error in Array, ArrayOutOfBounds
		
		for(int i = 0; i < len; i++)
		{
			WormPiece part = worm.parts.get(i);
			fields[part.x][part.y] = true;
		}
		*/
		
		
		
		int PeopleX = random.nextInt(WORLD_WIDTH);
		int PeopleY = random.nextInt(WORLD_HEIGHT);
		
		while(true)
		{
			for(int i = 0; i < len; i++)
			{
				WormPiece part = worm.parts.get(i);
				if(part.x == PeopleX && part.y == PeopleY)
				{
					PeopleX = random.nextInt(WORLD_WIDTH);
					PeopleY = random.nextInt(WORLD_HEIGHT);
					i = 0;
				}
			}
			
			break;
		}
		
		people = new People(PeopleX, PeopleY, 1);
	}
	
	
	
	public void update(float deltaTime)
	{
		WormPiece head = worm.parts.get(0);
		
		if(gameOver)
			return;
		
		tickTime += deltaTime;
		
		while(tickTime > tick)
		{
			tickTime -= tick;
			worm.advance();
			
			if(worm.checkBitten())
			{
				gameOver = true;
				return;
			}
			
			if(head.x == people.x && head.y == people.y && head.type == 1)
			{
				score += SCORE_INCREMENT;
				worm.eat();
			
				if(worm.parts.size() == WORLD_WIDTH * WORLD_HEIGHT)
				{
					gameOver = true;
					return;
				}
				
				else
					placePerson();
				
				
				if(score % 100 == 0 && tick - TICK_DECREMENT > 0)
				{
					tick -= TICK_DECREMENT;
				}	
			}	
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
