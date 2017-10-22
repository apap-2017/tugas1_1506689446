package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.*;
import com.example.model.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SidikServiceDatabase implements SidikService {
	@Autowired
	private SidikMapper sidikMapper;
	
	@Override
    public PendudukModel selectPenduduk(String nik)
    {
        log.info ("select penduduk with nik {}", nik);
        return sidikMapper.selectPenduduk(nik);
    }
	@Override
    public KeluargaModel selectKlg(String id_keluarga)
    {
        log.info ("select keluarga with id_keluarga {}", id_keluarga);
        return sidikMapper.selectKlg(id_keluarga);
    }
	
	@Override
	public KelurahanModel selectKel(String id_kelurahan)
	{
		 log.info ("select kelurahan with id_kelurahan {}", id_kelurahan);
	     return sidikMapper.selectKel(id_kelurahan);
	}
	
	@Override
	public KecamatanModel selectKec(String id_kecamatan)
	{
		 log.info ("select kecamatan with id_kecamatan {}", id_kecamatan);
	     return sidikMapper.selectKec(id_kecamatan);
	}
	
	@Override
	public KotaModel selectKot(String id_kota)
	{
		 log.info ("select kota with kota {}", id_kota);
	     return sidikMapper.selectKot(id_kota);
	}
	
	@Override
	public List<KotaModel> selectAllKota()
	{
		 log.info ("select all kota");
	     return sidikMapper.selectAllKota();
	}
	
	@Override
	public List<String> selectNamaKota()
	{
		 log.info ("select all kota");
	     return sidikMapper.selectNamaKota();
	}
	
	@Override
	public List<KecamatanModel> selectAllKecByKota(String id)
	{
		 log.info ("select all kecamatan where id kota {}", id);
	     return sidikMapper.selectAllKecByKota(id);
	}
	
	@Override
	public List<KelurahanModel> selectAllKelByKec(String id)
	{
		 log.info ("select all kelurahan where id kecamatan {}", id);
	     return sidikMapper.selectAllKelByKec(id);
	}
	
	@Override
	public List<PendudukModel> selectPendudukInKelurahan(String id)
	{
		log.info ("select all penduduk where id kelurahan {}", id);
		return sidikMapper.selectPendudukInKelurahan(id);
	}
	
	@Override
	public PendudukModel selectPendudukTertua(String id)
	{
		return sidikMapper.selectPendudukTertua(id);
	}
	
	@Override
	public PendudukModel selectPendudukTermuda(String id)
	{
		return sidikMapper.selectPendudukTermuda(id);
	}
	
	@Override
	public List<KelurahanModel> selectAllKel()
	{
		return sidikMapper.selectAllKel();
	}
	
	@Override
	public List<KecamatanModel> selectAllKec()
	{
		return sidikMapper.selectAllKec();
	}
}
