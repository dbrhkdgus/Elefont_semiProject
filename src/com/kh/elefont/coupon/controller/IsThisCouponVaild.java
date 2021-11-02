package com.kh.elefont.coupon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.coupon.model.service.CouponService;
import com.kh.elefont.coupon.model.vo.Coupon;

/**
 * Servlet implementation class IsThisCouponVaild
 */
@WebServlet("/coupon/isThisCouponVaild")
public class IsThisCouponVaild extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CouponService couponService = new CouponService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String memberNo = request.getParameter("memberNoToReg");
		String memberId = request.getParameter("memberIdToReg");
		System.out.println("비동기로 쿠폰 있는 지 확인하기 위한 memberNo : " + memberNo);
		System.out.println("비동기로 쿠폰 있는 지 확인하기 위한 memberId : " + memberId);
		
		String couponNo1 = request.getParameter("coupon-no1");
		String couponNo2 = request.getParameter("coupon-no2");
		String couponNo3 = request.getParameter("coupon-no3");
		
		System.out.println("couponNo1 : " + couponNo1);
		System.out.println("couponNo2 : " + couponNo2);
		System.out.println("couponNo3 : " + couponNo3);

		StringBuffer sb = new StringBuffer();
		sb.append(couponNo1);
		sb.append("-");
		sb.append(couponNo2);
		sb.append("-");
		sb.append(couponNo3);
		
		System.out.println("쿠폰 연결한 값 " + sb);
		
		String sbtoString = sb.toString();
		
		System.out.println("sbtoString : " + sbtoString);
		
		
		List<Coupon> couponList = couponService.selectAllCouponByMemberNo(memberNo);
		System.out.println("couponList " + couponList );
		
		for(Coupon coupon : couponList) {
			String couponNumber = coupon.getCouponNo();
			System.out.println("couponNumber는 " + couponNumber );

			if(sbtoString.equals(couponNumber)) {
				System.out.println(sbtoString + "이 쿠폰은 사용할 수 있는 쿠폰입니다");
			}
		}
		
	}

}
