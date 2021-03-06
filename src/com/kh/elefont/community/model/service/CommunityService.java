package com.kh.elefont.community.model.service;

import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.dao.CommunityDao;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.community.model.vo.DeletedCommunity;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.like_cart.model.vo.CommLike;



public class CommunityService {
	CommunityDao communityDao = new CommunityDao();
	
	public int enrollBoard(Community community) {
		
		 Connection conn = getConnection();
	        int result = 0;
	        
	        try {
	        	result = communityDao.enrollBoard(conn, community);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return result;
	}

	public String selectLastCommNo() {
		 Connection conn = getConnection();
	        String LastNo = "";
	        
	        try {
	        	LastNo = communityDao.selectLastCommNo(conn);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return LastNo;
	}

	public List<Community> selectAllCommunityList() {
		 Connection conn = getConnection();
		 List<Community> communityList = new ArrayList<>();
	        
	        try {
	        	communityList = communityDao.selectAllCommunityList(conn);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return communityList;
	}

	public int countTotalCommunityByWriter(String memberNo) {
		 Connection conn = getConnection();
		 int totalCommunityByWriter = 0;
	        
	        try {
	        	totalCommunityByWriter = communityDao.countTotalCommunityByWriter(conn, memberNo);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return totalCommunityByWriter;
	}


	public Community selectOneCommunity(String commNo) {
		Connection conn = getConnection();
		Community community = communityDao.selectOneCommunity(conn, commNo);
		close(conn);
		return community;
  }
  
	public List<Community> selectCommunityListByFontNo(String fontNo) {
		 Connection conn = getConnection();
		 List<Community> communityList = new ArrayList<>();
	        
	        try {
	        	communityList = communityDao.selectCommunityListByFontNo(conn, fontNo);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return communityList;
	}

	public int updateCommunityViewCount(String commNo) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = communityDao.updateCommunityViewCount(conn, commNo);

			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}

	public int deleteCommunity(String commNo) {
		Connection conn = getConnection();
        int result = 0;
        try {
            result = communityDao.deleteCommunity(conn, commNo);
//            if(result == 0)
//                throw new IllegalArgumentException("?????? ???????????? ???????????? ????????????. : " + commNo);
            commit(conn);
        } catch(Exception e) {
            rollback(conn);
            throw e; //controller??? ??????????????? ????????? ??? ????????? ??????.
        } finally {
            close(conn);
        }
        return result;
    }


	public int updateCommunity(Community community) {

		Connection conn = getConnection();
		int result = 0;
		try {
			// 1.????????? ?????? update board???
			result = communityDao.updateCommunity(conn, community);
			
			// 2.??????????????? ?????? ??????, insert into attachment??? ??????
//			Attachment attach = board.getAttach();
//			if(attach != null) {
//				result = boardDao.insertAttachment(conn, attach);
//			}
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int selectCommLike(Map<String, Object> param) {
		Connection conn = getConnection();
		int result = 0;
		int likeValid = 0;
		
		try {
			result = communityDao.selectCommLike(conn, param);
			
			if(result == 1) {
				//????????? ????????? ?????? ??????
				//like_comm ??????????????? ?????? ??? ??????
				result = communityDao.deleteCommLike(conn, param);
				
			}
			else if(result == 0) {
				//????????? ????????? ?????? ??????
				likeValid = 1;
				result = communityDao.insertCommLike(conn, param);
			}
			
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
			
		}finally {
			close(conn);
		}
		
		return likeValid;
	}

	public int countCommLike(String commNo) {
		Connection conn = getConnection();
		int likeCnt = 0;
		
		likeCnt = communityDao.countCommLike(conn, commNo);
		
		close(conn);
		
		return likeCnt;
	}

	public int updateCommLike(Map<String, Object> map) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = communityDao.updateCommLike(conn, map);
			
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
			
		}finally {
			close(conn);
		}
		return result;
	}

	public List<String> selectAllLikedComm(String memberNo) {
		Connection conn = getConnection();
		List<String> commLikeList = communityDao.selectAllLikedComm(conn, memberNo);
		
		close(conn);
		
		return commLikeList;
	}




	public List<Community> selectAllCommListByMemberNo(String memberNo) {
		Connection conn = getConnection();
		List<Community> communityList = communityDao.selectAllCommListByMemberNo(conn, memberNo);
		
		close(conn);
		
		return communityList;
	}
	
	public List<CommLike> selectAllLikedCommList(String memberNo) {

		Connection conn = getConnection();
		List<CommLike> commLikeList = communityDao.selectAllLikedCommList(conn, memberNo);
		
		close(conn);
		
		return commLikeList;
	}

	public List<Community> selectAllCommunityListByLikeCount() {
		Connection conn = getConnection();
		List<Community> communityList = communityDao.selectAllCommunityListByLikeCount(conn);
		
		close(conn);
		
		return communityList;
	}

	public List<Community> findCommListByMap(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Community> list = communityDao.findCommListByMap(conn, param);
		close(conn);
		return list;

	}

	public List<Community> selectAllLikedCommunity(String memberNo) {
		Connection conn = getConnection();
		List<Community> likeCommunityList = communityDao.selectAllLikedCommunity(conn, memberNo);
		
		close(conn);
		return likeCommunityList;
	}

	public List<Community> selectAllCommunityListByLikeCountThree() {
		Connection conn = getConnection();
		List<Community> communityList = communityDao.selectAllCommunityListByLikeCountThree(conn);
		
		close(conn);
		
		return communityList;
	}

	public List<DeletedCommunity> selectAllDeletedCommList() {
		Connection conn = getConnection();
		List<DeletedCommunity> deletedCommList = communityDao.selectAllDeletedCommList(conn);
		close(conn);
		return deletedCommList;
	}

	public List<String> selectAllCommNo() {
		Connection conn = getConnection();
		List<String> commNoList = communityDao.selectAllCommNo(conn);
		
		close(conn);
		
		return commNoList;
	}	

}
