package com.example.iterationone;

import android.support.v7.app.ActionBarActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntentFilter extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_filter);
		
		final Button call = (Button) findViewById(R.id.btn_call);		
		call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	try {
            		EditText input = (EditText)findViewById(R.id.input_filter);
            		String dial_number = input.getText().toString();
            		
                    Intent dial = new Intent();
                    dial.setAction("android.intent.action.CALL");
                    dial.setData(Uri.parse("tel:" + dial_number));
                    startActivity(dial);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }
            }
        });
		
		final Button web = (Button) findViewById(R.id.btn_web);
		web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	try {
            		EditText input = (EditText)findViewById(R.id.input_filter);
            		String site = input.getText().toString();
            		
                    Intent viewPage = new Intent();
                    viewPage.setAction("android.intent.action.VIEW");
                    viewPage.setData(Uri.parse("http:" + site));
                    startActivity(viewPage);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Viewing page", "View page failed", activityException);
                }
            }
        });
		
		final Button message = (Button) findViewById(R.id.btn_text_msg);
		message.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	try {
            		EditText input = (EditText)findViewById(R.id.input_filter);
            		String text_number = input.getText().toString();
            		
                    Intent text = new Intent();
                    text.setAction("android.intent.action.SENDTO");
                    text.setData(Uri.parse("smsto:" + text_number));
                    startActivity(text);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Sending a Text", "Text failed", activityException);
                }
            }
        });
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intent_filter, menu);
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
