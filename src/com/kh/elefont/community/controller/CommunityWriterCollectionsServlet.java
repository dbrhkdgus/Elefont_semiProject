package com.kh.elefont.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommunityWriterCollectionsServlet
 */
@WebServlet("/member/commL")
public class CommunityWriterCollectionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberNo = request.getParameter("memberNo");
		System.out.println(memberNo);
		
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/community/communityWriterCollections.jsp")
		.forward(request, response);
	}

}
