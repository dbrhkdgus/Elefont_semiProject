package com.kh.elefont.rep.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.rep.model.service.RepService;
import com.kh.elefont.rep.model.vo.Rep;

/**
 * Servlet implementation class ShopRepEnrollServlet
 */
@WebServlet("/rep/ShopRepEnroll")
public class ShopRepEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepService repService = new RepService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1.
		String fontNo = request.getParameter("font-no");
		String repInput = request.getParameter("reply-input");
		String repWriter = request.getParameter("rep-writer");
		int repLevel = Integer.valueOf(request.getParameter("rep-level"));
		int repRef = Integer.valueOf(request.getParameter("rep-ref"));
		
		Rep rep = new Rep();
		rep.setRepContent(repInput);
		rep.setFontNo(fontNo);
		rep.setRepWriter(repWriter);
		rep.setRepLevel(repLevel);
		rep.setRepRef(repRef);
		
		int result = repService.insertShopRep(rep);
		
		
		String location = request.getHeader("Referer"); 
		response.sendRedirect(location);
	}

}
