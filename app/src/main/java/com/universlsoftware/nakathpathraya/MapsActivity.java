package com.universlsoftware.nakathpathraya;


import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.universlsoftware.nakathpathraya.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private Circle currentLocationCircle;

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


        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 7.6f));

                    // Create a circle options object
                    CircleOptions circleOptions = new CircleOptions()
                            .center(currentLocation)
                            .radius(5000) // Set the radius in meters
                            .strokeColor(Color.RED) // Set the stroke color
                            .strokeWidth(30); // Set the stroke width

                    // Add the circle to the map
                    currentLocationCircle = mMap.addCircle(circleOptions);

                    final double initialMinDistance = 0.5;


                    double minDistance = initialMinDistance;
                    Marker nearestMarker = null;
                     int x=0;
                    for (CityList city : cityList) {
                        nearestMarker = null;
//            double latitude = city.getLat();
//            double longitude = city.getLon();
//            String name = city.getCity();
//            getwederdata(latitude, longitude, name);

                        String latitude = String.valueOf(city.getLat());
                        String longitude = String.valueOf(city.getLon());
                        assert latitude != null;
                        double latitudenumber = Double.parseDouble(latitude);

                        assert longitude != null;
                        double longitudenumber = Double.parseDouble(longitude);

                        LatLng location2 = new LatLng(latitudenumber, longitudenumber);

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(location2);
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        Marker marker = mMap.addMarker(markerOptions);
                        marker.setTag(x); // Set a unique tag for each marker
                            x++;
                        // Calculate the distance between the current location and the garage location
                        double distance = calculateDistance(currentLocation.latitude, currentLocation.longitude, latitudenumber, longitudenumber);
                        Log.i("wisadhas", String.valueOf(distance));
                        if (distance <= 5 ) {

                            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        }




                    }


                    mMap.setOnMarkerClickListener(MapsActivity.this);
                }


            }
        });


    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double distance = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.cos(Math.toRadians(theta));
        distance = Math.acos(distance);
        distance = Math.toDegrees(distance);
        distance = distance * 60 * 1.1515;
        distance = distance * 1.609344; // Convert to kilometers
        return distance;
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
        cityList.add(new CityList(6.585395, 79.960739, "Wuhan, China","iresh sammera","07484756484","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(	6.124593, 81.101074, "Wuhan, China","achala saliya","2124567890.","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(8.592200, 81.196793, "Wuhan, China","thusitha dananjaya","12124567890","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(7.290572, 80.633728, "Wuhan, China","sameera","07484756484","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(	6.894070, 79.902481, "Wuhan, China","muru danantaya","07484756484","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(	7.189464, 79.858734, "Wuhan, China","Julian Wiggins","07484756484","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(6.053519, 80.220978, "Wuhan, China","iresh sammera","212-456-7890","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(6.7144, 79.9891, "Wuhan, China","Alisson Benjamin","0237854385342","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(6.9107787, 79.8851087, "Wuhan, China","iresh sammera","12124567890","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(6.7144, 79.9891, "Wuhan, China","Lilia Moran","0724659574947","7: 00 am - 9: 00 am -"));;
        cityList.add(new CityList(6.7145, 79.9845, "Wuhan, China","Chloe Bright","07484756484","7: 00 am - 9: 00 am -"));
        cityList.add(new CityList(6.7149, 79.9895, "Wuhan, China","Julie Villa","12124567890","7: 00 am - 9: 00 am -"));
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        // Retrieve the tag assigned to the marker
        String ID = String.valueOf(marker.getTag());;
        CityList secondList = cityList.get(Integer.parseInt(ID));

        String name =  secondList.getName();;
        String contactNumber = secondList.getNumber();
        String openDateTime = secondList.getTime();



                    // Show the data in a pop-up window
                    showGarageDetails(name, contactNumber,openDateTime);





        return false;
    }

    private void showGarageDetails(String name, String contactNumber, String dateTimeParts) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(name);

        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder.append("Name:- ").append(contactNumber).append("\n");
        messageBuilder.append("\nContact Number:- ").append(contactNumber).append("\n");
        messageBuilder.append("\nTime:- ").append(contactNumber).append("\n");

        builder.setMessage(messageBuilder.toString());
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}