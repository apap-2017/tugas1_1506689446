package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.PendudukModel;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;

public interface KeluargaService {
	KeluargaModel selectKeluarga(String nomor_kk);
	
	List<PendudukModel> selectAnggota(String id);
	
	KelurahanModel selectKel(String id_kelurahan);
	
	KecamatanModel selectKec(String id_kecamatan);
	
	KotaModel selectKot(String id_kota);
	
	void addKeluarga (KeluargaModel keluarga);
	
	int selectKeluargaLike(String nomor_kk);
	
	int selectMaxIDKel();
	
	List<KeluargaModel> selectAllKeluargas();
	
	void updateKeluarga(KeluargaModel keluarga);
	
	String selectIDKeluarga(@Param("nomor_kk") String nomor_kk);
	
	void updateNkkKeluarga(@Param("nomor_kk") String nomor_kk, @Param("id") String id);
	
	void updateTidakBerlaku(String id);
}
