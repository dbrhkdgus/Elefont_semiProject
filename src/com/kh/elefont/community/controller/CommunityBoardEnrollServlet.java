package com.kh.elefont.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.font.model.service.FontService;

/**
 * Servlet implementation class CommunityBoardEnrollServlet
 */
@WebServlet("/community/boardEnroll")
public class CommunityBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommunityService communityService = new CommunityService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/community/communityBoardEnroll.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		
		// 2. 입력값 처리
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String font = request.getParameter("font");
		
		Community community = new Community();
		
		community.setCommTitle(title);
		community.setCommWriter(writer);
		community.setCommContent(content);
		
		FontService fontService = new FontService();
		community.setFontNo(fontService.selectFontNoByFontName(font));
		
		System.out.println("community@servlet : " + community);
		
		
		
		// 3. 업무로직
		int result = communityService.enrollBoard(community);
		
		String msg = result > 0 ? "게시물 등록 성공" : "게시물 등록 실패";
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath()+"/community");
		
	}

}
