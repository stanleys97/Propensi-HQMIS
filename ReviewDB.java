package com.project.propensib8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.propensib8.model.ReviewModel;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReviewDB extends JpaRepository<ReviewModel, Long>{
    String findById(long id);
	List<ReviewModel> findAllByTanggalBetween(Date startDate, Date endDate);
}
