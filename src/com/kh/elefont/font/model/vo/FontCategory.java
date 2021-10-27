package com.kh.elefont.font.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class FontCategory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoryCode;
	private String fontNo;
	private Date categoryReleaseYear;
	
	public FontCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FontCategory(String categoryCode, String fontNo, Date categoryReleaseYear) {
		super();
		this.categoryCode = categoryCode;
		this.fontNo = fontNo;
		this.categoryReleaseYear = categoryReleaseYear;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getFontNo() {
		return fontNo;
	}

	public void setFontNo(String fontNo) {
		this.fontNo = fontNo;
	}

	public Date getCategoryReleaseYear() {
		return categoryReleaseYear;
	}

	public void setCategoryReleaseYear(Date categoryReleaseYear) {
		this.categoryReleaseYear = categoryReleaseYear;
	}

	@Override
	public String toString() {
		return "FontCategory [categoryCode=" + categoryCode + ", fontNo=" + fontNo + ", categoryReleaseYear="
				+ categoryReleaseYear + "]";
	}
	
	
	
}
