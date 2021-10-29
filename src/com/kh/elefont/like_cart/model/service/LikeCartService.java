package com.kh.elefont.like_cart.model.service;
import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.getConnection;

import java.sql.Connection;

import com.kh.elefont.like_cart.model.dao.LikeCartDao;


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


	public int selectMemberCartByCartNo(String cartNo) {
		Connection conn = getConnection();

		int result = likeCartDao.selectMemberCartByCartNo(conn, cartNo);
		close(conn);
		return result;
	}

}
