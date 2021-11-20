package com.tugas.myappuaskel3.project2;


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
import com.tugas.myappuaskel3.project3.InputActivity2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TampilData extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String url_selected = "http://192.168.43.225/pmobilecrudsatu/selected.php";
    public static final String url_delete = "http://192.168.43.225/pmobilecrudsatu/delete.php";
    public static final String url_edit = "http://192.168.43.225/pmobilecrudsatu/edit.php";
    public static final String url_insert = "http://192.168.43.225/pmobilecrudsatu/insert.php";

    ListView list;
    SwipeRefreshLayout swipe;
    List<Data> itemlist = new ArrayList<Data>();
    Adapter adapter;
    AlertDialog.Builder dialog;

    LayoutInflater inflater;
    View dialogView;
    EditText tid,tnama,tktpemb,tkttujuan,tjeniskelamin,talamat;
    String vid, vnama, vktpemb, vkttujuan, vjeniskelamin, valamat;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_tampil);

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

        swipe = (SwipeRefreshLayout) findViewById(R.id.swip);
        list = (ListView) findViewById(R.id.list);

        adapter = new Adapter(TampilData.this, itemlist);
        list.setAdapter(adapter);
        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                itemlist.clear();
                adapter.notifyDataSetChanged();
                callVolley();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemlist.get(position).getId();
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(TampilData.this);

                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                ubah1(idx);
                                break;
                            case 1:
                                hapusData(idx);
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
    }

    public void ubah1(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_edit,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idx = jObj.getString("id");
                            String namax = jObj.getString("nama");
                            String alamatx = jObj.getString("alamat");
                            String jeniskelaminx = jObj.getString("jeniskelamin");
                            String ktpembx = jObj.getString("kota_pemberangkatan");
                            String kttujuanx = jObj.getString("kota_tujuan");


                            dialog_form1(idx, namax,alamatx, jeniskelaminx,ktpembx,kttujuanx, "UPDATE");

                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilData.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
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

    public void dialog_form1(String idx, String namax, String jeniskelaminx, String alamatx, String ktpembx,String kttujuanx,String button) {
        dialog = new AlertDialog.Builder(TampilData.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_edit, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.ic_icon);
        dialog.setTitle("Form Edit Data");

        tid = (EditText) dialogView.findViewById(R.id.et_id);
        tnama = (EditText) dialogView.findViewById(R.id.et_nama);
        talamat = (EditText) dialogView.findViewById(R.id.et_alamat);
        tjeniskelamin = (EditText) dialogView.findViewById(R.id.et_jkel);
        tktpemb = (EditText) dialogView.findViewById(R.id.et_ktpemb);
        tkttujuan= (EditText) dialogView.findViewById(R.id.et_kttujuan);

        if (!idx.isEmpty()) {
            tid.setText(idx);
            tnama.setText(namax);
            talamat.setText(alamatx);
            tjeniskelamin.setText(jeniskelaminx);
            tktpemb.setText(ktpembx);
            tkttujuan.setText(kttujuanx);

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
                vktpemb = tktpemb.getText().toString();
                vkttujuan = tkttujuan.getText().toString();

                simpan();
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

    void simpan(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_insert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(TampilData.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilData.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
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
                    params.put("kota_pemberangkatan", vktpemb);
                    params.put("kota_tujuan", vkttujuan);
                    return params;
                }else{
                    params.put("id", vid);
                    params.put("nama", vnama);
                    params.put("alamat", valamat);
                    params.put("jeniskelamin", vjeniskelamin);
                    params.put("kota_pemberangkatan", vktpemb);
                    params.put("kota_tujuan", vkttujuan);
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
        tktpemb.setText(null);
        tkttujuan.setText(null);

    }

    public void hapusData(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_delete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(TampilData.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilData.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();

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
        swipe.setRefreshing(true);
        JsonArrayRequest jArr = new JsonArrayRequest(url_selected,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);

                                Data item = new Data();

                                item.setId(obj.getString("id"));
                                item.setNama(obj.getString("nama"));
                                item.setAlamat(obj.getString("alamat"));
                                item.setJeniskelamin(obj.getString("jeniskelamin"));
                                item.setKota_pemberangkatan(obj.getString("kota_pemberangkatan"));
                                item.setKota_tujuan(obj.getString("kota_tujuan"));

                                itemlist.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();

                        swipe.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilData.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);
    }
}