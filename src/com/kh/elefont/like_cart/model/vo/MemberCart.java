package com.kh.elefont.like_cart.model.vo;

import java.io.Serializable;

public class MemberCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberNo;
	private String cartNo;
	public MemberCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberCart(String memberNo, String cartNo) {
		super();
		this.memberNo = memberNo;
		this.cartNo = cartNo;
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
	@Override
	public String toString() {
		return "MemberCart [memberNo=" + memberNo + ", cartNo=" + cartNo + "]";
	}
	
	
}
