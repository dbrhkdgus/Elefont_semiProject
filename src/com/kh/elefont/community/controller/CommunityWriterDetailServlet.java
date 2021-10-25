package com.kh.elefont.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class CommunityWriterDetailServlet
 */
@WebServlet("/community/writerDetail")
public class CommunityWriterDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 입력값
		String memberNo = request.getParameter("commWriter");
		System.out.println("memberNo@servlet : " + memberNo);
		
		// 업무로직
		Member writerMember = new Member();
		writerMember = memberService.selectOneMemberByMemberNo(memberNo);
		
		
		
		
		request.setAttribute("writerMember", writerMember);
		
		
		request.getRequestDispatcher("/WEB-INF/views/community/communityWriterDetail.jsp").forward(request, response);
	}

}
