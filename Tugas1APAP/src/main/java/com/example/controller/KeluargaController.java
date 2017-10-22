package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.PendudukModel;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.service.KeluargaService;
import com.example.service.PendudukService;
import com.example.service.SidikService;

@Controller
public class KeluargaController {
	@Autowired
	PendudukService pendudukDAO;

	@Autowired
	KeluargaService keluargaDAO;

	@Autowired
	SidikService kelurahanDAO;

	@Autowired
	SidikService kecamatanDAO;

	@Autowired
	SidikService kotaDAO;

	@RequestMapping("/keluarga")
	public String viewPenduduk(Model model, @RequestParam(value = "nomor_kk", required = false) String nomor_kk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nomor_kk);
		if (keluarga != null) {
			KelurahanModel kelurahan = kelurahanDAO.selectKel(keluarga.getId_kelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKec(kelurahan.getId_kecamatan());
			KotaModel kota = kotaDAO.selectKot(kecamatan.getId_kota());
			List<PendudukModel> anggota = keluargaDAO.selectAnggota(keluarga.getId());
			model.addAttribute("keluarga", keluarga);
			model.addAttribute("kelurahan", kelurahan);
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("kota", kota);
			model.addAttribute("anggota", anggota);
			return "view-keluarga";
		} else {
			model.addAttribute("nomor_kk", nomor_kk);
			return "not-found-keluarga";
		}
	}

	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.GET)
	public String addKeluarga(Model model) {
		List<KelurahanModel> kels = kelurahanDAO.selectAllKel();
		model.addAttribute("kels", kels);
		List<KecamatanModel> kecs = kecamatanDAO.selectAllKec();
		model.addAttribute("kecs", kecs);
		List<KotaModel> kots = kotaDAO.selectAllKota();
		model.addAttribute("kots", kots);
		return "form-tambah-keluarga";
	}

	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
	public String addKeluargaSubmit(@RequestParam(value = "nomor_kk", required = false) String nomor_kk,
			@RequestParam(value = "alamat", required = false) String alamat,
			@RequestParam(value = "RT", required = false) String RT,
			@RequestParam(value = "RW", required = false) String RW,
			@RequestParam(value = "id_kelurahan", required = false) String id_kelurahan,
			@RequestParam(value = "is_tidak_berlaku", required = false) String is_tidak_berlaku, Model model) {
		KelurahanModel kelurahan = kelurahanDAO.selectKel(id_kelurahan);
		KecamatanModel kecamatan = kecamatanDAO.selectKec(kelurahan.getId_kecamatan());
		String kode_kec = kecamatan.getKode_kecamatan();
		kode_kec = kode_kec.substring(0, 6);
		nomor_kk = kode_kec;
		Date tanggal = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyMMdd");
		String tgl = date.format(tanggal);
		nomor_kk += tgl;

		int keluargasama = keluargaDAO.selectKeluargaLike(nomor_kk + "%");
		keluargasama += 1;
		String counts = keluargasama + "";

		int i = 4;
		while (i > counts.length()) {
			counts = "0" + counts;
		}

		nomor_kk += counts;

		int idNow = keluargaDAO.selectMaxIDKel();
		idNow += 1;

		String id = "" + idNow;

		KeluargaModel keluarga = new KeluargaModel(id, nomor_kk, alamat, RT, RW, id_kelurahan, "0");
		keluargaDAO.addKeluarga(keluarga);
		model.addAttribute("keluarga", keluarga);
		return "sukses-tambah-keluarga";
	}

	@RequestMapping(value = "/keluarga/ubah/{nomor_kk}", method = RequestMethod.GET)
	public String update(Model model, @PathVariable(value = "nomor_kk") String nomor_kk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nomor_kk);
		List<KelurahanModel> kels = kelurahanDAO.selectAllKel();
		model.addAttribute("kels", kels);
		List<KecamatanModel> kecs = kecamatanDAO.selectAllKec();
		model.addAttribute("kecs", kecs);
		List<KotaModel> kots = kotaDAO.selectAllKota();
		model.addAttribute("kots", kots);
		if (keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			return "form-update-keluarga";
		} else {
			model.addAttribute("nomor_kk", nomor_kk);
			return "not-found-keluarga";
		}
	}

	@RequestMapping(value = "/keluarga/ubah/{nomor_kk}", method = RequestMethod.POST)
	public String updateSubmit(Model model, KeluargaModel keluarga, @PathVariable(value = "nomor_kk") String nomor_kk,
			@RequestParam(value = "id_kelurahan_old", required = false) String id_kelurahan_old,
			@RequestParam(value = "id", required = false) String id) {
		KelurahanModel kelurahan = kelurahanDAO.selectKel(keluarga.getId_kelurahan());
		KelurahanModel kelurahanLama = kelurahanDAO.selectKel(id_kelurahan_old);
		KecamatanModel kecamatan = kecamatanDAO.selectKec(kelurahan.getId_kecamatan());
		KecamatanModel kecamatanLama = kecamatanDAO.selectKec(kelurahanLama.getId_kecamatan());
		String kode_kec = kecamatan.getKode_kecamatan();
		String kode_kec_lama = kecamatanLama.getKode_kecamatan();
		if (!kode_kec.equalsIgnoreCase(kode_kec_lama)) {
			List<PendudukModel> anggota = keluargaDAO.selectAnggota(id);
			String nomor_kkNew = "";
			kode_kec = kode_kec.substring(0, 6);
			nomor_kkNew = kode_kec;
			Date tanggal = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyMMdd");
			String tgl = date.format(tanggal);
			nomor_kkNew += tgl;

			int keluargasama = keluargaDAO.selectKeluargaLike(nomor_kkNew + "%");
			keluargasama += 1;
			String counts = keluargasama + "";
			int i = 4;
			while (i > counts.length()) {
				counts = "0" + counts;
			}

			nomor_kkNew += counts;
			model.addAttribute("nomor_kk", keluarga.getNomor_kk());
			keluargaDAO.updateKeluarga(keluarga);
			keluargaDAO.updateNkkKeluarga(nomor_kkNew, id);
			System.out.println("kalau disini?");
			int j = 0;
			while (j < anggota.size()) {
				System.out.println("masuk gaa");
				String nikNew = "";
				String tgl_lahir = anggota.get(j).getTanggal_lahir();
				String[] tgls = tgl_lahir.split("-");
				nikNew = kode_kec;
				if (anggota.get(j).getJenis_kelamin().equals("1")) {
					tgls[2] = (Integer.parseInt(tgls[2]) + 40) + "";
				}
				nikNew += tgls[2] + tgls[1] + tgls[0].substring(2);
				int penduduksama = pendudukDAO.selectPendudukLike(nikNew + "%");
				penduduksama += 1;
				String countsPenduduk = penduduksama + "";

				int x = 4;
				while (x > countsPenduduk.length()) {
					countsPenduduk = "0" + countsPenduduk;
				}
				nikNew += countsPenduduk;
				String idPenduduk = pendudukDAO.selectIDpenduduk(anggota.get(j).getNik());
				model.addAttribute("nik", anggota.get(j).getNik());
				pendudukDAO.updatePenduduk(anggota.get(j));
				pendudukDAO.updateNikPenduduk(nikNew, idPenduduk);
				j++;
			}

			return "sukses-update-keluarga";
		} else {
			model.addAttribute("nomor_kk", keluarga.getNomor_kk());
			keluargaDAO.updateKeluarga(keluarga);
			return "sukses-update-keluarga";
		}

	}

}
