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

/**
 * Servlet implementation class CommunityServlet
 */
@WebServlet("/community")
public class CommunityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommunityService communityService = new CommunityService();
	AttachmentService attachmentService = new AttachmentService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 업무로직
		
		List<Community> communityList = new ArrayList<>();
		List<Attachment> attachmentList = new ArrayList<>();
		
		communityList = communityService.selectAllCommunityList();
		System.out.println("communityList@servlet : " + communityList);
		attachmentList = attachmentService.selectAllAttachmentList();
		System.out.println("attachmentList@servlet : " + attachmentList);
		
		
		request.setAttribute("communityList", communityList);
		request.setAttribute("attachmentList", attachmentList);
		
		request.getRequestDispatcher("/WEB-INF/views/community/community.jsp").forward(request, response);
	}

}
