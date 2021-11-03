package com.kh.elefont.coupon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.coupon.model.service.CouponService;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class RedeemCoupon
 */
@WebServlet("/coupon/redeemCoupon")
public class RedeemCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CouponService couponService = new CouponService();
	MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String memberNo = request.getParameter("memberNoToReg");
		String memberId = request.getParameter("memberIdToReg");
		String couponType = request.getParameter("couponType");
		System.out.println("쿠폰 redeem 위한 memberNo : " + memberNo);
		System.out.println("쿠폰 redeem 위한 memberId : " + memberId);
		System.out.println("비동기로 쿠폰 있는 지 확인하기 위한 couponType : " + couponType);
		
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
		System.out.println("couponNo " + couponNo);
		
		if("P".equals(couponType)) {
			int couponPrice = couponService.selectCouponPrice(couponNo);
			System.out.println("이 쿠폰의 충전 금액은 : " + couponPrice);
			
			int result = memberService.updateMemberPoint(couponPrice,memberNo);
			System.out.println("포인트 업데이트 잘 했나요?");
			
			int result2 = couponService.deleteUsedCoupon(couponNo);
			System.out.println("쿠폰 삭제 잘 했나요?" + result2);
			
			Member member = memberService.selectOneMember(memberId);
			session.setAttribute("loginMember", member);			
		}else {
			int result = couponService.updateCouponByMemberNo(couponNo, memberNo);
			System.out.println("할인 쿠폰 잘 등록되었나요? : " + result);
		}

		//뷰단처리
		String location = request.getContextPath()+"/member/memberDetail";
		response.sendRedirect(location);
		
		
	}

}
