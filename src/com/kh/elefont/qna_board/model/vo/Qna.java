package com.kh.elefont.qna_board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Qna implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int qnaNo;
	public String qnaPassword;
	public String qnaWriter;
	public String qnaContent;
	public int qnaReadCount;
	public String qnaCategory;
	public String qnaLockFlag;
	public Date qnaRegDate;
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Qna(int qnaNo, String qnaPassword, String qnaWriter, String qnaContent, int qnaReadCount, String qnaCategory,
			String qnaLockFlag, Date qnaRegDate) {
		super();
		this.qnaNo = qnaNo;
		this.qnaPassword = qnaPassword;
		this.qnaWriter = qnaWriter;
		this.qnaContent = qnaContent;
		this.qnaReadCount = qnaReadCount;
		this.qnaCategory = qnaCategory;
		this.qnaLockFlag = qnaLockFlag;
		this.qnaRegDate = qnaRegDate;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getQnaPassword() {
		return qnaPassword;
	}
	public void setQnaPassword(String qnaPassword) {
		this.qnaPassword = qnaPassword;
	}
	public String getQnaWriter() {
		return qnaWriter;
	}
	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public int getQnaReadCount() {
		return qnaReadCount;
	}
	public void setQnaReadCount(int qnaReadCount) {
		this.qnaReadCount = qnaReadCount;
	}
	public String getQnaCategory() {
		return qnaCategory;
	}
	public void setQnaCategory(String qnaCategory) {
		this.qnaCategory = qnaCategory;
	}
	public String getQnaLockFlag() {
		return qnaLockFlag;
	}
	public void setQnaLockFlag(String qnaLockFlag) {
		this.qnaLockFlag = qnaLockFlag;
	}
	public Date getQnaRegDate() {
		return qnaRegDate;
	}
	public void setQnaRegDate(Date qnaRegDate) {
		this.qnaRegDate = qnaRegDate;
	}
	@Override
	public String toString() {
		return "Qna [qnaNo=" + qnaNo + ", qnaPassword=" + qnaPassword + ", qnaWriter=" + qnaWriter + ", qnaContent="
				+ qnaContent + ", qnaReadCount=" + qnaReadCount + ", qnaCategory=" + qnaCategory + ", qnaLockFlag="
				+ qnaLockFlag + ", qnaRegDate=" + qnaRegDate + "]";
	}
	
	
}
