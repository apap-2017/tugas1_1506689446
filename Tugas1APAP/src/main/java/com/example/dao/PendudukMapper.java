package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.model.*;

@Mapper
public interface PendudukMapper {
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
	
	@Select("select max(id) from penduduk")
	int selectMaxID();
	
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) VALUES (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
    void addPenduduk (PendudukModel penduduk);
	
	@Select("select count(*) from penduduk where nik like #{nik}")
	int selectPendudukLike(String nik);
	
	@Select("select * from penduduk")
    List<PendudukModel> selectAllPenduduks ();
	
	@Update("UPDATE penduduk SET nama=#{nama}, tempat_lahir=#{tempat_lahir}, tanggal_lahir=#{tanggal_lahir}, jenis_kelamin=#{jenis_kelamin}, is_wni=#{is_wni}, id_keluarga=#{id_keluarga}, agama=#{agama}, pekerjaan=#{pekerjaan}, status_perkawinan=#{status_perkawinan}, status_dalam_keluarga=#{status_dalam_keluarga}, golongan_darah=#{golongan_darah} WHERE nik=#{nik}")
    void updatePenduduk(PendudukModel penduduk);
	
	@Update("UPDATE penduduk SET nik=#{nik} WHERE id=#{id}")
    void updateNikPenduduk(@Param("nik") String nik, @Param("id") String id);

	@Select("select id from penduduk where nik = #{nik}")
	String selectIDpenduduk(@Param("nik") String nik);
	
	@Update("update penduduk set is_wafat='1' where nik=#{nik}")
	void updateSetWafat(@Param("nik") String nik);
	
	@Select("select count(*) from penduduk p, keluarga k where id_keluarga = #{id} and p.id_keluarga = k.id and p.is_wafat='1'")
	int selectCountIsWafat(String id);
}
