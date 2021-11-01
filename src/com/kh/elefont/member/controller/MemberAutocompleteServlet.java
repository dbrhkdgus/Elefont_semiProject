package com.kh.elefont.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.elefont.member.model.service.MemberService;

/**
 * Servlet implementation class MemberAutocompleteServlet
 */
@WebServlet("/member/autocomplete")
public class MemberAutocompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
		String searchId = request.getParameter("searchId");
		
		//2. 업무 로직
		List<String> searchedIdList = memberService.selectSearchMember(searchId);
		
		//3. 응답 처리
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(searchedIdList, response.getWriter());
	}

}
