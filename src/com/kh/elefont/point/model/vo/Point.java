package com.kh.elefont.point.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Point implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String pointNo;
	private String pointCode;
	private String pointVal;
	private Date pointDate;
	private String pointTotal;
	
	private PointCategory pointCategory;

	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Point(String pointNo, String pointCode, String pointVal, Date pointDate, String pointTotal,
			PointCategory pointCategory) {
		super();
		this.pointNo = pointNo;
		this.pointCode = pointCode;
		this.pointVal = pointVal;
		this.pointDate = pointDate;
		this.pointTotal = pointTotal;
		this.pointCategory = pointCategory;
	}

	public String getPointNo() {
		return pointNo;
	}

	public void setPointNo(String pointNo) {
		this.pointNo = pointNo;
	}

	public String getPointCode() {
		return pointCode;
	}

	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}

	public String getPointVal() {
		return pointVal;
	}

	public void setPointVal(String pointVal) {
		this.pointVal = pointVal;
	}

	public Date getPointDate() {
		return pointDate;
	}

	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
	}

	public String getPointTotal() {
		return pointTotal;
	}

	public void setPointTotal(String pointTotal) {
		this.pointTotal = pointTotal;
	}

	public PointCategory getPointCategory() {
		return pointCategory;
	}

	public void setPointCategory(PointCategory pointCategory) {
		this.pointCategory = pointCategory;
	}

	@Override
	public String toString() {
		return "Point [pointNo=" + pointNo + ", pointCode=" + pointCode + ", pointVal=" + pointVal + ", pointDate="
				+ pointDate + ", pointTotal=" + pointTotal + ", pointCategory=" + pointCategory + "]";
	}
	
	
	
}
