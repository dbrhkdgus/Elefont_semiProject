package com.kh.elefont.rep.model.service;
import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.community.model.vo.DeletedCommunity;
import com.kh.elefont.rep.model.dao.RepDao;
import com.kh.elefont.rep.model.vo.DeletedRep;
import com.kh.elefont.rep.model.vo.Rep;

public class RepService {
	
	RepDao repDao = new RepDao();
	
	public int insertShopRep(Rep rep) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = repDao.insertShopRep(conn, rep);			
		
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}
		close(conn);
		return result;
		
	}

	public List<Rep> selectFontRepListByFontNo(String fontNo) {
		 Connection conn = getConnection();
		 List<Rep> repList = new ArrayList<>();
	        
	        try {
	        	repList = repDao.selectFontRepListByFontNo(conn, fontNo);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return repList;
		
	}

	public int updateRep(String repNo, String updateRepContent) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = repDao.updateRep(conn, repNo, updateRepContent);			
		
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteRep(String repNo) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = repDao.deleteRep(conn, repNo);			
		
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int insertCommunityRep(Rep rep) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = repDao.insertCommunityRep(conn, rep);			
		
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<Rep> selectAllCommunityRepListByCommNo(String commNo) {
		 Connection conn = getConnection();
		 List<Rep> repList = new ArrayList<>();
	        
	        try {
	        	repList = repDao.selectAllCommunityRepListByCommNo(conn, commNo);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return repList;
		
	}

	public List<DeletedRep> selectAllDeletedRepList() {
		Connection conn = getConnection();
		List<DeletedRep> deletedRepList = repDao.selectAllDeletedRepList(conn);
		close(conn);
		return deletedRepList;
	}

	public int deleteCommRep(String commNo) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = repDao.deleteCommRep(conn, commNo);			
		
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
