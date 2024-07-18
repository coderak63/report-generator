package com.abhishek.report_generator.model;

import java.util.List;


public class InputFileRecord {
	
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private double field5;
    private String refkey1;
    private String refkey2;
    
    public InputFileRecord(List<String> list) {
    	field1 = list.get(0);
    	field2 = list.get(1);
    	field3 = list.get(2);
    	field4 = list.get(3);
    	field5 = Double.parseDouble(list.get(4));
    	refkey1 = list.get(5);
    	refkey2 = list.get(6);
    }

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public double getField5() {
		return field5;
	}

	public void setField5(double field5) {
		this.field5 = field5;
	}

	public String getRefkey1() {
		return refkey1;
	}

	public void setRefkey1(String refkey1) {
		this.refkey1 = refkey1;
	}

	public String getRefkey2() {
		return refkey2;
	}

	public void setRefkey2(String refkey2) {
		this.refkey2 = refkey2;
	}
    
    
}