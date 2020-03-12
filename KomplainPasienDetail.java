package com.project.propensib8.rest;

import java.util.List;

import com.project.propensib8.model.KomplainModel;

public class KomplainPasienDetail {
	String namaPasien;
	String tanggalKomplain;
	int rating;
	List<KomplainModel> listKomplain;
	
	public String getNamaPasien() {
		return namaPasien;
	}
	public void setNamaPasien(String namaPasien) {
		this.namaPasien = namaPasien;
	}
	public String getTanggalKomplain() {
		return tanggalKomplain;
	}
	public void setTanggalKomplain(String tanggalKomplain) {
		this.tanggalKomplain = tanggalKomplain;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<KomplainModel> getListKomplain() {
		return listKomplain;
	}
	public void setListKomplain(List<KomplainModel> listKomplain) {
		this.listKomplain = listKomplain;
	}
	
	
}
