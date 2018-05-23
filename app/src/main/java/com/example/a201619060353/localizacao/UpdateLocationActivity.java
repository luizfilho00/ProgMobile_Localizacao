package com.example.a201619060353.localizacao;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

public class UpdateLocationActivity extends Activity implements LocationListener{
    private TextView txtLatitude;
    private TextView txtLongitude;
    private double lati = -20.501250;
    private double longi = -54.613495;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);

        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            // Não permitido
            return;
        }

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);

    }

    public void onClickAbrirMapa(View view) {
    }

    @Override
    public void onLocationChanged(Location location) {
        lati = location.getLatitude();
        longi = location.getLongitude();
        txtLatitude.setText("Latitude: " + lati);
        txtLongitude.setText("Longitude: " + longi);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
