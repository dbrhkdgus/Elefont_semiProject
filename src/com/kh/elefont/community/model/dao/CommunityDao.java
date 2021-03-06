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
import java.util.Map;
import java.util.Properties;

import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.community.model.vo.DeletedCommunity;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.like_cart.model.vo.CommLike;


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
			pstmt.setString(1, community.getCommWriter());
			pstmt.setString(2, community.getCommContent());
			pstmt.setString(3, community.getFontNo());
			pstmt.setString(4, community.getCommTitle());
			pstmt.setString(5, community.getMemberNo());
			
			
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
				community.setMemberNo(rset.getString("member_no"));
				
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

	public int selectCommLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommLike");
		int likeValid = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("commNo"));
			pstmt.setString(2, (String)param.get("memberNo"));
			
			rset = pstmt.executeQuery();
			if(!rset.next()) likeValid = 0;
			else likeValid = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return likeValid;
	}

	public int deleteCommLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteCommLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("commNo"));
			pstmt.setString(2, (String)param.get("memberNo"));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertCommLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertCommLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("memberNo"));
			pstmt.setString(2, (String)param.get("commNo"));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countCommLike(Connection conn, String commNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likeCnt = 0;
		String sql = prop.getProperty("countCommLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				likeCnt = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return likeCnt;
	}

	public int updateCommLike(Connection conn, Map<String, Object> map) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateCommLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)map.get("likeCnt"));
			pstmt.setString(2, (String)map.get("commNo"));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<String> selectAllLikedComm(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> commLikeList = new ArrayList<>();
		String sql = prop.getProperty("selectAllLikedComm");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String commNo = rset.getString("comm_no");
				
				commLikeList.add(commNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return commLikeList;
	}


	

	public List<Community> selectAllCommListByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Community> communityList = new ArrayList<>();
		String sql = prop.getProperty("selectAllCommListByMemberNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
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

	public List<CommLike> selectAllLikedCommList(Connection conn, String memberNo) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CommLike> commlikeList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllLikedCommList");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				CommLike commLike = new CommLike();
				
				commLike.setMemberNo(rset.getString("member_no"));
				commLike.setCommNo(rset.getString("comm_no"));
				commLike.setLikeCommRegDate(rset.getDate("like_comm_reg_date"));

				
				commlikeList.add(commLike);
				
			  }
      } catch (SQLException e) {
			e.printStackTrace();
		  } finally {
			close(rset);
			close(pstmt);
		}	
		return commlikeList;
  }

	public List<Community> selectAllCommunityListByLikeCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Community> communityList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllCommunityListByLikeCount");
		
		
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
				community.setMemberNo(rset.getString("member_no"));
				
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

	public List<Community> findCommListByMap(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Community> list = new ArrayList<>();
		String sql = null;
	
		String searchType = (String) param.get("searchType");
		switch(searchType) {
		case "writerName":
			sql = prop.getProperty("findCommListByWriterName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "title":
			sql = prop.getProperty("findCommListByTitle");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "content":
			sql = prop.getProperty("findCommListByContent");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("searchKeyword"));
			
			
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
				community.setMemberNo(rset.getString("member_no"));
				
				list.add(community);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return list;
	}

	public List<Community> selectAllLikedCommunity(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Community> likeCommunityList = new ArrayList<>();
		String sql = prop.getProperty("selectAllLikedCommunity");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Community community = new Community();
				community.setCommNo(rset.getString("comm_no"));
				
				likeCommunityList.add(community);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return likeCommunityList;
	}

	public List<Community> selectAllCommunityListByLikeCountThree(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Community> communityList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllCommunityListByLikeCountThree");
		
		
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
				community.setMemberNo(rset.getString("member_no"));
				
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

	public List<DeletedCommunity> selectAllDeletedCommList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<DeletedCommunity> deletedCommList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllDeletedCommList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DeletedCommunity deletedCommunity = new DeletedCommunity();
				deletedCommunity.setCommNo(rset.getString("comm_no"));
				deletedCommunity.setCommWriter(rset.getString("comm_writer"));
				deletedCommunity.setCommContent(rset.getString("comm_content"));
				deletedCommunity.setCommRegDate(rset.getDate("comm_reg_date"));
				deletedCommunity.setCommDeleteDate(rset.getDate("comm_delete_date"));
				deletedCommunity.setMemberNo(rset.getString("member_no"));
				
				deletedCommList.add(deletedCommunity);
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return deletedCommList;
	}

	public List<String> selectAllCommNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> commNoList = new ArrayList<>();
		String sql = prop.getProperty("selectAllCommNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String commNo = rset.getString(1);
				
				commNoList.add(commNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return commNoList;
	}
	


}
