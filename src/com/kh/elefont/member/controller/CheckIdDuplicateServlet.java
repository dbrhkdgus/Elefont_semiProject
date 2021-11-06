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
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/member/checkIdDuplicate")
public class CheckIdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		String memberId = request.getParameter("memberId");

	    
	    // 2.업무로직
        Member member = memberService.selectOneMember(memberId);
        boolean available = member == null;

        
        // 3.view단처리
        request.setAttribute("available", available);
        request
            .getRequestDispatcher("/WEB-INF/views/member/checkIdDuplicate.jsp")
            .forward(request, response);
    
	}

}
