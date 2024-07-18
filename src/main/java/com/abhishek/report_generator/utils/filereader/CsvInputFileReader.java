package com.abhishek.report_generator.utils.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.PrintJobAttribute;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.abhishek.report_generator.model.InputFileRecord;

@Component
public class CsvInputFileReader implements InputFileReader{

	private String COMMA_DELIMITER = ",";
	
	@Value("${INPUT_FILE_LOCATION}")
	private String INPUT_FILE_LOCATION;
	

	@Override
	public List<InputFileRecord> readFile(){

		List<InputFileRecord> records = new ArrayList<>();
		String input_file = INPUT_FILE_LOCATION;
		
		try (BufferedReader br = new BufferedReader(new FileReader(input_file))) {
			String line;
			
			// consume first line, it will contain header row
			br.readLine();
			
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
			        records.add(new InputFileRecord(Arrays.asList(values)));
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
