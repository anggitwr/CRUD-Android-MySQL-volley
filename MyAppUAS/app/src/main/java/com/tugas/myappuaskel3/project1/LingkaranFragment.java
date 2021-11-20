package com.tugas.myappuaskel3.project1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tugas.myappuaskel3.LoginActivity;
import com.tugas.myappuaskel3.R;

public class LingkaranFragment extends androidx.fragment.app.Fragment {
    private Button luas, keliling, hapus, btnlogout;
    private EditText sisiLuas, sisiKeliling;
    private EditText hslLuas, hslKeliling;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_lingkaran, container, false);

        luas = root.findViewById(R.id.btnprosesll);
        keliling = root.findViewById(R.id.btnproseskk);
        sisiLuas = root.findViewById(R.id.ejari);
        sisiKeliling = root.findViewById(R.id.ejari);
        hslLuas = root.findViewById(R.id.eluas);
        hslKeliling = root.findViewById(R.id.ekeliling);
        hapus = root.findViewById(R.id.btnhps);
        btnlogout = root.findViewById(R.id.logout_button);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentL = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentL);
                Toast.makeText(getActivity(),"Berhasil logout",Toast.LENGTH_LONG).show();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sisiKeliling.setText("");
                hslKeliling.setText("");
                hslLuas.setText("");
            }
        });
        luas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sisi = sisiLuas.getText().toString();
                if (sisi.isEmpty()){
                    sisiLuas.setError("Data harus diisi");
                    sisiLuas.requestFocus();
                } else {
                    Double angka = Double.parseDouble(sisi);
                    Double luas = angka * 3.14;
                    hslLuas.setText("Hasil : "+String.format("%.2f", luas));
                }
            }
        });

        keliling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sisi = sisiKeliling.getText().toString();
                if (sisi.isEmpty()){
                    sisiKeliling.setError("Data harus diisi");
                    sisiKeliling.requestFocus();
                } else {
                    Double angka = Double.parseDouble(sisi);
                    Double keliling = 3.14 * 2 * angka;
                    hslKeliling.setText("Hasil : "+String.format("%.2f", keliling));
                }
            }
        });
        return root;
    }
}