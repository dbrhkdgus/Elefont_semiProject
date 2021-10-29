package com.kh.elefont.like_cart.model.service;
import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.elefont.like_cart.model.dao.LikeCartDao;
import com.kh.elefont.like_cart.model.vo.MemberCart;


public class LikeCartService {
	LikeCartDao likeCartDao = new LikeCartDao();

	
	public int insertMemberCart(String memberNo, String cartNo) {
		
		Connection conn = getConnection();

		int result = likeCartDao.insertMemberCart(conn, memberNo, cartNo);
		close(conn);
		return result;
	}


	public int deleteCart(String cartNo) {
		Connection conn = getConnection();

		int result = likeCartDao.deleteCart(conn, cartNo);
		close(conn);
		return result;
	}


	public int insertCart(String cartNo, String fontNo) {
		Connection conn = getConnection();

		int result = likeCartDao.insertCart(conn, cartNo, fontNo);
		close(conn);
		return result;
	}


	public MemberCart selectMemberCartByCartNo(String cartNo) {
		Connection conn = getConnection();

		MemberCart memberCart = likeCartDao.selectMemberCartByCartNo(conn, cartNo);
		close(conn);
		return memberCart;
	}


	public int deleteMemberCart(String cartNo) {
		Connection conn = getConnection();

		int result = likeCartDao.deleteMemberCart(conn, cartNo);
		close(conn);
		return result;
	}


	public List<MemberCart> selectAllMemberCartByMemberNo(String memberNo) {
		Connection conn = getConnection();

		List<MemberCart> memberCartList = likeCartDao.selectAllMemberCartByMemberNo(conn, memberNo);
		close(conn);
		return memberCartList;
	}

}
