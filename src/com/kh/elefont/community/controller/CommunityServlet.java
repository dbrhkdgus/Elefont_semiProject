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
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class CommunityServlet
 */
@WebServlet("/community")
public class CommunityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		// 업무로직
		
		List<Community> communityList = new ArrayList<>();
		List<Attachment> attachmentList = new ArrayList<>();
		List<String> commLikeList = new ArrayList<>();
		List<Attachment> profileAttachmentList = new ArrayList<>();
		List<Attachment> allAttachmentList = new ArrayList<>();
			
		communityList = communityService.selectAllCommunityList();
		attachmentList = attachmentService.selectAllCommAttachmentList();
		profileAttachmentList = attachmentService.selectAllprofileAttachmentList();
		allAttachmentList = attachmentService.selectAllAttachmentList();
		
		if(loginMember != null) {
			commLikeList = communityService.selectAllLikedComm(loginMember.getMemberNo());
		}
		
		request.setAttribute("communityList", communityList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("commLikeList", commLikeList);
		request.setAttribute("profileAttachmentList", profileAttachmentList);
		request.setAttribute("allAttachmentList", allAttachmentList);
		
		request.getRequestDispatcher("/WEB-INF/views/community/community.jsp").forward(request, response);
	}

}
