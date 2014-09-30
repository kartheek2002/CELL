package com.example.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	// constants for saving / restoring state
	private static final String BILL_TOTAL = "BILL_TOTAL";
	private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";
	
	private double currentBillTotal; // user entered bill amount
	private int currentCustomPercent; // tip percent from seek bar
	private EditText tip10EditText; // displays 10% tip
	private EditText total10EditText; // displays total with 10% tip
	private EditText tip15EditText; // displays 15% tip
	private EditText total15EditText; // displays total with 15% tip
	private EditText billEditText; // accepts user input for bill total
	private EditText tip20EditText; // displays 20% tip
	private EditText total20EditText; // displays total with 20% tip
	private EditText tipCustomEditText; // displays total with custom tip
	private EditText totalCustomEditText; // displays custom tip amount
	private TextView customTipTextView; // displays custom tip percentage
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Freshly started
		if (savedInstanceState == null) {
			//For debugging
			Log.d("TipCalDebug", "TipCalculator.java onCreate called - savedInstanceState is null");
			
			currentBillTotal = 0.0;
			currentCustomPercent = 18;
		}
		else { //restored from memory
			//For debugging
			Log.d("TipCalDebug", "TipCalculator.java onCreate called - savedInstanceState is NOT null");
			
			currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
			currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
		}
		
		//references for view objects
		tip10EditText = (EditText) findViewById(R.id.tip10EditText);
		total10EditText = (EditText) findViewById(R.id.total10EditText);
		tip15EditText = (EditText) findViewById(R.id.tip15EditText);
		total15EditText = (EditText) findViewById(R.id.total15EditText);
		tip20EditText = (EditText) findViewById(R.id.tip20EditText);
		total20EditText = (EditText) findViewById(R.id.total20EditText);
		
		customTipTextView = (TextView) findViewById(R.id.customTipTextView);
		tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
		totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);
		
		billEditText = (EditText) findViewById(R.id.billEditText);
		billEditText.addTextChangedListener(billEditTextWatcher);
		
		SeekBar customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
		customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
	}
	
	// updates 10, 15, and 20 percent tip EditTexts
	private void updateStandard() {
		// calculate bill total with a ten percent tip
		double tenPercentTip = currentBillTotal * 0.1;
		double tenPercentTotal = currentBillTotal + tenPercentTip;
		
		// set text to tip
		tip10EditText.setText(String.format("%.02f", tenPercentTip));
		total10EditText.setText(String.format("%.02f", tenPercentTotal));
		
		// calculate bill total with a fifteen percent tip
		double fifteenPercentTip = currentBillTotal * 0.15;
		double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;
			
		// set text to tip
		tip15EditText.setText(String.format("%.02f", fifteenPercentTip));
		total15EditText.setText(String.format("%.02f", fifteenPercentTotal));
		
		// calculate bill total with a twenty percent tip
		double twentyPercentTip = currentBillTotal * 0.2;
		double twentyPercentTotal = currentBillTotal + twentyPercentTip;
				
		// set text to tip
		tip20EditText.setText(String.format("%.02f", twentyPercentTip));
		total20EditText.setText(String.format("%.02f", twentyPercentTotal));
	}
	
	// updates the custom tip and total
	private void updateCustom(){
		// set customTipTextView's text to match the position of SeekBar
		customTipTextView.setText(currentCustomPercent + "%");
		
		// calculate tip and total
		double customTipAmount = currentBillTotal * currentCustomPercent * .01;
		double customTotalAmount = currentBillTotal + customTipAmount;
		
		// display amounts
		tipCustomEditText.setText(String.format("%.02f", customTipAmount));
		totalCustomEditText.setText(String.format("%.02f", customTotalAmount));
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		outState.putDouble(BILL_TOTAL, currentBillTotal);
		outState.putInt(CUSTOM_PERCENT, currentCustomPercent);
	}
	
	// called when the user changes the position of the SeekBar
	private OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			// TODO Auto-generated method stub
			currentCustomPercent = seekBar.getProgress();
			updateCustom();
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
		}
	};
	
	// event-handling object that responds to billEditText's events
	private TextWatcher billEditTextWatcher = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		// called when user enters a number
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			// convert text to a double
			try {
				currentBillTotal = Double.parseDouble(s.toString());
			} catch (NumberFormatException e){
				// default if exception occurs
				currentBillTotal = 0.0;
			}
			// update all other tips
			updateStandard();
			updateCustom();
		}
		
	};
	
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
