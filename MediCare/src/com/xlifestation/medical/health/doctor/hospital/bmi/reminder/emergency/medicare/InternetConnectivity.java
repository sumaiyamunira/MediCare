package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternetConnectivity 
{
	public static boolean isConnectedToInternet(Context context)
	{
		ConnectivityManager conMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean connected = (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected());
		return connected;
	}
	

}
