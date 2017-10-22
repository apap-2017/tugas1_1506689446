package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.*;
import com.example.model.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	@Autowired
    private KeluargaMapper keluargaMapper;
	
	@Override
	public KeluargaModel selectKeluarga(String nomor_kk) {
		log.info ("select keluarga with nomor_kk {}", nomor_kk);
        return keluargaMapper.selectKeluarga(nomor_kk);
	}
	
	@Override
	public List<PendudukModel> selectAnggota(String id) {
		log.info("select anggota keluarga with id {}", id);
		return keluargaMapper.selectAnggota(id);
	}
	
	@Override
	public KelurahanModel selectKel(String id_kelurahan)
	{
		 log.info ("select kelurahan with id_kelurahan {}", id_kelurahan);
	     return keluargaMapper.selectKel(id_kelurahan);
	}
	
	@Override
	public KecamatanModel selectKec(String id_kecamatan)
	{
		 log.info ("select kecamatan with id_kecamatan {}", id_kecamatan);
	     return keluargaMapper.selectKec(id_kecamatan);
	}
	
	@Override
	public KotaModel selectKot(String id_kota)
	{
		 log.info ("select kota with kota {}", id_kota);
	     return keluargaMapper.selectKot(id_kota);
	}
	
	@Override
	public void addKeluarga(KeluargaModel keluarga)
	{
		log.info("insert keluarga");
		keluargaMapper.addKeluarga(keluarga);
	}
	
	@Override
	public int selectKeluargaLike(String nomor_kk)
	{
		log.info("Select Keluarga Like {}", nomor_kk);
		return keluargaMapper.selectKeluargaLike(nomor_kk);
	}
	
	@Override
	public int selectMaxIDKel()
	{
		return keluargaMapper.selectMaxIDKel();
	}
	
	@Override
	public List<KeluargaModel> selectAllKeluargas()
	{
		log.info("select all keluarga");
		return keluargaMapper.selectAllKeluargas();
	}
	
	@Override
	public void updateKeluarga(KeluargaModel keluarga)
	{
		log.info("keluarga dengan nkk" + keluarga.getNomor_kk() + "updated");
		keluargaMapper.updateKeluarga(keluarga);
	}
	
	@Override
	public String selectIDKeluarga(@Param("nomor_kk") String nomor_kk)
	{
		log.info("Keluarga dengan nkk {}", nomor_kk);
		return keluargaMapper.selectIDKeluarga(nomor_kk);
	}
	
	@Override
	public void updateNkkKeluarga(@Param("nomor_kk") String nomor_kk, @Param("id") String id) {
		log.info("update keluarga dengan id {}", id);
		keluargaMapper.updateNkkKeluarga(nomor_kk, id);
	}
	
	@Override
	public void updateTidakBerlaku(String id)
	{
		log.info("update keluarga dengan id {} tidak berlaku", id);
		keluargaMapper.updateTidakBerlaku(id);
	}
}
