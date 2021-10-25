package com.kh.elefont.common.model.service;

import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;

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

}
