package com.kh.elefont.coupon.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.elefont.coupon.model.service.CouponService;
import com.kh.elefont.coupon.model.vo.Coupon;

/**
 * Servlet implementation class IsThisCouponVaild
 */
@WebServlet("/coupon/isThisCouponVaild")
public class IsThisCouponVaildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CouponService couponService = new CouponService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String memberNo = request.getParameter("memberNoToReg");

		
		String couponNo1 = request.getParameter("coupon-no1");
		String couponNo2 = request.getParameter("coupon-no2");
		String couponNo3 = request.getParameter("coupon-no3");

		StringBuffer sb = new StringBuffer();
		sb.append(couponNo1);
		sb.append("-");
		sb.append(couponNo2);
		sb.append("-");
		sb.append(couponNo3);
		

		
		String couponNo = sb.toString();
		


		Coupon coupon = couponService.selectOneCouponByCouponNo(couponNo);
				
		if(coupon != null) {
			if(memberNo.equals(coupon.getMemberNo()) || coupon.getMemberNo() == null) {
				String couponNumber = coupon.getCouponNo();

				
				long expiredMilisecond = coupon.getCouponExpDate().getTime();
				long currentMilisecond = System.currentTimeMillis();
				
				long canWeUseTime = expiredMilisecond - currentMilisecond;

				
				if(canWeUseTime >0) { 
					//3. 응답 처리
					response.setContentType("application/json; charset=utf-8");
					new Gson().toJson(coupon, response.getWriter());					
				}else {
					
					String msg = "유효기간이 경과된 쿠폰입니다.";
					response.setContentType("application/json; charset=utf-8");
					new Gson().toJson(msg, response.getWriter());
				}
			}
		}
	}

}
