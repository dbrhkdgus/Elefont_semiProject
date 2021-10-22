package com.kh.elefont.point.model.vo;

import java.io.Serializable;

public class PointCategory implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pointCode;
	private String pointName;
	
	
	private PointCategory() {
		super();
		// TODO Auto-generated constructor stub
	}


	private PointCategory(String pointCode, String pointName) {
		super();
		this.pointCode = pointCode;
		this.pointName = pointName;
	}


	public String getPointCode() {
		return pointCode;
	}


	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}


	public String getPointName() {
		return pointName;
	}


	public void setPointName(String pointName) {
		this.pointName = pointName;
	}


	@Override
	public String toString() {
		return "PointCategory [pointCode=" + pointCode + ", pointName=" + pointName + "]";
	}
	
	
}
