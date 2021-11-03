package com.kh.elefont.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.coupon.model.service.CouponService;

/**
 * Servlet implementation class OrderCouponEnrollServlet
 */
@WebServlet("/order/couponEnroll")
public class OrderCouponEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CouponService couponService = new CouponService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberNo = request.getParameter("memberNoToReg");
		String memberId = request.getParameter("memberIdToReg");
		String couponType = request.getParameter("couponType");
		
		//화면에 띄어주기 위해서 가져온 값
		String fontName = request.getParameter("fontName");
		String fontNo = request.getParameter("font-no");
		String fontPrice = request.getParameter("font-price");
		String email = request.getParameter("purchase-email");
		System.out.println("fontName : " + fontName);
		System.out.println("fontNo : " + fontNo);
		System.out.println("fontPrice : " + fontPrice);
		System.out.println("email : " + email);
		
		double price = Double.parseDouble(fontPrice);
		
		System.out.println("price@servlet = " +price);
		
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
			
			int result = couponService.deleteUsedCoupon(couponNo);
			System.out.println("쿠폰 삭제 잘 했나요?" + result);
			double salePrice = price - (double)couponPrice;
			
			System.out.println("포인트쿠폰 적용한 가격 : " + salePrice);
			request.setAttribute("couponPrice", couponPrice);
			request.setAttribute("salePrice", salePrice);
			
		}else {
			
			double couponDiscountRate = couponService.selectCouponDiscountRate(couponNo);
			System.out.println("이 쿠폰의 할인율은? :  " + couponDiscountRate);
			
			int result = couponService.deleteUsedCoupon(couponNo);
			System.out.println("쿠폰 삭제 잘 했나요?" + result);
			double salePrice = price*(1-(couponDiscountRate*0.01));
			System.out.println("할인쿠폰 적용한 가격 : " + salePrice);

			request.setAttribute("couponDiscountRate", couponDiscountRate);
			request.setAttribute("salePrice", salePrice);
			
		}

		//3. 뷰단처리
		
//		String location = request.getHeader("referer");
//		response.sendRedirect(location);
		
				
		request.setAttribute("fontName", fontName);
		request.setAttribute("memberEmail", email);
		request.setAttribute("fontNo", fontNo);
		request.setAttribute("fontPrice", fontPrice);
		request.setAttribute("fontName", fontName);
		request.setAttribute("couponNo", couponNo);
		
		request
		.getRequestDispatcher("/WEB-INF/views/member/fontPurchaseView.jsp")
		.forward(request, response);
	}

}
