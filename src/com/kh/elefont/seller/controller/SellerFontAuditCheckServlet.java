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
		String fontApproval = request.getParameter("fontApproval");
		Map<String, Object> param = new HashMap<>();
		param.put("fontNo", fontNo);
		
		//	심사 결과에 따른 분기 처리(Y면 승인되어 판매되는 폰트로 이동, N이면 폰트 테이블에서 삭제)
		if("Y".equals(fontApproval)) {
			
			param.put("fontApproval", FONTCHECK);
			
			//2. 업무 로직
			int result = fontService.updateFontAuditCheck(param);
		}
		else if("N".equals(fontApproval)) {
			//2. 업무 로직
			int result = fontService.deleteFontAudit(fontNo);
		}
		
		//3. view단 처리
		response.sendRedirect(request.getContextPath() + "/seller/fontAudit");
	}

}
