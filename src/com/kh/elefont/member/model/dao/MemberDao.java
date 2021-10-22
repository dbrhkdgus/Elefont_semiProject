package com.kh.elefont.member.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.elefont.member.model.vo.Attachment;
import com.kh.elefont.member.model.vo.Member;

public class MemberDao {

	private Properties prop = new Properties();
	
	  public MemberDao() {
		  	
		  
	        String filepath = MemberDao.class.getResource("/member/member-query.properties").getPath();
	        System.out.println(filepath);
	        try {
	            prop.load(new FileReader(filepath));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	  }
	        
	
	public  Member selectOneMember(Connection conn, String memberId) {
		Attachment attach = null;
		String sql = prop.getProperty("selectOneMember");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			// 1.PreparedStatment객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			// 2.실행 & ResultSet객체 리턴
			rset = pstmt.executeQuery();
			
			// 3.ResultSet -> Member
			if(rset.next()) {
				String memberNo = rset.getString("member_No");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				String memberGender = rset.getString("member_gender");
				String memberEmail = rset.getString("member_email");
				String memberPhone = rset.getString("member_phone");
				Date memberBirthday = rset.getDate("member_birthday");
				String memberJob = rset.getString("member_job");
				String memberPoint = rset.getString("member_point");
				Date enrollDate = rset.getDate("member_reg_date");
				String memberQuitYN = rset.getString("member_quit_yn");
				String memberRole = rset.getString("member_role");
//				Attachment  attNo = rset.getObject("att_no");
				
				member = new Member(memberNo, memberId, memberPwd, memberName, memberGender, memberEmail, memberPhone, memberBirthday, memberJob, memberPoint, enrollDate, memberQuitYN, memberRole, null);
			}
			System.out.println(member);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return member;
	}


	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberPhone());
			pstmt.setDate(7, member.getMemberBirthday());
			pstmt.setString(8, member.getMemberJob());
			pstmt.setString(9, member.getMemberRole());
			
			result = pstmt.executeUpdate();
			System.out.println("result@dao"+ result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public Member selectOneMemberByEmail(Connection conn, String memberEmail) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneMemberByEmail");
		Member member = null;
		System.out.println("memberDao@"+memberEmail);
		
		try {
			// 1.PreparedStatment객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setString(1, memberEmail);
			
			// 2.실행 & ResultSet객체 리턴
			rset = pstmt.executeQuery();
			if(rset == null) System.out.println("null 리턴");
			
			// 3.ResultSet -> Member
			if(rset.next()) {
				String memberNo = rset.getString("member_No");
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				String memberGender = rset.getString("member_gender");
				String memberPhone = rset.getString("member_phone");
				Date memberBirthday = rset.getDate("member_birthday");
				String memberJob = rset.getString("member_job");
				String memberPoint = rset.getString("member_point");
				Date enrollDate = rset.getDate("member_reg_date");
				String memberQuitYN = rset.getString("member_quit_yn");
				String memberRole = rset.getString("member_role");
//				Attachment  attNo = rset.getObject("att_no");
				
				member = new Member(memberNo, memberId, memberPwd, memberName, memberGender, memberEmail, memberPhone, memberBirthday, memberJob, memberPoint, enrollDate, memberQuitYN, memberRole, null);
			}
			System.out.println(member);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return member;
	}


}