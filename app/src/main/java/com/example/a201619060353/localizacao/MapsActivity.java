package com.example.a201619060353.localizacao;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
    private GoogleMap mMap;
    private Double longiD;
    private Double latiD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bundle args = getIntent().getExtras();
        String lati = args.getString("lati");
        String longi = args.getString("longi");
        latiD = Double.parseDouble(lati);
        longiD = Double.parseDouble(longi);
        Log.i("TAG","latitude = "+lati);
        Log.i("TAG","longitude = "+longi);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latiD, longiD);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

