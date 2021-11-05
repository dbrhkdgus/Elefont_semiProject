package com.kh.elefont.font.model.service;

import static com.kh.elefont.common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.font.model.dao.FontDao;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.like_cart.model.vo.LikeFont;

import com.kh.elefont.font.model.vo.FontCategory;


public class FontService {
	private FontDao fontDao = new FontDao();

	public int insertFont(Font font) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = fontDao.insertFont(conn, font);
			
			
			String fontNo = fontDao.selectLastFontNo(conn); 
			
			
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
			
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}
	public Attachment selectOneFontAttachmentByFontNo(String fontNo) {
		Connection conn = getConnection();
		Attachment attach = fontDao.selectOneFontAttachmentByFontNo(conn, fontNo);
		
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
	public Font selectOneFontByFontNo(String fontNo) {
		Connection conn = getConnection();
		Font font = fontDao.selectOneFontByFontNo(conn, fontNo);
		
		close(conn);
		return font;
	}
	public int updateFontViewCount(String fontNo) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = fontDao.updateFontViewCount(conn, fontNo);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}
	public List<Font> selectAllApprovedFontOrderByDate() {
		Connection conn = getConnection();
		List<Font> fontList = fontDao.selectAllApprovedFontOrderByDate(conn);
		close(conn);
		return fontList;
	}
	public int deleteFontAudit(String fontNo) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = fontDao.deleteFontAudit(conn, fontNo);
			
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}
		return result;
	}
	public Font selectOneFontByFontnameNFontUrl(String fontName, String fontUrl) {
		Connection conn = getConnection();
		Font font = fontDao.selectOneFontByFontnameNFontUrl(conn, fontName, fontUrl);
		
		close(conn);
		return font;
	}
	public int selectFontLike(Map<String, Object> param) {
		Connection conn = getConnection();
		int likeValid = 0;
		try {
			likeValid = fontDao.selectFontLike(conn, param);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return likeValid;
	}
	
	public int countFontLike(String fontNo) {
		Connection conn = getConnection();
		
		int result = fontDao.countFontLike(conn, fontNo);
		
		close(conn);
		
		return result;
	}
	public int updateFontLike(Map<String, Object> map) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = fontDao.updateFontLike(conn, map);
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}
		
		
		return result;
	}
	public List<String> selectAllLikedFont(String memberNo) {
		Connection conn = getConnection();
		List<String> likeList = fontDao.selectAllLikedFont(conn, memberNo);
		
		close(conn);
		return likeList;
	}
	public List<Font> selectAllLikedFontByMemberNo(String memberNo) {
		Connection conn = getConnection();
		
		List<Font> likeFontList = fontDao.selectAllLikedFontByMemberNo(conn, memberNo);
		
		close(conn);
		return likeFontList;
	}
	
	public List<Font> selectSerchFont(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Font> fontList = fontDao.selectSearchFont(conn,param);
		
		close(conn);
		return fontList;
	}
	public int deleteFontLike(Map<String, Object> param) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = fontDao.deleteFontLike(conn,param);
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}
	public int insertFontLike(Map<String, Object> param) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = fontDao.insertFontLike(conn,param);
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}
	public List<Font> selectAllPurchasedFontByMemberNo(String memberNo) {
		Connection conn = getConnection();
		List<Font> fontPurchasedList = fontDao.selectAllPurchasedFontByMemberNo(conn, memberNo);
		
		close(conn);
		return fontPurchasedList;
	}
	public List<String> selectAllFontName() {
		Connection conn = getConnection();
		List<String> fontNameList = fontDao.selectAllFontName(conn);
		
		close(conn);
		return fontNameList;
	}
	public List<Font> selectFontListByFontName(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Font> fontList = fontDao.selectSearchFont(conn,param);
		
		close(conn);
		return fontList;
	}
	public List<Font> selectAllApprovedFontOrderByPopular() {
		Connection conn = getConnection();
		List<Font> fontList = fontDao.selectAllApprovedFontOrderByPopular(conn);
		close(conn);
		return fontList;
	}
	public List<Font> selectAllApprovedFontOrderByView() {
		Connection conn = getConnection();
		List<Font> fontList = fontDao.selectAllApprovedFontOrderByView(conn);
		close(conn);
		return fontList;
	}
	public List<Font> selectAllApprovedFontOrderByOrder() {
		Connection conn = getConnection();
		List<Font> fontList = fontDao.selectAllApprovedFontOrderByOrder(conn);
		close(conn);
		return fontList;
	}
	public List<Font> selectAllApproveByCategory(List<String> categoryList) {
		Connection conn = getConnection();
		List<Font> fontList = fontDao.selectAllApproveByCategory(conn ,categoryList);

		close(conn);
		return fontList;
	}
	public List<FontCategory> selectAllFontCategory() {
		Connection conn = getConnection();
		List<FontCategory> categoryList = fontDao.selectAllFontCategory(conn);
		close(conn);
		return categoryList;
	}
	public int updateFontPurchaseCount(Font font) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = fontDao.updateFontPurchaseCount(conn,font);
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}
	
	
	public List<LikeFont> selectAllLikeFontListByMemberNo(String memberNo) {
		Connection conn = getConnection();
		List<LikeFont> likefontList = fontDao.selectAllLikeFontListByMemberNo(conn,memberNo);
		close(conn);
		return likefontList;
	}
	public String selectFontNoByCartNo(String cartNo) {
		Connection conn = getConnection();
		String cartNom = fontDao.selectFontNoByCartNo(conn,cartNo);
		close(conn);
		return cartNom;
	}
	public String selectFontNameByFontNO(String fontNo) {
		Connection conn = getConnection();
		String cartName = fontDao.selectFontNameByFontNO(conn,fontNo);
		close(conn);
		return cartName;
	}
	public List<Font> selectShopFontByFontName(String fontName) {
		Connection conn = getConnection();
		List<Font> fontList = fontDao.selectShopFontByFontName(conn,fontName);
		
		close(conn);
		return fontList;
	}
	public List<String> selectAllFontNo() {
		Connection conn = getConnection();
		List<String> fontNoList = fontDao.selectAllFontNo(conn);
		
		close(conn);
		return fontNoList;
	}
	public List<Font> selectFontLikeCnt(String memberId) {
		Connection conn = getConnection();
		List<Font> checkedListByLikeCnt = fontDao.selectFontLikeCnt(conn, memberId);
		close(conn);
		return checkedListByLikeCnt;
	}
	public List<Font> selectFontPurchasedCnt(String memberId) {
		Connection conn = getConnection();
		List<Font> checkedListByPurchasedCnt = fontDao.selectFontPurchasedCnt(conn, memberId);
		close(conn);
		return checkedListByPurchasedCnt;
	}



}
