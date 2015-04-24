package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.google.android.gms.internal.bc;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class NearByPlace extends Activity implements OnClickListener,
OnMarkerClickListener, OnMapClickListener {


	private GoogleMap googleMap;
	Double mcurrent_lat;
	Double mcurrent_long;
	Polyline line;
	GPSTracker gps_loc;
	GMapV2GetRouteDirection md;
	FrameLayout frameLayout_main, frameLayout_slide;
	ImageButton menu_btn, back_btn;
	// ArrayList<LatLng> markerPoints;
	ArrayAdapterItem adapter;
	//ArrayAdapter<String> adapter;

	private final String TAG = getClass().getSimpleName();
	private String[] places;
	private LocationManager locationManager;
	private Location loc;
	Boolean flag_menu_close=true;
	TextView topbar_text;
	
	String [] Array_Place_Name;
	
	ListView listView_Place;

	private ArrayList<Place> findPlaces;

	MarkerOptions markerOptions;
	Document doc;
	PolylineOptions rectLine;
	
	Intent intent;
	int place_type;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_nearby_place);
		intent=getIntent();
		place_type=intent.getIntExtra("place_type", 0);
		
		frameLayout_main=(FrameLayout)findViewById(R.id.frameLayout_map);
		frameLayout_slide=(FrameLayout)findViewById(R.id.frame_2);
		topbar_text=(TextView)findViewById(R.id.top_bar_nearbyPlace);
		if(place_type==0)
		{
		topbar_text.setText("Nearest Hospital");
		}
		
		if(place_type==1)
		{
		topbar_text.setText("Nearest Pharmacy");
		}
		
		if(place_type==2)
		{
		topbar_text.setText("Nearest Doctor");
		}
		
		menu_btn=(ImageButton)findViewById(R.id.imageButton_menu);
		back_btn=(ImageButton)findViewById(R.id.imageButton_back);
		
		listView_Place=(ListView) findViewById(R.id.place_list);
		
		places = getResources().getStringArray(R.array.place_type);
		//((Button) findViewById(R.id.btn_listView)).setOnClickListener(this);
        gps_loc=new GPSTracker(this);
       
		
		back_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();

			}
		}); 
		
		
		if (!InternetConnectivity.isConnectedToInternet(NearByPlace.this)) {
			final AlertDialog.Builder alert = new AlertDialog.Builder(NearByPlace.this);
			alert.setTitle("Internet Problem");
			alert.setMessage("No internet connection. Please connect to a network first.");
			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					menu_btn.setVisibility(View.INVISIBLE);
					NearByPlace.this.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
					//finish();
					onBackPressed();
				}
			});

			alert.show();
		} 
		if(!gps_loc.isGPSEnabled)
		{  
			gps_loc.showSettingsAlert();
			//onBackPressed();
			
			
		}
		
		if(!gps_loc.canGetLocation)
		{   
			gps_loc.showSettingsAlert();
			//onBackPressed();
			
			
		}
		
		if(!gps_loc.isNetworkEnabled)
		{
			gps_loc.showSettingsAlert();
			//onResume();
		}
		
			else {
			initCompo();
			menu_btn.setVisibility(View.VISIBLE);
			
			md = new GMapV2GetRouteDirection();
			markerOptions = new MarkerOptions();

			currentLocation();

			// Enable MyLocation Button in the Map
			googleMap.setMyLocationEnabled(true);
			

			googleMap.setOnMarkerClickListener(this);

            
			
//			if(findPlaces!=null)
//			{
//			Log.e("hospital count", "--------------------------"+ findPlaces.size());
//			Toast.makeText(getApplicationContext(),"in if", Toast.LENGTH_LONG).show(); 
//	      
//			adapter = new ArrayAdapterItem(NearByPlace.this, R.layout.list_view_row, findPlaces);
//		    listView_Place.setAdapter(adapter);
//			}
//			else
//			{
//				Toast.makeText(getApplicationContext(),"in else", Toast.LENGTH_LONG).show(); 
//			}
		
			
//			for (int i = 0; i < findPlaces.size(); i++) {
//			
//			Array_Place_Name[i]=findPlaces.get(i).getName();
//				Log.e(TAG, "places : " + findPlaces.get(i).getName());
//			}
			
//			if(Array_Place_Name!=null )
//			{
//			adapter = new ArrayAdapter<String>(NearByPlace.this,R.layout.list_item_hospi, Array_Place_Name);
//			//county_list_adapter.setNotifyOnChange(true);
//			
//		   listView_Place.setAdapter(adapter);
//			}

			  menu_btn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						if (flag_menu_close) {
							flag_menu_close = false;
							//listView_Place.getChildAt(0).setBackgroundColor(Color.CYAN);
							frameLayout_main.animate().translationX(frameLayout_slide.getWidth()).withLayer();
							frameLayout_slide.setVisibility(View.VISIBLE);

						} else {
							//btn_Menu.setBackgroundResource(R.drawable.menu_btn);
							flag_menu_close = true;
							frameLayout_main.animate().translationX(0).withLayer();
							frameLayout_slide.setVisibility(View.INVISIBLE);
						}

					}
				});
			
			
		listView_Place.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {				
					
					
					if (line != null) {
						line.remove();
					}
					flag_menu_close = true;
					frameLayout_main.animate().translationX(0).withLayer();
					frameLayout_slide.setVisibility(View.INVISIBLE);
								
					ArrayList<Place> p1=((ArrayAdapterItem) listView_Place.getAdapter()).getData();
					drawPath(p1.get(position).getLatitude(), p1.get(position).getLongitude(), p1.get(position).getName());
					

				}
			});
		
		listView_Place.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				listView_Place.setSelection(position);
				//listView_Place.getChildAt(position).setBackgroundColor(Color.CYAN);
		
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});

		}

	}

	private class GetPlaces extends AsyncTask<Void, Void, ArrayList<Place>> {

		private ProgressDialog dialog;
		private Context context;
		private String places;
		private ArrayAdapterItem adapter1;

		public GetPlaces(Context context, String places) {
			this.context = context;
			this.places = places;
		}

		protected void onPostExecute(ArrayList<Place> result) {
			super.onPostExecute(result);
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		//	GPSTracker gps = new GPSTracker(NearByPlace.this);
//			MarkerOptions marker = new MarkerOptions().position(
//					new LatLng(gps.getLatitude(), gps.getLongitude())).title(
//					"My Location ");
			
			MarkerOptions marker = new MarkerOptions().position(new LatLng(googleMap.getMyLocation().getLatitude(),googleMap.getMyLocation().getLongitude())).title("My Location ");

			googleMap.addMarker(marker);

			for (int i = 0; i < result.size(); i++) {
				googleMap.addMarker(new MarkerOptions()
						.title(result.get(i).getName())
						.position(
								new LatLng(result.get(i).getLatitude(), result
										.get(i).getLongitude()))
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.pin))
						.snippet(result.get(i).getVicinity()));
				
			//	Array_Place_Name[i]=result.get(i).getName();

			}
			//adapter = new ArrayAdapter<String>(NearByPlace.this,R.layout.list_item_hospi, Array_Place_Name);
			//county_list_adapter.setNotifyOnChange(true);
			//adapter.clear();
			//adapter = new ArrayAdapterItem(NearByPlace.this, R.layout.list_view_row, result);
