package com.kh.elefont.font.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class ShopSearchServlet
 */
@WebServlet("/shopSearch")
public class ShopSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	private AttachmentService attachmentService = new AttachmentService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fontName = request.getParameter("fontName");

		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		// 랜딩 시, 기존 font 테이블 전부 조회
		
		List<Font> fontList = fontService.selectShopFontByFontName(fontName);

		List<Attachment> fontAttchmentList = attachmentService.selectAllFontAttachmentList();
		List<String> likeList = null;
		
		if(loginMember != null) {
			likeList = fontService.selectAllLikedFont(loginMember.getMemberNo());
		}
		session.setAttribute("fontList", fontList);
		request.setAttribute("fontList", fontList);
		request.setAttribute("fontAttchmentList", fontAttchmentList);
		request.setAttribute("likeList", likeList);
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/shop/shopSearch.jsp").forward(request, response);
	}

}
