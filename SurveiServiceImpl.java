package com.project.propensib8.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.repository.SurveiDB;

@Service
@Transactional
public class SurveiServiceImpl implements SurveiService{
	@Autowired
	SurveiDB surveiDb;

	@Override
	public List<SurveiModel> getAllKomplain() {
		List<SurveiModel> res = new ArrayList<>();
		List<SurveiModel> listSurvei = surveiDb.findAll();
		for(SurveiModel s : listSurvei){
			if(s.getRating() <= 3){
				res.add(s);
			}
		}

		return res;
	}

	@Override
	public SurveiModel getSurveiById(String id) {
		return surveiDb.findById(Long.parseLong(id));
	}

	@Override
	public List<SurveiModel> findByTanggal(Date startDate, Date endDate) {
		if(endDate == null || startDate == null){
			return this.surveiDb.findAll();
		}
		return this.surveiDb.findAllByTanggalBetween(startDate,endDate);
	}

	@Override
	public int countPersentaseKepuasan(Date startDate, Date endDate, String tipeSurvei) {
		List<SurveiModel> list = this.findByTanggal(startDate, endDate);
		List<SurveiModel> review = new ArrayList<>();
		int persentase;
		if(tipeSurvei != "Total") {
			for (SurveiModel survei : list) {
				if (survei.getJenisSurvei().equalsIgnoreCase(tipeSurvei) && survei.getRating() >= 4) {
					review.add(survei);
				}
			}
			int numerator = review.size();
			int denominator = this.findByJenisSurvei(tipeSurvei).size();
			persentase = numerator / denominator;
		}
		else {
			for(SurveiModel survei : list){
				if(survei.getRating() >= 4){
					review.add(survei);
				}
			}
			int numerator = review.size();
			int denominator = this.findByJenisSurvei(tipeSurvei).size();
			persentase = numerator / denominator;
		}
		return persentase;
	}

	@Override
	public int countKomplain(Date startDate, Date endDate, String tipeSurvei) {
		List<SurveiModel> listOfKomplain = this.findByTanggal(startDate,endDate);
		List<SurveiModel> komplain = new ArrayList<>();
		if(tipeSurvei != "Total") {
			for(SurveiModel survei : listOfKomplain) {
				if (survei.getRating() < 4 && survei.getJenisSurvei().equalsIgnoreCase(tipeSurvei)) {
					komplain.add(survei);
				}
			}
		}
		else {
			for(SurveiModel survei : listOfKomplain) {
				if(survei.getRating() < 4){
					komplain.add(survei);
				}
			}
		}
		return komplain.size();
	}

	@Override
	public List<SurveiModel> findByJenisSurvei(String jenisSurvei) {
		if(jenisSurvei == "Rawat Jalan"){
			return surveiDb.findAllByJenisSurvei("Rawat Jalan");
		}
		else if(jenisSurvei == "Rawat Inap"){
			return surveiDb.findAllByJenisSurvei("Rawat Inap");
		}
		else{
			return surveiDb.findAll();
		}
	}

	@Override
	public int countRating(Date startDate, Date endDate, String tipeSurvei) {
		List<SurveiModel> list = this.findByTanggal(startDate, endDate);
		List<SurveiModel> picked = new ArrayList<>();
		int rating = 0;
		int pembagi = 0;
		for (SurveiModel survei : list) {
			if (tipeSurvei == "Rawat Jalan") {
				if (survei.getJenisSurvei().equalsIgnoreCase(tipeSurvei)) {
					rating += survei.getRating();
					pembagi += 1;
				}
			} else if (tipeSurvei == "Rawat Inap") {
				if (survei.getJenisSurvei().equalsIgnoreCase(tipeSurvei)) {
					rating += survei.getRating();
					pembagi += 1;
				}
			} else {
				rating += survei.getRating();
				pembagi += 1;
			}
		}
		return rating/pembagi;
	}

	@Override
	public boolean cekKepuasan(Date startDate, Date endDate, String tipeSurvei){
		int kepuasanBulanIni = this.countPersentaseKepuasan(startDate, endDate, tipeSurvei);

		LocalDate tempStart = startDate.toLocalDate();
		LocalDate tempEnd = endDate.toLocalDate();
		LocalDate oneMonthBefore = tempStart.minusMonths(1);
		LocalDate oneMonthAfter = tempEnd.minusMonths(1);

		java.sql.Date dateFormatBefore = java.sql.Date.valueOf(oneMonthBefore);
		java.sql.Date dateFormatAfter = java.sql.Date.valueOf(oneMonthAfter);

		int kepuasanBulanLalu = this.countPersentaseKepuasan(dateFormatAfter,dateFormatBefore,tipeSurvei);

		if(kepuasanBulanIni > kepuasanBulanLalu){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean cekKomplain(Date startDate, Date endDate, String tipeSurvei) {
		int komplainNow = this.countKomplain(startDate, endDate, tipeSurvei);

		LocalDate tempStart = startDate.toLocalDate();
		LocalDate tempEnd = endDate.toLocalDate();
		LocalDate oneMonthBefore = tempStart.minusMonths(1);
		LocalDate oneMonthAfter = tempEnd.minusMonths(1);

		java.sql.Date dateFormatBefore = java.sql.Date.valueOf(oneMonthBefore);
		java.sql.Date dateFormatAfter = java.sql.Date.valueOf(oneMonthAfter);

		int komplainLalu = this.countKomplain(dateFormatBefore, dateFormatAfter, tipeSurvei);
		if(komplainNow > komplainLalu){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public int countKomplainLalu(Date startDate, Date endDate, String tipeSurvei) {
		LocalDate tempStart = startDate.toLocalDate();
		LocalDate tempEnd = endDate.toLocalDate();
		LocalDate oneMonthBefore = tempStart.minusMonths(1);
		LocalDate oneMonthAfter = tempEnd.minusMonths(1);

		java.sql.Date dateFormatBefore = java.sql.Date.valueOf(oneMonthBefore);
		java.sql.Date dateFormatAfter = java.sql.Date.valueOf(oneMonthAfter);

		int komplainLalu = this.countKomplain(dateFormatBefore, dateFormatAfter, tipeSurvei);
		return komplainLalu;
	}

	@Override
	public int countKepuasanLalu(Date startDate, Date endDate, String tipeSurvei) {
		LocalDate tempStart = startDate.toLocalDate();
		LocalDate tempEnd = endDate.toLocalDate();
		LocalDate oneMonthBefore = tempStart.minusMonths(1);
		LocalDate oneMonthAfter = tempEnd.minusMonths(1);

		java.sql.Date dateFormatBefore = java.sql.Date.valueOf(oneMonthBefore);
		java.sql.Date dateFormatAfter = java.sql.Date.valueOf(oneMonthAfter);

		int kepuasanBulanLalu = this.countPersentaseKepuasan(dateFormatAfter,dateFormatBefore,tipeSurvei);
		return kepuasanBulanLalu;
	}

}