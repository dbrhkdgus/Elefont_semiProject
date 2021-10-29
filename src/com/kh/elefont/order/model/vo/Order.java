package com.kh.elefont.order.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private String memberNo;
	private Date memberOrderDate;
	private String orderNo;
	private String fontNo;
	private String fontName;
	private int fontPrice;
	private double fontDiscoutRate;
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Order(String memberNo, Date memberOrderDate, String orderNo, String fontNo, String fontName,
			int fontPrice, double fontDiscoutRate) {
		super();
		this.memberNo = memberNo;
		this.memberOrderDate = memberOrderDate;
		this.orderNo = orderNo;
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


	public Date getMemberOrderDate() {
		return memberOrderDate;
	}


	public void setMemberOrderDate(Date memberOrderDate) {
		this.memberOrderDate = memberOrderDate;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
		return "Order [memberNo=" + memberNo + ", memberOrderDate=" + memberOrderDate + ", orderNo=" + orderNo
				+ ", fontNo=" + fontNo + ", fontName=" + fontName + ", fontPrice=" + fontPrice + ", fontDiscoutRate="
				+ fontDiscoutRate + "]";
	}


	
	

}
