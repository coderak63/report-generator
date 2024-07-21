package com.abhishek.report_generator.controller;

import com.abhishek.report_generator.service.ReportGenService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ReportGenControllerTest {

    @Mock
    private ReportGenService service;

    @InjectMocks
    private ReportGenController controller;

    public ReportGenControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateReport_Success() throws Exception {
        doNothing().when(service).generateReport();

        ResponseEntity<String> response = controller.generate();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Report successfully generated.", response.getBody());
    }

    @Test
    public void testGenerateReport_Failure() throws Exception {
        doThrow(new RuntimeException("Error")).when(service).generateReport();

        ResponseEntity<String> response = controller.generate();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error in report generation.", response.getBody());
    }
}

