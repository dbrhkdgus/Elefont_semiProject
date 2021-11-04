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
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.like_cart.model.vo.CommLike;
import com.kh.elefont.like_cart.model.vo.LikeFont;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class CommunityWriterCollectionsServlet
 */
@WebServlet("/community/writerCollections")
public class CommunityWriterCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();
	private FontService fontService = new FontService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("tabIndex", 0);
		String memberNo = request.getParameter("memberNo");
		
		List<Community> communityList = new ArrayList<>();
		List<Attachment> attachmentList = new ArrayList<>();
		Member member = memberService.selectOneMemberByMemberNo(memberNo);
		Attachment profileAttachment = attachmentService.selectProfileAttachment(memberNo);
		System.out.println("profileAttachment@servlet : " + profileAttachment);
		communityList = communityService.selectAllCommListByMemberNo(memberNo);
		attachmentList = attachmentService.selectAllCommAttachmentListByMemberNo(memberNo);
		
		List<Font> likeFontList = fontService.selectAllLikedFontByMemberNo(memberNo);
		List<Font> allFontList = fontService.selectAllFont();
		
		List<Community> commLikeList = communityService.selectAllLikedCommunity(memberNo);
		List<Community> allCommunityList = communityService.selectAllCommunityList();
		List<Attachment> allAttachmentList = attachmentService.selectAllAttachmentList();
		
		request.setAttribute("communityList", communityList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("profileAttachment", profileAttachment);
		request.setAttribute("member", member);
		
		request.setAttribute("likeFontList", likeFontList);
		request.setAttribute("allFontList", allFontList);
		
		request.setAttribute("commLikeList", commLikeList);
		request.setAttribute("allCommunityList", allCommunityList);
		request.setAttribute("allAttachmentList", allAttachmentList);
		
		request.getRequestDispatcher("/WEB-INF/views/community/communityWriterCollections.jsp").forward(request, response);
	}

}
