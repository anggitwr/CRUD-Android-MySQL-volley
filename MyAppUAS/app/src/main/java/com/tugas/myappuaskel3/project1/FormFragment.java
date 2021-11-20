package com.tugas.myappuaskel3.project1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tugas.myappuaskel3.LoginActivity;
import com.tugas.myappuaskel3.MainActivity;
import com.tugas.myappuaskel3.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {

    EditText nama, nik, alamat, usia, institusi;
    TextView hnama, hnik, halamat, humur, hinstitusi, hjekel;
    RadioGroup rgrup;
    RadioButton rblaki,rbperempuan;
    Button btnsimpan,btnlogout;
    String selectedrb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        nama = (EditText) view.findViewById(R.id.input_nama);
        nik = (EditText) view.findViewById(R.id.input_nik);
        alamat = (EditText) view.findViewById(R.id.input_alamat);
        usia = (EditText) view.findViewById(R.id.input_umur);
        institusi = (EditText) view.findViewById(R.id.input_institusi);
        rgrup = (RadioGroup) view.findViewById(R.id.opsi);
        rblaki = (RadioButton) view.findViewById(R.id.rd_laki);
        rbperempuan = (RadioButton) view.findViewById(R.id.rd_perempuan);
        hnama = (TextView) view.findViewById(R.id.hasil_nama);
        hjekel = (TextView) view.findViewById(R.id.hasil_jk);
        hnik = (TextView) view.findViewById(R.id.hasil_nik);
        halamat= (TextView) view.findViewById(R.id.hasil_alamat);
        humur= (TextView) view.findViewById(R.id.hasil_umur);
        hinstitusi= (TextView) view.findViewById(R.id.hasil_institusi);
        btnsimpan = (Button) view.findViewById(R.id.simpan_button);
        btnlogout = (Button) view.findViewById(R.id.logout_button);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentL = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentL);
                Toast.makeText(getActivity(),"Berhasil logout",Toast.LENGTH_LONG).show();
            }
        });
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namae, alamt, usi, institus;
                String nomorik;

                if (rblaki.isChecked()){
                    selectedrb = rblaki.getText().toString();

                } else if (rbperempuan.isChecked()){
                    selectedrb = rbperempuan.getText().toString();
                } else if (rblaki.isEnabled()){
                    rblaki.setError("Radio button tidak boleh kosong!");
                    rbperempuan.setError("Radio button tidak boleh kosong!");
                }

                namae = nama.getText().toString().trim();
                if (namae.equals("")){
                    nama.setError("Nama harus di isi!!");
                } else {
                    nama.setText(namae);
                }

                alamt = alamat.getText().toString().trim();
                if (alamt.equals("")){
                    alamat.setError("Alamat harus di isi!!");
                } else {
                    alamat.setText(alamt);
                }

                usi = usia.getText().toString().trim();
                if (usi.equals("")){
                    usia.setError("Usia harus di isi!!");
                } else {
                    usia.setText(usi);

                }

                institus = institusi.getText().toString().trim();
                if (institus.equals("")){
                    institusi.setError("Asal sekolah harus di isi!!");
                } else {
                    institusi.setText(institus);

                }

                nomorik = nik.getText().toString().trim();
                if (nomorik.equals("")){
                    nik.setError("Usia harus di isi!!");
                } else {
                    nik.setText(nomorik);
                }

                hnama.setText(namae);
                hjekel.setText(selectedrb);
                hnik.setText(nomorik);
                halamat.setText(alamt);
                humur.setText(usi);
                hinstitusi.setText(institus);
            }
        });
    }
}