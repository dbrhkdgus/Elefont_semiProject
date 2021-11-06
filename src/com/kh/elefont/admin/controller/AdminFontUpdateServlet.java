package com.kh.elefont.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;

/**
 * Servlet implementation class AdminFontUpdateServlet
 */
@WebServlet("/admin/fontUpdate")
public class AdminFontUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
		String[] fontApprovalArr = request.getParameterValues("fontApproval");
		String[] fontNoArr = request.getParameterValues("fontNo");
		String[] fontPriceArr = request.getParameterValues("fontPrice");
		String[] fontDiscountRateArr = request.getParameterValues("fontDiscountRate");
		Font[] fontArr = new Font[fontApprovalArr.length];
		
		for(int i = 0; i < fontNoArr.length; i++) {
			String fontApproval = fontApprovalArr[i];
			String fontNo = fontNoArr[i];
			double fontPrice = Double.parseDouble(fontPriceArr[i]);
			double fontDiscountRate = Double.parseDouble(fontDiscountRateArr[i]);
			fontArr[i] = new Font(fontNo, null, null, null, fontPrice, fontDiscountRate, 0,0,0,null, fontApproval, null, null, null);
			
		}
		
		//2. 업무 로직
		int result = fontService.updateFont(fontArr);
		String msg = result > 0? "폰트 관리 사항 업데이트 완료": "폰트 관리 사항 업데이트 실패";
		
		//3. sendRedirect
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/member/memberDetail?index=4");
	}

}
