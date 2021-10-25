package com.kh.elefont.common.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Attachment  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//이게왜안돼냐구
	
	private int attNo;
	private String memberNo;
	private int commNo;
	private String fontNo;
	
	private String originalFilename;
	private String renamedFilename;
	private Date regDate;
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Attachment(int attNo, String memberNo, int commNo, String fontNo, String originalFilename,
			String renamedFilename, Date regDate) {
		super();
		this.attNo = attNo;
		this.memberNo = memberNo;
		this.commNo = commNo;
		this.fontNo = fontNo;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.regDate = regDate;
	}

	public int getAttNo() {
		return attNo;
	}
	public void setAttNo(int attNo) {
		this.attNo = attNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public int getCommNo() {
		return commNo;
	}
	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public String getFontNo() {
		return fontNo;
	}

	public void setFontNo(String fontNo) {
		this.fontNo = fontNo;
	}

	@Override
	public String toString() {
		return "Attachment [attNo=" + attNo + ", memberNo=" + memberNo + ", commNo=" + commNo + ", fontNo=" + fontNo
				+ ", originalFilename=" + originalFilename + ", renamedFilename=" + renamedFilename + ", regDate="
				+ regDate + "]";
	}

	
}
