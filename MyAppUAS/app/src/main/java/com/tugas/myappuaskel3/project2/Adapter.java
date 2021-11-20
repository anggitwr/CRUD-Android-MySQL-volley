package com.tugas.myappuaskel3.project2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tugas.myappuaskel3.R;

import java.util.List;

public class Adapter extends BaseAdapter {

    Activity activity;
    List<Data> items;
    private LayoutInflater inflater;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;

    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list, null);

        TextView id = (TextView) convertView.findViewById(R.id.tv_id);
        TextView nama = (TextView) convertView.findViewById(R.id.tv_nama);
        TextView alamat = (TextView) convertView.findViewById(R.id.tv_alamat);
        TextView jeniskelamin = (TextView)convertView.findViewById(R.id.tv_jeniskelamin);
        TextView kota_pemberangkatan = (TextView) convertView.findViewById(R.id.tv_ktpemb);
        TextView kota_tujuan = (TextView) convertView.findViewById(R.id.tv_kttujuan);

        Data data = items.get(position);

        id.setText(data.getId());
        nama.setText(data.getNama());
        alamat.setText(data.getAlamat());
        jeniskelamin.setText(data.getJeniskelamin());
        kota_pemberangkatan.setText(data.getKota_pemberangkatan());
        kota_tujuan.setText(data.getKota_tujuan());

        return convertView;

    }
}
