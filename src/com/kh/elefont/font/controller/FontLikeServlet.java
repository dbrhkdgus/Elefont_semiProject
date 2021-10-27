package com.kh.elefont.font.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class FontLikeServlet
 */
@WebServlet("/font/fontLike")
public class FontLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 loginMember가 좋아요를 눌렀는지 안 눌렀는지 확인 -> 안 눌렀으면 카운트 올리기/ 눌렀으면 카운트 빼기
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		//1. 사용자 입력값 처리
		String fontNo = request.getParameter("fontNo");
		String memberNo = member.getMemberNo();
		Map<String, Object> param = new HashMap<>();
		param.put("fontNo", fontNo);
		param.put("memberNo", memberNo);
		
		//2. 업무 로직
		//like_font 테이블에서 조회. DQL이지만 존재 여부 확인 후, DML문 처리가 있을 예정이므로 int값으로 받는다.
		//json 변환할 데이터 객체 생성
		Map<String, Object> map = new HashMap<>();
		int result = fontService.selectFontLike(param);
		
		result = fontService.countFontLike(fontNo);

		map.put("fontNo", fontNo);
		map.put("likeCnt", result);
		
		//json문자열로 변환
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		System.out.println(jsonStr);
		
		//3. view단 처리
		response.setContentType("application/json; charset = utf-8");
		response.getWriter().print(jsonStr);
	}

}
