package com.kh.elefont.like_cart.model.service;
import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.getConnection;

import java.sql.Connection;

import com.kh.elefont.like_cart.model.dao.LikeCartDao;


public class LikeCartService {
	LikeCartDao likeCartDao = new LikeCartDao();

	
	public int insertCart(String fontNo, String memberNo) {
		
		Connection conn = getConnection();

		int result = likeCartDao.insertCart(conn, fontNo, memberNo);
		close(conn);
		return result;
	}


	public int deleteCart(String cartNo) {
		Connection conn = getConnection();

		int result = likeCartDao.deleteCart(conn, cartNo);
		close(conn);
		return result;
	}

}
