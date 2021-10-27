package com.kh.elefont.font.model.vo;

import java.io.Serializable;

public class FontCopyright implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fontNo;
	private String fontPublisher;
	private String fontDesigner;
	private String fontRootUrl;
	
	public FontCopyright() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FontCopyright(String fontNo, String fontPublisher, String fontDesigner, String fontRootUrl) {
		super();
		this.fontNo = fontNo;
		this.fontPublisher = fontPublisher;
		this.fontDesigner = fontDesigner;
		this.fontRootUrl = fontRootUrl;
	}

	public String getFontNo() {
		return fontNo;
	}

	public void setFontNo(String fontNo) {
		this.fontNo = fontNo;
	}

	public String getFontPublisher() {
		return fontPublisher;
	}

	public void setFontPublisher(String fontPublisher) {
		this.fontPublisher = fontPublisher;
	}

	public String getFontDesigner() {
		return fontDesigner;
	}

	public void setFontDesigner(String fontDesigner) {
		this.fontDesigner = fontDesigner;
	}

	public String getFontRootUrl() {
		return fontRootUrl;
	}

	public void setFontRootUrl(String fontRootUrl) {
		this.fontRootUrl = fontRootUrl;
	}

	@Override
	public String toString() {
		return "FontCopyright [fontNo=" + fontNo + ", fontPublisher=" + fontPublisher + ", fontDesigner=" + fontDesigner
				+ ", fontRootUrl=" + fontRootUrl + "]";
	}
	
	
	
	
}
