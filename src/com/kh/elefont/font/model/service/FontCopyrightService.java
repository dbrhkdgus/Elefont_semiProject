package com.kh.elefont.font.model.service;
import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.elefont.faq.model.vo.Faq;
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

	public FontCopyright selectOneFontCopyrightByFontNo(String fontNo) {
		System.out.println("서비스단에 도착했나요?");
		Connection conn = getConnection();
		FontCopyright fontCopyright = fontCopyrightDao.selectOneFontCopyrightByFontNo(conn,fontNo);
		close(conn);
		
		return fontCopyright;
	}

}
