package com.kh.elefont.font.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.font.model.vo.FontExt;


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
	
	public int insertFont(Connection conn, Font font) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertFont");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, font.getFontName());
			pstmt.setString(2, font.getFontUrl());
			pstmt.setDouble(3, font.getFontPrice());
			pstmt.setString(4, font.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectLastFontNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastFontNo");
		String fontNo = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				fontNo = rset.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachmentFromFont");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attach.getMemberNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			pstmt.setString(4, attach.getFontNo());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
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

	public List<Font> selectAllFont(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllFont");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval") == null? " ": rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
				
				fontList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontList;
	}

	public int updateFont(Connection conn, Font[] fontArr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateFont");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < fontArr.length; i++) {
				pstmt.setString(1, fontArr[i].getFontApproval());
				pstmt.setDouble(2, fontArr[i].getFontPrice());
				pstmt.setDouble(3, fontArr[i].getFontDiscountRate());
				pstmt.setString(4, fontArr[i].getFontNo());
				
				result = pstmt.executeUpdate();
				if(result < 0) break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public Attachment selectOneFontAttachmentByFontNo(Connection conn, String fontNo) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneFontAttachmentByFontNo");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, fontNo);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				attach = new Attachment();
				attach.setAttNo(rset.getInt("ATT_NO"));
				attach.setMemberNo(rset.getString("MEMBER_NO"));
				attach.setCommNo(rset.getString("COMM_NO"));
				attach.setFontNo(fontNo);
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public List<Font> selectFontByMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> list = new ArrayList<>();
		String sql = prop.getProperty("selectFontByMemberId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
				
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval") == null? " ": rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
				
				list.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateFontAuditCheck(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		int result  = 0;
		String sql = prop.getProperty("updateFontAuditCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("fontApproval"));
			pstmt.setString(2, (String)param.get("fontNo"));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Font selectOneFontByFontNo(Connection conn, String fontNo) {
		Font font = new Font();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneFontByFontNo");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, fontNo);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getInt("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontLikeCount(rset.getInt("font_like_count"));
				font.setFontViewCount(rset.getInt("font_view_count"));
				font.setFontPurchasedCount(rset.getInt("font_purchased_count"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return font;
	}

	public int updateFontViewCount(Connection conn, String fontNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateFontViewCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, fontNo);
			
				
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Font> selectAllApprovedFontOrderByDate(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllApprovedFontOrderByDate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval") == null? " ": rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
				font.setFontLikeCount(rset.getInt("font_like_count"));
				
				fontList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontList;
	}

	public int deleteFontAudit(Connection conn, String fontNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteFontAudit");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fontNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Font selectOneFontByFontnameNFontUrl(Connection conn, String fontName, String fontUrl) {
		Font font = new Font();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneFontByFontnameNFontUrl");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, fontName);
			pstmt.setString(2, fontUrl);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getInt("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontLikeCount(rset.getInt("font_like_count"));
				font.setFontViewCount(rset.getInt("font_view_count"));
				font.setFontPurchasedCount(rset.getInt("font_purchased_count"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return font;
	}

	public int selectFontLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectFontLike");
		int likeValid = 0; //회원이 좋아요를 눌렀다 => likeValid = 1/ 좋아요를 안 눌렀다 => likeValid = 0
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("fontNo"));
			pstmt.setString(2, (String)param.get("memberNo"));
			
			rset = pstmt.executeQuery(); 
			if(!rset.next()) likeValid = 0;
			else likeValid = 1;
			System.out.println("selectFontLike@dao = " + likeValid);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return likeValid;
	}

	public int deleteFontLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteFontLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("fontNo"));
			pstmt.setString(2, (String)param.get("memberNo"));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertFontLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertFontLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("memberNo"));
			pstmt.setString(2, (String)param.get("fontNo"));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int countFontLike(Connection conn, String fontNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("countFontLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fontNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				result = rset.getInt(1); // rset.getInt("count(*)");
				System.out.println("likeCnt@dao = " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int updateFontLike(Connection conn, Map<String, Object> map) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateFontLike");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)map.get("likeCnt"));
			pstmt.setString(2, (String)map.get("fontNo"));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<String> selectAllLikedFont(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> likeList = new ArrayList<>();
		String sql = prop.getProperty("selectAllLikedFont");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String fontNo = rset.getString("font_no");
				
				likeList.add(fontNo);
			}
			System.out.println(likeList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return likeList;
	}

	public List<Font> selectAllLikedFontByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontLikeList = new ArrayList<>();
		String sql = prop.getProperty("selectAllLikedFont");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				
				fontLikeList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return fontLikeList;
	}

	public List<Font> selectSearchFont(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontList = new ArrayList<>();
		String sql = "";
		String searchType = (String)param.get("searchType");
//		String searchKeyword = (String)param.get("searchKeyword");
		System.out.println("param.get(\"searchKeyword\")@Dao : " + param.get("searchKeyword"));
		
		switch(searchType) {
		case "font-no" : 
			sql = prop.getProperty("selectSearchFontByFontNo");
			break;
		case "font-name" : 
			sql = prop.getProperty("selectSearchFontByFontName");
			break;
		case "font-seller" : 
			sql = prop.getProperty("selectSearchFontByFontSeller");
			break;
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+param.get("searchKeyword")+"%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
	
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontApproval(rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
				font.setFontLikeCount(rset.getInt("font_like_count"));
				fontList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontList;
	}

	public List<Font> selectAllPurchasedFontByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontPurchasedList = new ArrayList<>();
		String sql = prop.getProperty("selectAllPurchasedFontByMemberNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FontExt font = new FontExt();
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setMemberOrderDate(rset.getDate("member_order_date"));
				font.setMemberOrderNo(rset.getString("order_no"));
				
				fontPurchasedList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontPurchasedList;
	}

	public List<String> selectAllFontName(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> fontNameList = new ArrayList<>();
		String sql = prop.getProperty("selectAllFontName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				fontNameList.add(rset.getString("font_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontNameList;
	}

	public List<Font> selectAllApprovedFontOrderByPopular(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllApprovedFontOrderByPopular");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval") == null? " ": rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
				font.setFontLikeCount(rset.getInt("font_like_count"));
				
				fontList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontList;
	}

	public List<Font> selectAllApprovedFontOrderByView(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllApprovedFontOrderByView");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval") == null? " ": rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
				font.setFontLikeCount(rset.getInt("font_like_count"));
				
				fontList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontList;
	}

	public List<Font> selectAllApprovedFontOrderByOrder(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllApprovedFontOrderByOrder");
		
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("sql @dao : " + sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval") == null? " ": rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
				font.setFontLikeCount(rset.getInt("font_like_count"));
				
				fontList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontList;
	}

	public List<Font> selectAllApproveByCategory(Connection conn, List<String> categoryList) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Font> fontList = new ArrayList<>();
		
		String sql = "select * from view_font_font_category where " ; 
		System.out.println("categorylist @dao" + categoryList);
		if(categoryList.contains("S")) {
			if(categoryList.indexOf("S")!= 0) {
				sql += "or ";
			}
			sql += "category_code like '%S%'";		
		}
		if(categoryList.contains("G")) {
			if(categoryList.indexOf("G")!= 0) {
				sql += "or ";
			}
			sql += "category_code like '%G%'";
		}
		if(categoryList.contains("H")) {
			if(categoryList.indexOf("H")!= 0) {
				sql += "or ";
			}
			sql += "category_code like '%H%'";
		}
		if(categoryList.contains("M")) {
			if(categoryList.indexOf("M")!= 0) {
				sql += "or ";
			}
			sql += "category_code like '%M%'";
		}
		
		System.out.println("sql @Dao :" + sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Font font = new Font();
				font.setFontNo(rset.getString("font_no"));
				font.setFontName(rset.getString("font_name"));
				font.setFontUrl(rset.getString("font_url"));
				font.setFontPrice(rset.getDouble("font_price"));
				font.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				font.setFontRegDate(rset.getDate("font_reg_date"));
				font.setFontApproval(rset.getString("font_approval") == null? " ": rset.getString("font_approval"));
				font.setMemberId(rset.getString("member_id"));
				font.setFontLikeCount(rset.getInt("font_like_count"));
				
				fontList.add(font);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fontList;
	}
}

	

	


