package com.kh.elefont.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class MemberDetailServlet
 */
@WebServlet("/member/memberDetail")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//memberRole이 관리자인 경우 전체 회원정보를 DB에서 불러와 함께 전달할 것
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberRole = loginMember.getMemberRole();
		System.out.println(memberRole);
		
		if("A".equals(memberRole)) {
			List<Member> list = memberService.selectAllMember();
			System.out.println("list@servlet = "+list);
			
			session.setAttribute("list", list);
		}
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
			.forward(request, response);
	}

}
