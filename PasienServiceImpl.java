package com.project.propensib8.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.repository.PasienDB;

@Service
@Transactional
public class PasienServiceImpl implements PasienService{

	@Autowired
	PasienDB pasienDb;

	@Autowired
	SurveiService surveiService;
	
	@Autowired
	KomplainDB komplainDb;

	@Override
	public PasienModel getPasienByIdMedrec(String idMedrec) {
		return pasienDb.findByIdMedrec(idMedrec);
	}

	@Override
	public PasienModel getPasienById(String id) {
		PasienModel res = pasienDb.getOne(Long.parseLong(id));
		return res;
	}

	@Override
	public List<PasienModel> getAllPasien(){
		return pasienDb.findAll();
	}

	@Override
	public KomplainModel getKomplainById(long id) {
		return null;
	}
}
