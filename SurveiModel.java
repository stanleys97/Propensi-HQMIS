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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "survei")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SurveiModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "tanggal", nullable = false)
	private Date tanggal;

	@Size(max = 20)
	@NotNull
	@Column(name = "jenis_survei", nullable = false)
	private String jenisSurvei;

	@NotNull
	@Column(name = "rating", nullable = false)
	private int rating;

	@Size(max = 255)
	@Column(name = "feedback")
	private String feedback;

	@OneToMany(mappedBy = "survei", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<KomplainModel> listKomplain;
	
	@OneToMany(mappedBy = "survei", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ReviewModel> listReview;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pasien", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private PasienModel pasien;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getJenisSurvei() {
		return jenisSurvei;
	}

	public void setJenisSurvei(String jenisSurvei) {
		this.jenisSurvei = jenisSurvei;
	}

	public PasienModel getPasien() {
		return pasien;
	}

	public void setPasien(PasienModel pasien) {
		this.pasien = pasien;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public List<ReviewModel> getListReview() {
		return listReview;
	}

	public void setListReview(List<ReviewModel> listReview) {
		this.listReview = listReview;
	}
}
