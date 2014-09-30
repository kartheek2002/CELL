package com.example.iterationone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	final public static int SECOND_ACTIVITY = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button sigin = (Button) findViewById(R.id.signin);
		sigin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	EditText username = (EditText)findViewById(R.id.input_username);
            	EditText password = (EditText)findViewById(R.id.input_password);
            	
            	String user = username.getText().toString();
            	String pass = password.getText().toString();
            	
            	String sResult = getString(R.string.res_success);
            	String fResult = getString(R.string.res_fail);
            	
            	if (user.equals("Android") && pass.equals("123123")) {
            		TextView view = (TextView) findViewById(R.id.result);
                	view.setText(sResult);
                	
                	Intent intent = new Intent(MainActivity.this, AfterLoginActivity.class);
                	intent.putExtra("username", user); //Optional parameters
                	intent.putExtra("password", pass); //Optional parameters
                	MainActivity.this.startActivity(intent);
            	}
            	else {
            		TextView view = (TextView) findViewById(R.id.result);
                	view.setText(fResult);
            	}
            }
        });
		
		final Button launch = (Button) findViewById(R.id.launch);
		launch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent intent = new Intent(MainActivity.this, GetName.class);
            	//MainActivity.this.startActivity(intent);
            	
            	startActivityForResult(intent, SECOND_ACTIVITY);
            }
        });
		
		final Button tryIntent = (Button) findViewById(R.id.try_intent);
		tryIntent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent intent = new Intent(MainActivity.this, IntentFilter.class);
            	MainActivity.this.startActivity(intent);
            }
        });
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK){
			// Do something
			Bundle b = data.getExtras();
			String name = b.getString("name");
			
			TextView view = (TextView) findViewById(R.id.result);
			view.setText("Hi! " + name);
			
			if(name != null) {
				view.setText("Hi! " + name);
			}
			else {
				view.setText("Can't get name!");
			}
			
		}
		else if(resultCode==Activity.RESULT_CANCELED){
			// Do something else
			TextView view = (TextView) findViewById(R.id.result);
			view.setText("Cancelled!");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
