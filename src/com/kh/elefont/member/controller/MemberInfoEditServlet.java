package com.kh.elefont.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class MemberDetailOptionServlet
 */
@WebServlet("/member/memberInfoEdit")
public class MemberInfoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
		
		String memberId = request.getParameter("memberId");
		System.out.println("설정을 눌렀을 때 memberId를 잘 받아왔나요 확인!" + memberId);
		
		//2. 업무로직
		Member member = memberService.selectOneMember(memberId);
		System.out.println("회원정보 수정을 위한 멤버를 잘 받아왔나요?"+member);
		
		//3. 뷰단처리
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/member/memberInfoEdit.jsp").forward(request, response);
	}

}
