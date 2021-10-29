package com.kh.elefont.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		List<Member> memberList = null;
		
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		//2. 업무 로직
		if("all".equals(searchType)) {
			memberList = memberService.selectAllMember();
		}else {
			memberList = memberService.selectSearchMember(param);
		}
		System.out.println(memberList);
		
		HttpSession session = request.getSession();
		session.setAttribute("memberList", memberList);
		session.setAttribute("tabIndex", 0);
		
		//3. view단 처리
		request
		.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
		.forward(request, response);
	}

}
