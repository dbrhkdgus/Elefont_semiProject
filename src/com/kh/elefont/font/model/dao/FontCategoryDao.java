package com.kh.elefont.font.model.dao;
import static com.kh.elefont.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.elefont.font.model.vo.FontCategory;
public class FontCategoryDao {
	private Properties prop = new Properties();

	public FontCategoryDao() {
		String filepath = FontDao.class.getResource("/font/font-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertFontCategory(Connection conn, String fontNo, FontCategory fontCategory) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertFontCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fontCategory.getCategoryCode());
			pstmt.setString(2, fontNo);
			pstmt.setDate(3, fontCategory.getCategoryReleaseYear());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	

}
