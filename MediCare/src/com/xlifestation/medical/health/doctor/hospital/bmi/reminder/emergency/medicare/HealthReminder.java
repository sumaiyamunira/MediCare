package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class HealthReminder extends Activity {
	ImageButton add_btn, back_btn;
	// ListView list_reminder;
	// ArrayAdapterItemMedicineReminder adapter;
	static Context context;

	List<String> listDataHeader;
	HashMap<String, List<Reminder>> listDataChild;

	Reminder ListChildReminder;
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;

	int total_Reminder;

	private ObjectPreference objectPreference;
	ComplexPreferences complexPreferences;
	

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_reminder);
		add_btn = (ImageButton) findViewById(R.id.imageButton_add);
		back_btn = (ImageButton) findViewById(R.id.imageButton_back);
		context = getApplicationContext();
		// list_reminder=(ListView) findViewById(R.id.listView_health_reminder);
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		objectPreference = (ObjectPreference) this.getApplication();
		complexPreferences = objectPreference.getComplexPreference();
	    //SharedPreferenceStoring.ClearPreferencesInt("TOTAL_NO_REMINDER");
		total_Reminder = SharedPreferenceStoring.readPreferencesInt(
				"TOTAL_NO_REMINDER", 0);
		Log.e("total medicine", "----------" + total_Reminder);

		prepareListData();

		listAdapter = new ExpandableListAdapter(HealthReminder.this,
				listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " Expanded",
				// Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " Collapsed",
				// Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub

				ListChildReminder = listDataChild.get(
						listDataHeader.get(groupPosition)).get(childPosition);
				int r1Id = ListChildReminder.getId();
				int r1Type = ListChildReminder.getReminderType();
				String name;
				String startDate;
				String endDate;
				String summery_message = null;
				String time;
				String serialNo;

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						HealthReminder.this);

				alertDialogBuilder.setTitle("Reminder Details");

				if (r1Type == 1) {
					name = "Medicine Name : " + ListChildReminder.getName();
					startDate = "Start Date :"
							+ ListChildReminder.getStartDate();
					MedicineReminder mr1 = complexPreferences.getObject(
							"Medicine_Reminder" + r1Id, MedicineReminder.class);
					endDate = "End Date : " + ListChildReminder.getEndDate();
					String[] DoesTime = new String[ListChildReminder
							.getDoesPerDay()];
					int hour_interval = 0;
					if (ListChildReminder.getDoesPerDay() > 1) {
						for (int k = 0; k < ListChildReminder.getDoesPerDay(); k++) {
							int hour = mr1.getFirstDoeshHour() + hour_interval;
							int doesNo = k + 1;

							String time24 = hour + ":"
									+ mr1.getFirstDoeshMinute();

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

							DoesTime[k] = "Does " + doesNo + " Time :"
									+ _12HourSDF.format(dateObj);
							hour_interval = hour_interval
									+ mr1.getintervalBetweenDosage();
							Log.e("DoesTime :", "------------" + DoesTime[k]);
						}
					}

						summery_message = name + "\n\n" + startDate + "\n"
								+ endDate + "\n";

						if (ListChildReminder.getDoesPerDay() > 1) {
							for (int i = 0; i < ListChildReminder
									.getDoesPerDay(); i++) {

								summery_message = summery_message + "\n"
										+ DoesTime[i];

							}
						}

					
				}

				if (r1Type == 0) {
					name = "Appointment : " + ListChildReminder.getName();
					time = "Time : " + ListChildReminder.getTime();
					serialNo = "Serial No : " + ListChildReminder.getSerialNo();
					startDate = "Date :" + ListChildReminder.getStartDate();
					summery_message = name + "\n\n" + serialNo + "\n"
							+ startDate + "\n" + time;

				}

				alertDialogBuilder
						.setMessage(summery_message)
						.setCancelable(true)
						.setNegativeButton("Edit",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										Intent startNewActivityOpen = new Intent(
												HealthReminder.this,
												AddHealthReminderTab.class); // explicit
																				// intent

										
										if (ListChildReminder.getReminderType() == 0) {
											startNewActivityOpen.putExtra(
													"TabNo", 2);
											
											startNewActivityOpen.putExtra(
													"ReminderIdApp",
													ListChildReminder.getId());
										}

										else if (ListChildReminder
												.getReminderType() == 1) {
											startNewActivityOpen.putExtra(
													"TabNo", 1);
											Log.e("Reminder Id from listchild","--------"+ListChildReminder.getId());
											startNewActivityOpen.putExtra(
													"ReminderIdMedi",
													ListChildReminder.getId());
										}
										startActivity(startNewActivityOpen);
										HealthReminder.this.finish();

										

									}
								})
						.setPositiveButton("Delete",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, close
										// current activity
										dialog.cancel();
										AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(
												HealthReminder.this);

										// set title
										alertDialogBuilder2
												.setTitle("Delete Reminder");

										// set dialog message
										alertDialogBuilder2
												.setMessage(
														"Are you sure you want to delete?")
												.setCancelable(true)
												.setPositiveButton(
														"Yes",
														new DialogInterface.OnClickListener() {
															public void onClick(
																	DialogInterface dialog,
																	int id) {
																ListChildReminder
																		.setStatus(1);

																
																if (complexPreferences != null) {
																	complexPreferences
																			.putObject(
																					"Reminder"
																							+ ListChildReminder
																									.getId(),
																					ListChildReminder);
																	complexPreferences
																			.commit();
																} else {
																	// android.util.Log.e(TAG,
																	// "Preference is null");
																	Log.e("Pref is Null",
																			"--------Preference is null");
																}
																Intent startNewActivityOpen = new Intent(HealthReminder.this,
																		HealthReminder.class); // explicit intent
																startActivity(startNewActivityOpen);
																HealthReminder.this
																		.finish();

															}
														})
												.setNegativeButton(
														"No",
														new DialogInterface.OnClickListener() {
															public void onClick(
																	DialogInterface dialog,
																	int id) {
																// if this
																// button is
																// clicked, just
																// close
																// the dialog
																// box and do
																// nothing
																dialog.cancel();
															}
														});

										// create alert dialog
										AlertDialog alertDialog2 = alertDialogBuilder2
												.create();

										// show it
										alertDialog2.show();

									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

				return false;
			}
		});

		add_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent startNewActivityOpen = new Intent(HealthReminder.this,
						AddHealthReminderTab.class); // explicit intent
				startActivity(startNewActivityOpen);
				HealthReminder.this
				.finish();

			}

		});

		back_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				onBackPressed();
			}

		});

	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<Reminder>>();

		// Adding child data
		listDataHeader.add("Appointment Reminder");
		listDataHeader.add("Medicine Reminder");

		// Adding child data
		List<Reminder> appointment = new ArrayList<Reminder>();

		List<Reminder> medicine = new ArrayList<Reminder>();
		listDataChild.clear();
		if (total_Reminder > 0) {

			for (int i = 1; i <= total_Reminder; i++) {
				Reminder r1 = complexPreferences.getObject("Reminder" + i,
						Reminder.class);
				Log.e("Reminder details ", "---------" + r1.getName());
				if (r1.getStatus() == 0) {
					if (r1.getReminderType() == 0) {
						appointment.add(r1);
					}

					else if (r1.getReminderType() == 1) {
						medicine.add(r1);
					}

				}

			}
		}

		listDataChild.put(listDataHeader.get(0), appointment); // Header, Child
																// data
		listDataChild.put(listDataHeader.get(1), medicine);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// list_reminder.clear();
		// myList.add("your array list items");
		// setListAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.health_reminder, menu);
		return true;
	}

}