//			adapter.clear();
//            for (Place name : result) {
//               adapter.add(name);
//            }
           
            listView_Place.setAdapter(adapter1);
			listView_Place.setSelector(R.drawable.selector);

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(googleMap.getMyLocation().getLatitude(),googleMap.getMyLocation().getLongitude())) // Sets
																				// the
																				// center
																				// of
																				// the
																				// map
																				// to
					// Mountain View
					.zoom(14) // Sets the zoom
					.tilt(0) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder
			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
		}

		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(context);
			dialog.setCancelable(false);
			dialog.setMessage("Loading..");
			dialog.isIndeterminate();
			dialog.show();
		}

		protected ArrayList<Place> doInBackground(Void... arg0) {
			PlacesService service = new PlacesService(
					"AIzaSyCQNObutx_J-JHhDBqSv_Y5qPK5cIo3S8A");
			Log.i("Find Places Location", "--------------------" + mcurrent_lat
					+ ":" + mcurrent_long);
			findPlaces = service.findPlaces(mcurrent_lat, // 28.632808
					mcurrent_long, places); // 77.218276
		//	adapter1 = new ArrayAdapterItem(NearByPlace.this, R.layout.list_view_row, findPlaces);
			adapter1 = new ArrayAdapterItem(NearByPlace.this, R.layout.list_item_row_for_all, findPlaces);
			
			adapter1.notifyDataSetChanged();
			for (int i = 0; i < findPlaces.size(); i++) {

				Place placeDetail = findPlaces.get(i);
				//Array_Place_Name[i]=findPlaces.get(i).getName();
				Log.e(TAG, "places, I am in background : " + placeDetail.getName());
			}
			return findPlaces;
		}

	}

	private void initCompo() {

		GPSTracker gps = new GPSTracker(this);

		mcurrent_lat = gps.getLatitude();
		mcurrent_long = gps.getLongitude();
		LatLng cur_Latlng = new LatLng(mcurrent_lat, mcurrent_long);
		googleMap = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		//LatLng cur_Latlng = new LatLng(googleMap.getMyLocation().getLatitude(), googleMap.getMyLocation().getLatitude());
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(cur_Latlng));

		googleMap.animateCamera(CameraUpdateFactory.zoomTo(4));

	}

	
	private void currentLocation() {
		new GetPlaces(NearByPlace.this, places[place_type].toLowerCase().replace("-",
				"_")).execute();
	}

	private LocationListener listener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onLocationChanged(Location location) {
			Log.e(TAG, "location update : " + location);

			loc = location;
			locationManager.removeUpdates(listener);
		}
	};

	@Override
	public void onClick(View v) {

	}

	public void drawPath(double lat, double lon, String destName) {
		//GPSTracker gps = new GPSTracker(NearByPlace.this);
		//LatLng src = new LatLng(gps.getLatitude(), gps.getLongitude());
		LatLng src=new LatLng(googleMap.getMyLocation().getLatitude(),googleMap.getMyLocation().getLongitude());
		LatLng dest = new LatLng(lat, lon);
		
		new GetRouteTask(src, dest, destName).execute();

	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
//		if (line != null) {
//			line.remove();
//		}
		
//		for (int i = 0; i < findPlaces.size(); i++) {
//			if (findPlaces.get(i).getName()
//					.equalsIgnoreCase(arg0.getTitle().toString())) {
//				drawPath(findPlaces.get(i).getLatitude(), findPlaces.get(i)
//						.getLongitude(), findPlaces.get(i).getName());
//			}
//		}

		return false;

	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub

	}
	
