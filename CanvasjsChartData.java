package com.project.propensib8.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanvasjsChartData {
    Map<Object,Object> dokter = new HashMap<Object, Object>();
    Map<Object,Object> perawat = new HashMap<Object, Object>();
    Map<Object,Object> radiologi = new HashMap<Object, Object>();
    Map<Object,Object> laboratorium = new HashMap<Object, Object>();
    Map<Object,Object> kamar = new HashMap<Object, Object>();
    Map<Object,Object> makanan = new HashMap<Object, Object>();
    Map<Object,Object> kebersihan = new HashMap<Object, Object>();
    Map<Object,Object> fisioterapi = new HashMap<Object, Object>();
    Map<Object,Object> kasir = new HashMap<Object, Object>();
    Map<Object,Object> pendaftaran = new HashMap<Object, Object>();
    Map<Object,Object> igd = new HashMap<Object, Object>();
    Map<Object,Object> farmasi = new HashMap<Object, Object>();
    Map<Object,Object> administrasi = new HashMap<Object, Object>();

    static List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
    List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();

    public Map<Object, Object> getDokter() {
        return dokter;
    }

    public void setDokter(Map<Object, Object> dokter) {
        this.dokter = dokter;
    }

    public Map<Object, Object> getPerawat() {
        return perawat;
    }

    public void setPerawat(Map<Object, Object> perawat) {
        this.perawat = perawat;
    }

    public Map<Object, Object> getRadiologi() {
        return radiologi;
    }

    public void setRadiologi(Map<Object, Object> radiologi) {
        this.radiologi = radiologi;
    }

    public Map<Object, Object> getLaboratorium() {
        return laboratorium;
    }

    public void setLaboratorium(Map<Object, Object> laboratorium) {
        this.laboratorium = laboratorium;
    }

    public Map<Object, Object> getKamar() {
        return kamar;
    }

    public void setKamar(Map<Object, Object> kamar) {
        this.kamar = kamar;
    }

    public Map<Object, Object> getMakanan() {
        return makanan;
    }

    public void setMakanan(Map<Object, Object> makanan) {
        this.makanan = makanan;
    }

    public Map<Object, Object> getKebersihan() {
        return kebersihan;
    }

    public void setKebersihan(Map<Object, Object> kebersihan) {
        this.kebersihan = kebersihan;
    }

    public Map<Object, Object> getFisioterapi() {
        return fisioterapi;
    }

    public void setFisioterapi(Map<Object, Object> fisioterapi) {
        this.fisioterapi = fisioterapi;
    }

    public Map<Object, Object> getKasir() {
        return kasir;
    }

    public void setKasir(Map<Object, Object> kasir) {
        this.kasir = kasir;
    }

    public Map<Object, Object> getPendaftaran() {
        return pendaftaran;
    }

    public void setPendaftaran(Map<Object, Object> pendaftaran) {
        this.pendaftaran = pendaftaran;
    }

    public Map<Object, Object> getIgd() {
        return igd;
    }

    public void setIgd(Map<Object, Object> igd) {
        this.igd = igd;
    }

    public Map<Object, Object> getFarmasi() {
        return farmasi;
    }

    public void setFarmasi(Map<Object, Object> farmasi) {
        this.farmasi = farmasi;
    }

    public Map<Object, Object> getAdministrasi() {
        return administrasi;
    }

    public void setAdministrasi(Map<Object, Object> administrasi) {
        this.administrasi = administrasi;
    }

    public List<List<Map<Object, Object>>> getList() {
        return list;
    }

    public void setList(List<List<Map<Object, Object>>> list) {
        this.list = list;
    }

    public List<Map<Object, Object>> getDataPoints1() {
        return dataPoints1;
    }

    public void setDataPoints1(List<Map<Object, Object>> dataPoints1) {
        this.dataPoints1 = dataPoints1;
    }

    public static List<List<Map<Object, Object>>> getCanvasjsDataList() {
        return list;
    }
}
