package kirk.EvilWorm;

import kirk.frameworkInterface.Game;
import kirk.frameworkInterface.Graphics;
import kirk.frameworkInterface.Graphics.PixmapFormat;
import kirk.frameworkInterface.Screen;


public class LoadingScreen extends Screen
{
	LoadingScreen(Game game)
	{
		super(game);
	}

	@Override
	public void update(float deltaTime) 
	{
		Graphics g = game.getGraphics();
		Assets.MainMenu = g.newPixmap("MainMenu.png", PixmapFormat.RGB565);
		Assets.HighScoreScreen = g.newPixmap("HighScoreScreen.png", PixmapFormat.RGB565);
		Assets.PauseMenu = g.newPixmap("PauseMenu.png", PixmapFormat.ARGB4444);
		Assets.GameOver = g.newPixmap("GameOver.png", PixmapFormat.ARGB4444);
		Assets.GameScreen = g.newPixmap("GameScreen.png", PixmapFormat.RGB565);
		Assets.SettingsScreen = g.newPixmap("SettingsScreen.png", PixmapFormat.RGB565);
		Assets.Dirt = g.newPixmap("Dirt.png", PixmapFormat.ARGB4444);
		Assets.ScaredFarmer = g.newPixmap("ScaredFarmer.png", PixmapFormat.ARGB4444);
		Assets.TremorBodyPiece = g.newPixmap("TremorBodyPiece.png", PixmapFormat.ARGB4444);
		Assets.TremorBodyPieceUp = g.newPixmap("TremorBodyPieceUp.png", PixmapFormat.ARGB4444);
		Assets.TremorHeadDown = g.newPixmap("TremorHeadDown.png", PixmapFormat.ARGB4444);
		Assets.TremorHeadUp = g.newPixmap("TremorHeadUp.png", PixmapFormat.ARGB4444);
		Assets.TremorHeadLeft = g.newPixmap("TremorHeadLeft.png", PixmapFormat.ARGB4444);
		Assets.TremorHeadRight = g.newPixmap("TremorHeadRight.png", PixmapFormat.ARGB4444);
		Assets.Numbers = g.newPixmap("Numbers.png", PixmapFormat.ARGB4444);
		Assets.GameControlsPink = g.newPixmap("GameControlsPink.png", PixmapFormat.ARGB4444);
		Assets.GameControlsBlack = g.newPixmap("GameControlsBlack.png", PixmapFormat.ARGB4444);
		Assets.GameControlsBlue = g.newPixmap("GameControlsBlue.png", PixmapFormat.ARGB4444);
		Assets.GameControlsGreen = g.newPixmap("GameControlsGreen.png", PixmapFormat.ARGB4444);
		
		Settings.Load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) 
	{
		
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
