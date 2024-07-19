package com.abhishek.report_generator.transformrule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abhishek.report_generator.model.InputFileRecord;
import com.abhishek.report_generator.model.OutputFileRecord;
import com.abhishek.report_generator.model.ReferenceFileRecord;

public class TransformRule {
	
	private static final Logger logger = LoggerFactory.getLogger(TransformRule.class);
	
	public static OutputFileRecord getOutputFileRecord(InputFileRecord inputFileRecord, ReferenceFileRecord referenceFileRecord) {
		
		logger.debug("Transforming InputFileRecord: {} and ReferenceFileRecord: {} to OutputFileRecord", inputFileRecord, referenceFileRecord);

        try {
        	double outfield1 = Double.parseDouble(inputFileRecord.getField1()) + Double.parseDouble(inputFileRecord.getField2());
            double outfield2 = Double.parseDouble(referenceFileRecord.getRefdata1());
            double outfield3 = Double.parseDouble(referenceFileRecord.getRefdata2()) + Double.parseDouble(referenceFileRecord.getRefdata3());
            double outfield4 = Double.parseDouble(inputFileRecord.getField3()) * Math.max( inputFileRecord.getField5() , referenceFileRecord.getRefdata4() );
            double outfield5 = Math.max( inputFileRecord.getField5() , referenceFileRecord.getRefdata4() );
            
            List<String> outputRecord = new ArrayList<>();
            
    		outputRecord.add(Double.toString(outfield1));
    		outputRecord.add(Double.toString(outfield2));
    		outputRecord.add(Double.toString(outfield3));
    		outputRecord.add(Double.toString(outfield4));
    		outputRecord.add(Double.toString(outfield5));

            OutputFileRecord outputFileRecord = new OutputFileRecord(outputRecord);
            logger.debug("Generated OutputFileRecord: {}", outputFileRecord);

            return outputFileRecord;
        } catch (NumberFormatException e) {
            logger.error("Error parsing numeric values: {}", e.getMessage(), e);
            throw e; // throwing the exception to handle it upstream
        } catch (Exception e) {
            logger.error("Unexpected error during transformation: {}", e.getMessage(), e);
            throw e;
        }
		

	}
}
