package com.project.propensib8.service;

import java.util.List;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;

public interface PasienService {
	
	PasienModel getPasienByIdMedrec(String medrec);
	PasienModel getPasienById(String id);
	List<PasienModel> getAllPasien();
	KomplainModel getKomplainById(long id);
}
