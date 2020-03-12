package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.propensib8.model.ReviewModel;
import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.model.UnitModel;
import com.project.propensib8.repository.ReviewDB;
import com.project.propensib8.rest.BaseResponse;
import com.project.propensib8.service.SurveiService;
import com.project.propensib8.service.UnitService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewDB reviewDb;
	@Autowired
	private SurveiService surveiService;
	@Autowired
	private UnitService unitService;
	
	@PostMapping(value="/add")
	public ResponseEntity<?> createReview(@Valid @RequestBody Map<String, String> res) throws URISyntaxException, ParseException{
		
		ReviewModel result = new ReviewModel();
		SurveiModel surveiModel = surveiService.getSurveiById(res.get("idSurvei"));
		UnitModel unitModel = unitService.getUnitByName(res.get("namaUnit"));
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date parsed = format.parse(res.get("tanggal"));
		java.sql.Date dateSql = new java.sql.Date(parsed.getTime());
		
		result.setTanggal(dateSql);
		
		result.setDeskripsi(res.get("deskripsi"));
		result.setUnit(unitModel);
		result.setSurvei(surveiModel);
		result = reviewDb.save(result);
		BaseResponse<ReviewModel> response = new BaseResponse<>();
		
		if(result.getId() != 0) {
			response.setStatus(200);
			response.setMessage("success");
			response.setResult(result);
		}
		
//		return ResponseEntity.created(new URI("/survei/add/" + result.getId())).body(result);
		return ResponseEntity.created(new URI("/survei")).body(response);
	}
}