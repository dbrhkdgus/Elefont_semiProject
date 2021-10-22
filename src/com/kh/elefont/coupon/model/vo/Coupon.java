package com.kh.elefont.coupon.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Coupon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String couponNO;
	private String couponType;
	private Date couponRegDate;
	private int couponExpired;
	private boolean couponUsed;
	private int couponPAmout;
	private double couponDiscount;
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coupon(String couponNO, String couponType, Date couponRegDate, int couponExpired, boolean couponUsed,
			int couponPAmout, double couponDiscount) {
		super();
		this.couponNO = couponNO;
		this.couponType = couponType;
		this.couponRegDate = couponRegDate;
		this.couponExpired = couponExpired;
		this.couponUsed = couponUsed;
		this.couponPAmout = couponPAmout;
		this.couponDiscount = couponDiscount;
	}
	public String getCouponNO() {
		return couponNO;
	}
	public void setCouponNO(String couponNO) {
		this.couponNO = couponNO;
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
	public boolean isCouponUsed() {
		return couponUsed;
	}
	public void setCouponUsed(boolean couponUsed) {
		this.couponUsed = couponUsed;
	}
	public int getCouponPAmout() {
		return couponPAmout;
	}
	public void setCouponPAmout(int couponPAmout) {
		this.couponPAmout = couponPAmout;
	}
	public double getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	@Override
	public String toString() {
		return "Coupon [couponNO=" + couponNO + ", couponType=" + couponType + ", couponExpired=" + couponExpired
				+ ", couponUsed=" + couponUsed + ", couponPAmout=" + couponPAmout + ", couponDiscount=" + couponDiscount
				+ "]";
	}
	
	

}
