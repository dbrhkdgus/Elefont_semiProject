package com.kh.elefont.font.model.service;
import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;

import com.kh.elefont.font.model.dao.FontCopyrightDao;
import com.kh.elefont.font.model.vo.FontCopyright;
public class FontCopyrightService {
private FontCopyrightDao fontCopyrightDao = new FontCopyrightDao();

	public int insertFontCopyright(String fontNo, FontCopyright fontCopyright) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = fontCopyrightDao.insertFontCopyright(conn, fontNo, fontCopyright);
			
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
