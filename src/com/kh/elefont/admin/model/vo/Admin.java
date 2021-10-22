package com.kh.elefont.admin.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.elefont.member.model.vo.Member;

/**
 * @author ehqkr
 *
 */
public class Admin extends Member implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String adminGrade;
	public String adminName;
	
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Admin(String adminGrade, String adminName) {
		super();
		this.adminGrade = adminGrade;
		this.adminName = adminName;
	}




	@Override
	public String toString() {
		return super.toString() +  "Admin [adminGrade=" + adminGrade + ", adminName=" + adminName + "]";
	}
	
	
	
	

	
	
	
}
