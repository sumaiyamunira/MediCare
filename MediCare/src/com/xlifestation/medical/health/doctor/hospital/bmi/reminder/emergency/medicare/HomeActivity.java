package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity {

	Button btn_nearest_hospital, btn_health_reminder, btn_nearest_pharmacy,
			btn_emergency, btn_nearestDoctor, btn_bmiCalculattor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btn_nearest_hospital = (Button) findViewById(R.id.button_NearestHospital);
		btn_health_reminder = (Button) findViewById(R.id.Button_HealthReminder);
		btn_nearest_pharmacy = (Button) findViewById(R.id.Button_NeartestPharmacy);
		btn_emergency = (Button) findViewById(R.id.button_EmergencyAlert);
		btn_nearestDoctor=(Button) findViewById(R.id.Button_nearestDoctor);
		btn_bmiCalculattor=(Button) findViewById(R.id.button_BMICalculator);

		btn_nearest_hospital.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent startNewActivityOpen = new Intent(HomeActivity.this,
						NearByPlace.class); // explicit intent
				startNewActivityOpen.putExtra("place_type", 0);
				startActivity(startNewActivityOpen);
			}

		});
		
		btn_health_reminder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent startNewActivityOpen = new Intent(HomeActivity.this,
						HealthReminder.class); // explicit intent
				
				startActivity(startNewActivityOpen);
			}

		});
		
		btn_nearest_pharmacy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent startNewActivityOpen = new Intent(HomeActivity.this,
						NearByPlace.class); // explicit intent
				startNewActivityOpen.putExtra("place_type", 1);
				startActivity(startNewActivityOpen);
			}

		});
		
		btn_nearestDoctor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent startNewActivityOpen = new Intent(HomeActivity.this,
						NearByPlace.class); // explicit intent
				startNewActivityOpen.putExtra("place_type", 2);
				startActivity(startNewActivityOpen);
			}

		});
		
		btn_emergency.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent startNewActivityOpen = new Intent(HomeActivity.this,
						EmergencyAlert.class); // explicit intent
				startActivity(startNewActivityOpen);
			}

		});
		
		btn_bmiCalculattor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent startNewActivityOpen = new Intent(HomeActivity.this,
						BMICalculator.class); // explicit intent
				startActivity(startNewActivityOpen);
			}

		});


	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
