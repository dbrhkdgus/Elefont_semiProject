package com.kh.elefont.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.member.model.service.MemberService;

/**
 * Servlet implementation class MemberWithdrawalServlet
 */
@WebServlet("/member/withdrawal")
public class MemberWithdrawalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberService = new MemberService();

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.사용자값
		String memberId = request.getParameter("memberId");


		// 2. 로직처리
		int result = memberService.withdrawalMember(memberId);
		String msg = (result > 0) ? "탈퇴가 완료되었습니다" : "탈퇴가 실패했습니다";
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);

		// 3. 뷰단처리
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/member/memberLogout");			
		}else {
			response.sendRedirect(request.getContextPath()+"/");		
		}

	}

}
