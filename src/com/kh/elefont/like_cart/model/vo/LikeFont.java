package com.kh.elefont.like_cart.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class LikeFont implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MemberNo;
	private String fontNo;
	private Date likeFontRegDate;
	
	
	public LikeFont() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LikeFont(String memberNo, String fontNo, Date likeFontRegDate) {
		super();
		MemberNo = memberNo;
		this.fontNo = fontNo;
		this.likeFontRegDate = likeFontRegDate;
	}


	public String getMemberNo() {
		return MemberNo;
	}


	public void setMemberNo(String memberNo) {
		MemberNo = memberNo;
	}


	public String getFontNo() {
		return fontNo;
	}


	public void setFontNo(String fontNo) {
		this.fontNo = fontNo;
	}


	public Date getLikeFontRegDate() {
		return likeFontRegDate;
	}


	public void setLikeFontRegDate(Date likeFontRegDate) {
		this.likeFontRegDate = likeFontRegDate;
	}


	@Override
	public String toString() {
		return "LikeFont [MemberNo=" + MemberNo + ", fontNo=" + fontNo + ", likeFontRegDate=" + likeFontRegDate + "]";
	} 
	
	

	


	
	
	
	
}
