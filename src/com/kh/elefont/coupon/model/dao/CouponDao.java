package com.kh.elefont.coupon.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.elefont.coupon.model.vo.Coupon;
import static com.kh.elefont.common.JdbcTemplate.*;

public class CouponDao {
	
	private Properties prop = new Properties();
	
	public CouponDao() {
		String filepath = CouponDao.class.getResource("/coupon/coupon-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Coupon> selectAllCoupon(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Coupon> couponList = new ArrayList<>();
		String sql = prop.getProperty("selectAllCoupon");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Coupon coupon = new Coupon();
				
				coupon.setCouponNo(rset.getString("coupon_no"));
				coupon.setCouponType(rset.getString("coupon_type"));
				coupon.setCouponRegDate(rset.getDate("coupon_reg_date"));
				coupon.setCouponExpDate(rset.getDate("coupon_exp_date"));
				coupon.setCouponUsed(rset.getString("coupon_used"));
				coupon.setCouponPAmount(rset.getInt("coupon_p_amount"));
				coupon.setCouponDiscount(rset.getDouble("coupon_discount"));
				coupon.setMemberNo(rset.getString("member_no"));
				
				couponList.add(coupon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return couponList;
	}

	public int insertCoupon(Connection conn, Coupon coupon) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		//회원 번호를 입력했는지, 쿠폰 종류가 무엇인지에 따라 분기 처리
		if(!coupon.getMemberNo().isBlank()) {
			if(("P".equals(coupon.getCouponType())))
				sql = prop.getProperty("insertPointCouponToMember");
			else
				sql = prop.getProperty("insertDiscountCouponToMember");
		}
		else {
			if(("P".equals(coupon.getCouponType())))
				sql = prop.getProperty("insertPointCoupon");
			else
				sql = prop.getProperty("insertDiscountCoupon");
		}

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coupon.getCouponType());
			pstmt.setInt(2, coupon.getCouponExpired());
			
			if("P".equals(coupon.getCouponType()))
				pstmt.setInt(3, coupon.getCouponPAmount());
			else
				pstmt.setDouble(3, coupon.getCouponDiscount());
			
			if(!coupon.getMemberNo().isBlank())
				pstmt.setString(4, coupon.getMemberNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public String selectLastCouponNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String couponNo = "";
		String sql = prop.getProperty("selectLastCouponNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next())
				couponNo = rset.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return couponNo;
	}

	public List<Coupon> selectAllCouponByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Coupon> couponList = new ArrayList<>();
		String sql = prop.getProperty("selectAllCouponByMemberNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Coupon coupon = new Coupon();
				coupon.setCouponNo(rset.getString("coupon_no"));
				coupon.setCouponType(rset.getString("coupon_type"));
				coupon.setCouponRegDate(rset.getDate("coupon_reg_date"));
				coupon.setCouponExpDate(rset.getDate("coupon_exp_date"));
				if("P".equals(coupon.getCouponType()))
					coupon.setCouponPAmount(rset.getInt("coupon_p_amount"));
				else
					coupon.setCouponDiscount(rset.getDouble("coupon_discount"));
				coupon.setMemberNo(memberNo);
				
				couponList.add(coupon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return couponList;
	}

	public int selectCouponPrice(Connection conn, String couponNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int couponPrice = 0;
		String sql = prop.getProperty("selectCouponPrice");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, couponNo);
			
			rset =pstmt.executeQuery();
			if(rset.next()) {
				couponPrice = rset.getInt("coupon_p_amount");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return couponPrice;
	}

	public int deleteUsedCoupon(Connection conn, String couponNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteUsedCoupon");
		//deleteUsedCoupon = delete from coupon where coupon_no = ?
		
		try {	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, couponNo);
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Coupon selectOneCouponByCouponNo(Connection conn, String couponNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Coupon coupon = null;
		String sql = prop.getProperty("selectOneCouponByCouponNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, couponNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				coupon = new Coupon();
				coupon.setCouponNo(couponNo);
				coupon.setCouponType(rset.getString("coupon_type"));
				coupon.setCouponRegDate(rset.getDate("coupon_reg_date"));
				coupon.setCouponExpDate(rset.getDate("coupon_exp_date"));
				if("P".equals(coupon.getCouponType()))
					coupon.setCouponPAmount(rset.getInt("coupon_p_amount"));
				else
					coupon.setCouponDiscount(rset.getDouble("coupon_discount"));
				
				coupon.setMemberNo(rset.getString("member_no"));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return coupon;
	}

	public int updateCouponByMemberNo(Connection conn, String couponNo, String memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateCouponByMemberNo");
		//updateCouponByMemberNo = update coupon set member_no = ? where coupon_no = ?
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			pstmt.setString(2, couponNo);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public double selectCouponDiscountRate(Connection conn, String couponNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		double couponDiscountRate = 0;
		String sql = prop.getProperty("selectOneCouponByCouponNo");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, couponNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				couponDiscountRate = rset.getDouble("coupon_discount");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return couponDiscountRate;
	}

}
