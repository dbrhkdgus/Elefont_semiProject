package com.kh.elefont.rep.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Rep implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int repNo;
	private String repWriter;
	private String repContent;
	private Date repRegDate;
	private String FontNo;
	private String ComnNo;
	private int repLevel;
	private int repRef;
	private String memberNo;
	
	public Rep() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rep(int repNo, String repWriter, String repContent, Date repRegDate, String fontNo, String comnNo,
			int repLevel, int repRef, String memberNo) {
		super();
		this.repNo = repNo;
		this.repWriter = repWriter;
		this.repContent = repContent;
		this.repRegDate = repRegDate;
		FontNo = fontNo;
		ComnNo = comnNo;
		this.repLevel = repLevel;
		this.repRef = repRef;
		this.memberNo = memberNo;
	}

	public int getRepNo() {
		return repNo;
	}

	public void setRepNo(int repNo) {
		this.repNo = repNo;
	}

	public String getRepWriter() {
		return repWriter;
	}

	public void setRepWriter(String repWriter) {
		this.repWriter = repWriter;
	}

	public String getRepContent() {
		return repContent;
	}

	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}

	public Date getRepRegDate() {
		return repRegDate;
	}

	public void setRepRegDate(Date repRegDate) {
		this.repRegDate = repRegDate;
	}

	public String getFontNo() {
		return FontNo;
	}

	public void setFontNo(String fontNo) {
		FontNo = fontNo;
	}

	public String getComnNo() {
		return ComnNo;
	}

	public void setComnNo(String comnNo) {
		ComnNo = comnNo;
	}

	public int getRepLevel() {
		return repLevel;
	}

	public void setRepLevel(int repLevel) {
		this.repLevel = repLevel;
	}

	public int getRepRef() {
		return repRef;
	}

	public void setRepRef(int repRef) {
		this.repRef = repRef;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "Rep [repNo=" + repNo + ", repWriter=" + repWriter + ", repContent=" + repContent + ", repRegDate="
				+ repRegDate + ", FontNo=" + FontNo + ", ComnNo=" + ComnNo + ", repLevel=" + repLevel + ", repRef="
				+ repRef + ", memberNo=" + memberNo + "]";
	}


	
	

	
}
