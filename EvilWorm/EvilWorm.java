package kirk.EvilWorm;

import kirk.framework.AndroidGame;
import kirk.frameworkInterface.Screen;

public class EvilWorm extends AndroidGame
{
	public Screen getStartScreen()
	{
		return new LoadingScreen(this);
	}
}
