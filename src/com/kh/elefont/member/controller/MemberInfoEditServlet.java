package com.kh.elefont.member.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.common.ElefontUtils;
import com.kh.elefont.common.model.vo.Attachment;
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
		String memberNo = request.getParameter("memberNo");
		System.out.println("MemberInfoEditServlet 에서 memberId 확인 : " + memberId);
		System.out.println("MemberInfoEditServlet 에서 memberNo 확인 : " + memberNo);
		
		//2. 업무로직
		Member member = memberService.selectOneMember(memberId);
		
		Attachment attach = memberService.selectOneAttachmentByNo(memberNo);
		System.out.println("MemberInfoEditServlet 에서 attach 잘 받아왔나 확인" + attach);

		// 서버컴퓨터 파일 
		String saveDirectory = getServletContext().getRealPath("/upload/profilephotos");
		File profilePhotoAttach = new File(saveDirectory, attach.getRenamedFilename());
		System.out.println("여기 뭐가 올렸나?" + profilePhotoAttach);

		
		//3. 뷰단처리
		request.setAttribute("member", member);
		request.setAttribute("profilePhotoAttach", profilePhotoAttach);
		
		request.getRequestDispatcher("/WEB-INF/views/member/memberInfoEdit.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력
		String memberId = request.getParameter("editId");
		String pwd = ElefontUtils.getEncryptedPassword(request.getParameter("editPwd"));
		String name = request.getParameter("editName");
		String gender = request.getParameter("eidtGender");
		String email = request.getParameter("editEmailk");
		String _birthday = request.getParameter("editBirthday");
		String phone = request.getParameter("editPhone");
		String job = request.getParameter("job");
		
		Date birthday = null;
		if(_birthday != null && !"".equals(_birthday))
			birthday = Date.valueOf(_birthday);
		
		System.out.println("멤버아이디 잘 받았니?" + memberId);
		System.out.println("멤버비번 잘 받았니?" +  pwd);
		System.out.println("멤버이름 잘 받았니?" +  name);
		System.out.println("멤버성별 잘 받았니?" + gender);
		System.out.println("멤버이메일 잘 받았니?" +  email);
		System.out.println("멤버생일 잘 받았니?" +  birthday );
		System.out.println("멤버연락처 잘 받았니?" +  phone );
		System.out.println("멤버직업 잘 받았니?" +  job );
		
		Member member = new Member (null,memberId,pwd,name,gender,email,phone,birthday,job,null,null,null,null,null);
		
		int result = memberService.updateMemberInfo(member);
		String msg =(result>0)?"회원정보수정이 완료되었습니다.":"회원정보 수정에 실패하였습니다";
		request.setAttribute("msg", msg);
		
		//3.뷰단처리
		String location = request.getHeader("Referer");
		response.sendRedirect(location);

		
	}

	
	

}
