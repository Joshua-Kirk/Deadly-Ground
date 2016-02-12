package kirk.framework;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;
import android.util.Log;

public class KeyListener extends Activity implements OnKeyListener
{
	StringBuilder builder = new StringBuilder();
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		textView = new TextView(this);
		textView.setText("Press a key");
		textView.setOnKeyListener(this);
		textView.setFocusableInTouchMode(true);
		textView.requestFocus();
		setContentView(textView);	
	}
	
	public boolean onKey(View view, int keyCode, KeyEvent event)
	{
		builder.setLength(0);
		switch(event.getAction())
		{
			case KeyEvent.ACTION_DOWN: builder.append("Down, "); break;
			case KeyEvent.ACTION_UP: builder.append("Up, "); break;
		}
		
		builder.append(event.getKeyCode());
		builder.append(", ");
		builder.append((char) event.getUnicodeChar());
		String text = builder.toString();
		Log.d("KeyTest", text);
		textView.setText(text);
		
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
			return false;
				
	    else
		    return true;
	}

}
