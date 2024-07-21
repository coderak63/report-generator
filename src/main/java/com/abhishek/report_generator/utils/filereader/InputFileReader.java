package com.abhishek.report_generator.utils.filereader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import com.abhishek.report_generator.model.InputFileRecord;

public interface InputFileReader {
	public List<InputFileRecord> readFile(String path) throws Exception;

	public void initializeInputFile(String path) throws IOException,FileNotFoundException;

	public boolean hasNext();

	public InputFileRecord next();
}
