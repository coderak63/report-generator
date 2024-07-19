package com.abhishek.report_generator.utils.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.abhishek.report_generator.model.ReferenceFileRecord;


@Component
public class CsvReferenceFileReader implements ReferenceFileReader{
	
	private static final Logger logger = LoggerFactory.getLogger(CsvReferenceFileReader.class);
	
	private String COMMA_DELIMITER = ",";
	
	@Value("${REFERENCE_FILE_LOCATION}")
	private String REFERENCE_FILE_LOCATION;
	

	@Override
	public List<ReferenceFileRecord> readFile(){

		List<ReferenceFileRecord> records = new ArrayList<>();
		String reference_file = REFERENCE_FILE_LOCATION;
		
		logger.info("Starting to read reference file from location: {}", reference_file);
		
		try (BufferedReader br = new BufferedReader(new FileReader(reference_file))) {
			String line;
			
			// consume first line, it will contain header row
			br.readLine();
						
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
			        records.add(new ReferenceFileRecord(Arrays.asList(values)));
			    }
			
			logger.info("Successfully read {} records from reference file.", records.size());
			
		}catch(FileNotFoundException e) {
			logger.error("Reference file not found: {}", e.getMessage(), e);
		}catch(IOException e) {
			logger.error("Error reading reference file: {}", e.getMessage(), e);
		}catch(Exception e) {
			logger.error("Unexpected error during reference file reading: {}", e.getMessage(), e);
		}
		
		return records;
	}

}
