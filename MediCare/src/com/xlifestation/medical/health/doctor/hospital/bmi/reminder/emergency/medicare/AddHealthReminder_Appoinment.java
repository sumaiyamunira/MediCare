package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class AddHealthReminder_Appoinment extends Activity {

	Button btn_cancel, btn_save, btn_picktime, btn_datepicker;

	EditText edtText_appointmentDetails, edtText_serialNo;

	private int hour;
	private int minute;
	private int year;
	private int month;
	private int day;

	String serial_no;
	String app_Name;
	String date;
	String time;
	Date dateObj2 = null;
	// private TimePicker timePicker1;
	static final int TIME_DIALOG_ID = 999;
	static final int DATE_DIALOG_ID = 9999;

	private ObjectPreference objectPreference;
	ComplexPreferences complexPrefenreces;
	Intent intent;
	int ReminderId;

	int total_Reminder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_health_reminder__appoinment);
		setupUI(findViewById(R.id.linearlayout_main));
		intent = getIntent();
		ReminderId = intent.getIntExtra("ReminderIdApp", 0);

		btn_cancel = (Button) findViewById(R.id.button_cancel);
		btn_save = (Button) findViewById(R.id.button_Save);
		btn_picktime = (Button) findViewById(R.id.button_pickTime);
		btn_datepicker = (Button) findViewById(R.id.button_datePicker);

		edtText_appointmentDetails = (EditText) findViewById(R.id.editText_appointmentDetails);
		edtText_serialNo = (EditText) findViewById(R.id.editText_SerialNo);

		objectPreference = (ObjectPreference) this.getApplication();
		complexPrefenreces = objectPreference.getComplexPreference();

		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		if (ReminderId != 0) {
			Reminder r1Editing = complexPrefenreces.getObject("Reminder"
					+ ReminderId, Reminder.class);
			AppointmentReminder arEditing = complexPrefenreces.getObject(
					"Appointment_Reminder" + ReminderId,
					AppointmentReminder.class);
			edtText_appointmentDetails.setText(r1Editing.getName());
			edtText_serialNo.setText(Integer.toString(r1Editing.getSerialNo()));
			hour = arEditing.getTimeHour();
			minute = arEditing.getTimeMinute();
			year = arEditing.getDateYear();
			month = arEditing.getDateMonth();
			day = arEditing.getDateDay();

		}

		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						AddHealthReminder_Appoinment.this);

				// set title
				alertDialogBuilder.setTitle("Alert!");

				// set dialog message
				alertDialogBuilder
						.setMessage(
								"Do you want to leave this page? Unsaved data will be lost.")
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, close
										// current activity
										Intent startNewActivityOpen = new Intent(AddHealthReminder_Appoinment.this,
												HealthReminder.class); // explicit intent
										startActivity(startNewActivityOpen);
										AddHealthReminder_Appoinment.this
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

		btn_picktime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(TIME_DIALOG_ID);

			}

		});

		btn_datepicker.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID);

			}

		});

		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						AddHealthReminder_Appoinment.this);

				// set title
				alertDialogBuilder.setTitle("Summary of Appointment");

				app_Name = edtText_appointmentDetails.getText().toString().trim();
				String time24 = hour + ":" + minute;

				final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
				SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");

				Date dateObj = null;
				try {
					dateObj = sdf.parse(time24);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				serial_no = "Serial No : "
						+ edtText_serialNo.getText().toString().trim();
				time = _12HourSDF.format(dateObj);
				String time_details = "Time: " + _12HourSDF.format(dateObj);
				SimpleDateFormat previous_format = new SimpleDateFormat(
						"d-M-yyyy");
				SimpleDateFormat simple_date_format = new SimpleDateFormat(
						"dd-MM-yyyy");

				try {
					dateObj2 = previous_format.parse(day + "-" + month + "-"
							+ year);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				date = simple_date_format.format(dateObj2);
				String date_details = "Date : "
						+ simple_date_format.format(dateObj2);

				String summery_message = app_Name + "\n\n" + serial_no + "\n"
						+ date_details + "\n" + time_details;

				alertDialogBuilder
						.setMessage(summery_message)
						.setCancelable(true)
						.setNegativeButton("Save Reminder",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										AppointmentReminder appR1 = new AppointmentReminder();
										appR1.setAppointmentName(app_Name);
										appR1.setTime(time);
										appR1.setTimeHour(hour);
										appR1.setTimeMinute(minute);
										appR1.setDateYear(year);
										appR1.setDateMonth(month);
										appR1.setDateDay(day);
										appR1.setSerialNo(Integer
												.parseInt(edtText_serialNo
														.getText().toString()));
										appR1.setDate(dateObj2);

										if (ReminderId == 0) {
											total_Reminder = SharedPreferenceStoring
													.readPreferencesInt(
															"TOTAL_NO_REMINDER",
															0);
											Log.e("total medicine",
													"----------"
															+ total_Reminder);

											total_Reminder++;

											SharedPreferenceStoring
													.savePreferencesInt(
															AddHealthReminder_Appoinment.this,
															"TOTAL_NO_REMINDER",
															total_Reminder);
											Log.e("total medicine",
													"----------"
															+ total_Reminder);

											Reminder r1 = new Reminder();
											r1.setId(total_Reminder);
											r1.setName(app_Name);
											r1.setStartDate(date);
											r1.setReminderType(0);
											r1.setTime(time);
											r1.setSerialNo(Integer
													.parseInt(edtText_serialNo
															.getText()
															.toString().trim()));

											if (complexPrefenreces != null) {
												complexPrefenreces
														.putObject(
																"Reminder"
																		+ total_Reminder,
																r1);
												complexPrefenreces
														.putObject(
																"Appointment_Reminder"
																		+ total_Reminder,
																appR1);
												complexPrefenreces.commit();
											} else {
												// android.util.Log.e(TAG,
												// "Preference is null");
												Log.e("Pref is Null",
														"--------Preference is null");
											}

										} else if (ReminderId != 0) {

											Reminder r1 = new Reminder();
											r1.setId(ReminderId);
											r1.setName(app_Name);
											r1.setStartDate(date);
											r1.setReminderType(0);
											r1.setTime(time);
											r1.setSerialNo(Integer
													.parseInt(edtText_serialNo
															.getText()
															.toString().trim()));

											if (complexPrefenreces != null) {
												complexPrefenreces
														.putObject("Reminder"
																+ ReminderId,
																r1);
												complexPrefenreces.putObject(
														"Appointment_Reminder"
																+ ReminderId,
														appR1);
												complexPrefenreces.commit();
											} else {
												// android.util.Log.e(TAG,
												// "Preference is null");
												Log.e("Pref is Null",
														"--------Preference is null");
											}

										}

										// addTask(mR1);
										Intent startNewActivityOpen = new Intent(AddHealthReminder_Appoinment.this,
												HealthReminder.class); // explicit intent
										startActivity(startNewActivityOpen);
										AddHealthReminder_Appoinment.this
												.finish();
										onBackPressed();

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

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener, hour, minute,
					false);

		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);

		}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;

			// value_first_dose_hour = hour;
			// value_first_dose_min = minute;

			// // set current time into textview
			// tvDisplayTime.setText(new StringBuilder().append(pad(hour))
			// .append(":").append(pad(minute)));

		}
	};

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			// tvDisplayDate.setText(new StringBuilder().append(month + 1)
			// .append("-").append(day).append("-").append(year)
			// .append(" "));

			// set selected date into datepicker also
			// dpResult.init(year, month, day, null);

		}
	};

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
					hideSoftKeyboard(AddHealthReminder_Appoinment.this);
					return false;
				}

			});
		}
		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupUI(innerView);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_health_reminder__appoinment, menu);
		return true;
	}

}
