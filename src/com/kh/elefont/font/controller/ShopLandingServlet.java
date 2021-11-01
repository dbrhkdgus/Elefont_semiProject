package com.kh.elefont.font.controller;

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
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class ShopLandingServlet
 */
@WebServlet("/shop")
public class ShopLandingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	private AttachmentService attachmentService = new AttachmentService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		// 랜딩 시, 기존 font 테이블 전부 조회
		String sort = request.getParameter("sort") == null ? "newest" : request.getParameter("sort");
		

		List<Font> fontList = new ArrayList<>();
		
		List<String> categoryList = session.getAttribute("categoryList") == null ? new ArrayList<>() : (List<String>)session.getAttribute("categoryList");
		
		if(request.getParameter("add") != null || request.getParameter("category") != null ) {
			if(request.getParameter("flag") == null) {
				categoryList.add(request.getParameter("add"));				
			}else{
				categoryList.remove(request.getParameter("flag"));

			}
		}
		
		switch(sort) {
		case "popular" : fontList = fontService.selectAllApprovedFontOrderByPopular(); break;
		case "view" : fontList = fontService.selectAllApprovedFontOrderByView(); break;
		case "order" : fontList = fontService.selectAllApprovedFontOrderByOrder(); break;
		case "recommand" : fontList = fontService.selectAllApprovedFontOrderByDate(); break;
		case "newest" : fontList = fontService.selectAllApprovedFontOrderByDate(); break;
		default : fontList = fontService.selectAllApprovedFontOrderByDate(); break;
		
		}
		
		
		List<Attachment> fontAttchmentList = attachmentService.selectAllFontAttachmentList();
		List<String> likeList = null;
		
		if(loginMember != null) {
			likeList = fontService.selectAllLikedFont(loginMember.getMemberNo());
		}
		request.setAttribute("fontList", fontList);
		request.setAttribute("fontAttchmentList", fontAttchmentList);
		request.setAttribute("likeList", likeList);
		
		
		request.setAttribute("sort", sort);
		session.setAttribute("categoryList", categoryList);
	
		request.getRequestDispatcher("/WEB-INF/views/shop/shopLanding.jsp").forward(request, response);
	}

}
