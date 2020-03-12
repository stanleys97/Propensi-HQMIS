package com.project.propensib8.rest;

import java.util.List;

import com.project.propensib8.model.KomplainModel;

public class KomplainDetailProfile {
	String namaPasien;
	String nomorTelefon;
	String nomorHp;
	String tanggalPengisian;
	String alamat;
	List<KomplainModel> listKomplain;
	
	public String getNamaPasien() {
		return namaPasien;
	}
	public void setNamaPasien(String namaPasien) {
		this.namaPasien = namaPasien;
	}
	public String getTanggalPengisian() {
		return tanggalPengisian;
	}
	public void setTanggalPengisian(String tanggalPengisian) {
		this.tanggalPengisian = tanggalPengisian;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public List<KomplainModel> getListKomplain() {
		return listKomplain;
	}
	public void setListKomplain(List<KomplainModel> listKomplain) {
		this.listKomplain = listKomplain;
	}

	public String getNomorTelefon() {
		return nomorTelefon;
	}

	public void setNomorTelefon(String nomorTelefon) {
		this.nomorTelefon = nomorTelefon;
	}

	public String getNomorHp() {
		return nomorHp;
	}

	public void setNomorHp(String nomorHp) {
		this.nomorHp = nomorHp;
	}
}