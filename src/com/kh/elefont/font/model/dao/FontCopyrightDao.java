package com.kh.elefont.font.model.dao;
import static com.kh.elefont.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.elefont.font.model.vo.FontCopyright;
public class FontCopyrightDao {
	private Properties prop = new Properties();

	public FontCopyrightDao() {
		String filepath = FontDao.class.getResource("/font/font-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertFontCopyright(Connection conn, String fontNo, FontCopyright fontCopyright) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertFontCopyright");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fontNo);
			pstmt.setString(2, fontCopyright.getFontPublisher());
			pstmt.setString(3, fontCopyright.getFontDesigner());
			pstmt.setString(4, fontCopyright.getFontRootUrl());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
