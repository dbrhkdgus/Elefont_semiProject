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
				coupon.setCouponExpired(rset.getInt("coupon_expired"));
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
		String sql = 
				("P".equals(coupon.getCouponType()))?
						prop.getProperty("insertPointCoupon")
						: prop.getProperty("insertDiscountCoupon");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coupon.getCouponType());
			pstmt.setDate(2, coupon.getCouponRegDate());
			pstmt.setInt(3, coupon.getCouponExpired());
			if("P".equals(coupon.getCouponType()))
				pstmt.setInt(4, coupon.getCouponPAmount());
			else
				pstmt.setDouble(4, coupon.getCouponDiscount());
			
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

}
