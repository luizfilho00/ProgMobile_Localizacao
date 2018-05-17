package com.example.a201619060353.localizacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView listView;
    private String[] activities = {"LastLocationActivity",
            "UpdateLocationActivity",
            "AddressLocationActivity"};
    private double lati=-20.501250;
    private double longi=-54.613495;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, activities);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                Intent intent = null;
                switch(position){
                    case 0:
                        intent = new Intent(view.getContext(),
                                LastLocation.class);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(),
                                UpdateLocationActivity.class);
                        break;
                    case 2:{
                        intent = new Intent(view.getContext(),AddressLocationActivity.class);
                        Bundle param = new Bundle();
                        param.putString("lati", Double.toString(lati));
                        param.putString("longi", Double.toString(longi));
                        intent.putExtras(param);
                        break;
                    }
                }
                startActivity(intent);
            }
        });
    }


}
