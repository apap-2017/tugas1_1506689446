package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.model.*;

@Mapper
public interface KeluargaMapper {
	@Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	KeluargaModel selectKeluarga(String nomor_kk);
	
	@Select("select * from penduduk where id_keluarga=#{id}")
	List<PendudukModel> selectAnggota(String id);
	
	@Select("select * from kelurahan where id = #{id_kelurahan}")
	KelurahanModel selectKel(String id_kelurahan);
	
	@Select("select * from kecamatan where id = #{id_kecamatan}")
	KecamatanModel selectKec(String id_kecamatan);
	
	@Select("select * from kota where id = #{id_kota}")
	KotaModel selectKot(String id_kota);
	
	@Insert("INSERT INTO keluarga (id, nomor_kk, alamat, RT, RW, id_kelurahan, is_tidak_berlaku) VALUES (#{id}, #{nomor_kk}, #{alamat}, #{RT}, #{RW}, #{id_kelurahan}, #{is_tidak_berlaku})")
    void addKeluarga (KeluargaModel keluarga);
	
	@Select("select count(*) from keluarga where nomor_kk like #{nomor_kk}")
	int selectKeluargaLike(String nomor_kk);
	
	@Select("select max(id) from keluarga")
	int selectMaxIDKel();
	
	@Select("select * from keluarga")
    List<KeluargaModel> selectAllKeluargas();
	
	@Update("UPDATE keluarga SET alamat=#{alamat}, RT=#{RT}, RW=#{RW}, id_kelurahan=#{id_kelurahan} WHERE id=#{id}")
    void updateKeluarga(KeluargaModel keluarga);
	
	@Update("UPDATE keluarga SET nomor_kk=#{nomor_kk} WHERE id=#{id}")
    void updateNkkKeluarga(@Param("nomor_kk") String nomor_kk, @Param("id") String id);

	@Select("select id from keluarga where nomor_kk = #{nomor_kk}")
	String selectIDKeluarga(@Param("nik") String nik);
	
	@Update("update keluarga set is_tidak_berlaku='1' where id=#{id}")
	void updateTidakBerlaku(String id);
}
