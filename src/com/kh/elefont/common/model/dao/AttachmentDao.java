package com.kh.elefont.common.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.dao.CommunityDao;
import com.kh.elefont.community.model.vo.Community;

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
	public List<Attachment> selectAllAttachmentList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> attachmentList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllAttachmentList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Attachment attachment = new Attachment();
				attachment.setAttNo(rset.getInt("att_no"));
				attachment.setMemberNo(rset.getString("member_no"));
				attachment.setCommNo(rset.getString("comm_no"));
				attachment.setFontNo(rset.getString("font_no"));
				attachment.setOriginalFilename(rset.getString("original_filename"));
				attachment.setRenamedFilename(rset.getString("renamed_filename"));
				attachment.setRegDate(rset.getDate("reg_date"));
				
				
				attachmentList.add(attachment);
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("attachmentListDao@" + attachmentList);
		return attachmentList;
	}
	public List<Attachment> selectAllAttachmentListByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> attachmentList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllAttachmentListByMemberNo");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Attachment attachment = new Attachment();
				attachment.setAttNo(rset.getInt("att_no"));
				attachment.setMemberNo(rset.getString("member_no"));
				attachment.setCommNo(rset.getString("comm_no"));
				attachment.setFontNo(rset.getString("font_no"));
				attachment.setOriginalFilename(rset.getString("original_filename"));
				attachment.setRenamedFilename(rset.getString("renamed_filename"));
				attachment.setRegDate(rset.getDate("reg_date"));
				
				
				attachmentList.add(attachment);
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attachmentList;
	}
	public Attachment selectOneAttachment(Connection conn, String commNo) {
		Attachment attachment = new Attachment();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOneAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, commNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				attachment.setAttNo(rset.getInt("att_no"));
				attachment.setMemberNo(rset.getString("member_no"));
				attachment.setCommNo(rset.getString("comm_no"));
				attachment.setFontNo(rset.getString("font_no"));
				attachment.setOriginalFilename(rset.getString("original_filename"));
				attachment.setRenamedFilename(rset.getString("renamed_filename"));
				attachment.setRegDate(rset.getDate("reg_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("attachmentDao@" + attachment);
		
		return attachment;
	}
	

}
