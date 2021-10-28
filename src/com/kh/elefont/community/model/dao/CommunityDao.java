package com.kh.elefont.community.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.mvc.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.member.model.vo.Member;


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


	public Community selectOneCommunity(Connection conn, String commNo) {
		Community community = new Community();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOneCommunity");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				community.setCommNo(rset.getString("comm_no"));
				community.setCommWriter(rset.getString("comm_writer"));
				community.setCommContent(rset.getString("comm_content"));
				community.setCommViewCount(rset.getInt("comm_view_count"));
				community.setCommLikeCount(rset.getInt("comm_like_count"));
				community.setCommRegDate(rset.getDate("comm_reg_date"));
				community.setFontNo(rset.getString("font_no"));
				community.setCommTitle(rset.getString("comm_title"));
 			  }
      
      } catch (SQLException e) {
			e.printStackTrace();
		  } finally {
			close(rset);
			close(pstmt);
		}
//    System.out.println("commDao@" + community);
		
		return community;
    }

  
	public List<Community> selectCommunityListByFontNo(Connection conn, String fontNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Community> communityList = new ArrayList<>();
		
		String sql = prop.getProperty("selectCommunityListByFontNo");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,fontNo);
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

	public int updateCommunityViewCount(Connection conn, String commNo) {
		PreparedStatement pstmt = null;
		int result  = 0;
		String sql = prop.getProperty("updateCommunityViewCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,commNo);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteCommunity(Connection conn, String commNo) {
		int result = 0;
        PreparedStatement pstmt = null;
        String query = prop.getProperty("deleteCommunity"); 
        
        try {
            //미완성쿼리문을 가지고 객체생성.
            pstmt = conn.prepareStatement(query);
            //쿼리문미완성
            pstmt.setString(1, commNo);
            
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


	public int updateCommunity(Connection conn, Community community) {
		System.out.println("updateCommunity@Dao" + community);
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateCommunity"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1,community.getCommContent());
			pstmt.setString(2, community.getFontNo());
			pstmt.setString(3, community.getCommTitle());
			pstmt.setString(4, community.getCommNo());
			
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


}
