package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.android.gms.drive.internal.n;
import com.google.android.gms.internal.ca;


import android.net.ParseException;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AddHealthReminder_Medicine extends Activity {
	
	static Context context;

	Spinner spinner_doesPerDay, spinner_duration,
			spinner_intervalBetweenDosage;

	Button btn_cancel, btn_save, btn_picktime;
    // TimePicker timePicker1;
	EditText editText_MedicineName;
	int value_does_per_day;
	int value_does_duration;
	int value_first_dose_hour;
	int value_first_dose_min;
	int value_interval_between_dosage;
	String mediDateEnd;
	String mediDateStart;
	
	 private ObjectPreference objectPreference;
	 ComplexPreferences complexPrefenreces;
	 

	private TimePicker timePicker1;
	
//	ArrayList<MedicineReminder> currentMedicine;

	private int hour;
	private int minute;
	Intent intent;
	int ReminderId;
	
    int total_Reminder;

	static final int TIME_DIALOG_ID = 999;

	String[] doesPerDay = { "1", "2", "3", "4", "5", "6", "7", "8" };
	String[] duration = { "1 day", "2 days", "3 days", "5 days", "7 days",
			"15 days", "1 month", "2 months", "3 months", "Life Time" };
	int[] duration_int = { 1, 2, 3, 5, 7, 15, 30, 60, 90, 1000 };
	String[] intervalBetweenDoages = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_health_reminder__medicine);
		setupUI(findViewById(R.id.linearLayoutMain));
		intent=getIntent();
		ReminderId=intent.getIntExtra("ReminderIdMedi", 0);
		
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		
		spinner_doesPerDay = (Spinner) findViewById(R.id.spinner1_DoesPerDay);
		spinner_duration = (Spinner) findViewById(R.id.spinner1_duration);
		spinner_intervalBetweenDosage = (Spinner) findViewById(R.id.spinner_intervalBetweenDosage);
		spinner_intervalBetweenDosage.setEnabled(false);
		
		context=AddHealthReminder_Medicine.this;
		objectPreference = (ObjectPreference) this.getApplication();
		complexPrefenreces = objectPreference.getComplexPreference();
			
		
		//for deleting sharedpref value
//		
		 total_Reminder=SharedPreferenceStoring.readPreferencesInt("TOTAL_NO_REMINDER",0);
		    Log.e("total medicine", "----------"+total_Reminder);
