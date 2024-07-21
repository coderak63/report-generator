package com.abhishek.report_generator.model;

import java.util.List;

public class OutputFileRecord {
	
	private String outfield1;
	private String outfield2;
	private String outfield3;
	private String outfield4;
	private String outfield5;
	
	
	
	public OutputFileRecord(List<String> list) {
		super();
		this.outfield1 = list.get(0);
		this.outfield2 = list.get(1);
		this.outfield3 = list.get(2);
		this.outfield4 = list.get(3);
		this.outfield5 = list.get(4);
	}
	
	public String getOutfield1() {
		return outfield1;
	}
	public void setOutfield1(String outfield1) {
		this.outfield1 = outfield1;
	}
	public String getOutfield2() {
		return outfield2;
	}
	public void setOutfield2(String outfield2) {
		this.outfield2 = outfield2;
	}
	public String getOutfield3() {
		return outfield3;
	}
	public void setOutfield3(String outfield3) {
		this.outfield3 = outfield3;
	}
	public String getOutfield4() {
		return outfield4;
	}
	public void setOutfield4(String outfield4) {
		this.outfield4 = outfield4;
	}
	public String getOutfield5() {
		return outfield5;
	}
	public void setOutfield5(String outfield5) {
		this.outfield5 = outfield5;
	}

	@Override
	public String toString() {
		return outfield1 + "," + outfield2 + "," + outfield3 + "," + outfield4 + "," + outfield5;
	}

	public static String getHeaderString() {
		return "outfield1,outfield2,outfield3,outfield4,outfield5";
	}
	
	
	
	
}
