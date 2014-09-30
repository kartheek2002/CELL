package com.example.alram;

import android.support.v7.app.ActionBarActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button start = (Button) findViewById(R.id.submit);
		start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startAlert(v);
            }
		});
	}
	
	public void startAlert(View view) {
	    EditText text = (EditText) findViewById(R.id.input);
	    int i = Integer.parseInt(text.getText().toString());
	    
	    Intent intent = new Intent(this, AlarmReceiver.class);
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
	    
	    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
	    
	    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), pendingIntent);
	    Toast.makeText(this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();
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
