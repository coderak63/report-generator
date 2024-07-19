package com.abhishek.report_generator.utils.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.PrintJobAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.abhishek.report_generator.model.InputFileRecord;

@Component
public class CsvInputFileReader implements InputFileReader{
	
	private static final Logger logger = LoggerFactory.getLogger(CsvInputFileReader.class);

	private String COMMA_DELIMITER = ",";
	
	@Value("${INPUT_FILE_LOCATION}")
	private String INPUT_FILE_LOCATION;
	

	@Override
	public List<InputFileRecord> readFile(){

		List<InputFileRecord> records = new ArrayList<>();
		String input_file = INPUT_FILE_LOCATION;
		logger.info("Starting to read input file from location: {}", input_file);
		
		try (BufferedReader br = new BufferedReader(new FileReader(input_file))) {
			String line;
			
			// consume first line, it will contain header row
			br.readLine();
			
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
			    records.add(new InputFileRecord(Arrays.asList(values)));
			}
			
			logger.info("Successfully read {} records from input file.", records.size());
		}catch(FileNotFoundException e) {
			logger.error("Input file not found: {}", e.getMessage(), e);
		}catch(IOException e) {
			logger.error("Error reading input file: {}", e.getMessage(), e);	
		}catch(Exception e) {
			logger.error("Unexpected error during input file reading: {}", e.getMessage(), e);	
		}
		
		return records;
	}

}
