package com.kh.elefont.community.model.service;

import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

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

}
