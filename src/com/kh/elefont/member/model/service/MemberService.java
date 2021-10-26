package com.kh.elefont.member.model.service;

import static com.kh.elefont.common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;

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

}
