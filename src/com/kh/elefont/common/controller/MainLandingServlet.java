package com.kh.elefont.common.controller;

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
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class MainLandingServlet
 */
@WebServlet("")
public class MainLandingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	private CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		//업무로직
		List<Font> fontList =  fontService.selectAllApprovedFontOrderByDate();
		List<Community> communityList = communityService.selectAllCommunityListByLikeCountThree();
		List<Attachment> attachmentList = attachmentService.selectAllCommAttachmentList();
		List<String> commLikeList = new ArrayList<>();
		
		if(loginMember != null) {
			commLikeList = communityService.selectAllLikedComm(loginMember.getMemberNo());
		}
		
		session.setAttribute("fontList", fontList);
		request.setAttribute("communityList", communityList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("commLikeList", commLikeList);
		//3. view단 처리
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
	}

}
