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
//			System.out.println("result@dao = "+result);
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
//                throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. : " + commNo);
            commit(conn);
        } catch(Exception e) {
            rollback(conn);
            throw e; //controller가 예외처리를 결정할 수 있도록 넘김.
        } finally {
            close(conn);
        }
        return result;
    }


	public int updateCommunity(Community community) {
		System.out.println("community:updateservice@ :" + community);
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1.게시글 수정 update board문
			result = communityDao.updateCommunity(conn, community);
			
			// 2.첨부파일이 있는 경우, insert into attachment문 실행
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
				//좋아요 이력이 있는 경우
				//like_comm 테이블에서 해당 행 삭제
				result = communityDao.deleteCommLike(conn, param);
				
			}
			else if(result == 0) {
				//좋아요 이력이 없는 경우
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
	
	public List<Community> selectAllLikedCommList(String memberNo) {
		Connection conn = getConnection();
		List<Community> commLikeList = communityDao.selectAllLikedCommList(conn, memberNo);
		
		close(conn);
		
		return commLikeList;
	}


}
