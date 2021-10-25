package com.kh.elefont.font.model.service;

import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.getConnection;

import java.sql.Connection;

import com.kh.elefont.font.model.dao.FontDao;

public class FontService {
	FontDao fontDao = new FontDao();
	public String selectFontNoByFontName(String font) {
		Connection conn = getConnection();
		String fontNo = fontDao.selectFontNoByFontName(conn, font);
		close(conn);

		return fontNo;
	}

}
