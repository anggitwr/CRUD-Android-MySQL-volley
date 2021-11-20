package com.tugas.myappuaskel3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tugas.myappuaskel3.project2.InputActivity;
import com.tugas.myappuaskel3.project2.TampilData;
import com.tugas.myappuaskel3.project3.InputActivity2;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigationView = findViewById(R.id.bottom_navigation_2);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    //check id
                    case R.id.home:
                        finish();
                        return false;

                    case R.id.form_1:
                        startActivity(new Intent(getApplicationContext(), InputActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return false;

                    case R.id.form_2:
                        startActivity(new Intent(getApplicationContext(), InputActivity2.class));
                        overridePendingTransition(0,0);
                        finish();
                        return false;
                }
                return false;
            }
        });
    }
}