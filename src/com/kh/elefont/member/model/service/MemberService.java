package com.kh.elefont.member.model.service;

import static com.kh.elefont.common.JdbcTemplate.*;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.member.model.dao.MemberDao;
import com.kh.elefont.member.model.vo.Member;

public class MemberService {

	public static final String GENDER_MALE = "M";
	public static final String GENDER_FEMALE = "F";

	private MemberDao memberDao = new MemberDao();

	public Member selectOneMember(String memberId) {
		System.out.println("서비스");
		Connection conn = getConnection();

		Member member = memberDao.selectOneMember(conn, memberId);
		close(conn);
		return member;
	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = 0;

		try {
			result = memberDao.insertMember(conn, member);

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Member selectOneMemberByEmail(String memberEmail) {
		System.out.println("서비스");
		Connection conn = getConnection();

		Member member = memberDao.selectOneMemberByEmail(conn, memberEmail);
		close(conn);
		return member;
	}

	public List<Member> selectAllMember() {
		Connection conn = getConnection();

		List<Member> list = memberDao.selectAllMember(conn);
		return list;
	}

	public String selectMemberNoByMemberName(String memberName) {
		Connection conn = getConnection();
		String Memberno = memberDao.selectMemberNoByMemberName(conn, memberName);
		close(conn);

		return Memberno;

	}

	public Member selectOneMemberByMemberNo(String memberNo) {
		Connection conn = getConnection();

		Member member = memberDao.selectOneMemberByMemberNo(conn, memberNo);
		close(conn);
		return member;
	}

	public int withdrawalMember(String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.withdrawalMember(conn, memberId);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int updateMemberInfo(Member member) {
		Connection conn = getConnection();
		int result =0;
		
		try {
			result = memberDao.updateMemberInfo(conn, member);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}


	public int insertProfileImage(Attachment attach) {
		System.out.println("서비스단에 왔나요?");
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = memberDao.insertProfileImage(conn, attach);
			commit(conn);
		}catch(Exception e){
		rollback(conn);
		throw e;
		}finally {
			close(conn);
		}
		
		
		return result;
	}
	
	public List<Member> selectSearchMember(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Member> memberList = memberDao.selectSearchMember(conn, param);
		
		close(conn);
		return memberList;
	}

	public int deletePrePhoto(String memberNo) {
		System.out.println("서비스단에 memberNo : " + memberNo);
		Connection conn = getConnection();
		int delResult = 0;
		
		try {
			delResult = memberDao.deletePrePhoto(conn, memberNo);
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
			throw e;
		}finally {
			close(conn);
			}
		return delResult;
	}

	public Attachment selectOneAttachmentByNo(String memberNo) {
		Connection conn = getConnection();
		
		Attachment attach = memberDao.selectOneAttachmentByNo(conn,memberNo);
		close(conn);

		return attach;
	}


	public Attachment BringDefaultProfilePhoto(String memberId, int defaultAttNo) {
		Connection conn = getConnection();
		Attachment attach = memberDao.BringDefaultProfilePhoto(conn,defaultAttNo,memberId);
		close(conn);

		return attach;
	}

	public int insertDefaultPhoto(String bringNo) {
		Connection conn = getConnection();
		int result = 0;

		try {
			result = memberDao.insertDefaultPhoto(conn, bringNo);

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public String selectMemberNoById(String memberId) {
		Connection conn = getConnection();
		String memberNo = memberDao.selectMemberNoById(conn, memberId);
		close(conn);

		return memberNo;
	}

	

}
