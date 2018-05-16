package com.example.a201619060353.localizacao;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

public class LastLocation extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    private TextView txtLastLocation;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_location);
        txtLastLocation = findViewById(R.id.txtLastLocation);
        Log.i("LOG", "LastLocationActivity.onCreate()");
        callConection();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null){
                        txtLastLocation.setText(Html.fromHtml(
                                "<b>Last coordinate: <\b><b/>Latitude: " + location.getLatitude()
                                + "<br/>Longitude: " + location.getLongitude()));
                    }
                }
            });
        }
        catch (SecurityException ex){
            ex.printStackTrace();
        }
        catch (Exception ignored){}
    }

    private synchronized void callConection(){
        Log.i("LOG", "LastLocationActivity.callConection()");
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionSuspended(int i){
        //TODO something
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //TODO alguma coisa
    }
}
