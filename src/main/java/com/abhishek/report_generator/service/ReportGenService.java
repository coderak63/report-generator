package com.abhishek.report_generator.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.abhishek.report_generator.mappingcondition.MappingCondition;
import com.abhishek.report_generator.model.InputFileRecord;
import com.abhishek.report_generator.model.OutputFileRecord;
import com.abhishek.report_generator.model.ReferenceFileRecord;
import com.abhishek.report_generator.transformrule.TransformRule;
import com.abhishek.report_generator.utils.filegenerator.FileGenerator;
import com.abhishek.report_generator.utils.filereader.InputFileReader;
import com.abhishek.report_generator.utils.filereader.ReferenceFileReader;

@Service
public class ReportGenService {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportGenService.class);

    @Value("${INPUT_FILE_LOCATION}")
	private String INPUT_FILE_LOCATION;

    @Value("${REFERENCE_FILE_LOCATION}")
	private String REFERENCE_FILE_LOCATION;
	
	@Autowired
	InputFileReader inputFileReader;
	
	@Autowired
	ReferenceFileReader referenceFileReader;
	
	@Autowired
	FileGenerator outputFileGenerator;
	
	@Scheduled(cron = "${report.schedule.cron}")
	public void generateReport() throws Exception{
		logger.info("Report generation started.");
		
		try {
            List<InputFileRecord> inputFile = inputFileReader.readFile(INPUT_FILE_LOCATION);
            logger.debug("Read {} records from input file.", inputFile.size());
            
            List<ReferenceFileRecord> referenceFile = referenceFileReader.readFile(REFERENCE_FILE_LOCATION);
            logger.debug("Read {} records from reference file.", referenceFile.size());
            
            List<OutputFileRecord> outputFile = new ArrayList<>();
            
            for (InputFileRecord inputRecord : inputFile) {
                ReferenceFileRecord ref = referenceFile.stream()
                    .filter(r -> MappingCondition.condition(inputRecord, r))
                    .findFirst()
                    .orElse(null);

                if (ref != null) {
                    OutputFileRecord outputFileRecord = TransformRule.getOutputFileRecord(inputRecord, ref);
                    outputFile.add(outputFileRecord);
                }
            }
            
            logger.debug("Generated {} records for output file.", outputFile.size());
            outputFileGenerator.generateOutputFile(outputFile);
            logger.info("Report generation completed successfully.");
        } catch (Exception e) {
            logger.error("Error during report generation: {}", e.getMessage(), e);
            throw e;
        }
		
		
	}
}
