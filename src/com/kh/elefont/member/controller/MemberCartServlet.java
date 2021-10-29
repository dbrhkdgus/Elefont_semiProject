package com.kh.elefont.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.like_cart.model.service.LikeCartService;

/**
 * Servlet implementation class MemberCartServlet
 */
@WebServlet("/member/memberCart")
public class MemberCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LikeCartService likeCartService = new LikeCartService();

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 입력값 처리
		String PerCartType = request.getParameter("PerCartType");
		String fontNo = request.getParameter("font_no");
		String memberNo = request.getParameter("member_no");
		
		System.out.println("PerCartType@servlet : "+PerCartType);
		System.out.println("fontNo@servlet : "+fontNo);
		System.out.println("memberNo@servlet : "+memberNo);
		// PerCartType에따른 업무 분기
		int result = 0;
		
		switch(PerCartType) {
		case "purchase" :  break;
		case "cart" : result = likeCartService.insertCart(fontNo, memberNo); break;
		}
		
		
		
		String location = request.getHeader("Referer"); 
		response.sendRedirect(location);
		
	}

}
