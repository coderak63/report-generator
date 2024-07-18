package com.abhishek.report_generator.utils.filereader;

import java.util.List;
import com.abhishek.report_generator.model.InputFileRecord;

public interface InputFileReader {
	public List<InputFileRecord> readFile();
}
