package com.abhishek.report_generator.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.abhishek.report_generator.model.CompositeReferenceKey;
import com.abhishek.report_generator.model.InputFileRecord;
import com.abhishek.report_generator.model.OutputFileRecord;
import com.abhishek.report_generator.model.ReferenceFileRecord;
import com.abhishek.report_generator.transformrule.TransformRule;
import com.abhishek.report_generator.utils.filegenerator.OutputFileGenerator;
import com.abhishek.report_generator.utils.filereader.InputFileReader;
import com.abhishek.report_generator.utils.filereader.ReferenceFileReader;


@SpringBootTest
@ActiveProfiles("test")
public class ReportGenServiceTest {

    @Mock
    private InputFileReader inputFileReader;

    @Mock
    private ReferenceFileReader referenceFileReader;

    @Mock
    private OutputFileGenerator outputFileGenerator;

    @InjectMocks
    private ReportGenService reportGenService;
    
    private MockedStatic<TransformRule> mockedStatic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockedStatic = Mockito.mockStatic(TransformRule.class);
    }
    
    @AfterEach
    void tearDown() {
        mockedStatic.close();
    }

    @Test
    void generateReport_success() throws Exception {
        
        String[] input_data = {"12.34","23.45","34.56","45.67","56.78","XYZ","LMN"};
        InputFileRecord inputFileRecord = new InputFileRecord(Arrays.asList(input_data));
        String[] reference_data = {"XYZ","123.45","LMN","234.56","345.67","456.78"};
        ReferenceFileRecord referenceFileRecord = new ReferenceFileRecord(Arrays.asList(reference_data));
        String[] output_data = {"35.79","123.45","580.23","15786.3168","456.78"};
        OutputFileRecord outputFileRecord = new OutputFileRecord(Arrays.asList(output_data));

        Map<CompositeReferenceKey, ReferenceFileRecord> referenceMap = new HashMap<>();
        referenceMap.put(new CompositeReferenceKey(inputFileRecord), referenceFileRecord);

        when(referenceFileReader.readFile(Mockito.any())).thenReturn(referenceMap);
        when(inputFileReader.hasNext()).thenReturn(true, true, false); // Simulate 2 records
        when(inputFileReader.next()).thenReturn(inputFileRecord, inputFileRecord); // Simulate 2 records
        mockedStatic.when(() -> TransformRule.getOutputFileRecord(inputFileRecord, referenceFileRecord)).thenReturn(outputFileRecord);

        // Act
        reportGenService.generateReport();

        // Assert
        verify(referenceFileReader).readFile(Mockito.any());
        verify(inputFileReader, times(2)).next();
        verify(outputFileGenerator, times(2)).writeLine(outputFileRecord.toString());
        verify(outputFileGenerator).close();
    }

    

}
