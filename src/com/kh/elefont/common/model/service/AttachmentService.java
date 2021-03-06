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
				throw new IllegalArgumentException("?????? ??????????????? ???????????? ????????????. : " + commNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; //controller??? ??????????????? ????????? ??? ????????? ??????.
		} finally {
			close(conn);
		}
		return result;
	}
	public int updateAttachment(Attachment attach) {
		 Connection conn = getConnection();
	        int result = 0;
	        
	        try {
	        	result = attachmentDao.updateAttachment(conn, attach);
	            
	            commit(conn);
	        }catch(Exception e) {
	            rollback(conn);
	            throw e;
	        }finally {
	            close(conn);
	        }
	        return result;
	}
	public List<String> selectAllAttachByFontNo(List<String> orderFonts) {
		Connection conn = getConnection();
		List<String> attachList = attachmentDao.selectAllAttachByFontNo(conn, orderFonts);
		
		close(conn);
		return attachList;
	}
	public Attachment selectProfileAttachment(String memberNo) {
		Connection conn = getConnection();
		Attachment attachment = attachmentDao.selectProfileAttachment(conn, memberNo);
		close(conn);
		return attachment;
	}
	public List<Attachment> selectAllprofileAttachmentList() {
		Connection conn = getConnection();
		List<Attachment> profileAttachmentList = new ArrayList<>();
		profileAttachmentList = attachmentDao.selectAllprofileAttachmentList(conn);
        close(conn);
        return profileAttachmentList;
	}
	public List<Attachment> selectAllAttachmentList() {
		Connection conn = getConnection();
		List<Attachment> allAttachmentList = new ArrayList<>();
		allAttachmentList = attachmentDao.selectAllAttachmentList(conn);
        close(conn);
        return allAttachmentList;
	}
	public Attachment selectOneAttachmentByFontNo(String fontNo) {
		Connection conn = getConnection();
		Attachment attachment = attachmentDao.selectOneAttachmentByFontNo(conn, fontNo);
		close(conn);
		return attachment;
	}
	
	
	


}
