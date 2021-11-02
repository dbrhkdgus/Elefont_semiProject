package com.kh.elefont.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class MemberCart implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	
	private String memberNo;
	private String cartNo;
	private Date cartRegDate;
	private String fontNo;
	private String fontName;
	private int fontPrice;
	private double fontDiscoutRate;
	
	
	
	public MemberCart() {
		super();
		// TODO Auto-generated constructor stub
	}



	public MemberCart(String memberNo, String cartNo, Date cartRegDate, String fontNo, String fontName, int fontPrice,
			double fontDiscoutRate) {
		super();
		this.memberNo = memberNo;
		this.cartNo = cartNo;
		this.cartRegDate = cartRegDate;
		this.fontNo = fontNo;
		this.fontName = fontName;
		this.fontPrice = fontPrice;
		this.fontDiscoutRate = fontDiscoutRate;
	}



	public String getMemberNo() {
		return memberNo;
	}



	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}



	public String getCartNo() {
		return cartNo;
	}



	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}



	public Date getCartRegDate() {
		return cartRegDate;
	}



	public void setCartRegDate(Date cartRegDate) {
		this.cartRegDate = cartRegDate;
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



	public int getFontPrice() {
		return fontPrice;
	}



	public void setFontPrice(int fontPrice) {
		this.fontPrice = fontPrice;
	}



	public double getFontDiscoutRate() {
		return fontDiscoutRate;
	}



	public void setFontDiscoutRate(double fontDiscoutRate) {
		this.fontDiscoutRate = fontDiscoutRate;
	}



	@Override
	public String toString() {
		return "MemberCart [memberNo=" + memberNo + ", cartNo=" + cartNo + ", cartRegDate=" + cartRegDate + ", fontNo="
				+ fontNo + ", fontName=" + fontName + ", fontPrice=" + fontPrice + ", fontDiscoutRate="
				+ fontDiscoutRate + "]";
	}
	
	
	
	
	
	
	
		
	
}
