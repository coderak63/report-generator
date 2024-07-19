package com.abhishek.report_generator.utils.filegenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.abhishek.report_generator.model.OutputFileRecord;

@Component
public class FileGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
	
	@Value("${OUTPUT_FILE_LOCATION}")
	private String OUTPUT_FILE_LOCATION;
	
	
	public void generateOutputFile(List<OutputFileRecord> outputFileRecords) {
		
		String outputFile = OUTPUT_FILE_LOCATION;
		logger.info("Starting to generate output file at location: {}", outputFile);
		
		try{
			FileWriter fileWriter = new FileWriter(outputFile, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			for(OutputFileRecord outputFileRecord: outputFileRecords) {
				
				writer.write(outputFileRecord.toString());
				writer.newLine();
			}
			
			writer.close();
			
			logger.info("Successfully added {} records to output file.",outputFileRecords.size());
			logger.info("Output file generated successfully at location: {}", outputFile);
			
		}catch(IOException e){
			logger.error("Error writing to output file: {}", e.getMessage(), e);
		}
	}
}
