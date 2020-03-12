package com.project.propensib8.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.propensib8.model.UnitModel;

@Repository
public interface UnitDB extends JpaRepository<UnitModel, Long>{
	UnitModel findByNama(String nama);
}