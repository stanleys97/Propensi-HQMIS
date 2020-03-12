package com.project.propensib8.service;

import java.util.List;
import java.sql.Date;

import com.project.propensib8.model.SurveiModel;

public interface SurveiService {

	List<SurveiModel> getAllKomplain();
	SurveiModel getSurveiById(String id);
	List<SurveiModel> findByTanggal( Date startDate, Date endDate);
	int countPersentaseKepuasan(Date startDate, Date endDate, String tipeSurvei);
	int countKomplain(Date startDate, Date endDate, String tipeSurvei);
	List<SurveiModel> findByJenisSurvei(String jenisSurvei);
	int countRating(Date startDate, Date endDate, String tipeSurvei);
	boolean cekKepuasan(Date startDate, Date endDate, String tipeSurvei);
	boolean cekKomplain(Date startDate, Date endDate, String tipeSurvei);
	int countKomplainLalu(Date startDate, Date endDate, String tipeSurvei);
	int countKepuasanLalu(Date startDate, Date endDate, String tipeSurvei);
}
