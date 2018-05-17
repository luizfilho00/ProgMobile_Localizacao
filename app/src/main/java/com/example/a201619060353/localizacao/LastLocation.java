package com.example.a201619060353.localizacao;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

public class LastLocation extends Activity {
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSED_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private Boolean mLocationPermissionGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private TextView txtLatitude;
    private TextView txtLongitude;
    private double lati = -20.501250;
    private double longi = -54.613495;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_location);
        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);

        getLocationPermission();
        getDeviceLocation();
    }

    public void getDeviceLocation(){
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if (mLocationPermissionGranted){
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener((task) -> {
                    if (task.isSuccessful()){
                        Location l = (Location) task.getResult();
                        lati = l.getLatitude();
                        longi = l.getLongitude();
                        txtLatitude.setText("Latitude: " + Double.toString(lati));
                        txtLongitude.setText("Longitude: " + Double.toString(longi));
                    }
                });
            }
        }catch (SecurityException e){
            Log.i("TAG", "onComplete current location is null");
        }
    }

    public void getLocationPermission(){
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)
                mLocationPermissionGranted = true;
            else
                ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSED_REQUEST_CODE);
        }
        else{
            ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSED_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case LOCATION_PERMISSED_REQUEST_CODE:
                if (grantResults.length > 0){
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                }
                break;
        }
    }

    public void onClickAbrirMapa(View view) {
        Toast.makeText(this, "Neymar trás o Hexa!", Toast.LENGTH_SHORT).show();
        Bundle param = new Bundle();
        param.putString("lati", Double.toString(lati));
        param.putString("longi", Double.toString(longi));
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtras(param);
        startActivity(intent);
    }
}
