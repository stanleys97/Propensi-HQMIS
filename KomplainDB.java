package com.project.propensib8.repository;

import java.sql.Date;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.propensib8.model.KomplainModel;

@Repository
public interface KomplainDB extends JpaRepository<KomplainModel, Long>{
	KomplainModel findById(long id);
	List<KomplainModel> findAllByTanggalBetween(Date startDate, Date endDate);
}
