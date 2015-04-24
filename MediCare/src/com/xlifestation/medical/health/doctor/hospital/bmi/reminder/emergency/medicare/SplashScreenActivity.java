package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;

public class SplashScreenActivity extends Activity {
	
	private CountDownTimer countDownTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		creatingSplashScreen();
	}

	private void creatingSplashScreen() {
		countDownTimer = new CountDownTimer(2000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {
				startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
				finish();
			}
		};
		countDownTimer.start();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(countDownTimer != null) {
			countDownTimer.cancel();
		}
	}
}
