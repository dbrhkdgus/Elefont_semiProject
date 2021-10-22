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
	private String categoryIsFree;
	private String categoryLang;
	private String categoryStyle;
	private Date categoryReleaseYear;
	private Font font;
	public FontCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FontCategory(String categoryCode, String fontNo, String categoryIsFree, String categoryLang,
			String categoryStyle, Date categoryReleaseYear, Font font) {
		super();
		this.categoryCode = categoryCode;
		this.fontNo = fontNo;
		this.categoryIsFree = categoryIsFree;
		this.categoryLang = categoryLang;
		this.categoryStyle = categoryStyle;
		this.categoryReleaseYear = categoryReleaseYear;
		this.font = font;
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
	public String getCategoryIsFree() {
		return categoryIsFree;
	}
	public void setCategoryIsFree(String categoryIsFree) {
		this.categoryIsFree = categoryIsFree;
	}
	public String getCategoryLang() {
		return categoryLang;
	}
	public void setCategoryLang(String categoryLang) {
		this.categoryLang = categoryLang;
	}
	public String getCategoryStyle() {
		return categoryStyle;
	}
	public void setCategoryStyle(String categoryStyle) {
		this.categoryStyle = categoryStyle;
	}
	public Date getCategoryReleaseYear() {
		return categoryReleaseYear;
	}
	public void setCategoryReleaseYear(Date categoryReleaseYear) {
		this.categoryReleaseYear = categoryReleaseYear;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	@Override
	public String toString() {
		return "FontCategory [categoryCode=" + categoryCode + ", fontNo=" + fontNo + ", categoryIsFree="
				+ categoryIsFree + ", categoryLang=" + categoryLang + ", categoryStyle=" + categoryStyle
				+ ", categoryReleaseYear=" + categoryReleaseYear + ", font=" + font + "]";
	}
	
	
	
}
