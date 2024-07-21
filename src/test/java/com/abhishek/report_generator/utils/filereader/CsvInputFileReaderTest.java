package com.abhishek.report_generator.utils.filereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.abhishek.report_generator.model.InputFileRecord;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class CsvInputFileReaderTest {

	@Value("${INPUT_FILE_LOCATION}")
	private String INPUT_FILE_LOCATION;


    @Test
    public void testReadFile_Success() {
        InputFileReader reader = new CsvInputFileReader();
        List<InputFileRecord> data;
        try {
            data = reader.readFile(INPUT_FILE_LOCATION);
            // Assert the expected data
            Assertions.assertEquals(5, data.size());
            Assertions.assertEquals(new InputFileRecord(List.of("12.34", "23.45", "34.56", "45.67", "56.78", "XYZ", "LMN")).toString(), data.get(0).toString());
            Assertions.assertEquals(new InputFileRecord(List.of("67.89", "78.90", "89.01", "90.12", "12.34", "ABC", "DEF")).toString(), data.get(1).toString());
            Assertions.assertEquals(new InputFileRecord(List.of("34.56", "45.67", "56.78", "67.89", "78.90", "LMN", "XYZ")).toString(), data.get(2).toString());
            Assertions.assertEquals(new InputFileRecord(List.of("89.01", "90.12", "12.34", "23.45", "34.56", "DEF", "ABC")).toString(), data.get(3).toString());
            Assertions.assertEquals(new InputFileRecord(List.of("56.78", "67.89", "78.90", "89.01", "90.12", "MNO", "PQR")).toString(), data.get(4).toString());
        } catch (Exception e) {
            // Handle the exception
            Assertions.fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testReadFile_FileNotFound() {
        CsvInputFileReader reader = new CsvInputFileReader();
        try {
            reader.readFile("path/to/nonexistent.csv");
            
        } catch (Exception e) {
            // Assert the expected exception message
            Assertions.assertEquals("File not found", e.getMessage());
        }
    }


}