package com.kh.elefont.font.model.service;

import static com.kh.elefont.common.JdbcTemplate.*;

import java.sql.Connection;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.font.model.dao.FontDao;
import com.kh.elefont.font.model.vo.Font;

public class FontService {
	private FontDao fontDao = new FontDao();

	public int insertFont(Font font) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = fontDao.insertFont(conn, font);
			System.out.println("result@service = " + result);
			
			String fontNo = fontDao.selectLastFontNo(conn); 
			System.out.println("fontNo@service = "+fontNo);
			
			font.setFontNo(fontNo);
			
			//attachment테이블 행 추가
			Attachment attach = font.getAttach();
			if(attach != null) {
				attach.setFontNo(fontNo);
				result = fontDao.insertAttachment(conn, attach);
			}
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
