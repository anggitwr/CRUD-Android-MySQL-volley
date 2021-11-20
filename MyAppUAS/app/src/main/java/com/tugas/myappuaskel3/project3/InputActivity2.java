package com.tugas.myappuaskel3.project3;


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
import com.tugas.myappuaskel3.project2.InputActivity;

import java.util.HashMap;
import java.util.Map;

public class InputActivity2 extends AppCompatActivity {

    public static final String url_insert = "http://192.168.43.225/pmobilecrud2/insert.php";

    EditText tnama,tjeniskelamin,talamat,tnohp,tjkend;
    Button btn_simpan_data, btn_tampil_list, btn_logout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2);

        bottomNavigationView = findViewById(R.id.bottom_navigation_2);
        bottomNavigationView.setSelectedItemId(R.id.form_2);

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
                        startActivity(new Intent(getApplicationContext(), InputActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return false;

                    case R.id.form_2:
                        finish();
                        return false;
                }
                return false;
            }
        });
        tnama = findViewById(R.id.et_nama2);
        talamat = findViewById(R.id.et_alamat2);
        tjeniskelamin = findViewById(R.id.et_jeniskelamin2);
        tnohp = findViewById(R.id.et_nohp);
        tjkend = findViewById(R.id.et_jkendaraan);

        btn_simpan_data = findViewById(R.id.btn_tambah2);
        btn_simpan_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_data2();
            }
        });
        btn_tampil_list = findViewById(R.id.btn_tampil_list2);
        btn_tampil_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil_list2();
            }
        });
        btn_logout = findViewById(R.id.logout_button);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentL = new Intent(InputActivity2.this, LoginActivity.class);
                startActivity(intentL);
                finish();
                Toast.makeText(InputActivity2.this, "Log out success",Toast.LENGTH_SHORT).show();
            }
        });
    }

    void tampil_list2() {
        Intent intent = new Intent(InputActivity2.this, TampilData2.class);
        startActivity(intent);
    }

    void input_data2() {
        String nama = tnama.getText().toString();
        String alamat = talamat.getText().toString();
        String jeniskelamin = tjeniskelamin.getText().toString();
        String nohp = tnohp.getText().toString();
        String jenis_kendaraan = tjkend.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_insert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(InputActivity2.this, response, Toast.LENGTH_LONG).show();
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
                params.put("nohp", nohp);
                params.put("jenis_kendaraan", jenis_kendaraan);

                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}