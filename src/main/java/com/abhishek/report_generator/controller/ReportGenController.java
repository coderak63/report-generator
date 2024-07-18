package com.abhishek.report_generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.report_generator.service.ReportGenService;

@RestController
@RequestMapping("/api/reports")
public class ReportGenController {
	
	@Autowired
	ReportGenService service;
	
	@GetMapping("/test")
	public String test() {
		return "Report Generation Service is running.";
	}
	
	@GetMapping("/generate")
	public ResponseEntity<String> generate() {
		try {
			service.generateReport();
			return new ResponseEntity<>("Report successfully generated.", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error in report generation.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
