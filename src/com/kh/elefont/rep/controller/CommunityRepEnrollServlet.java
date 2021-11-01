package com.kh.elefont.rep.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.rep.model.service.RepService;
import com.kh.elefont.rep.model.vo.Rep;

/**
 * Servlet implementation class CommunityRepEnrollServlet
 */
@WebServlet("/rep/communityRepEnroll")
public class CommunityRepEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepService repService = new RepService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.사용자입력값처리
		
		String repContent =request.getParameter("rep-content");
		String commNo =request.getParameter("commNo");
		String memberNo =request.getParameter("memberNo");
		String repWriter =request.getParameter("repWriter");
		String repLevel =request.getParameter("repLevel");
		String repRef =request.getParameter("repRef");
		
		
		Rep rep = new Rep();
		rep.setRepContent(repContent);
		rep.setComnNo(commNo);
		rep.setMemberNo(memberNo);
		rep.setRepWriter(repWriter);
		rep.setRepLevel(Integer.valueOf(repLevel));
		rep.setRepRef(Integer.valueOf(repRef));
		//2.업무처리
		
		int result = repService.insertCommunityRep(rep);
		
		String msg = result == 0? "댓글 등록에 실패하셨습니다":"댓글 등록에 성공하셨습니다";
		
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		//view단처리
		
		System.out.println("repContent@Servlet = " +repContent);
		String location = request.getHeader("Referer"); 
		response.sendRedirect(location);
		
	}

}
