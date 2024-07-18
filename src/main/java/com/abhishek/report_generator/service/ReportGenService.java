package com.abhishek.report_generator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	InputFileReader inputFileReader;
	
	@Autowired
	ReferenceFileReader referenceFileReader;
	
	@Autowired
	FileGenerator outputFileGenerator;
	
	@Scheduled(cron = "${report.schedule.cron}")
	public void generateReport() {
		
		List<InputFileRecord> inputFile = inputFileReader.readFile();
		List<ReferenceFileRecord> referenceFile = referenceFileReader.readFile();
		
		List<OutputFileRecord> outputFile = new ArrayList<OutputFileRecord>();
		
		for (InputFileRecord inputrecord : inputFile) {
            ReferenceFileRecord ref = referenceFile.stream()
                .filter(r -> MappingCondition.condition(inputrecord, r))
                .findFirst()
                .orElse(null);

            if (ref != null) {
            	
            	OutputFileRecord outputFileRecord = TransformRule.getOutputFileRecord(inputrecord, ref);
            	outputFile.add(outputFileRecord);

            }
        }

		outputFileGenerator.generateOutputFile(outputFile);
	}
}
