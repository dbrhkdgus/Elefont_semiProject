package com.kh.elefont.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.common.model.vo.Attachment;

public class Member implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberGender;
	private String memberEmail;
	private String memberPhone;
	private Date memberBirthday;
	private String memberJob;
	private String memberPoint;
	private Date memberRegDate;
	private String memberQuitYN;
	private String memberRole;
	private Attachment attNo;
	
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Member(String memberNo, String memberId, String memberPwd, String memberName, String memberGender,
			String memberEmail, String memberPhone, Date memberBirthday, String memberJob, String memberPoint,
			Date memberRegDate, String memberQuitYN, String memberRole, Attachment attNo) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberGender = memberGender;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberBirthday = memberBirthday;
		this.memberJob = memberJob;
		this.memberPoint = memberPoint;
		this.memberRegDate = memberRegDate;
		this.memberQuitYN = memberQuitYN;
		this.memberRole = memberRole;
		this.attNo = attNo;
	}


	public String getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getMemberPwd() {
		return memberPwd;
	}


	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberGender() {
		return memberGender;
	}


	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public String getMemberPhone() {
		return memberPhone;
	}


	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}


	public Date getMemberBirthday() {
		return memberBirthday;
	}


	public void setMemberBirthday(Date memberBirthday) {
		this.memberBirthday = memberBirthday;
	}


	public String getMemberJob() {
		return memberJob;
	}


	public void setMemberJob(String memberJob) {
		this.memberJob = memberJob;
	}


	public String getMemberPoint() {
		return memberPoint;
	}


	public void setMemberPoint(String memberPoint) {
		this.memberPoint = memberPoint;
	}


	public Date getMemberRegDate() {
		return memberRegDate;
	}


	public void setMemberRegDate(Date memberRegDate) {
		this.memberRegDate = memberRegDate;
	}


	public String getMemberQuitYN() {
		return memberQuitYN;
	}


	public void setMemberQuitYN(String memberQuitYN) {
		this.memberQuitYN = memberQuitYN;
	}


	public String getMemberRole() {
		return memberRole;
	}


	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}


	public Attachment getAttNo() {
		return attNo;
	}


	public void setAttNo(Attachment att_no) {
		this.attNo = att_no;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", memberGender=" + memberGender + ", memberEmail=" + memberEmail + ", memberPhone="
				+ memberPhone + ", memberBirthday=" + memberBirthday + ", memberJob=" + memberJob + ", memberPoint="
				+ memberPoint + ", memberRegDate=" + memberRegDate + ", memberQuitYN=" + memberQuitYN + ", memberRole="
				+ memberRole + ", att_no=" + attNo + "]";
	}
	
	
	
	
	
}

