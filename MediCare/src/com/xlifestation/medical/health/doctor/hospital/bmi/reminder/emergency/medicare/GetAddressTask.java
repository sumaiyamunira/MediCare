package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;
//package com.example.fastestmedicare;
//
//import java.io.IOException;
//import java.util.List;
//
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.location.Address;
//import android.location.Geocoder;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.TextView;
//
//
//
//public class GetAddressTask extends AsyncTask<String, Void, String> {
//	
//	TextView bmImage;
//	Geocoder g1;
//	double lat;
//	double lon;
//	private ProgressDialog dialog;
//	Context mc;
//	//RelativeLayout relativeLayout;
//
//	public GetAddressTask(TextView bmImage, Geocoder g1, double lat, double lon, Context mc) 
//	{
//		this.bmImage = bmImage;
//		this.g1=g1;
//		this.lat=lat;
//		this.lon=lon;
//		this.mc=mc;
//		
//	}
//	
//	protected void onPreExecute() {
//		super.onPreExecute();
////		dialog = new ProgressDialog(mc);
////		dialog.setCancelable(false);
////		dialog.setMessage("Address are Loading..");
////		dialog.isIndeterminate();
////		dialog.show();
//	}
//
//	protected String doInBackground(String... urls) 
//	{
//		String result = null;
//        try {
//            List<Address> list = g1.getFromLocation(lat, lon, 1);
//            if (list != null && list.size() > 0)
//                {
//                Address address = list.get(0);
//                // sending back first address line and locality
//                result = address.getAddressLine(0) + ", " + address.getLocality()+","+address.getCountryName();
//                    }
//            } 
//         catch (IOException e)
//            {
//            Log.e("TAG", "Impossible to connect to Geocoder", e);
//            } 
//        return result;
//	}
//
//	@SuppressWarnings("deprecation")
//	protected void onPostExecute(String result) 
//	{
////		if (dialog.isShowing()) {
////			dialog.dismiss();
////		}
//		if(bmImage != null)
//			bmImage.setText(result);
//		
//		
//	}
//}
//
