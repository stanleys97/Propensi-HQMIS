package com.project.propensib8.controller;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.rest.DashboardExecutive;
import com.project.propensib8.service.KomplainService;
import com.project.propensib8.service.ReviewService;
import com.project.propensib8.service.SurveiService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    SurveiService surveiService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    KomplainService komplainService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getDashboardTopManager(@RequestParam ("tipeSurvei") String tipeSurvei,
                                                   @RequestParam ("bulanTahunStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date tanggalMulai,
                                                   @RequestParam ("bulanTahunEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date tanggalSelesai){
        DashboardExecutive dashboardExecutive = new DashboardExecutive();
        List<SurveiModel> listOfSurvei = surveiService.findByTanggal(tanggalMulai,tanggalSelesai);
        dashboardExecutive.setJmlhSurvei(listOfSurvei.size());
        dashboardExecutive.setPersentaseKepuasan(surveiService.countPersentaseKepuasan(tanggalMulai, tanggalSelesai, tipeSurvei));
        dashboardExecutive.setJmlhKomplain(surveiService.countKomplain(tanggalMulai, tanggalSelesai, tipeSurvei));
        dashboardExecutive.setRating(surveiService.countRating(tanggalMulai, tanggalSelesai, tipeSurvei));
        dashboardExecutive.setUnitTerbaik(reviewService.getMostCommonUnit(tanggalMulai, tanggalSelesai, tipeSurvei));
        dashboardExecutive.setPersentaseHigher(surveiService.cekKepuasan(tanggalMulai,tanggalSelesai,tipeSurvei));
        dashboardExecutive.setKomplainHigher(surveiService.cekKomplain(tanggalMulai,tanggalSelesai,tipeSurvei));
        dashboardExecutive.setJmlhKomplainLalu(surveiService.countKomplainLalu(tanggalMulai,tanggalSelesai,tipeSurvei));
        dashboardExecutive.setPersentaseKepuasanLalu(surveiService.countKepuasanLalu(tanggalMulai,tanggalSelesai,tipeSurvei));
        dashboardExecutive.setKomplain(komplainService.isiKomplain(tanggalMulai,tanggalSelesai,tipeSurvei));
        dashboardExecutive.setReview(reviewService.isiReview(tanggalMulai,tanggalSelesai,tipeSurvei));
        System.out.println(dashboardExecutive.getJmlhKomplain());
        return new ResponseEntity<>(dashboardExecutive, HttpStatus.OK);
    }
}
