package com.kh.elefont.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.common.ElefontUtils;
import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;
import com.kh.elefont.question.model.service.QuestionService;
import com.kh.elefont.question.model.vo.Question;

/**
 * Servlet implementation class adminAnswerQnAservlet
 */
@WebServlet("/admin/answerQnA")
public class AdminAnswerQnAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService = new QuestionService();
	private MemberService memberService = new MemberService();
	private AttachmentService attachmentService = new AttachmentService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Question> questionList = questionService.selectAllQuestionForAdmin();
		List<String> questionerList = questionService.selectAllQuestioner();
		List<Question> questionListGroupBy = new ArrayList<>();
		
		List<Member> memberList = memberService.selectAllMember();
		List<Attachment> attachmentList = attachmentService.selectAllprofileAttachmentList();
		
		
		for(String questionner : questionerList) {
				questionListGroupBy.add(questionService.selectAllQuestionGroupByForAdmin(questionner));
		}
		
		//qContent xss처리
		for(Question q : questionList) {
			//XSS 공격 대비 
			//cross-site script공격. 악성코드를 웹페이지 삽입하여 클라이언트의 개인정보를 탈취하는 공격법
			String content = ElefontUtils.escapeHtml(q.getqContent());
			
			//개행문자 br태그 변환 처리
			content = ElefontUtils.convertLineFeedToBr(content);
			q.setqContent(content);
			 
		 }
		
		request.setAttribute("questionList", questionList); 
		request.setAttribute("questionerList", questionerList); 
		
		request.setAttribute("attachmentList", attachmentList);  
		request.setAttribute("questionListGroupBy", questionListGroupBy);  
		request.setAttribute("memberList", memberList);  
		request.getRequestDispatcher("/WEB-INF/views/admin/adminAnswerQnA.jsp").forward(request, response);
	}

}
