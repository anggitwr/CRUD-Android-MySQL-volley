package com.tugas.myappuaskel3.project2;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.tugas.myappuaskel3.LoginActivity;
import com.tugas.myappuaskel3.MainActivity;
import com.tugas.myappuaskel3.MainActivity2;
import com.tugas.myappuaskel3.R;
import com.tugas.myappuaskel3.project3.InputActivity2;

import java.util.HashMap;
import java.util.Map;

public class InputActivity extends AppCompatActivity {

    public static final String url_insert = "http://192.168.43.225/pmobilecrudsatu/insert.php";

    EditText tnama,tjeniskelamin,talamat,tpemb,ttujuan;
    Button btn_simpan_data, btn_tampil_list, btn_logout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        bottomNavigationView = findViewById(R.id.bottom_navigation_2);
        bottomNavigationView.setSelectedItemId(R.id.form_1);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    //check id
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                        overridePendingTransition(0,0);
                        finish();
                        return false;

                    case R.id.form_1:
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

        tnama = findViewById(R.id.et_nama);
        talamat = findViewById(R.id.et_alamat);
        tjeniskelamin = findViewById(R.id.et_jeniskelamin);
        tpemb = findViewById(R.id.et_pembarangkatan);
        ttujuan = findViewById(R.id.et_tujuan);

        btn_simpan_data = findViewById(R.id.btn_tambah);
        btn_simpan_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_data();
            }
        });
        btn_tampil_list = findViewById(R.id.btn_tampil_list);
        btn_tampil_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil_list();
            }
        });

        btn_logout = findViewById(R.id.logout_button);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentL = new Intent(InputActivity.this, LoginActivity.class);
                startActivity(intentL);
                finish();
                Toast.makeText(InputActivity.this, "Log out success",Toast.LENGTH_SHORT).show();
            }
        });
    }

    void tampil_list() {
        Intent intent = new Intent(InputActivity.this, TampilData.class);
        startActivity(intent);
        return;
    }

    void input_data() {
        String nama = tnama.getText().toString();
        String alamat = talamat.getText().toString();
        String jeniskelamin = tjeniskelamin.getText().toString();
        String kota_pemberangkatan = tpemb.getText().toString();
        String kota_tujuan = ttujuan.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_insert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(InputActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("nama", nama);
                params.put("alamat", alamat);
                params.put("jeniskelamin", jeniskelamin);
                params.put("kota_pemberangkatan", kota_pemberangkatan);
                params.put("kota_tujuan", kota_tujuan);

                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}