package com.abhishek.report_generator.utils.filereader;

import java.util.List;
import com.abhishek.report_generator.model.ReferenceFileRecord;

public interface ReferenceFileReader {	
	public List<ReferenceFileRecord> readFile(String path) throws Exception;
}
