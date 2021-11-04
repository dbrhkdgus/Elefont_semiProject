package com.kh.elefont.community.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.common.model.vo.Attachment;

public class DeletedCommunity extends Community implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date commDeleteDate;

	public DeletedCommunity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeletedCommunity(String commNo, String commTitle, String commWriter, String commContent, int commViewCount,
			int commLikeCount, Date commRegDate, String fontNo, String memberNo, Attachment attach,
			Date commDeleteDate) {
		super(commNo, commTitle, commWriter, commContent, commViewCount, commLikeCount, commRegDate, fontNo, memberNo,
				attach);
		this.commDeleteDate = commDeleteDate;
	}

	public Date getCommDeleteDate() {
		return commDeleteDate;
	}

	public void setCommDeleteDate(Date commDeleteDate) {
		this.commDeleteDate = commDeleteDate;
	}

	@Override
	public String toString() {
		return super.toString() + "DeletedCommunity [commDeleteDate=" + commDeleteDate + "]";
	}

	
}
