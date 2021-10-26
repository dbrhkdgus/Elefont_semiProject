package com.kh.elefont.font.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;

/**
 * Servlet implementation class ShopDetailServlet
 */
@WebServlet("/shopDetail")
public class ShopDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fontNo = request.getParameter("fontNo");
		
		Font font = fontService.selectOneFontByFontNo(fontNo);
		
		
		request.setAttribute("font", font);
		request.getRequestDispatcher("/WEB-INF/views/shop/shopDetail.jsp").forward(request, response);
	}

	

}
