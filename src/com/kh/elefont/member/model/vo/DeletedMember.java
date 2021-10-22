package com.kh.elefont.member.model.vo;

import java.sql.Date;

public class DeletedMember extends Member {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 상속관계 어떻게?
	
	
	private Date memberQuitDate;
	private String memberQuitReason;
	public DeletedMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeletedMember(String memberNo, String memberId, String memberPwd, String memberName, String memberGender,
			String memberEmail, String memberPhone, Date memberBirthday, String memberJob, String memberPoint,
			Date memberRegDate, String memberQuitYN, String memberRole, Attachment att_no) {
		super(memberNo, memberId, memberPwd, memberName, memberGender, memberEmail, memberPhone, memberBirthday, memberJob,
				memberPoint, memberRegDate, memberQuitYN, memberRole, att_no);
		// TODO Auto-generated constructor stub
	}
	public DeletedMember(Date memberQuitDate, String memberQuitReason) {
		super();
		this.memberQuitDate = memberQuitDate;
		this.memberQuitReason = memberQuitReason;
	}
	public Date getMemberQuitDate() {
		return memberQuitDate;
	}
	public void setMemberQuitDate(Date memberQuitDate) {
		this.memberQuitDate = memberQuitDate;
	}
	public String getMemberQuitReason() {
		return memberQuitReason;
	}
	public void setMemberQuitReason(String memberQuitReason) {
		this.memberQuitReason = memberQuitReason;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DeletedMember [memberQuitDate=" + memberQuitDate + ", memberQuitReason=" + memberQuitReason + "]";
	}
	
	
	
}
