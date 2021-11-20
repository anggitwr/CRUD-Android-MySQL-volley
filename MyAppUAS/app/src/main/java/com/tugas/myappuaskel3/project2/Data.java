package com.tugas.myappuaskel3.project2;

public class Data {
    private String id,nama,alamat,jeniskelamin,kota_tujuan,kota_pemberangkatan;


    public Data (String id, String nama, String jeniskelamin, String alamat, String kota_pemberangkatan, String kota_tujuan){
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.jeniskelamin = jeniskelamin;
        this.kota_pemberangkatan = kota_pemberangkatan;
        this.kota_tujuan = kota_tujuan;
    }

    public Data() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getKota_tujuan() {
        return kota_tujuan;
    }

    public void setKota_tujuan(String kota_tujuan) {
        this.kota_tujuan = kota_tujuan;
    }

    public String getKota_pemberangkatan() {
        return kota_pemberangkatan;
    }

    public void setKota_pemberangkatan(String kota_pemberangkatan) {
        this.kota_pemberangkatan = kota_pemberangkatan;
    }
}
