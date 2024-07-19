package com.abhishek.report_generator.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ReportGenController.class);
	
	@Autowired
	ReportGenService service;
	
	@GetMapping("/test")
	public String test() {
		logger.info("Received request to /test endpoint.");
		return "Report Generation Service is running.";
	}
	
	@GetMapping("/generate")
	public ResponseEntity<String> generate() {
		logger.info("Received request to /generate endpoint.");
		try {
			logger.info("Calling service to generate report.");
			service.generateReport();
			logger.info("Report generated successfully.");
			return new ResponseEntity<>("Report successfully generated.", HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Error in report generation: ", e);
			return new ResponseEntity<>("Error in report generation.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
