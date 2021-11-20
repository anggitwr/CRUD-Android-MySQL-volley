<?php
	include "koneksi.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
	class emp{}
	
	if (empty($id)) { 
		echo "Error Mengambil Data id kosong"; 
		
	} else {
		$query 	= mysqli_query($con,"SELECT * FROM tb_customer WHERE id='".$id."'");
		$row 	= mysqli_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->id = $row["id"];
			$response->nama = $row["nama"];
			$response->alamat = $row["alamat"];
			$response->jeniskelamin= $row["jeniskelamin"];
			$response->kota_pemberangkatan = $row["kota_pemberangkatan"];
			$response->kota_tujuan = $row["kota_tujuan"];
		
			echo(json_encode($response));
		} else{ 
			
			echo("Error Mengambil Data"); 
		}	
	}