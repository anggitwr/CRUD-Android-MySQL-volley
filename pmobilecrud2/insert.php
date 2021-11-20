<?php
	
	include "koneksi.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
	$nama = isset($_POST['nama']) ? $_POST['nama'] : '';
	$alamat = isset($_POST['alamat']) ? $_POST['alamat'] : '';
	$jeniskelamin = isset($_POST['jeniskelamin']) ? $_POST['jeniskelamin'] : '';
	$nohp = isset($_POST['nohp']) ? $_POST['nohp'] : '';
	$jenis_kendaraan = isset($_POST['jenis_kendaraan']) ? $_POST['jenis_kendaraan'] : '';
	
	if (empty($nama) || empty($alamat) || empty($jeniskelamin) || empty($nohp) || empty($jenis_kendaraan)) { 
		echo "Kolom isian tidak boleh kosong"; 
		
	} else if(empty($id)) {
		$query = mysqli_query($con,"INSERT INTO tb_sopir (id,nama,alamat,jeniskelamin,nohp,jenis_kendaraan) VALUES(0,'".$nama."','".$alamat."','".$jeniskelamin."','".$nohp."','".$jenis_kendaraan."')");
		
		if ($query) {
			echo "Data berhasil di simpan";
			
		} else{ 
			echo "Error simpan Data";
			
		}
	}else{
		$query = mysqli_query($con,"UPDATE tb_sopir set nama = '".$nama."', alamat = '".$alamat."',jeniskelamin = '".$jeniskelamin."',nohp = '".$nohp."',jenis_kendaraan = '".$jenis_kendaraan."' where id = '". $id ."'");
		
		if ($query) {
			echo "Data berhasil di ubah";
			
		} else{ 
			echo "Error ubah Data";
			
		}
		
	}
