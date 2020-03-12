package com.project.propensib8.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.propensib8.model.PasienModel;

@Repository
public interface PasienDB extends JpaRepository<PasienModel, Long>{
	PasienModel findByIdMedrec(String idMedrec);
	List<PasienModel> findAll();
}
