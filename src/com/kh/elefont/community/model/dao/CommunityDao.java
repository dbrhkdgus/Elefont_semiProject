package com.kh.elefont.community.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
		
		String sql = prop.getProperty("selectLastCommNo");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				LastNo = rset.getString("comm_no");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return LastNo;
	}

	public List<Community> selectAllCommunityList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Community> communityList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllCommunityList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Community community = new Community();
				community.setCommNo(rset.getString("comm_no"));
				community.setCommWriter(rset.getString("comm_writer"));
				community.setCommContent(rset.getString("comm_content"));
				community.setCommViewCount(rset.getInt("comm_view_count"));
				community.setCommLikeCount(rset.getInt("comm_like_count"));
				community.setCommRegDate(rset.getDate("comm_reg_date"));
				community.setFontNo(rset.getString("font_no"));
				community.setCommTitle(rset.getString("comm_title"));
				
				communityList.add(community);
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return communityList;
	}

	public int countTotalCommunityByWriter(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCommunityByWriter = 0;
		
		String sql = prop.getProperty("countTotalCommunityByWriter");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,memberNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalCommunityByWriter = rset.getInt(1);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalCommunityByWriter;
	}

}
