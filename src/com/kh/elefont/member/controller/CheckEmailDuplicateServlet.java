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
 * Servlet implementation class CheckEmailDuplicateServlet
 */
@WebServlet("/member/checkEmailDuplicate")
public class CheckEmailDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1. 사용자 입력값
		String memberEmail = request.getParameter("memberEmail");
	    System.out.println("memberEmail@servlet = " + memberEmail);
	    
	    // 2.업무로직
        Member member = memberService.selectOneMemberByEmail(memberEmail);
        boolean available = member == null;
        System.out.println("available@servlet = " + available);
        
        // 3.view단처리
        request.setAttribute("available", available);
        request
            .getRequestDispatcher("/WEB-INF/views/member/checkEmailDuplicate.jsp")
            .forward(request, response);
	}

}
