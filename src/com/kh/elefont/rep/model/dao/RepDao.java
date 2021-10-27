package com.kh.elefont.rep.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.elefont.font.model.dao.FontDao;
import com.kh.elefont.rep.model.vo.Rep;

public class RepDao {
	
	private Properties prop = new Properties();

	public RepDao() {
		String filepath = RepDao.class.getResource("/rep/rep-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertShopRep(Connection conn, Rep rep) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertShopRep");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rep.getRepWriter());
			pstmt.setString(2, rep.getRepContent());
			pstmt.setString(3, rep.getFontNo());
		
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