//	@Override
//	protected void onPause() {
//		// TODO Auto-generated method stub
//		finish();
//		startActivity(getIntent());
//		super.onPause();
//	}

	private class GetRouteTask extends AsyncTask<String, Void, String> {

		private ProgressDialog Dialog;
		String response = "";

		LatLng toPosition, fromPostion;
		String destination_Name;

		public GetRouteTask(LatLng toPosition, LatLng fromPostion, String destName) {
			// TODO Auto-generated constructor stub
			this.toPosition = toPosition;
			this.fromPostion = fromPostion;
			this.destination_Name=destName;
		}

		@Override
		protected void onPreExecute() {
			Dialog = new ProgressDialog(NearByPlace.this);
			Dialog.setMessage("Loading...");
			Dialog.show();
		}

		@Override
		protected String doInBackground(String... urls) {
			// Get All Route values
			doc = md.getDocument(fromPostion, toPosition,
					GMapV2GetRouteDirection.MODE_DRIVING);
			response = "Success";
			return response;

		}

		@Override
		protected void onPostExecute(String result) {
			googleMap.clear();
			new GetPlaces(NearByPlace.this, places[place_type].toLowerCase().replace(
					"-", "_")).execute();
			if (response.equalsIgnoreCase("Success")) {
				ArrayList<LatLng> directionPoint = md.getDirection(doc);
				rectLine = new PolylineOptions().width(10).color(Color.RED);

				for (int i = 0; i < directionPoint.size(); i++) {
					rectLine.add(directionPoint.get(i));
				}
				// Adding route on the map
				googleMap.addPolyline(rectLine);
				
//				googleMap.addMarker(new MarkerOptions()
//			    .position(new LatLng(directionPoint.get(directionPoint.size()-1).latitude, directionPoint.get(directionPoint.size()-1).longitude))
//			    .title(destination_Name));

				@SuppressWarnings("unused")
				Circle circle = googleMap.addCircle(new CircleOptions()
						.center(new LatLng(directionPoint.get(0).latitude,
								directionPoint.get(0).longitude)).radius(0.5)
						.strokeColor(Color.BLACK).fillColor(Color.RED));

//				markerOptions.position(toPosition);
//				markerOptions.draggable(true);
				//googleMap.addMarker(markerOptions);

			}

			Dialog.dismiss();
		}
	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		//finish();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
			finish();
		
		super.onDestroy();
	}
	

}


