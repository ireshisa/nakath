package com.universlsoftware.nakathpathraya;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.universlsoftware.nakathpathraya.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<CityList> cityList;
    String status;

    int meterValue;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        adddata();


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (CityList city : cityList) {
            double latitude = city.getLat();
            double longitude = city.getLon();
            String name = city.getCity();
            getwederdata(latitude, longitude, name);
        }


        // Enable the My Location layer
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        // Get the user's current location
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // Check if the user's location is available
        if (location != null) {
            // Animate the camera to the user's location
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }


    private void getwederdata(double lat, double lon, String city) {


        String appid = "7056ce2358b34c9193cc7a2158802e89";


        double finalLon = lon;
        double finalLat = lat;

        BitmapDescriptor bitmapDescriptor;
        bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.red5);

        Marker melbourne = mMap.addMarker(
                new MarkerOptions()
                        .position(new LatLng(finalLat, finalLon))
                        .title("asdsad")
                        .snippet("Name")
                        .icon(bitmapDescriptor));


    }


    void adddata() {

        cityList = new ArrayList<>();
        cityList.add(new CityList(30.5928, 114.3052, "Wuhan, China"));
        cityList.add(new CityList(18.7883, 98.9853, "Chiang Mai, Thailand"));
        cityList.add(new CityList(25.2048, 55.2708, "Dubai, United Arab Emirates"));
        cityList.add(new CityList(16.8409, 96.1735, "Yangon, Myanmar"));
        cityList.add(new CityList(13.7563, 100.5018, "Bangkok, Thailand"));
        cityList.add(new CityList(27.7172, 85.3240, "Kathmandu, Nepal"));
        cityList.add(new CityList(29.5657, 106.5512, "Chongqing, China, Nepal"));
        cityList.add(new CityList(24.8607, 67.0011, "Karachi, Pakistan, Nepal"));


    }
}