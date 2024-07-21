package com.abhishek.report_generator.utils.filegenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.abhishek.report_generator.model.OutputFileRecord;


@Component
public class OutputFileGenerator implements AutoCloseable {
	
	private static final Logger logger = LoggerFactory.getLogger(OutputFileGenerator.class);
	private BufferedWriter writer;
    
    public void initializeOutputFile(String filePath) throws IOException {
        logger.info("Initializing output file at path: {}", filePath);
        try {
            this.writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(OutputFileRecord.getHeaderString());
            writer.newLine(); // Write a newline character after the header
            logger.info("Output file initialized and header written successfully.");
        } catch (IOException e) {
            logger.error("Error initializing output file: {}", e.getMessage(), e);
            throw e;
        }
    }

    
    public void writeLine(String line) throws IOException {
        if (writer == null) {
            logger.error("Attempted to write to file before initialization.");
            throw new IOException("FileWriter is not initialized.");
        }
        try {
            writer.write(line);
            writer.newLine(); // Write a newline character after the line
        } catch (IOException e) {
            logger.error("Error writing line to file: {}", e.getMessage(), e);
            throw e;
        }
    }

    
    @Override
    public void close() throws IOException {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                logger.error("Error closing BufferedWriter: {}", e.getMessage(), e);
                throw e;
            }
        }
    }
}
