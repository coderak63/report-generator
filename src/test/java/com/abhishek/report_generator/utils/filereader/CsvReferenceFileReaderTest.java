package com.abhishek.report_generator.utils.filereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.test.context.ActiveProfiles;

import com.abhishek.report_generator.model.CompositeReferenceKey;
import com.abhishek.report_generator.model.ReferenceFileRecord;

import java.util.List;
import java.util.Map;


@SpringBootTest
@ActiveProfiles("test")
public class CsvReferenceFileReaderTest {
	
	@Value("${REFERENCE_FILE_LOCATION}")
	private String REFERENCE_FILE_LOCATION;


    @Test
    public void testReadFile_Success() {
        CsvReferenceFileReader reader = new CsvReferenceFileReader();
        Map<CompositeReferenceKey, ReferenceFileRecord> data;
        try {
            data = reader.readFile(REFERENCE_FILE_LOCATION);
            // Assert the expected data
            Assertions.assertEquals(5, data.size());
            
            ReferenceFileRecord referenceFileRecord;
            referenceFileRecord = new ReferenceFileRecord(List.of("XYZ", "123.45", "LMN", "234.56", "345.67", "456.78"));
            Assertions.assertEquals(referenceFileRecord.toString(), data.get(new CompositeReferenceKey(referenceFileRecord)).toString());
            
            referenceFileRecord = new ReferenceFileRecord(List.of("ABC", "567.89", "DEF", "678.90", "789.01", "890.12"));
            Assertions.assertEquals(referenceFileRecord.toString(), data.get(new CompositeReferenceKey(referenceFileRecord)).toString());
            
            referenceFileRecord = new ReferenceFileRecord(List.of("LMN", "234.56", "XYZ", "345.67", "456.78", "567.89"));
            Assertions.assertEquals(referenceFileRecord.toString(), data.get(new CompositeReferenceKey(referenceFileRecord)).toString());
            
            referenceFileRecord = new ReferenceFileRecord(List.of("DEF", "678.90", "ABC", "789.01", "890.12", "901.23"));
            Assertions.assertEquals(referenceFileRecord.toString(), data.get(new CompositeReferenceKey(referenceFileRecord)).toString());
            
            referenceFileRecord = new ReferenceFileRecord(List.of("MNO", "345.67", "PQR", "456.78", "567.89", "678.90"));
            Assertions.assertEquals(referenceFileRecord.toString(), data.get(new CompositeReferenceKey(referenceFileRecord)).toString());
            
        } catch (Exception e) {
            // Handle the exception
            Assertions.fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testReadFile_FileNotFound() {
        CsvReferenceFileReader reader = new CsvReferenceFileReader();
        try {
            reader.readFile("path/to/nonexistent.csv");

        } catch (Exception e) {
            // Assert the expected exception message
            Assertions.assertEquals("File not found", e.getMessage());
        }
    }


}