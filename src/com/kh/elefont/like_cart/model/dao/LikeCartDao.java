package com.kh.elefont.like_cart.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.elefont.like_cart.model.vo.MemberCart;
import com.kh.elefont.like_cart.model.vo.MemberCartView;

public class LikeCartDao {
	
	private Properties prop = new Properties();
	public LikeCartDao() {
		  
        String filepath = LikeCartDao.class.getResource("/likeCart/likeCart-query.properties").getPath();

        try {
            prop.load(new FileReader(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public int insertMemberCart(Connection conn, String memberNo, String cartNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMemberCart");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			pstmt.setString(2, cartNo);
			
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteCart(Connection conn, String cartNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteCart");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartNo);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertCart(Connection conn, String cartNo, String fontNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertCart");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartNo);
			pstmt.setString(2, fontNo);
			
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public MemberCart selectMemberCartByCartNo(Connection conn, String cartNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberCartByCartNo");
		MemberCart memberCart = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartNo);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				memberCart.setCartNo(rset.getString("cart_no"));
				memberCart.setMemberNo(rset.getString("member_no"));
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberCart;
	}

	public int deleteMemberCart(Connection conn, String cartNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteMemberCart");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartNo);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<MemberCart> selectAllMemberCartByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllMemberCartByMemberNo");
		List<MemberCart> memberCartList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MemberCart memberCart = new MemberCart();
				
				memberCart.setCartNo(rset.getString("cart_no"));
				memberCart.setMemberNo(rset.getString("member_no"));
				
				memberCartList.add(memberCart);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberCartList;
	}

	public List<MemberCartView> selectAllMemberCartViewByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllMemberCartViewByMemberNo");
		List<MemberCartView> memberCartViewList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MemberCartView memberCartView = new MemberCartView();
				
				memberCartView.setMemberNo(rset.getString("member_no"));
				memberCartView.setCartNo(rset.getString("cart_no"));
				memberCartView.setFontName(rset.getString("font_name"));
				memberCartView.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				memberCartView.setFontNo(rset.getString("font_no"));
				memberCartView.setFontPrice(rset.getInt("font_price"));
				
				
				memberCartViewList.add(memberCartView);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberCartViewList;
	}

	public List<MemberCartView> selectMemberCartList(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberCartList");
		List<MemberCartView> memberCartList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MemberCartView memberCartView = new MemberCartView();
				
				memberCartView.setMemberNo(rset.getString("member_no"));
				memberCartView.setCartNo(rset.getString("cart_no"));
				memberCartView.setFontName(rset.getString("font_name"));
				memberCartView.setFontDiscountRate(rset.getDouble("font_discount_rate"));
				memberCartView.setFontNo(rset.getString("font_no"));
				memberCartView.setFontPrice(rset.getInt("font_price"));
				memberCartView.setCartRegDate(rset.getDate("cart_regdate"));
				
				
				memberCartList.add(memberCartList);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberCartList;
	}

}
