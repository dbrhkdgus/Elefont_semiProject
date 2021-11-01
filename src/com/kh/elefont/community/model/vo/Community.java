package com.kh.elefont.community.model.vo;
import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.common.model.vo.Attachment;
public class Community implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commNo;
	private String commTitle;
	private String commWriter;
	private String commContent;
	private int commViewCount;
	private int commLikeCount;
	private Date commRegDate;
	private String fontNo;
	private String memberNo;
	
	private Attachment attach;
	public Community() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Community(String commNo, String commTitle, String commWriter, String commContent, int commViewCount,
			int commLikeCount, Date commRegDate, String fontNo, String memberNo, Attachment attach) {
		super();
		this.commNo = commNo;
		this.commTitle = commTitle;
		this.commWriter = commWriter;
		this.commContent = commContent;
		this.commViewCount = commViewCount;
		this.commLikeCount = commLikeCount;
		this.commRegDate = commRegDate;
		this.fontNo = fontNo;
		this.memberNo = memberNo;
		this.attach = attach;
	}
	public String getCommNo() {
		return commNo;
	}
	public void setCommNo(String commNo) {
		this.commNo = commNo;
	}
	public String getCommTitle() {
		return commTitle;
	}
	public void setCommTitle(String commTitle) {
		this.commTitle = commTitle;
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
	public String getFontNo() {
		return fontNo;
	}
	public void setFontNo(String fontNo) {
		this.fontNo = fontNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public Attachment getAttach() {
		return attach;
	}
	public void setAttach(Attachment attach) {
		this.attach = attach;
	}
	@Override
	public String toString() {
		return "Community [commNo=" + commNo + ", commTitle=" + commTitle + ", commWriter=" + commWriter
				+ ", commContent=" + commContent + ", commViewCount=" + commViewCount + ", commLikeCount="
				+ commLikeCount + ", commRegDate=" + commRegDate + ", fontNo=" + fontNo + ", memberNo=" + memberNo
				+ ", attach=" + attach + "]";
	}
	

	
}