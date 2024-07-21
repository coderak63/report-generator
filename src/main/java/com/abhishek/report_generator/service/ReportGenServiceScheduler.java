package com.abhishek.report_generator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportGenServiceScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportGenServiceScheduler.class);
	
	@Autowired
	ReportGenService service;
	
	@Scheduled(cron = "${report.schedule.cron}")
	public void generateReportScheduler() {
		
		try {
			logger.info("Scheduled report generation started.");
            service.generateReport();
            logger.info("Scheduled report generation completed successfully.");
        } catch (Exception e) {
            logger.error("Error during scheduled report generation: {}", e.getMessage(), e);
        }
	}

}
