package com.kh.elefont.coupon.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Coupon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String couponNo;
	private String couponType;
	private Date couponRegDate;
	private int couponExpired;
	private String couponUsed;
	private int couponPAmount;
	private double couponDiscount;
	private String memberNo;
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coupon(String couponNO, String couponType, Date couponRegDate, int couponExpired, String couponUsed,
			int couponPAmout, double couponDiscount, String memberNo) {
		super();
		this.couponNo = couponNO;
		this.couponType = couponType;
		this.couponRegDate = couponRegDate;
		this.couponExpired = couponExpired;
		this.couponUsed = couponUsed;
		this.couponPAmount = couponPAmout;
		this.couponDiscount = couponDiscount;
		this.memberNo = memberNo;
	}
	public String getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(String couponNO) {
		this.couponNo = couponNO;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public Date getCouponRegDate() {
		return couponRegDate;
	}
	public void setCouponRegDate(Date couponRegDate) {
		this.couponRegDate = couponRegDate;
	}
	public int getCouponExpired() {
		return couponExpired;
	}
	public void setCouponExpired(int couponExpired) {
		this.couponExpired = couponExpired;
	}
	public String getCouponUsed() {
		return couponUsed;
	}
	public void setCouponUsed(String couponUsed) {
		this.couponUsed = couponUsed;
	}
	public int getCouponPAmount() {
		return couponPAmount;
	}
	public void setCouponPAmount(int couponPAmout) {
		this.couponPAmount = couponPAmout;
	}
	public double getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	@Override
	public String toString() {
		return "Coupon [couponNo=" + couponNo + ", couponType=" + couponType + ", couponRegDate=" + couponRegDate
				+ ", couponExpired=" + couponExpired + ", couponUsed=" + couponUsed + ", couponPAmout=" + couponPAmount
				+ ", couponDiscount=" + couponDiscount + ", memberNo=" + memberNo + "]";
	}
	
	
	

}