//		 
//		    SharedPreferenceStoring.ClearPreferencesInt("TOTAL_NO_REMINDER");
//		    total_Reminder=SharedPreferenceStoring.readPreferencesInt("TOTAL_NO_REMINDER",0);  
//		    Log.e("total medicine", "----------"+total_Reminder);
		
		btn_cancel = (Button) findViewById(R.id.button_cancel);
		btn_save = (Button) findViewById(R.id.button_Save);
		editText_MedicineName = (EditText) findViewById(R.id.editText_MedicineName);
		btn_picktime = (Button) findViewById(R.id.button_pickTime);
		// timePicker1=(TimePicker) findViewById(R.id.timePicker1);

		ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item, doesPerDay);
		spinner_doesPerDay.setAdapter(spinnerArrayAdapter1);

		ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item, duration);
		spinner_duration.setAdapter(spinnerArrayAdapter2);

		ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item,
				intervalBetweenDoages);
		spinner_intervalBetweenDosage.setAdapter(spinnerArrayAdapter3);
		
		if(ReminderId!=0)
		{
			Reminder r1Editing = complexPrefenreces.getObject("Reminder" + ReminderId,
					Reminder.class);
			MedicineReminder mrEditing = complexPrefenreces.getObject("Medicine_Reminder" + ReminderId,
					MedicineReminder.class);

			editText_MedicineName.setText(r1Editing.getName());
			for(int k=0; k<doesPerDay.length; k++)
			{
				if(Integer.parseInt(doesPerDay[k])==r1Editing.getDoesPerDay())
				spinner_doesPerDay.setSelection(k);	
			}
			
			for(int k=0; k<duration_int.length; k++)
			{
				if(duration_int[k]==mrEditing.getDoesDuration())
				
				spinner_duration.setSelection(k);	
			}
			
			for(int k=0; k<intervalBetweenDoages.length; k++)
			{
				if(Integer.parseInt(intervalBetweenDoages[k])==mrEditing.getintervalBetweenDosage())
				
				spinner_intervalBetweenDosage.setSelection(k);
			}
		
			hour=mrEditing.getFirstDoeshHour();
			minute=mrEditing.getFirstDoeshMinute();
			
		}

		spinner_doesPerDay
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						// your code here
						value_does_per_day = Integer
								.parseInt(doesPerDay[position]);
						if (value_does_per_day > 1) {
							spinner_intervalBetweenDosage.setAlpha(1.0f);
							spinner_intervalBetweenDosage.setEnabled(true);

						} else {
							spinner_intervalBetweenDosage.setAlpha(0.5f);
							spinner_intervalBetweenDosage.setEnabled(false);
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {
						// your code here
					}

				});

		spinner_duration
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						// your code here
						value_does_duration = duration_int[position];
					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {
						// your code here
					}

				});

		spinner_intervalBetweenDosage
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						// your code here
						value_interval_between_dosage = Integer
								.parseInt(intervalBetweenDoages[position]);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {
						// your code here
					}

				});

		btn_picktime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				

				showDialog(TIME_DIALOG_ID);

			}

		});

		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						AddHealthReminder_Medicine.this);

				// set title
				alertDialogBuilder.setTitle("Alert!");

				// set dialog message
				alertDialogBuilder
						.setMessage(
								"Do you want to leave this page? Unsaved data will be lost.")
						.setCancelable(true)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, close
										// current activity
										Intent startNewActivityOpen = new Intent(AddHealthReminder_Medicine.this,
												HealthReminder.class); // explicit intent
										startActivity(startNewActivityOpen);
										AddHealthReminder_Medicine.this
												.finish();
										
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

			}
		});

		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						AddHealthReminder_Medicine.this);

				// set title
				alertDialogBuilder.setTitle("Summary of Medicine");

				String medi_Name = "Medicine Name : "
						+ editText_MedicineName.getText().toString().trim();

				final Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

				String medi_StartDate = "Start Date: "
						+ sdf1.format(c.getTime());
				mediDateStart=sdf1.format(c.getTime());

				String[] DoesTime = new String[value_does_per_day];

				Log.e("1st does hour: min", value_first_dose_hour + ":"
						+ value_first_dose_min);
				Log.e("hour intrval", "----------" + value_does_per_day);
				Log.e("value does per day", "----------"
						+ value_interval_between_dosage);

				int hour_interval = 0;
				// final Calendar c1 = Calendar.getInstance();
				if (value_does_per_day > 1) {
					for (int i = 0; i < value_does_per_day; i++) {
						int hour = value_first_dose_hour + hour_interval;
						// c1.add(value_first_dose_hour, hour_interval);
						int doesNo = i + 1;

						String time24 = hour + ":" + value_first_dose_min;

						final SimpleDateFormat sdf = new SimpleDateFormat(
								"H:mm");
						SimpleDateFormat _12HourSDF = new SimpleDateFormat(
								"hh:mm a");

						Date dateObj = null;
						try {
							dateObj = sdf.parse(time24);
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						DoesTime[i] = "Does " + doesNo + " Time :"
								+ _12HourSDF.format(dateObj);
						hour_interval = hour_interval
								+ value_interval_between_dosage;
						Log.e("DoesTime :", "------------" + DoesTime[i]);
					}
				}
				c.add(Calendar.DATE, value_does_duration); // number of days to
															// add, can also use
															// Calendar.DAY_OF_MONTH
															// in place of
															// Calendar.DATE
				// String output = sdf1.format(c.getTime());

				String medicine_End;
				
				if (value_does_duration == 1000) {
					medicine_End = "End Date : Life Time";
					mediDateEnd="Life Time";
				} else
				{
					medicine_End = "End Date : " + sdf1.format(c.getTime());
					mediDateEnd=sdf1.format(c.getTime());
				}

				String summery_message = medi_Name + "\n\n" + medi_StartDate
						+ "\n" + medicine_End + "\n";

				if (value_does_per_day > 1) {
					for (int i = 0; i < value_does_per_day; i++) {

						summery_message = summery_message + "\n" + DoesTime[i];

					}
				}

				alertDialogBuilder
						.setMessage(summery_message)
						.setCancelable(true)
						.setNegativeButton("Save Reminder",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										
										MedicineReminder mR1=new MedicineReminder();
										mR1.setMedicineName(editText_MedicineName.getText().toString().trim());
										mR1.setDoesPerDay(value_does_per_day);
										mR1.setDoesDuration(value_does_duration);
										mR1.setFirstDoeshHour(value_first_dose_hour);
										mR1.setFirstDoeshMinute(value_first_dose_min);
										mR1.setStartDate(mediDateStart);
										mR1.setEndDate(mediDateEnd);
										if(value_does_per_day>1)
										{
											mR1.setintervalBetweenDosage(value_interval_between_dosage);
										}
											
										
										if(ReminderId==0)
										{
											total_Reminder=SharedPreferenceStoring.readPreferencesInt("TOTAL_NO_REMINDER",0);
										    Log.e("total medicine", "----------"+total_Reminder);
											total_Reminder++;
											SharedPreferenceStoring.savePreferencesInt(AddHealthReminder_Medicine.this,"TOTAL_NO_REMINDER", total_Reminder);
										   
											Reminder r1=new Reminder();
											r1.setId(total_Reminder);
											r1.setName(editText_MedicineName.getText().toString().trim());
											r1.setStartDate(mediDateStart);
											r1.setEndDate(mediDateEnd);
											r1.setReminderType(1);
											r1.setDoesPerDay(value_does_per_day);
											
										 
										Log.e("total medicine", "----------"+total_Reminder);
										
										     if(complexPrefenreces != null) {
										        complexPrefenreces.putObject("Reminder"+total_Reminder, r1);
										        complexPrefenreces.putObject("Medicine_Reminder"+total_Reminder, mR1);
										        complexPrefenreces.commit();
										    } else {
										       // android.util.Log.e(TAG, "Preference is null");
										    	Log.e("Pref is Null", "--------Preference is null");
										    }
										     
										}
										
									else if(ReminderId!=0 )
									{
										Reminder r1=new Reminder();
										r1.setId(total_Reminder);
										r1.setName(editText_MedicineName.getText().toString());
										r1.setStartDate(mediDateStart);
										r1.setEndDate(mediDateEnd);
										r1.setReminderType(1);
										r1.setDoesPerDay(value_does_per_day);
										
										if(complexPrefenreces != null) {
									        complexPrefenreces.putObject("Reminder"+ReminderId, r1);
									        complexPrefenreces.putObject("Medicine_Reminder"+ReminderId, mR1);
									        complexPrefenreces.commit();
									    } else {
									       // android.util.Log.e(TAG, "Preference is null");
									    	Log.e("Pref is Null", "--------Preference is null");
									    }
									}
										
										
										

										//addTask(mR1);
										Intent startNewActivityOpen = new Intent(AddHealthReminder_Medicine.this,
												HealthReminder.class); // explicit intent
										startActivity(startNewActivityOpen);
										AddHealthReminder_Medicine.this.finish();

									}
								})
						.setPositiveButton("Edit",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, close
										// current activity
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

			}
		});

	}

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}

	public void setupUI(View view) {

		// Set up touch listener for non-text box views to hide keyboard.
		if (!(view instanceof EditText)) {

			view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					hideSoftKeyboard(AddHealthReminder_Medicine.this);
					return false;
				}

			});
		}

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupUI(innerView);
			}
		}
	}

//	public void addTask(MedicineReminder mR) {
//		if (null == currentMedicine) {
//			currentMedicine = new ArrayList<MedicineReminder>();
//		}
//		currentMedicine.add(mR);
//
//		// save the task list to preference
//		SharedPreferences prefs = getSharedPreferences("SHARED_PREFS_FILE",
//				Context.MODE_PRIVATE);
//		Editor editor = prefs.edit();
//		try {
//			editor.putString("Medicine",
//					ObjectSerializer.serialize(currentMedicine));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		editor.commit();
//	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener, hour, minute,
					false);

		}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;

			value_first_dose_hour = hour;
			value_first_dose_min = minute;

			// // set current time into textview
			// tvDisplayTime.setText(new StringBuilder().append(pad(hour))
			// .append(":").append(pad(minute)));

		}
	};

	// private static String pad(int c) {
	// if (c >= 10)
	// return String.valueOf(c);
	// else
	// return "0" + String.valueOf(c);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_health_reminder__medicine, menu);
		return true;
	}

}
