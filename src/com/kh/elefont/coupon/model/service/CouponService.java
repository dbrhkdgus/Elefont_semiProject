package com.kh.elefont.coupon.model.service;

import static com.kh.elefont.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.elefont.coupon.model.dao.CouponDao;
import com.kh.elefont.coupon.model.vo.Coupon;

public class CouponService {
	
	private CouponDao couponDao = new CouponDao();

	public List<Coupon> selectAllCoupon() {
		Connection conn = getConnection();
		
		List<Coupon> couponList = couponDao.selectAllCoupon(conn);
		
		close(conn);
		return couponList;
	}

	public List<String> insertCoupon(Coupon coupon, int couponCnt) {
		Connection conn = getConnection();
		List<String> couponList = new ArrayList<>();
		
		try {
			for(int i = 0; i< couponCnt; i++) {
				String couponNo = "";
				int result = couponDao.insertCoupon(conn,coupon);
				System.out.println("result@couponService = " + result);
				
				couponNo = couponDao.selectLastCouponNo(conn);
				couponList.add(couponNo);
			}
			commit(conn);
		} catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		
		return couponList;
	}

}
