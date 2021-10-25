package com.kh.elefont.community.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.elefont.community.model.vo.Community;


public class CommunityDao {
	private Properties prop = new Properties();
	
	public CommunityDao() {
		
		String filepath = CommunityDao.class.getResource("/community/community-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int enrollBoard(Connection conn, Community community) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("enrollBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1,community.getCommWriter());
			pstmt.setString(2, community.getCommContent());
			pstmt.setString(3, community.getFontNo());
			pstmt.setString(4, community.getCommTitle());
			
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectLastCommNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String LastNo = "";
		
		String sql = null;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				LastNo = rset.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return LastNo;
	}

}
