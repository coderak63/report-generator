package com.abhishek.report_generator.service;

import static org.mockito.Mockito.verify;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.abhishek.report_generator.model.OutputFileRecord;
import com.abhishek.report_generator.utils.filegenerator.FileGenerator;
import com.abhishek.report_generator.utils.filereader.InputFileReader;
import com.abhishek.report_generator.utils.filereader.ReferenceFileReader;

@SpringBootTest
@ActiveProfiles("test")
public class ReportGenServiceTest {

    @Value("${INPUT_FILE_LOCATION}")
	private String INPUT_FILE_LOCATION;

    @Value("${REFERENCE_FILE_LOCATION}")
	private String REFERENCE_FILE_LOCATION;

    @Autowired
    private ReportGenService reportGenService;

    @MockBean
    private InputFileReader inputFileReader;

    @MockBean
    private ReferenceFileReader referenceFileReader;

    @MockBean
    private FileGenerator outputFileGenerator;

    @BeforeEach
    public void setup() {
        // Mock any necessary behavior or data setup here
    }

    @Test
    public void testGenerateReport() {
        // Mock any necessary behavior or data setup here

        // Call the method to be tested
        try {
			reportGenService.generateReport();
			// Verify that the expected methods were called
	        verify(inputFileReader).readFile(INPUT_FILE_LOCATION);
	        verify(referenceFileReader).readFile(REFERENCE_FILE_LOCATION);

	        List<OutputFileRecord> outputFileRecords = new ArrayList<>();
	        verify(outputFileGenerator).generateOutputFile(outputFileRecords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }
}