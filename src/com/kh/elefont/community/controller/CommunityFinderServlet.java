package com.kh.elefont.community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class CommunityFinderServlet
 */
@WebServlet("/community/commFinder")
public class CommunityFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		
		List<Community> communityList = new ArrayList<>();
		List<Attachment> attachmentList = new ArrayList<>();
		List<String> commLikeList = new ArrayList<>();
		List<Attachment> allAttachmentList = new ArrayList<>();
		
		
		communityList = communityService.selectAllCommunityList();
		attachmentList = attachmentService.selectAllCommAttachmentList();
		allAttachmentList = attachmentService.selectAllAttachmentList();
		
		Map<String,Object> param = new HashMap<>(); 
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		
		List<Community> list = communityService.findCommListByMap(param);
		
		String queryString = String.format("?searchType=%s&searchKeyword=%s", searchType, searchKeyword);
		String url = request.getRequestURI() + queryString; // /mvc/admin/memberFinder?searchType=memberId&searchKeyword=a 
		
		
		System.out.println("param@servlet = " + param);
		System.out.println("찾은거@servlet = " + list);
		
		request.setAttribute("communityList", communityList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("commLikeList", commLikeList);
		request.setAttribute("allAttachmentList", allAttachmentList);
		request.setAttribute("list", list);
		request
		.getRequestDispatcher("/WEB-INF/views/community/community.jsp")
		.forward(request, response);
	}

}
