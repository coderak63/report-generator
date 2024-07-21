package com.abhishek.report_generator.utils.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.abhishek.report_generator.model.CompositeReferenceKey;
import com.abhishek.report_generator.model.InputFileRecord;
import com.abhishek.report_generator.model.ReferenceFileRecord;

@Component
public class CsvInputFileReader implements InputFileReader, Iterator<InputFileRecord>{
	
	private static final Logger logger = LoggerFactory.getLogger(CsvInputFileReader.class);

	private String COMMA_DELIMITER = ",";
	

	private BufferedReader reader;
    private String currentLine;
    
    
    @Override
    public void initializeInputFile(String filePath) throws IOException,FileNotFoundException {
        logger.info("Initializing input file from path: {}", filePath);
        try {
            this.reader = new BufferedReader(new FileReader(filePath));
            // Consume the header line
            String headerLine = reader.readLine();
            if (headerLine == null) {
                logger.warn("Input File is empty. No data to process.");
            }
            this.currentLine = reader.readLine(); // Read the first line of data
        } catch (FileNotFoundException e) {
            logger.error("Input file not found: {}", filePath, e);
            throw e;
        } catch (IOException e) {
            logger.error("Error initializing input file: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public boolean hasNext() {
        return currentLine != null;
    }

    @Override
    public InputFileRecord next() {
        if (currentLine == null) {
        	logger.error("No more lines to read.");
            throw new NoSuchElementException("No more lines to read.");
        }
        String line = currentLine;
        
        try {
            currentLine = reader.readLine(); // Read the next line
        } catch (IOException e) {
        	logger.error("Error reading the next line from file: {}", e.getMessage(), e);
            throw new RuntimeException("Error reading the next line", e);
        }
        
        String[] values = line.split(COMMA_DELIMITER);
        return new InputFileRecord(Arrays.asList(values));
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (reader != null) {
                reader.close();
            }
        } finally {
            super.finalize();
        }
    }

	
	
	@Override
	public List<InputFileRecord> readFile(String input_file) throws Exception{

		List<InputFileRecord> records = new ArrayList<>();
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
			throw new FileNotFoundException("File not found");
		}catch(IOException e) {
			logger.error("Error reading input file: {}", e.getMessage(), e);
			throw e;
		}catch(Exception e) {
			logger.error("Unexpected error during input file reading: {}", e.getMessage(), e);
			throw e;
		}
		
		return records;
	}
	
	
	

}
