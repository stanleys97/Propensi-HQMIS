package com.project.propensib8.rest;

import com.project.propensib8.rest.DetailPerforma;

import java.util.List;

public class PerformaKaryawan {
	String idUnit;
    String namaUnit;
    int review;
    int komplain;
    int komplainSolved;
    
    public int getKomplainSolved() {
		return komplainSolved;
	}

	public void setKomplainSolved(int komplainSolved) {
		this.komplainSolved = komplainSolved;
	}

	public String getIdUnit() {
		return idUnit;
	}

	public void setIdUnit(String idUnit) {
		this.idUnit = idUnit;
	}

	public String getNamaUnit() {
        return namaUnit;
    }

    public void setNamaUnit(String namaUnit) {
        this.namaUnit = namaUnit;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getKomplain() {
        return komplain;
    }

    public void setKomplain(int komplain) {
        this.komplain = komplain;
    }

}
