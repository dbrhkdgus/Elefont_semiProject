package com.kh.elefont.question.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int qNo;
	private String qQuestioner;
	private String qContent;
	private String qWriter;
	private Timestamp qDate;
	private String qIsAnswered;
	
	public Question() {
		super();
	}

	public Question(int qNo, String qQuestioner, String qContent, String qWriter, Timestamp qDate, String qIsAnswered) {
		super();
		this.qNo = qNo;
		this.qQuestioner = qQuestioner;
		this.qContent = qContent;
		this.qWriter = qWriter;
		this.qDate = qDate;
		this.qIsAnswered = qIsAnswered;
	}

	public int getqNo() {
		return qNo;
	}

	public void setqNo(int qNo) {
		this.qNo = qNo;
	}

	public String getqQuestioner() {
		return qQuestioner;
	}

	public void setqQuestioner(String qQuestioner) {
		this.qQuestioner = qQuestioner;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public String getqWriter() {
		return qWriter;
	}

	public void setqWriter(String qWriter) {
		this.qWriter = qWriter;
	}

	public Timestamp getqDate() {
		return qDate;
	}

	public void setqDate(Timestamp qDate) {
		this.qDate = qDate;
	}

	public String getqIsAnswered() {
		return qIsAnswered;
	}

	public void setqIsAnswered(String qIsAnswered) {
		this.qIsAnswered = qIsAnswered;
	}

	@Override
	public String toString() {
		return "Question [qNo=" + qNo + ", qQuestioner=" + qQuestioner + ", qContent=" + qContent + ", qWriter="
				+ qWriter + ", qDate=" + qDate + ", qIsAnswered=" + qIsAnswered + "]";
	} 
}
