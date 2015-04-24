package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;
//package com.example.fastestmedicare;
//
//import java.util.ArrayList;
//
//import org.w3c.dom.Document;
//
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
//import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.Circle;
//import com.google.android.gms.maps.model.CircleOptions;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.Polyline;
//import com.google.android.gms.maps.model.PolylineOptions;
//
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Color;
//import android.util.Log;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import android.widget.Button;
//
//public class MainActivity extends Activity implements OnClickListener,
//OnMarkerClickListener, OnMapClickListener {
//
//
//		private GoogleMap googleMap;
//		Double mcurrent_lat;
//		Double mcurrent_long;
//		Polyline line;
//		GPSTracker gps_loc;
//		GMapV2GetRouteDirection md;
//		// ArrayList<LatLng> markerPoints;
//
//		private final String TAG = getClass().getSimpleName();
//		private String[] places;
//		private LocationManager locationManager;
//		private Location loc;
//
//		ArrayList<Place> findPlaces;
//
//		MarkerOptions markerOptions;
//		Document doc;
//		PolylineOptions rectLine;
//
//		@Override
//		protected void onCreate(Bundle savedInstanceState) {
//			//requestWindowFeature(Window.FEATURE_NO_TITLE);
//			super.onCreate(savedInstanceState);
//
//			setContentView(R.layout.activity_main);
//			((Button) findViewById(R.id.btn_listView)).setOnClickListener(this);
//	         gps_loc=new GPSTracker(this);
//			if (!InternetConnectivity.isConnectedToInternet(MainActivity.this)) {
//				final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
//				alert.setTitle("Internet Problem");
//				alert.setMessage("No internet connection. Please connect to a network first.");
//				alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//						MainActivity.this.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//					}
//				});
//
//				alert.show();
//			} else {
//				initCompo();
//				places = getResources().getStringArray(R.array.place_type);
//				md = new GMapV2GetRouteDirection();
//				markerOptions = new MarkerOptions();
//
//				currentLocation();
//
//				// Enable MyLocation Button in the Map
//				googleMap.setMyLocationEnabled(true);
//
//				googleMap.setOnMarkerClickListener(this);
//			}
//
//		}
//
//		private class GetPlaces extends AsyncTask<Void, Void, ArrayList<Place>> {
//
//			private ProgressDialog dialog;
//			private Context context;
//			private String places;
//
//			public GetPlaces(Context context, String places) {
//				this.context = context;
//				this.places = places;
//			}
//
//			protected void onPostExecute(ArrayList<Place> result) {
//				super.onPostExecute(result);
//				if (dialog.isShowing()) {
//					dialog.dismiss();
//				}
//				GPSTracker gps = new GPSTracker(MainActivity.this);
//				MarkerOptions marker = new MarkerOptions().position(
//						new LatLng(gps.getLatitude(), gps.getLongitude())).title(
//						"My Location ");
//
//				googleMap.addMarker(marker);
//
//				for (int i = 0; i < result.size(); i++) {
//					googleMap.addMarker(new MarkerOptions()
//							.title(result.get(i).getName())
//							.position(
//									new LatLng(result.get(i).getLatitude(), result
//											.get(i).getLongitude()))
//							.icon(BitmapDescriptorFactory
//									.fromResource(R.drawable.pin))
//							.snippet(result.get(i).getVicinity()));
//
//				}
//
//				CameraPosition cameraPosition = new CameraPosition.Builder()
//						.target(new LatLng(gps.getLatitude(), gps.getLongitude())) // Sets
//																					// the
//																					// center
//																					// of
//																					// the
//																					// map
//																					// to
//						// Mountain View
//						.zoom(14) // Sets the zoom
//						.tilt(0) // Sets the tilt of the camera to 30 degrees
//						.build(); // Creates a CameraPosition from the builder
//				googleMap.animateCamera(CameraUpdateFactory
//						.newCameraPosition(cameraPosition));
//			}
//
//			protected void onPreExecute() {
//				super.onPreExecute();
//				dialog = new ProgressDialog(context);
//				dialog.setCancelable(false);
//				dialog.setMessage("Loading..");
//				dialog.isIndeterminate();
//				dialog.show();
//			}
//
//			protected ArrayList<Place> doInBackground(Void... arg0) {
//				PlacesService service = new PlacesService(
//						"AIzaSyCF3OzeF-ftyofyNsp-Qx-NfGbsf3LxIzo");
//				Log.i("Find Places Location", "--------------------" + mcurrent_lat
//						+ ":" + mcurrent_long);
//				findPlaces = service.findPlaces(mcurrent_lat, // 28.632808
//						mcurrent_long, places); // 77.218276
//
//				for (int i = 0; i < findPlaces.size(); i++) {
//
//					Place placeDetail = findPlaces.get(i);
//					Log.e(TAG, "places : " + placeDetail.getName());
//				}
//				return findPlaces;
//			}
//
//		}
//
//		private void initCompo() {
//
//			GPSTracker gps = new GPSTracker(this);
//
//			mcurrent_lat = gps.getLatitude();
//			mcurrent_long = gps.getLongitude();
//			LatLng cur_Latlng = new LatLng(mcurrent_lat, mcurrent_long);
//			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
//					R.id.map)).getMap();
//			googleMap.moveCamera(CameraUpdateFactory.newLatLng(cur_Latlng));
//
//			googleMap.animateCamera(CameraUpdateFactory.zoomTo(4));
//
//		}
//
//		
//		private void currentLocation() {
//			new GetPlaces(MainActivity.this, places[0].toLowerCase().replace("-",
//					"_")).execute();
//		}
//
//		private LocationListener listener = new LocationListener() {
//
//			@Override
//			public void onStatusChanged(String provider, int status, Bundle extras) {
//			}
//
//			@Override
//			public void onProviderEnabled(String provider) {
//			}
//
//			@Override
//			public void onProviderDisabled(String provider) {
//			}
//
//			@Override
//			public void onLocationChanged(Location location) {
//				Log.e(TAG, "location update : " + location);
//
//				loc = location;
//				locationManager.removeUpdates(listener);
//			}
//		};
//
//		@Override
//		public void onClick(View v) {
//			switch (v.getId()) {
//			case R.id.btn_listView:
//				Intent startNewActivityOpen = new Intent(MainActivity.this, ListView_Hospital.class); //explicit intent
//				startNewActivityOpen.putParcelableArrayListExtra("Hospital_List", findPlaces);
//	             startActivity(startNewActivityOpen);
//				break;
//
//			default:
//				break;
//			}
//		}
//
//		public void drawPath(double lat, double lon, String destName) {
//			GPSTracker gps = new GPSTracker(MainActivity.this);
//			LatLng src = new LatLng(gps.getLatitude(), gps.getLongitude());
//			LatLng dest = new LatLng(lat, lon);
//			
//			new GetRouteTask(src, dest, destName).execute();
//
//		}
//
//		@Override
//		public boolean onMarkerClick(Marker arg0) {
//			// TODO Auto-generated method stub
//			if (line != null) {
//				line.remove();
//			}
//			for (int i = 0; i < findPlaces.size(); i++) {
//				if (findPlaces.get(i).getName()
//						.equalsIgnoreCase(arg0.getTitle().toString())) {
//					drawPath(findPlaces.get(i).getLatitude(), findPlaces.get(i)
//							.getLongitude(), findPlaces.get(i).getName());
//				}
//			}
//
//			return false;
//
//		}
//
//		@Override
//		public void onMapClick(LatLng arg0) {
//			// TODO Auto-generated method stub
//
//		}
//
//		private class GetRouteTask extends AsyncTask<String, Void, String> {
//
//			private ProgressDialog Dialog;
//			String response = "";
//
//			LatLng toPosition, fromPostion;
//			String destination_Name;
//
//			public GetRouteTask(LatLng toPosition, LatLng fromPostion, String destName) {
//				// TODO Auto-generated constructor stub
//				this.toPosition = toPosition;
//				this.fromPostion = fromPostion;
//				this.destination_Name=destName;
//			}
//
//			@Override
//			protected void onPreExecute() {
//				Dialog = new ProgressDialog(MainActivity.this);
//				Dialog.setMessage("Loading...");
//				Dialog.show();
//			}
//
//			@Override
//			protected String doInBackground(String... urls) {
//				// Get All Route values
//				doc = md.getDocument(fromPostion, toPosition,
//						GMapV2GetRouteDirection.MODE_DRIVING);
//				response = "Success";
//				return response;
//
//			}
//
//			@Override
//			protected void onPostExecute(String result) {
//				googleMap.clear();
//				new GetPlaces(MainActivity.this, places[0].toLowerCase().replace(
//						"-", "_")).execute();
//				if (response.equalsIgnoreCase("Success")) {
//					ArrayList<LatLng> directionPoint = md.getDirection(doc);
//					rectLine = new PolylineOptions().width(10).color(Color.RED);
//
//					for (int i = 0; i < directionPoint.size(); i++) {
//						rectLine.add(directionPoint.get(i));
//					}
//					// Adding route on the map
//					googleMap.addPolyline(rectLine);
//					
////					googleMap.addMarker(new MarkerOptions()
////				    .position(new LatLng(directionPoint.get(directionPoint.size()-1).latitude, directionPoint.get(directionPoint.size()-1).longitude))
////				    .title(destination_Name));
//
//					@SuppressWarnings("unused")
//					Circle circle = googleMap.addCircle(new CircleOptions()
//							.center(new LatLng(directionPoint.get(0).latitude,
//									directionPoint.get(0).longitude)).radius(0.5)
//							.strokeColor(Color.BLACK).fillColor(Color.RED));
//
////					markerOptions.position(toPosition);
////					markerOptions.draggable(true);
//					//googleMap.addMarker(markerOptions);
//
//				}
//
//				Dialog.dismiss();
//			}
//		}
//
//
//	}
//
//
