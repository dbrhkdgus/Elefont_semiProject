package com.kh.elefont.common.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.dao.CommunityDao;

public class AttachmentDao {
	
	private Properties prop = new Properties();
	
	public AttachmentDao() {
		
		String filepath = CommunityDao.class.getResource("/attachment/attach-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public int insertAttachment(Connection conn, Attachment attach) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertAttachment"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, attach.getMemberNo());
			pstmt.setString(2, attach.getCommNo());
			pstmt.setString(3, attach.getOriginalFilename());
			pstmt.setString(4, attach.getRenamedFilename());
			
			
			
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

}
