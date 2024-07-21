package com.abhishek.report_generator.utils.filereader;

import java.util.Map;

import com.abhishek.report_generator.model.CompositeReferenceKey;
import com.abhishek.report_generator.model.ReferenceFileRecord;

public interface ReferenceFileReader {	
	public Map<CompositeReferenceKey, ReferenceFileRecord> readFile(String path) throws Exception;
}
