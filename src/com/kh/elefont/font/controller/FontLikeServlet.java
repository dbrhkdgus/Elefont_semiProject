package com.kh.elefont.font.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FontLikeServlet
 */
@WebServlet("/font/fontLike")
public class FontLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 loginMember가 좋아요를 눌렀는지 안 눌렀는지 확인 -> 안 눌렀으면 카운트 올리기/ 눌렀으면 카운트 빼기
		//1. 사용자 입력값 처리
		
		//2. 업무 로직
		
		//3. view단 처리
	}

}
