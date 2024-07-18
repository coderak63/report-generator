package com.abhishek.report_generator.utils.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.abhishek.report_generator.model.ReferenceFileRecord;


@Component
public class CsvReferenceFileReader implements ReferenceFileReader{

private String COMMA_DELIMITER = ",";
	
	@Value("${REFERENCE_FILE_LOCATION}")
	private String REFERENCE_FILE_LOCATION;
	

	@Override
	public List<ReferenceFileRecord> readFile(){

		List<ReferenceFileRecord> records = new ArrayList<>();
		String reference_file = REFERENCE_FILE_LOCATION;
		
		try (BufferedReader br = new BufferedReader(new FileReader(reference_file))) {
			String line;
			
			// consume first line, it will contain header row
			br.readLine();
						
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
			        records.add(new ReferenceFileRecord(Arrays.asList(values)));
			    }
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return records;
	}

}
