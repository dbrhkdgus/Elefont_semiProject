package com.kh.elefont.community.model.service;

import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.elefont.community.model.dao.CommunityDao;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.member.model.vo.Member;


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

}
