package com.kh.elefont.question.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.elefont.question.model.service.QuestionService;
import com.kh.elefont.question.model.vo.Question;

/**
 * Servlet implementation class ChatInputServlet
 */
@WebServlet("/chat/chatInput")
public class ChatInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService = new QuestionService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qContent = request.getParameter("qContent");
		String qWriter = request.getParameter("qWriter");
		
		// 업무처리
		int result = questionService.insertQuestion(qContent,qWriter);
		System.out.println("result@questionServlet = " + result);
		
		//		등록한 질문을 불러와서 그 값을 담아 jsp append처리
		Question question = questionService.selectLastQuestion();
		System.out.println("question@servlet : " + question);
		  
		response.setContentType("application/json; charset=utf-8"); 
		new Gson().toJson(question, response.getWriter());
		 
	}

}
