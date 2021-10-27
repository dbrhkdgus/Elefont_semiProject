package com.kh.elefont.common.model.service;

import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;
import static com.kh.mvc.common.JdbcTemplate.close;
import static com.kh.mvc.common.JdbcTemplate.commit;
import static com.kh.mvc.common.JdbcTemplate.getConnection;
import static com.kh.mvc.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.elefont.common.model.dao.AttachmentDao;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.member.model.vo.Member;

public class AttachmentService {
	AttachmentDao attachmentDao = new AttachmentDao();
	public int insertAttachment(Attachment attach) {
		 Connection conn = getConnection();
	        int result = 0;
	        
	        try {
	        	result = attachmentDao.insertAttachment(conn, attach);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return result;
	}
	public List<Attachment> selectAllCommAttachmentList() {
		Connection conn = getConnection();
		 List<Attachment> attachmentList = new ArrayList<>();
	        
	        try {
	        	attachmentList = attachmentDao.selectAllCommAttachmentList(conn);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return attachmentList;
	}
	public List<Attachment> selectAllCommAttachmentListByMemberNo(String memberNo) {
		Connection conn = getConnection();
		 List<Attachment> attachmentList = new ArrayList<>();
	        
	        try {
	        	attachmentList = attachmentDao.selectAllAttachmentListByMemberNo(conn, memberNo);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return attachmentList;
	}

	
	public Attachment selectOneAttachment(String commNo) {
		Connection conn = getConnection();
		Attachment attachment = attachmentDao.selectOneAttachment(conn, commNo);
		close(conn);
		return attachment;
	}


	public List<Attachment> selectAllFontAttachmentList() {
		 Connection conn = getConnection();
		 List<Attachment> fontAttchmentList = new ArrayList<>();
	        
	        try {
	        	fontAttchmentList = attachmentDao.selectAllFontAttachmentList(conn);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return fontAttchmentList;
	}
	
	public int deleteAttachmentByCommNo(String commNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = attachmentDao.deleteAttachmentByCommNo(conn, commNo);
			if(result == 0)
				throw new IllegalArgumentException("해당 첨부파일이 존재하지 않습니다. : " + commNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; //controller가 예외처리를 결정할 수 있도록 넘김.
		} finally {
			close(conn);
		}
		return result;
	}
	
	


}
