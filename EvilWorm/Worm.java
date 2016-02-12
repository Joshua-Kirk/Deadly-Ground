package kirk.EvilWorm;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import kirk.framework.AndroidGraphics;
import kirk.frameworkInterface.Pixmap;

public class Worm 
{
	public static final int UP  = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	
	public static final int Above = 4;
	public static final int Below = 5;
	
	public int direction;
	public static int AboveOrBelow;
	
	public List<WormPiece> parts = new ArrayList<WormPiece>();
	
	public Worm()
	{	
		direction = UP;
		parts.add(new WormPiece(5, 6, 1, UP));
		parts.add(new WormPiece(5, 7, 1, UP));
		parts.add(new WormPiece(5, 8, 1, UP));
	}
	
	public static void AboveGround()
	{
		AboveOrBelow = Above;
	}
	
	public static void BelowGround()
	{
		AboveOrBelow = Below;
	}
	
	public void turnUp()
	{
		if(direction != 2)
		direction = 0;
	}
	
	public void turnLeft()
	{
		if(direction != 3)
		direction = 1;
	}
	
	public void turnDown()
	{
		if(direction != 0)
		direction = 2;
	}
	
	public void turnRight()
	{
		if(direction != 1)
		direction = 3;
	}
	
	public void eat()
	{
		WormPiece end = parts.get(parts.size() - 1);
		parts.add(new WormPiece(end.x, end.y, end.type, end.direction));
	}
	
	public void advance()
	{
		WormPiece head = parts.get(0);	
		int len = parts.size() - 1;
		
		for(int i = len; i > 0; i--)
		{
			WormPiece before = parts.get(i - 1);
			WormPiece part = parts.get(i);
			
			part.x = before.x;
			part.y = before.y;
			part.type = before.type;
			part.direction = before.direction;
		}

		if(AboveOrBelow == Above)
			head.type = 0;
			
		else if(AboveOrBelow == Below)
			head.type = 1;
		
		if(direction == UP)
		{
			head.direction = UP;
			
			if(head.y < 1 )
				head.y = World.WORLD_HEIGHT;
			else
				head.y -= 1;
		}
		
		if(direction == LEFT)
		{
			head.direction = LEFT;
			
			if(head.x < 1)
				head.x = World.WORLD_WIDTH;
			
			else	
				head.x -= 1;
		}
		
		if(direction == DOWN)
		{
			head.direction = DOWN;
			
			if(head.y >= World.WORLD_HEIGHT)
				head.y = 0;
			
			else
				head.y += 1;
		}
		
		if(direction == RIGHT)
		{
			head.direction = RIGHT;
			
			if(head.x > World.WORLD_WIDTH)
				head.x = 0;
			
			else
				head.x += 1;
		}
		
	}
	
	public boolean checkBitten()
	{
		int len = parts.size();
		WormPiece head = parts.get(0);
		
		for(int i = 1; i < len; i++)
		{
			WormPiece part = parts.get(i);
			
			if(head.x == part.x && head.y == part.y && head.type == part.type)
				return true;
		}
		
		return false;
	}
}
