package com.abhishek.report_generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportGenServiceScheduler {
	
	@Autowired
	ReportGenService service;
	
	@Scheduled(cron = "${report.schedule.cron}")
	public void generateReportScheduler() {
		service.generateReport();
	}

}
