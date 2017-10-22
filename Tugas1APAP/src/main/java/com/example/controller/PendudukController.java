package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KotaModel;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.PendudukModel;
import com.example.service.KeluargaService;
import com.example.service.PendudukService;
import com.example.service.SidikService;

@Controller
public class PendudukController {
	@Autowired
    PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaSDAO;
	
	@Autowired
    SidikService keluargaDAO;
	
	@Autowired
    SidikService kelurahanDAO;
	
	@Autowired
    SidikService kecamatanDAO;
	
	@Autowired
    SidikService kotaDAO;
	
	@Autowired
    SidikService sidikDAO;
	

    @RequestMapping("/penduduk")
    public String viewPenduduk (Model model,
        @RequestParam(value = "nik", required = false) String nik)
	    {
    	  PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);  	  
    	  if (penduduk != null) {
    		  KeluargaModel keluarga = keluargaDAO.selectKlg(penduduk.getId_keluarga());
        	  KelurahanModel kelurahan = kelurahanDAO.selectKel(keluarga.getId_kelurahan());
        	  KecamatanModel kecamatan = kecamatanDAO.selectKec(kelurahan.getId_kecamatan());
        	  KotaModel kota = kotaDAO.selectKot(kecamatan.getId_kota());
              model.addAttribute ("penduduk", penduduk);
              model.addAttribute ("keluarga", keluarga);
              model.addAttribute ("kelurahan", kelurahan);
              model.addAttribute ("kecamatan", kecamatan);
              model.addAttribute ("kota", kota);
              return "view-penduduk";
          } else {
              model.addAttribute ("nik", nik);
              return "not-found-penduduk";
          }
	    }
    @RequestMapping(value = "/penduduk/tambah", method = RequestMethod.GET)
    public String addPenduduk()
	    {
          	return "form-tambah-penduduk";
	    }
    
    @RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
    public String addPendudukSubmit(@RequestParam(value = "nik", required = false) String nik,
    		   @RequestParam(value = "nama", required = false) String nama,
    		   @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir, 
    		   @RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
    		   @RequestParam(value = "golongan_darah", required = false) String golongan_darah,
    		   @RequestParam(value = "jenis_kelamin", required = false) String jenis_kelamin,
    		   @RequestParam(value = "agama", required = false) String agama,
    		   @RequestParam(value = "status_perkawinan", required = false) String status_perkawinan,
    		   @RequestParam(value = "pekerjaan", required = false) String pekerjaan,
    		   @RequestParam(value = "is_wni", required = false) String is_wni,
    		   @RequestParam(value = "is_wafat", required = false) String is_wafat,
    		   @RequestParam(value = "id_keluarga", required = false) String id_keluarga,
    		   @RequestParam(value = "status_dalam_keluarga", required = false) String status_dalam_keluarga,
    		   Model model)
	    {
    		KeluargaModel keluarga = keluargaDAO.selectKlg(id_keluarga);
   	  		KelurahanModel kelurahan = kelurahanDAO.selectKel(keluarga.getId_kelurahan());
   	  		KecamatanModel kecamatan = kecamatanDAO.selectKec(kelurahan.getId_kecamatan());
    	  	String kode_kec = kecamatan.getKode_kecamatan();
    	  	kode_kec = kode_kec.substring(0, 6);
    	  	String tgl_lahir = tanggal_lahir;
    	  	String[] tgls = tgl_lahir.split("-");
    	  	nik = kode_kec;
    	  	if(jenis_kelamin.equals("1")) {
    	  		tgls[2] = (Integer.parseInt(tgls[2])+ 40) + "";
    	  	}
    	  	nik += tgls[2] + tgls [1] + tgls[0].substring(2);
    	  	
    	  	int penduduksama = pendudukDAO.selectPendudukLike(nik+"%");
    	  	penduduksama += 1;
    	  	String counts = penduduksama + "";
    	  	
    	  	int i = 4;
    	  	while (i > counts.length()) {
    	  		counts = "0" + counts;
    	  	}
    	  	
    	  	nik += counts;

    	  	PendudukModel penduduk = new PendudukModel (nik, nama, tempat_lahir, tanggal_lahir, 
    			    jenis_kelamin, is_wni, id_keluarga,
    			    agama, pekerjaan, status_perkawinan,
    			    status_dalam_keluarga, golongan_darah, is_wafat);
    	  	pendudukDAO.addPenduduk(penduduk);
    	    model.addAttribute("penduduk",penduduk);
    	  	return "sukses-tambah-penduduk";
	    }
    
	    @RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.GET)
	    public String update (Model model, @PathVariable(value = "nik") String nik)
	    {
	    	PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
	    	if (penduduk != null) {
	    		model.addAttribute("penduduk", penduduk);
	            return "form-update-penduduk";
	        } else {
	            model.addAttribute ("nik", nik);
	            return "not-found-penduduk";
	        }
	    }
	    
	    @RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	    public String updateSubmit (
	            Model model, PendudukModel penduduk, @PathVariable(value = "nik") String nik, 
	               @RequestParam(value = "id_keluarga_old", required = false) String id_keluarga_old,
	    		   @RequestParam(value = "jenis_kelamin_old", required = false) String jenis_kelamin_old, 
	    		   @RequestParam(value = "tanggal_lahir_old", required = false) String tanggal_lahir_old)
	    {
	    	
	    	if(!penduduk.getId_keluarga().equalsIgnoreCase(id_keluarga_old) || !penduduk.getJenis_kelamin().equalsIgnoreCase(jenis_kelamin_old)  || !penduduk.getTanggal_lahir().equals(tanggal_lahir_old)) {
	    		String nikNew = "";
	    		KeluargaModel keluarga = keluargaDAO.selectKlg(penduduk.getId_keluarga());
	   	  		KelurahanModel kelurahan = kelurahanDAO.selectKel(keluarga.getId_kelurahan());
	   	  		KecamatanModel kecamatan = kecamatanDAO.selectKec(kelurahan.getId_kecamatan());
	    	  	String kode_kec = kecamatan.getKode_kecamatan();
	    	  	kode_kec = kode_kec.substring(0, 6);
	    	  	String tgl_lahir = penduduk.getTanggal_lahir();
	    	  	String[] tgls = tgl_lahir.split("-");
	    	  	nikNew = kode_kec;
	    	  	if(penduduk.getJenis_kelamin().equals("1")) {
	    	  		tgls[2] = (Integer.parseInt(tgls[2])+ 40) + "";
	    	  	}
	    	  	nikNew += tgls[2] + tgls [1] + tgls[0].substring(2);
	    	  	int penduduksama = pendudukDAO.selectPendudukLike(nikNew+"%");
	    	  	penduduksama += 1;
	    	  	String counts = penduduksama + "";
	    	  	
	    	  	int i = 4;
	    	  	while (i > counts.length()) {
	    	  		counts = "0" + counts;
	    	  	}
	    	  	nikNew += counts;
	    	  	String id = pendudukDAO.selectIDpenduduk(penduduk.getNik());
		    	model.addAttribute("nik", penduduk.getNik());
		    	pendudukDAO.updatePenduduk(penduduk);
		    	pendudukDAO.updateNikPenduduk(nikNew, id);
		        return "sukses-update-penduduk";
	    	} else {
	    		model.addAttribute("nik", penduduk.getNik());
	    		pendudukDAO.updatePenduduk(penduduk);
	    		return "sukses-update-penduduk";
	    	}
	    	
	    }
	    
	    @RequestMapping(value="/penduduk/mati", method = RequestMethod.POST)
	    public String updateIsWafat (@RequestParam(value = "nik", required = false) String nik, Model model)
	    {
	    	PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
	    	model.addAttribute ("penduduk", penduduk);
	    	return "form-mati";
	    }
	    
	    	    @RequestMapping(value = "/penduduk", method = RequestMethod.POST)
	    public String updateIsWafatSubmit (@RequestParam(value = "nik", required = false) String nik, Model model)
	    {
	    	PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
	    	pendudukDAO.updateSetWafat(nik);
	    	KeluargaModel keluarga = keluargaDAO.selectKlg(penduduk.getId_keluarga());
	    	boolean flag = false;
	    	List<PendudukModel> anggota = keluargaSDAO.selectAnggota(keluarga.getId());
	    	
	    	if(pendudukDAO.selectCountIsWafat(penduduk.getId_keluarga()) == anggota.size()) {
	    		keluargaSDAO.updateTidakBerlaku(keluarga.getId());
	    	}
	    	model.addAttribute("penduduk", penduduk);
	    	model.addAttribute("flag", true);
	    	return "sukses-non-aktif";
	    }
	    
	    @RequestMapping(value = "/penduduk/cari")
	    public String cari(@RequestParam(value = "kt", required = false) String kt, @RequestParam(value = "kc", required = false) String kc,
	    		@RequestParam(value = "kl", required = false) String kl, Model model) {
	    	boolean flag = false;
	    	if(kl != null){
				model.addAttribute("namakota", kotaDAO.selectKot(kt).getNama_kota());
				model.addAttribute("namakecamatan", kecamatanDAO.selectKec(kc).getNama_kecamatan());
				model.addAttribute("namakelurahan", kelurahanDAO.selectKel(kl).getNama_kelurahan());
				model.addAttribute("flag_kl", true);
				model.addAttribute("kl", kl);
				flag=true;
				List<PendudukModel> penduduks = sidikDAO.selectPendudukInKelurahan(kl);
				model.addAttribute("penduduks", penduduks);
				PendudukModel pendudukMuda = sidikDAO.selectPendudukTermuda(kl);
				PendudukModel pendudukTua = sidikDAO.selectPendudukTertua(kl);
				model.addAttribute("pendudukMuda", pendudukMuda);
				model.addAttribute("pendudukTua", pendudukTua);
				return "hasil-cari";

			}
	    	List<KotaModel> kotas = kotaDAO.selectAllKota();
	    	model.addAttribute("kotas", kotas);
	    	boolean flagkt = false;
	    	if(kt != null) {
	    		KotaModel kota = kotaDAO.selectKot(kt);
	    		List<KecamatanModel> kecs = kecamatanDAO.selectAllKecByKota(kt);
	    		String namaKota = kota.getNama_kota();
	    		model.addAttribute("id_kota", kt);
	    		model.addAttribute("nama_kota", namaKota);
	    		model.addAttribute("kecs", kecs);
	    		model.addAttribute("flagkt", true);
	    		model.addAttribute("flagpasskt", false);
	    		boolean flagkc = false;
	    		if(kc != null) {
	    			KecamatanModel kecamatan = kecamatanDAO.selectKec(kc);
		    		List<KelurahanModel> kels = kelurahanDAO.selectAllKelByKec(kc);
		    		String namaKecamatan = kecamatan.getNama_kecamatan();
		    		model.addAttribute("id_kecamatan", kc);
		    		model.addAttribute("nama_kecamatan", namaKecamatan);
		    		model.addAttribute("kels", kels);
		    		model.addAttribute("flagkc", true);
		    		model.addAttribute("flagpasskt", true);
		    		boolean flagkl = false;
	    		}
	    	}
	    	
	    	return "form-cari";
	    }
}
