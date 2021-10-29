package com.kh.elefont.like_cart.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class MemberCartView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberNo;
	private String fontNo;
	private String cartNo;
	private Date fontRegDate;
	private String fontName;
	private int fontPrice;
	private double fontDiscountRate;
	public MemberCartView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberCartView(String memberNo, String fontNo, String cartNo, Date fontRegDate, String fontName,
			int fontPrice, double fontDiscountRate) {
		super();
		this.memberNo = memberNo;
		this.fontNo = fontNo;
		this.cartNo = cartNo;
		this.fontRegDate = fontRegDate;
		this.fontName = fontName;
		this.fontPrice = fontPrice;
		this.fontDiscountRate = fontDiscountRate;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getFontNo() {
		return fontNo;
	}
	public void setFontNo(String fontNo) {
		this.fontNo = fontNo;
	}
	public String getCartNo() {
		return cartNo;
	}
	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}
	public Date getFontRegDate() {
		return fontRegDate;
	}
	public void setFontRegDate(Date fontRegDate) {
		this.fontRegDate = fontRegDate;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public int getFontPrice() {
		return fontPrice;
	}
	public void setFontPrice(int fontPrice) {
		this.fontPrice = fontPrice;
	}
	public double getFontDiscountRate() {
		return fontDiscountRate;
	}
	public void setFontDiscountRate(double fontDiscountRate) {
		this.fontDiscountRate = fontDiscountRate;
	}
	@Override
	public String toString() {
		return "MemberCartView [memberNo=" + memberNo + ", fontNo=" + fontNo + ", cartNo=" + cartNo + ", fontRegDate="
				+ fontRegDate + ", fontName=" + fontName + ", fontPrice=" + fontPrice + ", fontDiscountRate="
				+ fontDiscountRate + "]";
	}
	
	
	

}
