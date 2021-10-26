package com.kh.elefont.font.model.service;

import static com.kh.elefont.common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

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
	public String selectFontNoByFontName(String font) {
		Connection conn = getConnection();
		String fontNo = fontDao.selectFontNoByFontName(conn, font);
		close(conn);

		return fontNo;
	}
	public List<Font> selectAllFont() {
		Connection conn = getConnection();
		List<Font> fontList = fontDao.selectAllFont(conn);
		close(conn);
		return fontList;
	}
	public int updateFont(Font[] fontArr) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = fontDao.updateFont(conn, fontArr);
			System.out.println("result@dao = "+result);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}
	public Attachment selectOneAttachment(String fontNo) {
		Connection conn = getConnection();
		Attachment attach = fontDao.selectOneAttachment(conn, fontNo);
		System.out.println("제가 attach 객체를 잘 받아왔나요?! " + attach);
		close(conn);
		return attach;
	}
	public List<Font> selectFontByMemberId(String memberId) {
		Connection conn = getConnection();
		List<Font> list = null;
		try {
			list = fontDao.selectFontByMemberId(conn, memberId);
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}
		return list;
	}
	public int updateFontAuditCheck(Map<String, Object> param) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = fontDao.updateFontAuditCheck(conn, param);
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
