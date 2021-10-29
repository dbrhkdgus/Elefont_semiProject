package com.kh.elefont.faq.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.faq.model.service.FaqService;
import com.kh.elefont.faq.model.vo.Faq;
import com.kh.elefont.member.model.vo.Member;
import com.kh.elefont.question.model.service.QuestionService;
import com.kh.elefont.question.model.vo.Question;


/**
 * Servlet implementation class FaqServlet
 */
@WebServlet("/faq")
public class FaqLandingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FaqService faqService = new FaqService();
	private QuestionService questionService = new QuestionService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		//1.사용자 입력 값 필요없음! (쿼리문에 물음표가 없어서!)
		

		//2.업무로직 _db를 갔다옴
		 List<Faq> faqList = faqService.selectAllFaq();
		 System.out.println("디비를 잘 갔다오셨나요?" + faqList);
		 
		 List<Question> questionList = new ArrayList<>();
		 
		 if(loginMember != null) {
			 questionList = questionService.selectAllQuestion(loginMember.getMemberNo());
		 }
		 
		 
		
		//3. 뷰단처리
		request.setAttribute("faqList", faqList);
		request.setAttribute("questionList", questionList);
		request
			.getRequestDispatcher("/WEB-INF/views/faq/faqLanding.jsp")
			.forward(request, response);
	}

}
