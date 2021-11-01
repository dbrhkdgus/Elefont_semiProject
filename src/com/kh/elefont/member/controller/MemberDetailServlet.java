package com.kh.elefont.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class MemberDetailServlet
 */
@WebServlet("/member/memberDetail")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private FontService fontService = new FontService();
	private AttachmentService attachmentService = new AttachmentService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//memberRole이 관리자인 경우 전체 회원정보를 DB에서 불러와 함께 전달할 것
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberRole = loginMember.getMemberRole();
//		System.out.println(memberRole);
		
		if("A".equals(memberRole)) {
			List<Member> memberList = memberService.selectAllMember();
			List<Font> fontList = fontService.selectAllFont();
			
			session.setAttribute("memberList", memberList);
			session.setAttribute("fontList", fontList);
			session.setAttribute("tabIndex", 0);
		}else if("U".equals(memberRole) || "S".equals(memberRole)) {
			List<Attachment> commAttachmentList = attachmentService.selectAllCommAttachmentListByMemberNo(loginMember.getMemberNo());
			List<Font> fontLikeList = fontService.selectAllLikedFontByMemberNo(loginMember.getMemberNo());
			List<Font> fontPurchasedList = fontService.selectAllPurchasedFontByMemberNo(loginMember.getMemberNo());
			request.setAttribute("commAttachmentList", commAttachmentList);
			request.setAttribute("fontLikeList", fontLikeList);
			request.setAttribute("fontPurchasedList", fontPurchasedList);
		}
		
		// 회원의 커뮤니티 게시글 조회를 위해 전달할 것
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
			.forward(request, response);
	}

}
