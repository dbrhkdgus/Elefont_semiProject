package com.kh.elefont.admin.controller;

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

import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;


/**
 * Servlet implementation class AdminFontFinderServlet
 */
@WebServlet("/admin/fontFinder")
public class AdminFontFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		System.out.println("searchType" + searchType);
		System.out.println("searchKeyword" + searchKeyword);
		List<Font> fontList = null;
		
		
		Map<String,Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		
		//2.업무로직!!
		if("all".equals(searchType)) {
			fontList = fontService.selectAllFont();
			
		}else {
			fontList = fontService.selectSerchFont(param);
		}
		System.out.println(fontList);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("fontList", fontList);
		session.setAttribute("tabIndex", 4);

		//3. view단 처리
		request
		.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
		.forward(request, response);
	}

}
