package com.abhishek.report_generator.utils.filegenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.abhishek.report_generator.model.OutputFileRecord;

@Component
public class FileGenerator {
	
	@Value("${OUTPUT_FILE_LOCATION}")
	private String OUTPUT_FILE_LOCATION;
	
	
	public void generateOutputFile(List<OutputFileRecord> outputFileRecords) {
		
		String outputFile = OUTPUT_FILE_LOCATION;
		
		
		try{
			FileWriter fileWriter = new FileWriter(outputFile, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			for(OutputFileRecord outputFileRecord: outputFileRecords) {
				
				writer.write(outputFileRecord.toString());
				writer.newLine();
			}
			
			writer.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
