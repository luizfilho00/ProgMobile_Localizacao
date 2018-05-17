package com.example.a201619060353.localizacao;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class AddressLocationActivity extends Activity {
    private TextView txtLatitude, txtLongitude, txtRua, txtBairro, txtCidade, txtEstado, txtPais;
    double latiD, longiD;
    Address endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_location);
        txtLatitude = findViewById(R.id.txtLatitudeRua);
        txtLongitude = findViewById(R.id.txtLongitudeRua);
        txtRua = findViewById(R.id.txtRua);
        txtBairro = findViewById(R.id.txtBairro);
        txtCidade = findViewById(R.id.txtCidade);
        txtEstado = findViewById(R.id.txtEstado);
        txtPais = findViewById(R.id.txtPais);

        Bundle args = getIntent().getExtras();
        String lati = args.getString("lati");
        String longi = args.getString("longi");
        latiD = Double.parseDouble(lati);
        longiD = Double.parseDouble(longi);

        txtLatitude.setText("Latitude: " + lati);
        txtLongitude.setText("Longitude: " + longi);
        try{
            endereco = buscarEndereco(latiD, longiD);
            String end = endereco.getAddressLine(0);
            end = end.split("-")[0];
            txtBairro.setText("Bairro: " + endereco.getSubLocality());
            txtPais.setText("Pais: " + endereco.getCountryName());
            txtCidade.setText("Cidade: " + endereco.getLocality());
            txtEstado.setText("Estado: " + endereco.getAdminArea());
            txtRua.setText("Rua: " + end);
        }catch (Exception e){ Log.i("GPS", e.getMessage()); }

    }

    public Address buscarEndereco(double lati, double longi) throws Exception{
        Geocoder geocoder;
        Address address = null;
        List<Address> add;
        geocoder = new Geocoder(getApplicationContext());
        add = geocoder.getFromLocation(lati, longi, 1);

        if (add.size() > 0){
            address = add.get(0);
        }

        return address;
    }
}
