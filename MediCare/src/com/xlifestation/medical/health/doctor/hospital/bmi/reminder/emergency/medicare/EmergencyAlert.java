package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class EmergencyAlert extends Activity {
	FrameLayout main_frame, slide_frame;
	public static Context context;
	ImageButton btn_help, btn_back;
	Button btn_cancel, btn_save;
	Boolean flag_menu_close = true;
	EditText edtTxt_phoneNo;
	EditText edtTxt_Msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergency_alert);
		context=EmergencyAlert.this;

		main_frame = (FrameLayout) findViewById(R.id.frameLayout1);
		slide_frame = (FrameLayout) findViewById(R.id.frame_2);
		btn_help = (ImageButton) findViewById(R.id.imageButton_help);
		btn_back = (ImageButton) findViewById(R.id.imageButton_back);
		btn_cancel = (Button) findViewById(R.id.button_Cancel);
		edtTxt_phoneNo = (EditText) findViewById(R.id.editText_phone_no);
		edtTxt_Msg = (EditText) findViewById(R.id.editText_EmergencyMessage);

		String phoneNo = SharedPreferenceStoring.readPreferences("PHONE_NO",
				"0");
		String message = SharedPreferenceStoring.readPreferences("MESSAGE",
				"Need Help");
		
		edtTxt_phoneNo.setText(phoneNo);
		edtTxt_Msg.setText(message);

		btn_save = (Button) findViewById(R.id.button_Save);

		btn_help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (flag_menu_close) {
					flag_menu_close = false;
					// listView_Hospital.getChildAt(0).setBackgroundColor(Color.CYAN);
					main_frame.animate().translationX(slide_frame.getWidth())
							.withLayer();
					slide_frame.setVisibility(View.VISIBLE);

				} else {
					// btn_Menu.setBackgroundResource(R.drawable.menu_btn);
					flag_menu_close = true;
					main_frame.animate().translationX(0).withLayer();
					slide_frame.setVisibility(View.INVISIBLE);
				}

			}
		});

		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();

			}
		});

		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();

			}
		});

		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (edtTxt_phoneNo.getText().toString().trim().equalsIgnoreCase(null)
						|| edtTxt_phoneNo.getText().toString().trim().equalsIgnoreCase("")) {
					Toast.makeText(getApplicationContext(),
							"Please insert a phone number", Toast.LENGTH_LONG)
							.show();
				} else {
					if (edtTxt_Msg.getText().toString().trim().equalsIgnoreCase(null)
							|| edtTxt_Msg.getText().toString().trim().equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(),
								"Please insert a message", Toast.LENGTH_LONG)
								.show();
					} 
				   else {
					   
					   String phone_no=edtTxt_phoneNo.getText().toString().trim();
					   String message=edtTxt_Msg.getText().toString().trim();
					   
					   SharedPreferenceStoring.savePreferences(EmergencyAlert.this,"PHONE_NO", phone_no);
					   SharedPreferenceStoring.savePreferences(EmergencyAlert.this,"MESSAGE", message);
					   
					   Toast.makeText(getApplicationContext(),
								"Message is stored Successfully", Toast.LENGTH_LONG)
								.show();
	                   

					}

				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emergency_alert, menu);
		return true;
	}

}
