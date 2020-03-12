package com.project.propensib8.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pasien")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PasienModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 10)
	@Column(name = "id_medrec", nullable = false, unique = true)
	private String idMedrec;
	
	@NotNull
	@Size(max = 20)
	@Column(name = "nik", nullable = false)
	private String nik;
	
	@NotNull
	@Size(max = 60)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 60)
	@Column(name = "alamat", nullable = false)
	private String alamat;
	
	@Column(name = "tanggal_lahir", nullable = false)
	private Date tanggalLahir;
	
	@NotNull
	@Size(max = 60)
	@Column(name = "tempat_lahir", nullable = false)
	private String tempatLahir;
	
	@NotNull
	@Size(max = 1)
    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelamin;
	
	@NotNull
	@Size(max = 20)
	@Column(name = "nomor_hp", nullable = false)
	private String nomorHp;
	
	@NotNull
	@Size(max = 20)
	@Column(name = "nomor_telepon", nullable = false)
	private String nomorTelepon;
	
	@OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SurveiModel> listSurvei;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdMedrec() {
		return idMedrec;
	}

	public void setIdMedrec(String idMedrec) {
		this.idMedrec = idMedrec;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public List<SurveiModel> getListSurvei() {
		return listSurvei;
	}

	public void setListSurvei(List<SurveiModel> listSurvei) {
		this.listSurvei = listSurvei;
	}

	public String getNomorHp() {
		return nomorHp;
	}

	public void setNomorHp(String nomorHp) {
		this.nomorHp = nomorHp;
	}

	public String getNomorTelepon() {
		return nomorTelepon;
	}

	public void setNomorTelepon(String nomorTelepon) {
		this.nomorTelepon = nomorTelepon;
	}
	
}
