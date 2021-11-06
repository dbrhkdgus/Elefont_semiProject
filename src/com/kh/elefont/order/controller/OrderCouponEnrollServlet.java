package com.kh.elefont.order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
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
		double salePrice = 0;
		
		//화면에 띄어주기 위해서 가져온 값
		String fontName = request.getParameter("fontName");
		String fontNo = request.getParameter("font-no");
		String fontPrice = request.getParameter("font-price");
		String email = request.getParameter("purchase-email");

		
		double price = Double.parseDouble(fontPrice);
		

		
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

		
		if("P".equals(couponType)) {
			
			int couponPrice = couponService.selectCouponPrice(couponNo);
			salePrice = price - (double)couponPrice;

		}else {
			double couponDiscountRate = couponService.selectCouponDiscountRate(couponNo);
			salePrice = price*(1-(couponDiscountRate*0.01));
		}

		//3. 뷰단처리
			
		Map<String,Object> map = new HashMap<>();
		map.put("couponNo", couponNo);
		map.put("salePrice", salePrice);

		
		response.setContentType("application/json; charset = utf -8");
		new Gson().toJson(map, response.getWriter());
		
	}

}
