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
		int result = 0;
		int likeValid = 0;
		Map<String, Object> map = new HashMap<>();
		likeValid = fontService.selectFontLike(param);

		
		if(likeValid == 1) {
			//좋아요 이력이 있는 경우 like_font 테이블에서 내용 삭제 후 font테이블 좋아요 카운트 감소
			likeValid = 0;
			result = fontService.deleteFontLike(param);
		}
		else if(likeValid == 0) {
			likeValid = 1;
			//좋아요 이력이 없는 경우 like_font 테이블에서 내용 추가 후 font테이블 좋아요 카운트 증가
			result = fontService.insertFontLike(param);
		}
		
		int likeCnt = fontService.countFontLike(fontNo);

		map.put("likeValid", likeValid);
		map.put("likeCnt", likeCnt);
		map.put("fontNo", fontNo);
		result = fontService.updateFontLike(map);
		
		//json문자열로 변환
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);

		
		//3. view단 처리
		response.setContentType("application/json; charset = utf-8");
		response.getWriter().print(jsonStr);
	}

}
