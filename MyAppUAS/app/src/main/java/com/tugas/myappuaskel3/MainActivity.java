package com.tugas.myappuaskel3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.tugas.myappuaskel3.project1.FormFragment;
import com.tugas.myappuaskel3.project1.HomeFragment;
import com.tugas.myappuaskel3.project1.LingkaranFragment;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener navigation= new OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment f = null;
            switch (menuItem.getItemId()){
                case R.id.home:
                    f = new HomeFragment();
                    break;
                case R.id.form:
                    f = new FormFragment();
                    break;
                case R.id.lingkaran:
                    f = new LingkaranFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, f).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new HomeFragment()).commit();
    }
}