package de.soenkedomroese.RandomQuote;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RandomQuote extends Activity {
	DBAdapter db = new DBAdapter(this);
	EditText Quote;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Capture our button from layout
		Button setButton = (Button) findViewById(R.id.go);
		Button getButton = (Button) findViewById(R.id.genRan);
		// Register the onClick listener with the implementation above
		setButton.setOnClickListener(mAddListener);
		getButton.setOnClickListener(mAddListener);
	}
	
	
	// Create an anonymous implementation of OnClickListener
	private OnClickListener mAddListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			switch(v.getId())
			{
			case R.id.go:
				db.open();
				long id = 0;
				// do something when the button is clicked
				try
				{
					Quote = (EditText)findViewById(R.id.Quote);
					db.insertQuote(Quote.getText().toString());
			
					id = db.getAllEntries();
			
					Context context = getApplicationContext();
					CharSequence text = "The quote '" + Quote.getText() + "' was added successfully!\nQuotes Total = " + id;
					int duration = Toast.LENGTH_LONG;
			
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					Quote.setText("");
				}
				catch (Exception ex)
				{
					Context context = getApplicationContext();
					CharSequence text = ex.toString() + "ID = " + id;
					int duration = Toast.LENGTH_LONG;
			
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
				db.close();
				break;
    		case R.id.genRan:
    			db.open();
    			//long id1 = 0;
    			// do something when the button is clicked
    			try
    			{
    				String quote = "";
    				quote = db.getRandomEntry();
    				Context context = getApplicationContext();
    				CharSequence text = quote;
    				int duration = Toast.LENGTH_LONG;

    				Toast toast = Toast.makeText(context, text, duration);
    				toast.show();
    			}
    			catch (Exception ex)
    			{
    				Context context = getApplicationContext();
    				CharSequence text = ex.toString();
    				int duration = Toast.LENGTH_LONG;

    				Toast toast = Toast.makeText(context, text, duration);
    				toast.show();
    			}
    			db.close();
    		}
		}
    };	
}