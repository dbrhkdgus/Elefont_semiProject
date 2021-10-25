package com.kh.elefont.font.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.common.model.vo.Attachment;

public class Font implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fontNo;
	private String fontName;
	private String fontUrl;
	private double fontPrice;
	private double fontDiscountRate;
	private int fontLikeCount;
	private int fontViewCount;
	
	private int fontPurchasedCount;
	
	private Date fontRegDate;
	
	private Attachment attach;

	public Font() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Font(String fontNo, String fontName, String fontUrl, double fontPrice, double fontDiscountRate,
			int fontLikeCount, int fontViewCount, int fontPurchasedCount, Date fontRegDate) {
		super();
		this.fontNo = fontNo;
		this.fontName = fontName;
		this.fontUrl = fontUrl;
		this.fontPrice = fontPrice;
		this.fontDiscountRate = fontDiscountRate;
		this.fontLikeCount = fontLikeCount;
		this.fontViewCount = fontViewCount;
		this.fontPurchasedCount = fontPurchasedCount;
		this.fontRegDate = fontRegDate;
	}

	public String getFontNo() {
		return fontNo;
	}

	public void setFontNo(String fontNo) {
		this.fontNo = fontNo;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public String getFontUrl() {
		return fontUrl;
	}

	public void setFontUrl(String fontUrl) {
		this.fontUrl = fontUrl;
	}

	public double getFontPrice() {
		return fontPrice;
	}

	public void setFontPrice(double fontPrice) {
		this.fontPrice = fontPrice;
	}

	public double getFontDiscountRate() {
		return fontDiscountRate;
	}

	public void setFontDiscountRate(double fontDiscountRate) {
		this.fontDiscountRate = fontDiscountRate;
	}

	public int getFontLikeCount() {
		return fontLikeCount;
	}

	public void setFontLikeCount(int fontLikeCount) {
		this.fontLikeCount = fontLikeCount;
	}

	public int getFontViewCount() {
		return fontViewCount;
	}

	public void setFontViewCount(int fontViewCount) {
		this.fontViewCount = fontViewCount;
	}

	public int getFontPurchasedCount() {
		return fontPurchasedCount;
	}

	public void setFontPurchasedCount(int fontPurchasedCount) {
		this.fontPurchasedCount = fontPurchasedCount;
	}

	public Date getFontRegDate() {
		return fontRegDate;
	}

	public void setFontRegDate(Date fontRegDate) {
		this.fontRegDate = fontRegDate;
	}

	@Override
	public String toString() {
		return "Font [fontNo=" + fontNo + ", fontName=" + fontName + ", fontUrl=" + fontUrl + ", fontPrice=" + fontPrice
				+ ", fontDiscountRate=" + fontDiscountRate + ", fontLikeCount=" + fontLikeCount + ", fontViewCount="
				+ fontViewCount + ", fontPurchasedCount=" + fontPurchasedCount + ", fontRegDate=" + fontRegDate + "]";
	}
	
	
	
}
