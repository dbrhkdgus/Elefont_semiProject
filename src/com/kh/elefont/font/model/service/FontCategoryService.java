package com.kh.elefont.font.model.service;
import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;

import com.kh.elefont.font.model.dao.FontCategoryDao;
import com.kh.elefont.font.model.vo.FontCategory;
public class FontCategoryService {
private FontCategoryDao fontCategoryDao = new FontCategoryDao();
	public int insertFontCategory(String fontNo, FontCategory fontCategory) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = fontCategoryDao.insertFontCategory(conn, fontNo, fontCategory);
			
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}
		return result;
	}

}
