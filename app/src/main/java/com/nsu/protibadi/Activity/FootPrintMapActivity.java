package com.nsu.protibadi.Activity;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
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
        latlngList= (ArrayList<CustomLatLng>) getIntent().getSerializableExtra("ListOfTrackPoint");

        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        ArrayList<LatLng> allPoint = new ArrayList<>();
        for (CustomLatLng customLatLng : latlngList){
            allPoint.add(new LatLng(customLatLng.getLat(),customLatLng.getLng()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(allPoint.get(0),15));
        mMap.clear();
        Polyline line = mMap.addPolyline(new PolylineOptions().clickable(true)
                .addAll(allPoint)
                .width(20)
                .color(ContextCompat.getColor(FootPrintMapActivity.this,R.color.colorPrimary)));
    }


}
