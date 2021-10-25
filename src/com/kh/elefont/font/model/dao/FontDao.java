package com.kh.elefont.font.model.dao;

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
import com.kh.elefont.font.model.vo.Font;


public class FontDao {
	private Properties prop = new Properties();

	public FontDao() {
		String filepath = FontDao.class.getResource("/font/font-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertFont(Connection conn, Font font) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertFont");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, font.getFontName());
			pstmt.setString(2, font.getFontUrl());
			pstmt.setDouble(3, font.getFontPrice());
			pstmt.setString(4, font.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectLastFontNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastFontNo");
		String fontNo = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				fontNo = rset.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachmentFromFont");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attach.getMemberNo());
			pstmt.setString(2, attach.getFontNo());
			pstmt.setString(3, attach.getOriginalFilename());
			pstmt.setString(4, attach.getRenamedFilename());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public String selectFontNoByFontName(Connection conn, String fontName) {
		String sql = prop.getProperty("selectFontNoByFontName");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String fontNo = null;
		
		try {
			// 1.PreparedStatment객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fontName);
			
			// 2.실행 & ResultSet객체 리턴
			rset = pstmt.executeQuery();
			
			// 3.ResultSet -> Member
			if(rset.next()) {
				fontNo = rset.getString("font_no");
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return fontNo;
	}

	public List<Font> selectAllFont(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllFont");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval"));
				
				fontList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontList;
	}
	

}
