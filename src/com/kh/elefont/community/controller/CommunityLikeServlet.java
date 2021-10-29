package com.kh.elefont.community.controller;

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
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class CommunityLikeServlet
 */
@WebServlet("/community/commLike")
public class CommunityLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommunityService communityService = new CommunityService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 입력값 처리
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberNo = loginMember.getMemberNo();
		String commNo = request.getParameter("commNo");
		
		Map<String, Object> param = new HashMap<>();
		param.put("memberNo", memberNo);
		param.put("commNo", commNo);
		
		// 업무 로직
		//like_comm 테이블에서 조회
		
		int likeValid; //loginMember가 좋아요를 눌렀는지 아닌지 확인하는 값
		likeValid = communityService.selectCommLike(param);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("likeValid", likeValid);
		
		//커뮤니티 좋아요 수를 comm_no count해서 좋아요 개수 찾기
		int likeCnt = communityService.countCommLike(commNo);
		map.put("likeCnt", likeCnt);
		map.put("commNo", commNo);
		
		//update문으로 보내기
		int result = communityService.updateCommLike(map);
		
		//json 문자열로 변환
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		
		//뷰단 처리
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonStr);
	}

}
