package com.abhishek.report_generator.model;

import java.util.List;


public class ReferenceFileRecord {
	
	private String refkey1;
    private String refdata1;
    private String refkey2;
    private String refdata2;
    private String refdata3;
    private double refdata4;
    
    public ReferenceFileRecord(List<String> list) {
		refkey1 = list.get(0);
		refdata1 = list.get(1);
		refkey2 = list.get(2);
		refdata2 = list.get(3);
		refdata3 = list.get(4);
		refdata4 = Double.parseDouble(list.get(5));
	}

	public String getRefkey1() {
		return refkey1;
	}

	public void setRefkey1(String refkey1) {
		this.refkey1 = refkey1;
	}

	public String getRefdata1() {
		return refdata1;
	}

	public void setRefdata1(String refdata1) {
		this.refdata1 = refdata1;
	}

	public String getRefkey2() {
		return refkey2;
	}

	public void setRefkey2(String refkey2) {
		this.refkey2 = refkey2;
	}

	public String getRefdata2() {
		return refdata2;
	}

	public void setRefdata2(String refdata2) {
		this.refdata2 = refdata2;
	}

	public String getRefdata3() {
		return refdata3;
	}

	public void setRefdata3(String refdata3) {
		this.refdata3 = refdata3;
	}

	public double getRefdata4() {
		return refdata4;
	}

	public void setRefdata4(double refdata4) {
		this.refdata4 = refdata4;
	}
    
    @Override
	public String toString() {
		return refkey1 + "," + refdata1 + "," + refkey2 + "," + refdata2 + "," + refdata3 + "," + refdata4;
	}
}
