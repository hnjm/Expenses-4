package com.example.Expenses;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

import com.example.prototype_phase_1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ExpensesMap extends Activity {
 
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 10; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000000; // in Milliseconds
    
	protected LocationManager locationManager;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.expenses_map);
      
      locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
      locationManager.requestLocationUpdates(
              LocationManager.GPS_PROVIDER,
              MINIMUM_TIME_BETWEEN_UPDATES,
              MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, 
              (LocationListener) new MyLocationListener()
      );
      
      Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

      if(location==null)
      {
    	  Toast.makeText(ExpensesMap.this,"No location found due to gps ", Toast.LENGTH_LONG).show();
      }
      // Get a handle to the Map Fragment
      else
      {
      GoogleMap map = ((MapFragment) getFragmentManager()
              .findFragmentById(R.id.map)).getMap();

      LatLng CurrentLoc = new LatLng(location.getLatitude(),location.getLongitude());

      map.setMyLocationEnabled(true);
      map.moveCamera(CameraUpdateFactory.newLatLngZoom(CurrentLoc, 13));

      map.addMarker(new MarkerOptions()
              .title("Current Location")
              .snippet("This is your current location")
              .position(CurrentLoc));
      		}
      }
  
  
  
  //////////////////////////////////////////////////////////////////////////
  class MyLocationListener implements LocationListener {
		 
      public void onLocationChanged(Location location) {
          String message = String.format(
                  "New Location \n Longitude: %1$s \n Latitude: %2$s",
                  location.getLatitude(),location.getLongitude()
          );
          
        
          
          
          
      }

      public void onStatusChanged(String s, int i, Bundle b) {
         
      	//Toast.makeText(Home.this,"Provider status changed" , Toast.LENGTH_SHORT).show();
      	//Toast.makeText(Home.this,"No address found" , Toast.LENGTH_SHORT).show();
      	//retrieveLocationButton.setText("Provider status changed");
      	//retrieveLocationButton.setText("Retrieve Location");
}

      public void onProviderDisabled(String s) {
         
      	Toast.makeText(ExpensesMap.this,"Provider disabled by the user. GPS turned off", Toast.LENGTH_SHORT).show();
      	//retrieveLocationButton.setText( "Provider disabled by the user. GPS turned off");
                  
      }

      public void onProviderEnabled(String s) {
          
      	Toast.makeText(ExpensesMap.this,"Provider enabled by the user. GPS turned on", Toast.LENGTH_SHORT).show();
      	//retrieveLocationButton.setText( "Provider enabled by the user. GPS turned on");
      }

  }



  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.home, menu);
    return true;
  }

}
