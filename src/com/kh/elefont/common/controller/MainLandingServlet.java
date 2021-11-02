package com.kh.elefont.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;

/**
 * Servlet implementation class MainLandingServlet
 */
@WebServlet("/mainLanding")
public class MainLandingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	private CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//업무로직
		List<Font> fontList =  fontService.selectAllApprovedFontOrderByDate();
		List<Community> communityList = communityService.selectAllCommunityListByLikeCount();
		List<Attachment> attachmentList = attachmentService.selectAllCommAttachmentList();
		Map<String, Object> map = new HashMap<>();
		map.put("fontList", fontList);
		map.put("communityList", communityList);
		map.put("attachmentList", attachmentList);
		
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		System.out.println(jsonStr);
		
		//3. view단 처리
		response.setContentType("application/json; charset = utf-8");
		response.getWriter().print(jsonStr);
	}

}
