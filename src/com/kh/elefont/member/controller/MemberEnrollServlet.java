package com.kh.elefont.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.ElefontUtils;
import com.kh.elefont.common.MailSend;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 입력값 전달
		String memberId = request.getParameter("memberId");
		String password = ElefontUtils.getEncryptedPassword(request.getParameter("password"));
		String memberName = request.getParameter("memberName");
		String memberGender = request.getParameter("gender");
		String memberRole = request.getParameter("memberRole");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String _birthday = request.getParameter("birthday");
		String job = request.getParameter("job");
		
		//java.sql.Date 날짜 타입으로 변경
		Date birthday = null;
		if(_birthday != null && !"".equals(_birthday))
			birthday = Date.valueOf(_birthday);
		
		//default 프로필 사진 넣어주기 시작
		int defaultAttNo = 211;
		Attachment attach = memberService.BringDefaultProfilePhoto(memberId,defaultAttNo);
		
		
		////default 프로필 사진 넣어주기  끝
		
		Member member = new Member(null, memberId, password, memberName, memberGender, email, phone, birthday, job, null, null, null, memberRole, attach);
		System.out.println("member@servlet" + member);
		
		
		//업무 로직
		int result = memberService.insertMember(member);
		String bringNo = memberService.selectMemberNoById(memberId);
				
		int result2 = memberService.insertDefaultPhoto(bringNo);
		
		String msg = result > 0 ? "회원가입을 축하 드립니다" : "";
		
		new MailSend().enrollMailSend(member);
		
		//응답 처리
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		String location = request.getHeader("Referer");
		response.sendRedirect(location);
	}

}
