package com.kh.elefont.seller.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.font.model.service.FontService;

/**
 * Servlet implementation class SellerFontAuditCheckServlet
 */
@WebServlet("/seller/fontAuditCheck")
public class SellerFontAuditCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	private static final String FONTCHECK = "C"; 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
		String fontNo = request.getParameter("fontNo");
		Map<String, Object> param = new HashMap<>();
		param.put("fontNo", fontNo);
		param.put("fontApproval", FONTCHECK);
		
		//2. 업무 로직
		int result = fontService.updateFontAuditCheck(param);
		
		//3. view단 처리
		response.sendRedirect(request.getContextPath() + "/seller/fontAudit");
	}

}
