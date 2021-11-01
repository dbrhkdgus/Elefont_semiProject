package com.kh.elefont.coupon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.elefont.coupon.model.service.CouponService;
import com.kh.elefont.coupon.model.vo.Coupon;

/**
 * Servlet implementation class CouponEnrollServlet
 */
@WebServlet("/coupon/couponEnroll")
public class CouponEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CouponService couponService = new CouponService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
		Coupon coupon;
		String couponType = request.getParameter("couponType");
		int couponPAmount = 0;
		double couponDiscountRate = 0;
		String _couponExpired = request.getParameter("couponExpired");
		System.out.println(_couponExpired);
		int couponExpired = Integer.parseInt(_couponExpired);
		String memberNo = request.getParameter("memberNo");
		int couponCnt = Integer.parseInt(request.getParameter("couponCnt"));
		
		if("P".equals(couponType)) {
			couponPAmount = Integer.parseInt(request.getParameter("couponRate"));
			coupon = new Coupon(null, couponType, null, couponExpired, null, couponPAmount, 0, memberNo);
		}
		else {
			couponDiscountRate = Double.parseDouble(request.getParameter("couponRate"));
			coupon = new Coupon(null, couponType, null, couponExpired, null, 0, couponDiscountRate, memberNo);
		}
		
		//2. 업무 로직
		// 쿠폰 등록 후 등록한 쿠폰 정보를 가져와 뷰단에 뿌려주어야 함
		
		List<String> couponList = couponService.insertCoupon(coupon, couponCnt);
		
		//3. 응답 처리
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(couponList, response.getWriter());
	}

}
