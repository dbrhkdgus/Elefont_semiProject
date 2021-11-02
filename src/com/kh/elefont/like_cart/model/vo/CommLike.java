package com.kh.elefont.like_cart.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.font.model.vo.Font;

public class CommLike implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberNo;
	private String commNo;
	private Date likeCommRegDate;

	
	public CommLike() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CommLike(String memberNo, String commNo, Date likeCommRegDate) {
		super();
		this.memberNo = memberNo;
		this.commNo = commNo;
		this.likeCommRegDate = likeCommRegDate;
	}


	public String getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}


	public String getCommNo() {
		return commNo;
	}


	public void setCommNo(String commNo) {
		this.commNo = commNo;
	}


	public Date getLikeCommRegDate() {
		return likeCommRegDate;
	}


	public void setLikeCommRegDate(Date likeCommRegDate) {
		this.likeCommRegDate = likeCommRegDate;
	}


	@Override
	public String toString() {
		return "LikeReview [memberNo=" + memberNo + ", commNo=" + commNo + ", likeCommRegDate=" + likeCommRegDate + "]";
	}
	
	
		

		
	
	
	
	
}
