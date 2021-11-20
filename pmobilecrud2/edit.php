<?php
	include "koneksi.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
	class emp{}
	
	if (empty($id)) { 
		echo "Error Mengambil Data id kosong"; 
		
	} else {
		$query 	= mysqli_query($con,"SELECT * FROM tb_sopir WHERE id='".$id."'");
		$row 	= mysqli_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->id = $row["id"];
			$response->nama = $row["nama"];
			$response->alamat = $row["alamat"];
			$response->jeniskelamin= $row["jeniskelamin"];
			$response->nohp = $row["nohp"];
			$response->jenis_kendaraan = $row["jenis_kendaraan"];
		
			echo(json_encode($response));
		} else{ 
			
			echo("Error Mengambil Data"); 
		}	
	}