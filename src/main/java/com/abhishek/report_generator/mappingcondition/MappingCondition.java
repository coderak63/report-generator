package com.abhishek.report_generator.mappingcondition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abhishek.report_generator.model.InputFileRecord;
import com.abhishek.report_generator.model.ReferenceFileRecord;

public class MappingCondition {
	
	private static final Logger logger = LoggerFactory.getLogger(MappingCondition.class);
	
	public static boolean condition(InputFileRecord inputFileRecord, ReferenceFileRecord referenceFileRecord) {
		logger.debug("Checking mapping condition for InputFileRecord: {} and ReferenceFileRecord: {}", inputFileRecord, referenceFileRecord);
		
		boolean flag = false;
		flag = 		referenceFileRecord.getRefkey1().equals(inputFileRecord.getRefkey1())
				&& 	referenceFileRecord.getRefkey2().equals(inputFileRecord.getRefkey2());
		
		logger.debug("Mapping condition result: {}", flag);
		return flag;
	}
}
