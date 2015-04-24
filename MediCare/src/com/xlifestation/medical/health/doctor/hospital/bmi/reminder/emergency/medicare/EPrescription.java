package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;
//package com.example.fastestmedicare;
//
//import java.util.ArrayList;
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Intent;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemLongClickListener;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.AdapterView.OnItemClickListener;
//
//public class EPrescription extends Activity {
//	ImageButton add_btn, back_btn;
//	ListView list_prescription;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_eprescription);
//		add_btn = (ImageButton) findViewById(R.id.imageButton_add);
//		back_btn = (ImageButton) findViewById(R.id.imageButton_back);
//		list_prescription=(ListView) findViewById(R.id.listView_eprescription);
//
//		add_btn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent startNewActivityOpen = new Intent(EPrescription.this,
//						AddPrescription.class); // explicit intent
//				startActivity(startNewActivityOpen);
//
//			}
//
//		});
//
//		back_btn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//				onBackPressed();
//			}
//
//		});
//		
//		
//		list_prescription.setOnItemLongClickListener(new OnItemLongClickListener(){
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				return false;
//			}	
//				
//				
//
//		});
//	
//
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.eprescription, menu);
//		return true;
//	}
//
//}
