package com.kh.elefont.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberCartServlet
 */
@WebServlet("/member/memberCart")
public class MemberCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//2.사용자 값 
		
		//3.업무로직
		
		//4.
		request
		.getRequestDispatcher("/WEB-INF/views/member/memberCart.jsp")
		.forward(request, response);

	}

}
