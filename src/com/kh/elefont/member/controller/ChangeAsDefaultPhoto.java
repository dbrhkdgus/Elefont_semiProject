package com.kh.elefont.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.member.model.service.MemberService;

/**
 * Servlet implementation class ChangeAsDefaultPhoto
 */
@WebServlet("/member/changeAsDefaultPhoto")
public class ChangeAsDefaultPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberNo = request.getParameter("memberNo");
		System.out.println("프로필 기본이미지로 변경하기 위한 memberNo : " + memberNo);
		
		int result = memberService.updateDefaultImage(memberNo);
		
		if(result>0) {
			request.getRequestDispatcher("/WEB-INF/views/member/popUpClose.jsp").forward(request, response);
		}
		
		
	}

}
