package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.propensib8.model.UnitModel;
import com.project.propensib8.repository.UnitDB;
import com.project.propensib8.service.UnitService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/unit")
public class UnitController {

	@Autowired
	private UnitService unitService;
	
	@Autowired
	private UnitDB unitDb;
	
}
