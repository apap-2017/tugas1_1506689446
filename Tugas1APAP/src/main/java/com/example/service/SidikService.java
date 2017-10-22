package com.example.service;

import java.util.List;

import com.example.model.*;

public interface SidikService {
	PendudukModel selectPenduduk(String nik);
	
	KeluargaModel selectKlg(String id_keluarga);
	
	KelurahanModel selectKel(String id_kelurahan);
	
	KecamatanModel selectKec(String id_kecamatan);
	
	KotaModel selectKot(String id_kota);
	
	List<KotaModel> selectAllKota();
	
	List<String> selectNamaKota();
	
	List<KecamatanModel> selectAllKecByKota(String id);
	
	List<KelurahanModel> selectAllKelByKec(String id);
	
	List<PendudukModel> selectPendudukInKelurahan(String id);
	
	PendudukModel selectPendudukTertua(String id);
	
	PendudukModel selectPendudukTermuda(String id);	
	
	List<KelurahanModel> selectAllKel();
	
	List<KecamatanModel> selectAllKec();
	
}
