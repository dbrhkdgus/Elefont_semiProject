package com.kh.elefont.member.controller;

import java.io.IOException;
import java.sql.Date;

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

		
		//2. 업무로직
		Member member = memberService.selectOneMember(memberId);

		
		//3. 뷰단처리
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/member/memberInfoEdit.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력
		String memberId = request.getParameter("editId");
		String pwd = request.getParameter("editPwd");
		String birthday = request.getParameter("editBirthday");
		String phone = request.getParameter("editPhone");
		String job = request.getParameter("job");
		
		System.out.println("멤버아이디 잘 받았니?" + memberId);
		System.out.println("멤버비번 잘 받았니?" +  pwd);
		System.out.println("멤버생일 잘 받았니?" +  birthday );
		System.out.println("멤버연락처 잘 받았니?" +  phone );
		System.out.println("멤버직업 잘 받았니?" +  job );
		
		int result = memberService.updateMemberInfo(memberId);
		
	}

	
	

}
