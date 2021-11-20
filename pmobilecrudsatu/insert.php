<?php
	
	include "koneksi.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
	$nama = isset($_POST['nama']) ? $_POST['nama'] : '';
	$alamat = isset($_POST['alamat']) ? $_POST['alamat'] : '';
	$jeniskelamin = isset($_POST['jeniskelamin']) ? $_POST['jeniskelamin'] : '';
	$kota_pemberangkatan = isset($_POST['kota_pemberangkatan']) ? $_POST['kota_pemberangkatan'] : '';
	$kota_tujuan = isset($_POST['kota_tujuan']) ? $_POST['kota_tujuan'] : '';
	
	if (empty($nama) || empty($alamat) || empty($jeniskelamin) || empty($kota_pemberangkatan) || empty($kota_tujuan)) { 
		echo "Kolom isian tidak boleh kosong"; 
		
	} else if(empty($id)) {
		$query = mysqli_query($con,"INSERT INTO tb_customer (id,nama,alamat,jeniskelamin,kota_pemberangkatan,kota_tujuan) VALUES(0,'".$nama."','".$alamat."','".$jeniskelamin."','".$kota_pemberangkatan."','".$kota_tujuan."')");
		
		if ($query) {
			echo "Data berhasil di simpan";
			
		} else{ 
			echo "Error simpan Data";
			
		}
	}else{
		$query = mysqli_query($con,"UPDATE tb_customer set nama = '".$nama."', alamat = '".$alamat."',jeniskelamin = '".$jeniskelamin."',kota_pemberangkatan = '".$kota_pemberangkatan."',kota_tujuan = '".$kota_tujuan."' where id = '". $id ."'");
		
		if ($query) {
			echo "Data berhasil di ubah";
			
		} else{ 
			echo "Error ubah Data";
			
		}
		
	}
