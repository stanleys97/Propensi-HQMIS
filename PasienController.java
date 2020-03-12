package com.project.propensib8.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.net.URISyntaxException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.repository.PasienDB;
import com.project.propensib8.rest.KomplainDetailProfile;
import com.project.propensib8.rest.KomplainPasienDetail;
import com.project.propensib8.service.KomplainService;
import com.project.propensib8.service.PasienService;

import javax.print.URIException;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pasien")
public class PasienController {
	@Autowired
	private PasienService pasienService;

	@Autowired
	private KomplainDB komplainDb;

	@Autowired
	private PasienDB pasienDb;

	@Autowired
	private KomplainService komplainService;

	@GetMapping(value = "/{idMedrec}")
	public ResponseEntity<?> getPasien(@PathVariable("idMedrec") String idMedrec) {
		PasienModel result = pasienService.getPasienByIdMedrec(idMedrec);
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@GetMapping(value = "/komplain")
	public ResponseEntity<?> getKomplainPasien(){
		List<KomplainModel> allKomplain = komplainService.findAll();
		List<KomplainPasienDetail> res = new ArrayList<>();

		for(KomplainModel k : allKomplain) {
			KomplainPasienDetail details = new KomplainPasienDetail();
			String lastPasien = "";
			String lastTanggal = "";
			String tanggalNow = "";

			//Kalau list yang ingin di return msh kosong, langsung buaat object
			if(res.size() == 0) {
				details.setNamaPasien(k.getSurvei().getPasien().getNama());

				details.setRating(k.getSurvei().getRating());

				java.sql.Date sqlDate = k.getSurvei().getTanggal();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String resDate = formatter.format(sqlDate);

				details.setTanggalKomplain(resDate);
				List<KomplainModel> listKomplain = new ArrayList<>();
				listKomplain.add(k);
				details.setListKomplain(listKomplain);
				res.add(details);
			}

			//Kalau sudah terisi, check
			else {
				lastPasien = res.get(res.size()-1).getNamaPasien();

				java.sql.Date tanggalPresent = k.getSurvei().getTanggal();
				java.sql.Date tanggalKemarin = res.get(res.size()-1).getListKomplain().get(0).getSurvei().getTanggal();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				tanggalNow = formatter.format(tanggalPresent);
				lastTanggal = formatter.format(tanggalKemarin);


				lastTanggal = res.get(res.size()-1).getTanggalKomplain();
				if(lastPasien.equalsIgnoreCase(k.getSurvei().getPasien().getNama()) && lastTanggal.equalsIgnoreCase(tanggalNow)){
					//masukkan objek komplain miliki pasien ke KomplainPasienDetail yang sudah ada di array list
					res.get(res.size()-1).getListKomplain().add(k);
				}

				else {
					details.setNamaPasien(k.getSurvei().getPasien().getNama());

					details.setRating(k.getSurvei().getRating());

					java.sql.Date sqlDate = k.getSurvei().getTanggal();
					String resDate = formatter.format(sqlDate);

					details.setTanggalKomplain(resDate);
					List<KomplainModel> listKomplain = new ArrayList<>();
					listKomplain.add(k);
					details.setListKomplain(listKomplain);
					res.add(details);
				}
			}
		}

		return new ResponseEntity(res, HttpStatus.OK);
	}

	@GetMapping(value = "/komplain/{namaPasien}/{tanggalPengisian}")
	public ResponseEntity<?> getDetailKomplain(@PathVariable ("namaPasien") String namaPasien, @PathVariable ("tanggalPengisian") String tanggalPengisian){
		KomplainDetailProfile detail = new KomplainDetailProfile();
		PasienModel pasien = komplainService.getPasienByNamaTanggal(namaPasien, tanggalPengisian);
		List<KomplainModel> listOfKomplain = komplainService.getKomplainByNamaTanggal(namaPasien, tanggalPengisian);
		detail.setNomorHp(pasien.getNomorHp());
		detail.setNomorTelefon(pasien.getNomorTelepon());
		detail.setAlamat(pasien.getAlamat());
		detail.setListKomplain(listOfKomplain);
		detail.setNamaPasien(namaPasien);
		detail.setTanggalPengisian(tanggalPengisian);
		return new ResponseEntity(detail, HttpStatus.OK);
	}

	@GetMapping( value = "/komplain/{tahun}/{bulan}")
	public ResponseEntity<?> getSolvedKomplainByTahunBulan(@PathVariable ("tahun") String tahun, @PathVariable ("bulan") String bulan){
		List<KomplainModel> listOfAllKomplain = komplainService.findAllSolvedKomplain();
		List<KomplainModel> listOfSolvedByParam = new ArrayList<>();

		for(KomplainModel komplain : listOfAllKomplain){
			java.sql.Date sqlDate = komplain.getSurvei().getTanggal();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String resDate = formatter.format(sqlDate);

			String[] dateSplit = resDate.split("-");
			if(dateSplit[0].equals(tahun) && dateSplit[1].equals(bulan)){
				listOfSolvedByParam.add(komplain);
			}
		}
		return new ResponseEntity(listOfSolvedByParam, HttpStatus.OK);
	}
}
