package com.kh.elefont.community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.member.model.service.MemberService;

/**
 * Servlet implementation class CommunityPictureDetailServlet
 */
@WebServlet("/community/pictureDetail")
public class CommunityPictureDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CommunityService communityService = new CommunityService();
	AttachmentService attachmentService = new AttachmentService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commNo = request.getParameter("commNo");
		System.out.println("commNo@servlet : " + commNo);
		
		Community community = new Community();
		community = communityService.selectOneCommunity(commNo);
		
		Attachment attachment = new Attachment();
		attachment = attachmentService.selectOneAttachment(commNo);
		
		List<Attachment> attachmentList = attachmentService.selectAllCommAttachmentListByMemberNo(attachment.getMemberNo());
		
		request.setAttribute("community", community);
		request.setAttribute("attachment", attachment);
		request.setAttribute("attachmentList", attachmentList);
		request.getRequestDispatcher("/WEB-INF/views/community/communityPictureDetail.jsp").forward(request, response);
	}

}
