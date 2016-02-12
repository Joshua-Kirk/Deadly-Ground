package kirk.frameworkInterface;

import kirk.frameworkInterface.Graphics.PixmapFormat;

public interface Pixmap 
{
	public int getWidth();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();
}
