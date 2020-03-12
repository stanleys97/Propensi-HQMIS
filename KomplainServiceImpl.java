package com.project.propensib8.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.project.propensib8.rest.DashboardExecutive;
import com.project.propensib8.rest.KomplainRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.repository.KomplainDB;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Transactional
public class KomplainServiceImpl implements KomplainService{

	@Autowired
	KomplainDB komplainDb;

	@Autowired
	PasienService pasienService;

	@Override
	public PasienModel getPasienByNamaTanggal(String namaPasien, String tanggalPengisian) {
		PasienModel picked = new PasienModel();
		for (KomplainModel komplain: komplainDb.findAll()){
			if(komplain.getSurvei().getPasien().getNama().equals(namaPasien)){
				if(komplain.getSurvei().getTanggal().equals(tanggalPengisian)){
					picked = komplain.getSurvei().getPasien();
				}
			}
		}
		return picked;
	}

	@Override
	public KomplainModel getKomplainByNamaPasien(String namaPasien) {
		KomplainModel picked = null;
		for(KomplainModel komplain: komplainDb.findAll()) {
			if (komplain.getSurvei().getPasien().getNama().equalsIgnoreCase(namaPasien)) {
				picked = komplain;
			}
		}
		return picked;
	}

	@Override
	public List<KomplainModel> getKomplainByNamaTanggal(String namaPasien, String tanggalPengisian) {
		String tanggalPicked = "";
		List<KomplainModel> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain: komplainDb.findAll()){

			java.sql.Date sqlDate = komplain.getSurvei().getTanggal();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			tanggalPicked = formatter.format(sqlDate);

			if(komplain.getSurvei().getPasien().getNama().equalsIgnoreCase(namaPasien) && tanggalPicked.equalsIgnoreCase(tanggalPengisian)){
				listOfKomplain.add(komplain);
			}
		}
		return listOfKomplain;
	}

	@Override
	public KomplainModel getKomplainById(long id) {
		return komplainDb.findById(id);
	}

	@Override
	public List<KomplainModel> findAll() {
		List<KomplainModel> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain : komplainDb.findAll()){
			if(komplain.getSurvei().getRating() <= 3 && komplain.isSolvedMarketing() == false){
				listOfKomplain.add(komplain);
			}
		}
		return listOfKomplain;
	}

	@Override
	public List<KomplainModel> findAllSolvedKomplain() {
		List<KomplainModel> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain : komplainDb.findAll()){
			if(komplain.isSolvedMarketing() == true){
				listOfKomplain.add(komplain);
			}
		}
		return listOfKomplain;
	}

	@Override
	public List<KomplainModel> getKomplainByNama(String nama) {
		List<KomplainModel> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain : komplainDb.findAll()){
			if(komplain.getUnit().getNama().equalsIgnoreCase(nama)){
				listOfKomplain.add(komplain);
			}
		}
		return listOfKomplain;
	}

	public int countKomplainOverviewUnit(String namaUnit, Date startDate, Date endDate, String tipeSurvei) {
		int count = 0;
		List<KomplainModel> komplains = komplainDb.findAllByTanggalBetween(startDate, endDate);
		if(!tipeSurvei.equalsIgnoreCase("total")) {
			for (KomplainModel komplain : komplains) {
				String nama = komplain.getUnit().getNama();
				String survei = komplain.getSurvei().getJenisSurvei();
				if (nama.equalsIgnoreCase(nama) && survei.equalsIgnoreCase("tipeSurvei")) {
					count++;
				}
			}
		}
		else{
			for(KomplainModel komplain : komplains){
				String nama = komplain.getUnit().getNama();
				if(nama.equalsIgnoreCase(namaUnit)){
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public int countRatingByNama(String nama) {
		int count = 0;
		int denominator = 0;
		for(int num = 0; num < komplainDb.findAll().size(); num++){
			if(komplainDb.findAll().get(num).getUnit().getNama().equalsIgnoreCase(nama)) {
				count += komplainDb.findAll().get(num).getSurvei().getRating();
				denominator += 1;
			}
		}
		count /= denominator;
		return count;
	}

	@Override
	public int countSolvedComplaints(String namaUnit, Date startDate, Date endDate, String tipeSurvei) {
		int count = 0;
		List<KomplainModel> komplains = komplainDb.findAllByTanggalBetween(startDate, endDate);
		if(!tipeSurvei.equalsIgnoreCase("total")) {
			for (KomplainModel komplain : komplains) {
				String nama = komplain.getUnit().getNama();
				String survei = komplain.getSurvei().getJenisSurvei();
				if (nama.equalsIgnoreCase(namaUnit) && survei.equalsIgnoreCase(tipeSurvei) && komplain.isSolvedMarketing()) {
					count++;
				}
			}
		}
		else{
			for(KomplainModel komplain : komplains){
				String nama = komplain.getUnit().getNama();
				if(nama.equalsIgnoreCase(namaUnit) && komplain.isSolvedMarketing()){
					count++;
				}
			}
		}
		return count;
	}


	@Override
	public List<KomplainModel> getKomplainByNamaUnit(String nama) {
		List<KomplainModel> list = new ArrayList<>();
		for(KomplainModel komplain : komplainDb.findAll()){
			if(komplain.getUnit().getNama().equalsIgnoreCase(nama)){
				list.add(komplain);
			}
		}
		return list;
	}

	@Override
	public List<String> getNamaPasienKomplainByNama(String nama) {
		List<String> list = new ArrayList<>();
		List<KomplainModel> listOfKomplain = komplainDb.findAll();
		for(KomplainModel komplain : listOfKomplain) {
			if(komplain.getUnit().getNama().equalsIgnoreCase(nama) && komplain.isSolvedMarketing() == true){
				list.add(komplain.getSurvei().getPasien().getNama());
			}
		}
		return list;
	}

	@Override
	public List<String> getDeskripsiKomplainByNama(String nama) {
		List<String> list = new ArrayList<>();
		List<KomplainModel> listOfKomplain = komplainDb.findAll();
		for(KomplainModel komplain : listOfKomplain) {
			if(komplain.getUnit().getNama().equalsIgnoreCase(nama) && komplain.isSolvedMarketing() == true){
				list.add(komplain.getDeskripsi());
			}
		}
		return list;
	}

	@Override
	public List<KomplainRest> createKomplainRest(String namaUnit, Date startDate, Date endDate, String tipeSurvei) {
		List<KomplainRest> list = new ArrayList<>();
		List<KomplainModel> listOfKomplain = komplainDb.findAllByTanggalBetween(startDate, endDate);
		if(!tipeSurvei.equalsIgnoreCase("total")) {
			for (KomplainModel komplain : listOfKomplain) {
				String nama = komplain.getUnit().getNama();
				String namaPasien = komplain.getSurvei().getPasien().getNama();
				String survei = komplain.getSurvei().getJenisSurvei();
				if (komplain.isSolvedMarketing() == true && nama.equalsIgnoreCase(namaUnit) && survei.equalsIgnoreCase(tipeSurvei)) {
					KomplainRest komplainRest = new KomplainRest();
					komplainRest.setNama(namaPasien);
					komplainRest.setDeskripsi(komplain.getDeskripsi());
					komplainRest.setRating(komplain.getSurvei().getRating());

					java.sql.Date sqlDate = komplain.getSurvei().getTanggal();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String resDate = formatter.format(sqlDate);
					komplainRest.setTanggalIsi(resDate);

					list.add(komplainRest);
				}
			}
		}
		else{
			for (KomplainModel komplain : listOfKomplain) {
				String nama = komplain.getUnit().getNama();
				String namaPasien = komplain.getSurvei().getPasien().getNama();
				if (komplain.isSolvedMarketing() == true && nama.equalsIgnoreCase(namaUnit)) {
					KomplainRest komplainRest = new KomplainRest();
					komplainRest.setNama(namaPasien);
					komplainRest.setDeskripsi(komplain.getDeskripsi());
					komplainRest.setRating(komplain.getSurvei().getRating());

					java.sql.Date sqlDate = komplain.getSurvei().getTanggal();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String resDate = formatter.format(sqlDate);
					komplainRest.setTanggalIsi(resDate);

					list.add(komplainRest);
				}
			}
		}
		return list;
	}

	@Override
	public List<String> isiKomplain(Date startDate, Date endDate, String tipeSurvei) {
		List<KomplainModel> list = this.findAllByTanggal(startDate,endDate);
		List<String> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain : list){
			if(komplain.getSurvei().getJenisSurvei().equalsIgnoreCase(tipeSurvei)){
				listOfKomplain.add(komplain.getDeskripsi());
			}
			else{
				listOfKomplain.add(komplain.getDeskripsi());
			}
			if(listOfKomplain.size() == 5){
				break;
			}
		}
		return listOfKomplain;
	}

	@Override
	public List<KomplainModel> findAllByTanggal(Date startDate, Date endDate) {
		if(startDate == null || endDate == null){
			return komplainDb.findAll();
		}
		return komplainDb.findAllByTanggalBetween(startDate, endDate);
	}

	public KomplainModel createKomplain(KomplainModel komplainModel) {
		return komplainDb.save(komplainModel);
	}
}
