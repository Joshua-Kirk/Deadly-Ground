package kirk.deadlyGound;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import kirk.frameworkInterface.FileIO;

public class Settings
{
	public static boolean SoundEnabled = true;
	public static int[] highscore = new int[] {150, 100, 80, 50, 30};
	
	public static void Load(FileIO file)
	{
		BufferedReader in = null;
		
		try
		{
			in = new BufferedReader(new InputStreamReader(file.readFile(".EvilWorm")));
			SoundEnabled = Boolean.parseBoolean(in.readLine());
			
			for(int i = 0; i < 5; i++)
			{
				highscore[i] = Integer.parseInt(in.readLine());
			}
		}
		
		catch(IOException E)
		{	
			
		}
		
		catch(NumberFormatException E)
		{
			
		}
		
		finally
		{
			try
			{
				if(in != null)
					in.close();
			}
			
			catch(IOException E)
			{
				
			}
		}
	}
	
	public static void Save(FileIO file)
	{
		BufferedWriter out = null;
		
		try
		{
			out = new BufferedWriter(new OutputStreamWriter(file.writeFile("EvilWorm")));
			out.write(Boolean.toString(SoundEnabled));
			
			for(int i = 0; i < 5; i++)
			{
				out.write(Integer.toString(highscore[i]));
			}
		}
		
		catch(IOException E)
		{
			
		}
		
		finally
		{
			try
			{
				if(out != null)
					out.close();
			}
			
			catch(IOException E)
			{
				
			}
			
		}
		
	}
	
	public static void addScore(int score) 
	{ 
		for (int i = 0; i < 5; i++)
		{
			if (highscore[i] < score) 
			{ 
				for (int j = 4; j > i; j--)
				
					highscore[j] = highscore[j - 1];
					highscore[i] = score;
					break;			
			}
		}
	}
}




























