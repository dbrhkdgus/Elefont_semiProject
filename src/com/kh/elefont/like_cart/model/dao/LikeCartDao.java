package com.kh.elefont.like_cart.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.kh.elefont.common.JdbcTemplate.*;

public class LikeCartDao {
	
	private Properties prop = new Properties();
	public LikeCartDao() {
		  
        String filepath = LikeCartDao.class.getResource("/likeCart/likeCart-query.properties").getPath();

        try {
            prop.load(new FileReader(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public int insertMemberCart(Connection conn, String memberNo, String cartNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMemberCart");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			pstmt.setString(2, cartNo);
			
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteCart(Connection conn, String cartNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteCart");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartNo);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertCart(Connection conn, String cartNo, String fontNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertCart");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartNo);
			pstmt.setString(2, fontNo);
			
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectMemberCartByCartNo(Connection conn, String cartNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberCartByCartNo");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartNo);
			
			
			rset = pstmt.executeQuery();
			if(!rset.next()) result = 0;
			else result = 1;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

}
