package com.kh.elefont.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.ElefontUtils;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class LoginMemberServlet
 */
@WebServlet("/login/loginMember")
public class LoginMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//인코딩 처리 - filter 처리
		//입력값 지금 받을 것 X
		String memberId = request.getParameter("loginId");
		String password = ElefontUtils.getEncryptedPassword(request.getParameter("password"));
		//업무 로직 X
		Member member = memberService.selectOneMember(memberId);
		HttpSession session = request.getSession();
		
		
		String msg = "";
		if(member != null && password.equals(member.getMemberPwd())) {
			// 로그인 성공
			// session객체에 로그인 정보 기록
			
			session.setAttribute("loginMember", member);
			session.setAttribute("memberNo", member.getMemberNo());
//			 @@@@session유효시간 할건가요??? @@@
//			 session.setMaxInactiveInterval(60); // 60초
			
			
			
			//프로필 사진 작업 처리 - 다현
			
			//1. 사용자 입력값

			String memberNo = member.getMemberNo();


			
			Attachment attach = memberService.selectOneAttachmentByNo(memberNo);


			// 서버컴퓨터 파일 
			String saveDirectory = getServletContext().getRealPath("/upload/profilephotos");
			File profilePhotoAttach = new File(saveDirectory, attach.getRenamedFilename());

			
			//3. 뷰단처리
			session.setAttribute("member", member);
			session.setAttribute("profilePhotoAttach", profilePhotoAttach);
			//프로필 작업 끝
			
		
		//view단 처리

		}else if(member == null){
			session.setAttribute("msg", "존재하지 않는 아이디 입니다.");
		}else if(member != null &&  !password.equals(member.getMemberPwd())) {
			session.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
		}
		
		
		
		
		String location = request.getHeader("Referer"); 
		response.sendRedirect(location);
	}

}
