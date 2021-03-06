package com.kh.elefont.community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.like_cart.model.vo.LikeFont;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class CommunityWriterDetailServlet
 */
@WebServlet("/community/writerDetail")
public class CommunityWriterDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();
	private FontService fontService = new FontService();
	private LikeFont likeFont = new LikeFont();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		// 입력값
		String memberNo = request.getParameter("commWriter");

		
		// 업무로직
		Member writerMember = new Member();
		writerMember = memberService.selectOneMemberByMemberNo(memberNo);
		Attachment profileAttachment = attachmentService.selectProfileAttachment(memberNo);

		// 게시물 수 
		int totalCommunityByWriter = communityService.countTotalCommunityByWriter(writerMember.getMemberNo());
		// 폰트 좋아요 수
		int totalFontLikeByWriter = fontService.countTotalFontLikeByWriter(writerMember.getMemberNo());
		
		
		List<String> categoryList = session.getAttribute("categoryList") == null ? new ArrayList<>() : (List<String>) session.getAttribute("categoryList");
		List<Attachment> attachmentList = attachmentService.selectAllCommAttachmentListByMemberNo(memberNo);
		List<Font> fontLikeList = fontService.selectAllLikedFontByMemberNo(memberNo);
		List<Font> allFontList = fontService.selectAllFont();

		
		
		
		
		request.setAttribute("writerMember", writerMember);
		request.setAttribute("totalCommunityByWriter", totalCommunityByWriter);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("profileAttachment", profileAttachment);
		request.setAttribute("fontLikeList", fontLikeList);
		request.setAttribute("allFontList", allFontList);
		request.setAttribute("totalFontLikeByWriter", totalFontLikeByWriter);
		
		request.getRequestDispatcher("/WEB-INF/views/community/communityWriterDetail.jsp").forward(request, response);
	}

}
