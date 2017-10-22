package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KecamatanModel;
import com.example.model.KotaModel;

public interface PendudukService {
	PendudukModel selectPenduduk(String nik);
	
	KeluargaModel selectKlg(String id_keluarga);
	
	KelurahanModel selectKel(String id_kelurahan);
	
	KecamatanModel selectKec(String id_kecamatan);
	
	KotaModel selectKot(String id_kota);
	
	int selectMaxID();
	
	int selectPendudukLike(String nik);
	
	List<PendudukModel> selectAllPenduduks();
	
	void addPenduduk(PendudukModel penduduk);
	
	void updatePenduduk(PendudukModel penduduk);
	
	String selectIDpenduduk(@Param("nik") String nik);
	
	void updateNikPenduduk(@Param("nik") String nik, @Param("id") String id);
	
	void updateSetWafat(@Param("nik") String nik);
	
	public int selectCountIsWafat(String id);
}
