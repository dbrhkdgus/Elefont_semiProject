package com.kh.elefont.faq.model.vo;

import java.io.Serializable;

public class Faq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	private int faqNo;
	private String faqTitle;
	private String faqContent;
	
	
	
	public Faq() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Faq(int faqNo, String faqTitle, String faqContent) {
		super();
		this.faqNo = faqNo;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
	}



	public int getFaqNo() {
		return faqNo;
	}



	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}



	public String getFaqTitle() {
		return faqTitle;
	}



	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}



	public String getFaqContent() {
		return faqContent;
	}



	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}



	@Override
	public String toString() {
		return "FAQ [faqNo=" + faqNo + ", faqTitle=" + faqTitle + ", faqContent=" + faqContent + "]";
	}
	
	
	
	
}
