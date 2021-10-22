package com.kh.elefont.like_cart.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.font.model.vo.Font;

public class LikeFont implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String likeFontNo;
	private String fontNo;
	private Date likeFontRegDate;
	
	private Font font;

	public LikeFont() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeFont(String likeFontNo, String fontNo, Date likeFontRegDate, Font font) {
		super();
		this.likeFontNo = likeFontNo;
		this.fontNo = fontNo;
		this.likeFontRegDate = likeFontRegDate;
		this.font = font;
	}

	public String getLikeFontNo() {
		return likeFontNo;
	}

	public void setLikeFontNo(String likeFontNo) {
		this.likeFontNo = likeFontNo;
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

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public String toString() {
		return "LikeFont [likeFontNo=" + likeFontNo + ", fontNo=" + fontNo + ", likeFontRegDate=" + likeFontRegDate
				+ ", font=" + font + "]";
	}
	
	
	
	
}
