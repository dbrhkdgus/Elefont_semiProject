package com.kh.elefont.like_cart.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.font.model.vo.Font;

public class LikeReview implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String likeReviewNo;
	private Date likeReviewRegDate;
	private String reviewNo;
	private Font font;
	public LikeReview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeReview(String likeReviewNo, Date likeReviewRegDate, String reviewNo, Font font) {
		super();
		this.likeReviewNo = likeReviewNo;
		this.likeReviewRegDate = likeReviewRegDate;
		this.reviewNo = reviewNo;
		this.font = font;
	}
	public String getLikeReviewNo() {
		return likeReviewNo;
	}
	public void setLikeReviewNo(String likeReviewNo) {
		this.likeReviewNo = likeReviewNo;
	}
	public Date getLikeReviewRegDate() {
		return likeReviewRegDate;
	}
	public void setLikeReviewRegDate(Date likeReviewRegDate) {
		this.likeReviewRegDate = likeReviewRegDate;
	}
	public String getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(String reviewNo) {
		this.reviewNo = reviewNo;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	@Override
	public String toString() {
		return "LikeReview [likeReviewNo=" + likeReviewNo + ", likeReviewRegDate=" + likeReviewRegDate + ", reviewNo="
				+ reviewNo + ", font=" + font + "]";
	}
	
	
	
	
	
}
