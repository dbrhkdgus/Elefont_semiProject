package com.kh.elefont.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FontPerchaseServlet
 */
@WebServlet("/font/fontPurchase")
public class FontPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberNo = request.getParameter("member-no");
		String fontNo = request.getParameter("font-no");
		
		
		
		//업무처리
		//1. 테이블에 인서트하기. view_member_orders
		
		
		//2. 회원의 포인트를 차감시키기. view_member_point
		
		// view단 처리
		
		response.sendRedirect(request.getContextPath()+"/shopDetail?fontNo="+fontNo);
		
		
	}

}
