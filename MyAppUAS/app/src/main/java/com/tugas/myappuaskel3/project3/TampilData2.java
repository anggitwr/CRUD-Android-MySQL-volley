package com.tugas.myappuaskel3.project3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tugas.myappuaskel3.MainActivity2;
import com.tugas.myappuaskel3.R;
import com.tugas.myappuaskel3.project2.InputActivity;
//import com.tugas.myappuaskel3.project2.InputActivity;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TampilData2 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String url = "http://192.168.43.225/pmobilecrud2/selected.php";
    public static final String url_delete = "http://192.168.43.225/pmobilecrud2/delete.php";
    public static final String url_edit = "http://192.168.43.225/pmobilecrud2/edit.php";
    public static final String url_insert = "http://192.168.43.225/pmobilecrud2/insert.php";

    ListView list2;
    SwipeRefreshLayout swipe2;
    List<Data2> itemlist = new ArrayList<>();
    Adapter2 adapter;
    AlertDialog.Builder dialog;

    LayoutInflater inflater;
    View dialogView;
    EditText tid,tnama,tnohp,tjekend,tjeniskelamin,talamat;
    String vid, vnama, vkjekend, vnohp, vjeniskelamin, valamat;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data2);
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
        swipe2 = (SwipeRefreshLayout) findViewById(R.id.swip2);

        list2 = (ListView) findViewById(R.id.list2);

        adapter = new Adapter2(TampilData2.this, itemlist);
        list2.setAdapter(adapter);

        swipe2.setOnRefreshListener(this);

        swipe2.post(new Runnable() {
            @Override
            public void run() {
                swipe2.setRefreshing(true);
                itemlist.clear();
                adapter.notifyDataSetChanged();
                callVolley();
            }
        });
        list2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemlist.get(position).getId();
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(TampilData2.this);

                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                ubah2(idx);
                                break;
                            case 1:
                                hapusData2(idx);
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
    }

    public void ubah2(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_edit,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idx2 = jObj.getString("id");
                            String namax = jObj.getString("nama");
                            String alamatx = jObj.getString("alamat");
                            String jeniskelaminx = jObj.getString("jeniskelamin");
                            String nohpx = jObj.getString("nohp");
                            String jekendx = jObj.getString("jenis_kendaraan");


                            dialog_form(idx2, namax,alamatx, jeniskelaminx,nohpx,jekendx, "UPDATE");

                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilData2.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("id", id );
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    public void dialog_form(String idx, String namax, String alamatx,String jeniskelaminx,String nohpx,String jekendx, String button) {
        dialog = new AlertDialog.Builder(TampilData2.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_edit2, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.ic_icon);
        dialog.setTitle("Form Edit Data");

        tid = (EditText) dialogView.findViewById(R.id.et_id2);
        tnama = (EditText) dialogView.findViewById(R.id.et_nama2);
        talamat = (EditText) dialogView.findViewById(R.id.et_alamat2);
        tjeniskelamin = (EditText) dialogView.findViewById(R.id.et_jeniskelamin2);
        tnohp = (EditText) dialogView.findViewById(R.id.et_nohp);
        tjekend = (EditText) dialogView.findViewById(R.id.et_jekend);

        if (!idx.isEmpty()) {
            tid.setText(idx);
            tnama.setText(namax);
            talamat.setText(alamatx);
            tjeniskelamin.setText(jeniskelaminx);
            tnohp.setText(nohpx);
            tjekend.setText(jekendx);

        } else {
            kosong();
        }

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                vid = tid.getText().toString();
                vnama = tnama.getText().toString();
                valamat = talamat.getText().toString();
                vjeniskelamin = tjeniskelamin.getText().toString();
                vnohp = tnohp.getText().toString();
                vkjekend = tjekend.getText().toString();

                simpan2();
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                kosong();
            }
        });

        dialog.show();

    }

    void simpan2(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_insert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(TampilData2.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilData2.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                if (vid.isEmpty()) {
                    params.put("nama", vnama);
                    params.put("alamat", valamat);
                    params.put("jeniskelamin", vjeniskelamin);
                    params.put("nohp", vnohp);
                    params.put("jenis_kendaraan", vkjekend);

                    return params;
                }else{
                    params.put("id", vid);
                    params.put("nama", vnama);
                    params.put("alamat", valamat);
                    params.put("jeniskelamin", vjeniskelamin);
                    params.put("nohp", vnohp);
                    params.put("jenis_kendaraan", vkjekend);

                    return params;
                }
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    private void kosong() {
        tid.setText(null);
        tnama.setText(null);
        talamat.setText(null);
        tjeniskelamin.setText(null);
        tnohp.setText(null);
        tjekend.setText(null);

    }

    public void hapusData2(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_delete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(TampilData2.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilData2.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id );
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    public void onRefresh(){
        itemlist.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }

    private void callVolley() {
        itemlist.clear();
        adapter.notifyDataSetChanged();
        swipe2.setRefreshing(true);
        JsonArrayRequest jArr = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);

                                Data2 item = new Data2();

                                item.setId(obj.getString("id"));
                                item.setNama(obj.getString("nama"));
                                item.setAlamat(obj.getString("alamat"));
                                item.setJeniskelamin(obj.getString("jeniskelamin"));
                                item.setNohp(obj.getString("nohp"));
                                item.setJenis_kendaraan(obj.getString("jenis_kendaraan"));


                                itemlist.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();

                        swipe2.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilData2.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
                swipe2.setRefreshing(false);
            }
        });
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);
    }
}