package com.kh.elefont.font.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.common.model.vo.Attachment;

public class FontExt extends Font implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date memberOrderDate;
	private String memberOrderNo;
	private double finalPrice;
	
	public FontExt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FontExt(String fontNo, String fontName, String fontUrl, String memberId, double fontPrice,
			double fontDiscountRate, int fontLikeCount, int fontViewCount, int fontPurchasedCount, Date fontRegDate,
			String fontApproval, String fontFamily, String fontWeight, Attachment attach, Date memberOrderDate,
			String memberOrderNo, double finalPrice) {
		super(fontNo, fontName, fontUrl, memberId, fontPrice, fontDiscountRate, fontLikeCount, fontViewCount,
				fontPurchasedCount, fontRegDate, fontApproval, fontFamily, fontWeight, attach);
		this.memberOrderDate = memberOrderDate;
		this.memberOrderNo = memberOrderNo;
		this.finalPrice = finalPrice;
	}

	public Date getMemberOrderDate() {
		return memberOrderDate;
	}

	public void setMemberOrderDate(Date memberOrderDate) {
		this.memberOrderDate = memberOrderDate;
	}

	public String getMemberOrderNo() {
		return memberOrderNo;
	}

	public void setMemberOrderNo(String memberOrderNo) {
		this.memberOrderNo = memberOrderNo;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FontExt [memberOrderDate=" + memberOrderDate + ", memberOrderNo=" + memberOrderNo + ", finalPrice="
				+ finalPrice + "]";
	}
	
}
