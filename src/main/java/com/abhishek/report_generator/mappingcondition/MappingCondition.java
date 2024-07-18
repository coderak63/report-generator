package com.abhishek.report_generator.mappingcondition;

import com.abhishek.report_generator.model.InputFileRecord;
import com.abhishek.report_generator.model.ReferenceFileRecord;

public class MappingCondition {
	
	public static boolean condition(InputFileRecord inputFileRecord, ReferenceFileRecord referenceFileRecord) {
		boolean flag = false;
		flag = 		referenceFileRecord.getRefkey1().equals(inputFileRecord.getRefkey1())
				&& 	referenceFileRecord.getRefkey2().equals(inputFileRecord.getRefkey2());
		
		return flag;
	}
}
