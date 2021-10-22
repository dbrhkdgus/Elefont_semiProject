package com.kh.elefont.like_cart.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.font.model.vo.Font;

public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cartNo;
	private String fontNo;
	private Date cartRegDate;
	private String cartIsPaid;
	private Date cartPayDate;
	private Font font;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(String cartNo, String fontNo, Date cartRegDate, String cartIsPaid, Date cartPayDate, Font font) {
		super();
		this.cartNo = cartNo;
		this.fontNo = fontNo;
		this.cartRegDate = cartRegDate;
		this.cartIsPaid = cartIsPaid;
		this.cartPayDate = cartPayDate;
		this.font = font;
	}
	public String getCartNo() {
		return cartNo;
	}
	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}
	public String getFontNo() {
		return fontNo;
	}
	public void setFontNo(String fontNo) {
		this.fontNo = fontNo;
	}
	public Date getCartRegDate() {
		return cartRegDate;
	}
	public void setCartRegDate(Date cartRegDate) {
		this.cartRegDate = cartRegDate;
	}
	public String getCartIsPaid() {
		return cartIsPaid;
	}
	public void setCartIsPaid(String cartIsPaid) {
		this.cartIsPaid = cartIsPaid;
	}
	public Date getCartPayDate() {
		return cartPayDate;
	}
	public void setCartPayDate(Date cartPayDate) {
		this.cartPayDate = cartPayDate;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", fontNo=" + fontNo + ", cartRegDate=" + cartRegDate + ", cartIsPaid="
				+ cartIsPaid + ", cartPayDate=" + cartPayDate + ", font=" + font + "]";
	}
	
	
	
}
