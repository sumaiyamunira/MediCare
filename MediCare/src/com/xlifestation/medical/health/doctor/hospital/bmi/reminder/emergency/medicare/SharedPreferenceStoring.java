package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPreferenceStoring {
	
	public static void savePreferences(Activity activity, String key, String value){
	    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
	   

			Editor editor = sp.edit();
			editor.putString(key, value);
			editor.commit();
	    }

	  public static String readPreferences(String key, String defaultValue){
		  	Context context=EmergencyAlert.context;
	    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
	    	return sp.getString(key, defaultValue);
	    }
	  
	  public static void savePreferencesInt(Activity activity, String key, int value){
	    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
	   

			Editor editor = sp.edit();
			editor.putInt(key, value);
			editor.commit();
	    }

	  public static int readPreferencesInt(String key, int defaultValue){
		  	Context context=HealthReminder.context;
	    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
	    	return sp.getInt(key, defaultValue);
	    }
	 
	  public static boolean ClearPreferencesInt(String key){
		  	Context context=HealthReminder.context;
	    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
	    	Editor editor = sp.edit();
			editor.clear();
			editor.commit();
	    	return true;
	    }

}
