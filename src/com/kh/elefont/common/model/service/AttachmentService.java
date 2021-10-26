package com.kh.elefont.common.model.service;

import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.elefont.common.model.dao.AttachmentDao;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.vo.Community;

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

}
