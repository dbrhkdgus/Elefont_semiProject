package com.kh.elefont.coupon.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class UsedCoupon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Coupon coupon;
	private Date couoponUsedDate;
	public UsedCoupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UsedCoupon(Coupon coupon, Date couoponUsedDate) {
		super();
		this.coupon = coupon;
		this.couoponUsedDate = couoponUsedDate;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Date getCouoponUsedDate() {
		return couoponUsedDate;
	}

	public void setCouoponUsedDate(Date couoponUsedDate) {
		this.couoponUsedDate = couoponUsedDate;
	}

	@Override
	public String toString() {
		return "UsedCoupon [coupon=" + coupon + ", couoponUsedDate=" + couoponUsedDate + "]";
	}
	
	
	
}
