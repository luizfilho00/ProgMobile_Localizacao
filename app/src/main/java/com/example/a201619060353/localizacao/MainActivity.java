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
    String[] activities = {"LastLocationActivity", "UpdateLocationActivity",
            "AddressLocationActivity", "Map" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(view.getContext(), LastLocation.class);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), UpdateLocationActivity.class);
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), AddressLocationActivity.class);
                        break;
                    case 3:
                        intent = new Intent(view.getContext(), MapsActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }

}
