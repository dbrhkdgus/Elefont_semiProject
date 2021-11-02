package com.kh.elefont.coupon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IsThisCouponVaild
 */
@WebServlet("/coupon/isThisCouponVaild")
public class IsThisCouponVaild extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberNo = request.getParameter("memberNoToReg");
		System.out.println("비동기로 쿠폰 있는 지 확인하기 위한 memberNo : " + memberNo);
		
		
	}

}
