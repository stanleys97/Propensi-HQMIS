package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.repository.UnitDB;
import com.project.propensib8.rest.DetailPerforma;
import com.project.propensib8.rest.PerformaKaryawan;
import com.project.propensib8.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.propensib8.model.ParameterModel;
import com.project.propensib8.model.UnitModel;
import com.project.propensib8.model.UnitParameterModel;
import com.project.propensib8.repository.UnitParameterDB;
import com.project.propensib8.rest.BaseResponse;
import com.project.propensib8.rest.ReviewRest;
import com.project.propensib8.rest.KomplainRest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/unit-parameter")
public class UnitParameterController {	
	@Autowired
	private UnitParameterDB unitParameterDb;
	@Autowired
	private UnitService unitService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private UnitDB unitDb;
	@Autowired
	private KomplainDB komplainDb;
	@Autowired
	private KomplainService komplainService;
	@Autowired
	private ReviewService reviewService;


	@PostMapping(value="/add")
	public ResponseEntity<?> createUnitParameter(@Valid @RequestBody Map<String, String> res) throws URISyntaxException, ParseException{
		
		UnitParameterModel result = new UnitParameterModel();
		UnitModel unitModel = unitService.getUnitByName(res.get("unit"));
		ParameterModel parameterModel = parameterService.getParameterByName(res.get("parameter"));
		
		System.out.println(unitModel.getNama());
		System.out.println(parameterModel.getNama());
		
		result.setParameter(parameterModel);
		result.setUnit(unitModel);
		result = unitParameterDb.save(result);
		BaseResponse<UnitParameterModel> response = new BaseResponse<>();
		
		if(result.getId() != 0) {
			response.setStatus(200);
			response.setMessage("success");
			response.setResult(result);
		}
		
//		return ResponseEntity.created(new URI("/survei/add/" + result.getId())).body(result);
		return ResponseEntity.created(new URI("/survei")).body(response);
	}

	@GetMapping(value = "/performa/{namaUnit}/{tipeSurvei}")
	public ResponseEntity<?> getAllPerforma(@PathVariable ("namaUnit") String namaUnit,
											@PathVariable ("tipeSurvei") String tipeSurvei,
											@RequestParam ("bulanTahunStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String tanggalMulais,
											@RequestParam ("bulanTahunEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String tanggalSelesais) {
		PerformaKaryawan performa = new PerformaKaryawan();
		Date tanggalMulai = Date.valueOf(tanggalMulais);
		Date tanggalSelesai = Date.valueOf(tanggalSelesais);
		performa.setNamaUnit(namaUnit);
		performa.setKomplain(komplainService.countKomplainOverviewUnit(namaUnit, tanggalMulai, tanggalSelesai, tipeSurvei));
		performa.setKomplainSolved(komplainService.countSolvedComplaints(namaUnit, tanggalMulai, tanggalSelesai, tipeSurvei));
		performa.setIdUnit(Long.toString(unitService.getUnitByName(namaUnit).getId()));
		performa.setReview(reviewService.countReviewOverviewUnit(namaUnit, tanggalMulai, tanggalSelesai, tipeSurvei));
		return new  ResponseEntity(performa, HttpStatus.OK);
	}

	@GetMapping(value = "/detail-performa/{namaUnit}/{tipeSurvei}")
	public ResponseEntity<?> getDetailPerforma(@PathVariable ("namaUnit") String namaUnit,
											   @PathVariable ("tipeSurvei") String tipeSurvei,
											   @RequestParam ("bulanTahunStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String tanggalMulais,
											   @RequestParam ("bulanTahunEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String tanggalSelesais){
		DetailPerforma detailPerforma = new DetailPerforma();
		Date tanggalMulai = Date.valueOf(tanggalMulais);
		Date tanggalSelesai = Date.valueOf(tanggalSelesais);
		detailPerforma.setReviewRest(reviewService.createReviewRest(namaUnit, tanggalMulai, tanggalSelesai, tipeSurvei));
		detailPerforma.setKomplainRest(komplainService.createKomplainRest(namaUnit, tanggalMulai, tanggalSelesai, tipeSurvei));
		detailPerforma.setKomplain(komplainService.countKomplainOverviewUnit(namaUnit, tanggalMulai, tanggalSelesai, tipeSurvei));
		detailPerforma.setKomplainSolved(komplainService.countSolvedComplaints(namaUnit, tanggalMulai, tanggalSelesai, tipeSurvei));
		detailPerforma.setReview(reviewService.countReviewOverviewUnit(namaUnit, tanggalMulai, tanggalSelesai, tipeSurvei));
		return new ResponseEntity<>(detailPerforma, HttpStatus.OK);
	}

}
