package com.example.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.model.*;

@Mapper
public interface SidikMapper {
	@Select("select * from penduduk where nik = #{nik}")
	PendudukModel selectPenduduk(String nik);
	
	@Select("select * from keluarga where id = #{id_keluarga}")
	KeluargaModel selectKlg(String id_keluarga);
	
	@Select("select * from kelurahan where id = #{id_kelurahan}")
	KelurahanModel selectKel(String id_kelurahan);
	
	@Select("select * from kecamatan where id = #{id_kecamatan}")
	KecamatanModel selectKec(String id_kecamatan);
	
	@Select("select * from kota where id = #{id_kota}")
	KotaModel selectKot(String id_kota);
	
	@Select("select * from kota")
	List<KotaModel> selectAllKota();
	
	@Select("select * from kecamatan")
	List<KecamatanModel> selectAllKec();
	
	@Select("select * from kelurahan")
	List<KelurahanModel> selectAllKel();
	
	@Select("select nama_kota from kota")
	List<String> selectNamaKota();
	
	@Select("select * from kecamatan where id_kota=#{id}")
	List<KecamatanModel> selectAllKecByKota(String id);
	
	@Select("select * from kelurahan where id_kecamatan=#{id}")
	List<KelurahanModel> selectAllKelByKec(String id);
	
	@Select("SELECT * FROM penduduk p, keluarga k, kelurahan kl where kl.id = #{id} and kl.id = k.id_kelurahan and p.id_keluarga = k.id")
	List<PendudukModel> selectPendudukInKelurahan(String id);
	
	@Select("SELECT * FROM penduduk p, keluarga k, kelurahan kl where kl.id = #{id} and kl.id = k.id_kelurahan and p.id_keluarga = k.id order by p.tanggal_lahir ASC LIMIT 1")
	PendudukModel selectPendudukTertua(String id);
	
	@Select("SELECT * FROM penduduk p, keluarga k, kelurahan kl where kl.id = #{id} and kl.id = k.id_kelurahan and p.id_keluarga = k.id order by p.tanggal_lahir DESC LIMIT 1")
	PendudukModel selectPendudukTermuda(String id);
}	
