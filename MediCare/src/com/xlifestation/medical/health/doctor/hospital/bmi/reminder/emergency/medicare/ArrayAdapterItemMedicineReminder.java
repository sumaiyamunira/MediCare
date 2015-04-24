package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;
//package com.example.fastestmedicare;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import android.app.Activity;
//import android.content.Context;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebView.FindListener;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//public class ArrayAdapterItemMedicineReminder extends ArrayAdapter<MedicineReminder> {
//
//		Context mContext;
//		int layoutResourceId;
//		ArrayList<MedicineReminder> data = null;
//		TextView textView_Hospital_Location ;
//
//		public ArrayAdapterItemMedicineReminder(Context mContext, int layoutResourceId,
//				ArrayList<MedicineReminder> data) {
//
//			super(mContext, layoutResourceId, data);
//
//			this.layoutResourceId = layoutResourceId;
//			this.mContext = mContext;
//			this.data = data;
//		}
//
//		/*
//		 * public ArrayAdapterItem sortData() { Collections.sort(data); return this;
//		 * }
//		 */
//		@SuppressWarnings("null")
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//
//			/*
//			 * The convertView argument is essentially a "ScrapView" as described is
//			 * Lucas post
//			 * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
//			 * It will have a non-null value when ListView is asking you recycle the
//			 * row layout. So, when convertView is not null, you should simply
//			 * update its contents instead of inflating a new row layout.
//			 */
//			if (convertView == null) {
//				// inflate the layout
//				LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
//				convertView = inflater.inflate(layoutResourceId, parent, false);
//			}
//			
//
//			TextView textView_MedicineName = (TextView) convertView
//					.findViewById(R.id.textView1_ReminderDetails);
//			textView_MedicineName.setText(data.get(position).getMedicineName());
//			
//			TextView textView_startDate = (TextView) convertView
//					.findViewById(R.id.textView_startDate);
//			textView_startDate.setText(data.get(position).getStartDate());
//			
//			TextView textView_endDate = (TextView) convertView
//					.findViewById(R.id.textView_endDate);
//			textView_endDate .setText(data.get(position).getEndDate());
//			
//			TextView textView_doesPerDay = (TextView) convertView
//					.findViewById(R.id.textView_doesPerDay_or_time);
//			textView_doesPerDay.setText(data.get(position).getDoesPerDay());
//			
//	        
//			return convertView;
//
//		}
//		
//		public ArrayList<MedicineReminder> getData() {
//			return data;
//		}
//		
////		private class GeocoderHandler extends Handler {
////		    @Override
////		    public void handleMessage(Message message) {
////		        String result;
////		        switch (message.what) {
////		        case 1:
////		            Bundle bundle = message.getData();
////		            result = bundle.getString("address");
////		            break;
////		        default:
////		            result = null;
////		        }
////		        // replace by what you need to do
////		       textView_Hospital_Location.setText(result);
////		    }   
////		}
//		
//		
//
//	}
//
