package kirk.EvilWorm;


public class WormPiece 
{
	public int type;
	public int direction;
	public int x,y;
	
	public WormPiece(int x, int y, int type, int direction)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		this.direction = direction;
	}
}
