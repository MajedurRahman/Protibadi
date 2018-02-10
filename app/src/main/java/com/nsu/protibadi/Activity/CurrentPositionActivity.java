package com.nsu.protibadi.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nsu.protibadi.R;

import java.util.Timer;
import java.util.TimerTask;

import mmr.finder.com.FinderLocationAppCompatActivity;
import mmr.finder.com.FinderLocationRequest;
import mmr.finder.com.FinderRequestBuilder;

public class CurrentPositionActivity extends FinderLocationAppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean isCrrentMarkerAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        initLocationService();
    }


    public void initLocationService() {
        LocationRequest locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(5000)
                .setFastestInterval(5000);
        final FinderLocationRequest finderLocationRequest = new FinderRequestBuilder()
                .setLocationRequest(locationRequest)
                .setFallBackToLastLocationTime(3000)
                .build();

        requestSingleLocationFix(finderLocationRequest);


    }

    void setMapMarker(LatLng latLng) {

        if (!isCrrentMarkerAvailable) {
            mMap.addMarker(new MarkerOptions().position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker1)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            isCrrentMarkerAvailable = true;
        }
    }

    @Override
    public void onLocationPermissionGranted() {

    }

    @Override
    public void onLocationPermissionDenied() {

    }

    @Override
    public void onLocationReceived(Location location) {
        setMapMarker(new LatLng(location.getLatitude(), location.getLongitude()));
        Toast.makeText(this, location.toString(), Toast.LENGTH_SHORT).show();
        Log.e("LocationService", location.getAltitude() + "  Time : " + location.getTime());
    }

    @Override
    public void noLocationReceived() {

    }

    @Override
    public void onLocationProviderEnabled() {

    }

    @Override
    public void onLocationProviderDisabled() {

    }
}
