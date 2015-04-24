package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;


import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AddHealthReminderTab extends TabActivity {
	
	ImageButton buttonBack;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health_reminder);
        
        buttonBack=(ImageButton)findViewById(R.id.imageButton_back);
        
        buttonBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				//onBackPressed();
				Intent startNewActivityOpen = new Intent(AddHealthReminderTab.this,
						HealthReminder.class); // explicit intent
				startActivity(startNewActivityOpen);
				AddHealthReminderTab.this.finish();
				
			}

		});
         
        TabHost tabHost = getTabHost();
        Intent intent = getIntent();  
        
        int tabNo=intent.getIntExtra("TabNo",0); 
        int ReminderIdMedi=intent.getIntExtra("ReminderIdMedi",0);
        int ReminderIdApp=intent.getIntExtra("ReminderIdApp",0);
         
        // Tab for Medicine
        TabSpec medicine = tabHost.newTabSpec("Medicine");
        // setting Title and Icon for the Tab
        medicine.setIndicator("Medicine", getResources().getDrawable(R.drawable.medicine));
        Intent medicineIntent = new Intent(this, AddHealthReminder_Medicine.class);
        if(tabNo ==1)
        {
        	Log.e("Reminder ID","---------"+ReminderIdMedi);
        	medicineIntent.putExtra("ReminderIdMedi", ReminderIdMedi);
        }
        medicine.setContent(medicineIntent);
         
        // Tab for Appointment
        TabSpec appointment = tabHost.newTabSpec("Appointment");       
        appointment .setIndicator("Appoinment", getResources().getDrawable(R.drawable.doctor));
        Intent appointmentIntent = new Intent(this, AddHealthReminder_Appoinment.class);
        if(tabNo ==2)
        {
        	appointmentIntent.putExtra("ReminderIdApp", ReminderIdApp);
        }
        appointment.setContent(appointmentIntent);
        
       
      
        // Adding all TabSpec to TabHost
        tabHost.addTab(medicine); // Adding photos tab
        tabHost.addTab(appointment); // Adding songs tab
        
        
        if(tabNo ==1){  
        	
            tabHost.setCurrentTab(0);  
            
        } else if(tabNo==2){  
            tabHost.setCurrentTab(1);  
        } 
        else
        	  tabHost.setCurrentTab(0); 
        	
       
    }
}