package com.abhishek.report_generator.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.abhishek.report_generator.model.CompositeReferenceKey;
import com.abhishek.report_generator.model.InputFileRecord;
import com.abhishek.report_generator.model.OutputFileRecord;
import com.abhishek.report_generator.model.ReferenceFileRecord;
import com.abhishek.report_generator.transformrule.TransformRule;
import com.abhishek.report_generator.utils.filegenerator.OutputFileGenerator;
import com.abhishek.report_generator.utils.filereader.InputFileReader;
import com.abhishek.report_generator.utils.filereader.ReferenceFileReader;

@Service
public class ReportGenService {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportGenService.class);

    @Value("${INPUT_FILE_LOCATION}")
	private String INPUT_FILE_LOCATION;

    @Value("${REFERENCE_FILE_LOCATION}")
	private String REFERENCE_FILE_LOCATION;
    
    @Value("${OUTPUT_FILE_LOCATION}")
	private String OUTPUT_FILE_LOCATION;
	
	@Autowired
	InputFileReader inputFileReader;
	
	@Autowired
	ReferenceFileReader referenceFileReader;
	
	@Autowired
	OutputFileGenerator outputFileGenerator;
	
	
	@Scheduled(cron = "${report.schedule.cron}")
	public void generateReport() throws Exception{
		
		int inputFileLineCount = 0,outputFileLineCount = 0;
		logger.info("Report generation started.");
		
		try {
            
			Map<CompositeReferenceKey, ReferenceFileRecord> referenceMap = referenceFileReader.readFile(REFERENCE_FILE_LOCATION);
            logger.info("Read {} records from reference file.", referenceMap.size());
            
    		logger.info("Starting to read input file from location: {}", INPUT_FILE_LOCATION);
    		inputFileReader.initializeInputFile(INPUT_FILE_LOCATION);
    		
    		logger.info("Starting to write output file to location: {}", OUTPUT_FILE_LOCATION);
    		outputFileGenerator.initializeOutputFile(OUTPUT_FILE_LOCATION);
            
    		while (inputFileReader.hasNext()) {
                InputFileRecord inputFileRecord = inputFileReader.next();
                inputFileLineCount++;
                
                ReferenceFileRecord ref = referenceMap.get(new CompositeReferenceKey(inputFileRecord));

                if (ref != null) {
                    OutputFileRecord outputFileRecord = TransformRule.getOutputFileRecord(inputFileRecord, ref);
                    outputFileGenerator.writeLine(outputFileRecord.toString());
                    outputFileLineCount++;
                }
            }
            
            outputFileGenerator.close();
            
            logger.info("Read {} records from input file.", inputFileLineCount);

            logger.info("Successfully added {} records to output file.",outputFileLineCount);
			logger.info("Output file generated successfully at location: {}", OUTPUT_FILE_LOCATION);
            logger.info("Report generation completed successfully.");
        } catch (Exception e) {
            logger.error("Error during report generation: {}", e.getMessage(), e);
            throw e;
        }
		
		
	}
	
}
