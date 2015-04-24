package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;


import java.math.BigDecimal;
import java.math.RoundingMode;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalculator extends Activity {

	EditText editText_weight;
	Spinner spinnerHeight_inch, spinnerHeight_fit;
	Button btn_calculate;
	ImageButton btn_back;
	double weight=0.0;
	double height_fit = 4.0;
	double height_inch = 0.0;
	double total_height_inch;
	String[] Array_fit = {"4'", "5'", "6'", "7'" };
	String[] Array_inch = {"0\"", "1\"", "2\"", "3\"", "4\"", "5\"", "6\"", "7\"",
			"8\"", "9\"", "10\"", "11\"", "12\"" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bmicalculator);
		editText_weight = (EditText) findViewById(R.id.editText1_bodyweight);
		spinnerHeight_fit = (Spinner) findViewById(R.id.spinner_fit);
		spinnerHeight_inch = (Spinner) findViewById(R.id.Spinner01_inch);
		btn_calculate = (Button) findViewById(R.id.button1_calculate);
		
		
		btn_back = (ImageButton) findViewById(R.id.imageButton_back);
		// spinnerHeight_fit.setOnItemSelectedListener(BMICalculator.this);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item, Array_fit);
		spinnerHeight_fit.setAdapter(spinnerArrayAdapter);
		
		

		ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item, Array_inch);
		spinnerHeight_inch.setAdapter(spinnerArrayAdapter2);
		
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();

			}
		});


		spinnerHeight_fit
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						height_fit = height_fit + arg2;
						Log.e("height fit", "----------" + height_fit);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

		spinnerHeight_inch
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						height_inch = height_inch + arg2;
						Log.e("height inch", "----------" + height_inch);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

		btn_calculate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!editText_weight.getText().toString().equalsIgnoreCase("")
						&& !editText_weight.getText().toString()
								.equalsIgnoreCase(null)) {
					total_height_inch = (height_fit * 12) + height_inch;
					Double height_in_meter = total_height_inch * 0.0254;
					weight = Double.parseDouble(editText_weight.getText().toString());
					Log.e("weight", "----------" + weight);

					double bmi = weight / (height_in_meter * height_in_meter);
					BigDecimal bd = new BigDecimal(bmi).setScale(2, RoundingMode.HALF_EVEN);
					bmi = bd.doubleValue();
					Log.e("bmi", "----------" + bmi);
					
					AlertDialog.Builder builder = new AlertDialog.Builder(
							BMICalculator.this);
					builder.setCancelable(true);
					builder.setTitle("Your BMI is  " +bmi);
					builder.setMessage("\nUnderweight = <18.5 \nNormal weight = 18.5–24.9 \nOverweight = 25–29.9 \nObesity = BMI of 30 or greater\n");
							
					builder.setInverseBackgroundForced(false);
					
					builder.setPositiveButton("Okey",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									dialog.dismiss();
								}
							});
					
					AlertDialog alert = builder.create();
					alert.show();

				}
				else 
					  Toast.makeText(getApplicationContext(),"Please Insert Weight", Toast.LENGTH_LONG).show();
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bmicalculator, menu);
		return true;
	}

}
