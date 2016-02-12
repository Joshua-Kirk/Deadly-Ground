package kirk.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;
import android.content.res.AssetManager;

import kirk.frameworkInterface.FileIO;

public class AndroidFileIO implements FileIO
{
	AssetManager asset;
	String externalStoragePath;
	
	public AndroidFileIO(AssetManager asset)
	{
		this.asset = asset;
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}

	//read any sort of file, file must be a String Variable.  InputStream loads in files and the asset.open() outputs file contents into the view.	
	public InputStream readAsset(String fileName) throws IOException
	{
		return asset.open(fileName);
	}
	
	public InputStream readFile(String fileName) throws IOException
	{
		return new FileInputStream(externalStoragePath + fileName);
	}
	
	public OutputStream writeFile(String fileName) throws IOException
	{
		return new FileOutputStream(externalStoragePath + fileName);
	}
}
