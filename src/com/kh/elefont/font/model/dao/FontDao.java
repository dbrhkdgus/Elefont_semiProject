package com.kh.elefont.font.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.member.model.vo.Member;
import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.rollback;


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

}
