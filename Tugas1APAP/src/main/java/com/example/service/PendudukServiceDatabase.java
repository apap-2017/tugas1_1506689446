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

public class PendudukServiceDatabase implements PendudukService {
	@Autowired
    private PendudukMapper pendudukMapper;
	
	@Override
    public PendudukModel selectPenduduk(String nik)
    {
        log.info ("select penduduk with nik {}", nik);
        return pendudukMapper.selectPenduduk(nik);
    }
	@Override
    public KeluargaModel selectKlg(String id_keluarga)
    {
        log.info ("select keluarga with id_keluarga {}", id_keluarga);
        return pendudukMapper.selectKlg(id_keluarga);
    }
	
	@Override
	public KelurahanModel selectKel(String id_kelurahan)
	{
		 log.info ("select kelurahan with id_kelurahan {}", id_kelurahan);
	     return pendudukMapper.selectKel(id_kelurahan);
	}
	
	@Override
	public KecamatanModel selectKec(String id_kecamatan)
	{
		 log.info ("select kecamatan with id_kecamatan {}", id_kecamatan);
	     return pendudukMapper.selectKec(id_kecamatan);
	}
	
	@Override
	public KotaModel selectKot(String id_kota)
	{
		 log.info ("select kota with kota {}", id_kota);
	     return pendudukMapper.selectKot(id_kota);
	}
	
	@Override
	public int selectMaxID()
	{
		 log.info ("select max id");
	     return pendudukMapper.selectMaxID();
	}
	
	@Override
	public void addPenduduk(PendudukModel penduduk)
	{
		log.info("add penduduk");
		pendudukMapper.addPenduduk(penduduk);
	}
	
	@Override
	public int selectPendudukLike(String nik)
	{
		log.info("select penduduk like nik {}", nik);
		return pendudukMapper.selectPendudukLike(nik);
	}
	
	@Override
	public List<PendudukModel> selectAllPenduduks()
	{
		log.info("select all penduduk");
		return pendudukMapper.selectAllPenduduks();
	}
	
	@Override
	public void updatePenduduk(PendudukModel penduduk)
	{
		log.info("penduduk dengan nik" + penduduk.getNik() + "updated");
		pendudukMapper.updatePenduduk(penduduk);
	}
	
	@Override
	public String selectIDpenduduk(@Param("nik") String nik)
	{
		log.info("Penduduk dengan nik {}", nik);
		return pendudukMapper.selectIDpenduduk(nik);
	}
	
	@Override
	public void updateNikPenduduk(@Param("nik") String nik, @Param("id") String id) 
	{
		log.info("update penduduk dengan id {}", id);
		pendudukMapper.updateNikPenduduk(nik, id);
	}
	
	@Override
	public void updateSetWafat(@Param("nik") String nik)
	{
		log.info("update penduduk dengan nik {} wafat", nik);
		pendudukMapper.updateSetWafat(nik);
	}
	
	@Override
	public int selectCountIsWafat(String id)
	{
		log.info("count is wafat in keluarga id {}", id);
		return pendudukMapper.selectCountIsWafat(id);
		
	}
	
}
