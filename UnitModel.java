package com.project.propensib8.model;

import java.io.Serializable;
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
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "unit")
public class UnitModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 60)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@OneToMany(mappedBy = "unit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<KomplainModel> listKomplain;
	
	@OneToMany(mappedBy = "unit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ReviewModel> listReview;
	
	@OneToMany(mappedBy = "unit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UnitParameterModel> listUnitParameter;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public List<KomplainModel> getListKomplain() {
		return listKomplain;
	}

	public void setListKomplain(List<KomplainModel> listKomplain) {
		this.listKomplain = listKomplain;
	}


	public List<UnitParameterModel> getListUnitParameter() {
		return listUnitParameter;
	}

	public void setListUnitParameter(List<UnitParameterModel> listUnitParameter) {
		this.listUnitParameter = listUnitParameter;
	}

	public List<ReviewModel> getListReview() {
		return listReview;
	}

	public void setListReview(List<ReviewModel> listReview) {
		this.listReview = listReview;
	}
}

