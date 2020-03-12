package com.project.propensib8.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.ParameterModel;
import com.project.propensib8.repository.ParameterDB;

@Service
@Transactional
public class ParameterServiceImpl implements ParameterService{
	@Autowired
	ParameterDB parameterDb;

	@Override
	public ParameterModel getParameterByName(String nama) {
		// TODO Auto-generated method stub
		return parameterDb.findByNama(nama);
	}
	
	
	//your code goes here ...
}
