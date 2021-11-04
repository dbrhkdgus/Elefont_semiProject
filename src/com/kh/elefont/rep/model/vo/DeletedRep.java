package com.kh.elefont.rep.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class DeletedRep extends Rep implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date repDeleteDate;

	public DeletedRep() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeletedRep(int repNo, String repWriter, String repContent, Date repRegDate, String fontNo, String comnNo,
			int repLevel, int repRef, String memberNo, Date repDeleteDate) {
		super(repNo, repWriter, repContent, repRegDate, fontNo, comnNo, repLevel, repRef, memberNo);
		this.repDeleteDate = repDeleteDate;
	}

	public Date getRepDeleteDate() {
		return repDeleteDate;
	}

	public void setRepDeleteDate(Date repDeleteDate) {
		this.repDeleteDate = repDeleteDate;
	}

	@Override
	public String toString() {
		return super.toString() + "DeletedRep [repDeleteDate=" + repDeleteDate + "]";
	}
	
	

}
