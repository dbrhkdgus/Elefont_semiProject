package com.kh.elefont.member.controller;

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
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.like_cart.model.vo.LikeFont;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class FontLikeListServlet
 */
@WebServlet("/member/fontLikeList")
public class FontLikeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	private AttachmentService attachmentService = new AttachmentService();
	private LikeFont likeFont = new LikeFont();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		// 랜딩 시, 기존 font 테이블 전부 조회
		String sort = request.getParameter("sort") == null ? "newest" : request.getParameter("sort");

		List<Font> fontList = new ArrayList<>();
		List<LikeFont> likeFontList = new ArrayList<>(); 

		List<String> categoryList = session.getAttribute("categoryList") == null ? new ArrayList<>() : (List<String>) session.getAttribute("categoryList");
		List<Attachment> allAttachmentList = new ArrayList<>();
		
		categoryList.remove(request.getParameter("flag"));
		if (request.getParameter("add") != null || request.getParameter("category") != null) {
			categoryList.add(request.getParameter("add"));
		}

		allAttachmentList = attachmentService.selectAllAttachmentList();
		likeFontList = fontService.selectAllLikeFontListByMemberNo(loginMember.getMemberNo());
		fontList = fontService.selectAllApproveByCategory(categoryList);
		
		if(categoryList.isEmpty()) {
			 switch(sort) {
			 case "popular" : fontList = fontService.selectAllApprovedFontOrderByPopular(); break; 
			 case "view" : fontList = fontService.selectAllApprovedFontOrderByView(); break; 
			 case "order" : fontList = fontService.selectAllApprovedFontOrderByOrder(); break;
			 case "recommand" : fontList = fontService.selectAllApprovedFontOrderByDate(); break; 
			 case "newest" : fontList = fontService.selectAllApprovedFontOrderByDate(); break; 

			 
			 }
		}
		
		 

		List<Attachment> fontAttchmentList = attachmentService.selectAllFontAttachmentList();
		List<String> likeList = null;

		if (loginMember != null) {
			likeList = fontService.selectAllLikedFont(loginMember.getMemberNo());
		}
		
		
		
		request.setAttribute("likeFontList", likeFontList);
		request.setAttribute("fontAttchmentList", fontAttchmentList);
		request.setAttribute("likeList", likeList);

		request.setAttribute("sort", sort);
		request.setAttribute("allAttachmentList", allAttachmentList);
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("fontList", fontList);
		session.setAttribute("loginMember", loginMember);
		
		
		
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/member/fontLikeList.jsp")
			.forward(request, response);
	}

}
