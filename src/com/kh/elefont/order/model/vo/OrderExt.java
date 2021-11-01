package com.kh.elefont.order.model.vo;

import java.sql.Date;

public class OrderExt extends Order{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String memberId;
	private String memberEmail;
	private String fontUrl;
	
	
	
	public OrderExt() {
		super();
	}


	public OrderExt(String memberNo, Date memberOrderDate, String orderNo, String fontNo, String fontName,
			int fontPrice, double fontDiscoutRate, String memberId, String memberEmail, String fontUrl) {
		super(memberNo, memberOrderDate, orderNo, fontNo, fontName, fontPrice, fontDiscoutRate);
		this.memberId = memberId;
		this.memberEmail = memberEmail;
		this.fontUrl = fontUrl;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public String getFontUrl() {
		return fontUrl;
	}


	public void setFontUrl(String fontUrl) {
		this.fontUrl = fontUrl;
	}


	@Override
	public String toString() {
		return "OrderExt [memberId=" + memberId + ", memberEmail=" + memberEmail + ", fontUrl=" + fontUrl + "]";
	}

	
}
