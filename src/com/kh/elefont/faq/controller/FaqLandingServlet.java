package com.kh.elefont.faq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.faq.model.service.FaqService;
import com.kh.elefont.faq.model.vo.Faq;


/**
 * Servlet implementation class FaqServlet
 */
@WebServlet("/faq")
public class FaqLandingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FaqService faqService = new FaqService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.사용자 입력 값 필요없음!
		

		//2.업무로직 _db를 갔다옴
		 List<Faq> faqList = faqService.selectAllFaq();
		 System.out.println("디비를 잘 갔다오셨나요?" + faqList);
		 
		 
		
		//3. 뷰단처리
		request.setAttribute("faqList", faqList);
		request
			.getRequestDispatcher("/WEB-INF/views/faq/faqLanding.jsp")
			.forward(request, response);
	}

}
