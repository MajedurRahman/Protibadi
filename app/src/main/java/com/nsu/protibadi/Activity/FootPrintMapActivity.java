package com.nsu.protibadi.Activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nsu.protibadi.Model.CustomLatLng;
import com.nsu.protibadi.R;

import java.util.ArrayList;

public class FootPrintMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    ArrayList<CustomLatLng> latlngList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_foot_print_map);
        latlngList = (ArrayList<CustomLatLng>) getIntent().getSerializableExtra("ListOfTrackPoint");

        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        ArrayList<LatLng> allPoint = new ArrayList<>();
        for (CustomLatLng customLatLng : latlngList) {
            allPoint.add(new LatLng(customLatLng.getLat(), customLatLng.getLng()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(allPoint.get(allPoint.size() / 2), 15));
        mMap.clear();
        Polyline line = mMap.addPolyline(new PolylineOptions().clickable(true)
                .addAll(allPoint)
                .width(20)
                .color(ContextCompat.getColor(FootPrintMapActivity.this, R.color.colorPrimary)));
        setMapMarker(allPoint.get(0), R.drawable.marker1);
        setMapMarker(allPoint.get(allPoint.size() - 1), R.drawable.marker2);
    }

    void setMapMarker(LatLng latLng, int marker2) {

        mMap.addMarker(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(marker2)));
    }
}
