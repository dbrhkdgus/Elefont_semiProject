package com.kh.elefont.community.model.vo;
import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.member.model.vo.Attachment;
public class Community implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commNo;
	private String commWriter;
	private String commContent;
	private int commViewCount;
	private int commLikeCount;
	private Date commRegDate;
	private int fontNo;
	
	private Attachment attach;
	public Community() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Community(String commNo, String commWriter, String commContent, int commViewCount, int commLikeCount,
			Date commRegDate, int fontNo, Attachment attach) {
		super();
		this.commNo = commNo;
		this.commWriter = commWriter;
		this.commContent = commContent;
		this.commViewCount = commViewCount;
		this.commLikeCount = commLikeCount;
		this.commRegDate = commRegDate;
		this.fontNo = fontNo;
		this.attach = attach;
	}
	public String getCommNo() {
		return commNo;
	}
	public void setCommNo(String commNo) {
		this.commNo = commNo;
	}
	public String getCommWriter() {
		return commWriter;
	}
	public void setCommWriter(String commWriter) {
		this.commWriter = commWriter;
	}
	public String getCommContent() {
		return commContent;
	}
	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}
	public int getCommViewCount() {
		return commViewCount;
	}
	public void setCommViewCount(int commViewCount) {
		this.commViewCount = commViewCount;
	}
	public int getCommLikeCount() {
		return commLikeCount;
	}
	public void setCommLikeCount(int commLikeCount) {
		this.commLikeCount = commLikeCount;
	}
	public Date getCommRegDate() {
		return commRegDate;
	}
	public void setCommRegDate(Date commRegDate) {
		this.commRegDate = commRegDate;
	}
	public int getFontNo() {
		return fontNo;
	}
	public void setFontNo(int fontNo) {
		this.fontNo = fontNo;
	}
	public Attachment getAttach() {
		return attach;
	}
	public void setAttach(Attachment attach) {
		this.attach = attach;
	}

	
}