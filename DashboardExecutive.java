package com.project.propensib8.rest;

import java.util.List;

public class DashboardExecutive {
    int jmlhSurvei;
    int persentaseKepuasan;
    int jmlhKomplain;
    int rating;
    int persentaseKepuasanLalu;
    int jmlhKomplainLalu;
    String unitTerbaik;
    boolean persentaseHigher;
    boolean komplainHigher;
    List<String> review;
    List<String> komplain;

    public List<String> getReview() { return review; }

    public void setReview(List<String> review) { this.review = review; }

    public List<String> getKomplain() { return komplain; }

    public void setKomplain(List<String> komplain) { this.komplain = komplain; }

    public int getPersentaseKepuasanLalu() { return persentaseKepuasanLalu; }

    public void setPersentaseKepuasanLalu(int persentaseKepuasanLalu) { this.persentaseKepuasanLalu = persentaseKepuasanLalu; }

    public int getJmlhKomplainLalu() { return jmlhKomplainLalu; }

    public void setJmlhKomplainLalu(int jmlhKomplainLalu) { this.jmlhKomplainLalu = jmlhKomplainLalu; }

    public int getJmlhSurvei() {
        return jmlhSurvei;
    }

    public void setJmlhSurvei(int jmlhSurvei) {
        this.jmlhSurvei = jmlhSurvei;
    }

    public int getPersentaseKepuasan() {
        return persentaseKepuasan;
    }

    public void setPersentaseKepuasan(int persentaseKepuasan) {
        this.persentaseKepuasan = persentaseKepuasan;
    }

    public int getJmlhKomplain() {
        return jmlhKomplain;
    }

    public void setJmlhKomplain(int jmlhKomplain) {
        this.jmlhKomplain = jmlhKomplain;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUnitTerbaik() {
        return unitTerbaik;
    }

    public void setUnitTerbaik(String unitTerbaik) {
        this.unitTerbaik = unitTerbaik;
    }

    public boolean isPersentaseHigher() {
        return persentaseHigher;
    }

    public void setPersentaseHigher(boolean persentaseHigher) {
        this.persentaseHigher = persentaseHigher;
    }

    public boolean isKomplainHigher() {
        return komplainHigher;
    }

    public void setKomplainHigher(boolean komplainHigher) {
        this.komplainHigher = komplainHigher;
    }
}


