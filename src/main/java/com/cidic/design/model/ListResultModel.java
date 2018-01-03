package com.cidic.design.model;

import java.util.List;

public class ListResultModel {
	
	private boolean success;
	private Object aaData;
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private String sEcho;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Object getAaData() {
		return aaData;
	}
	public void setAaData(Object aaData) {
		this.aaData = aaData;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	
	
}
