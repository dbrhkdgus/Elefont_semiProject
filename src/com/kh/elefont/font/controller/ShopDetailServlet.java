package com.kh.elefont.font.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;

/**
 * Servlet implementation class ShopDetailServlet
 */
@WebServlet("/shopDetail")
public class ShopDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	private CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fontNo = request.getParameter("fontNo");
		
		Font font = fontService.selectOneFontByFontNo(fontNo);
		
		List<Community> communityList =  communityService.selectCommunityListByFontNo(fontNo);
		
		List<Attachment> commAttachmentList = attachmentService.selectAllCommAttachmentList();
		
		request.setAttribute("font", font);
		request.setAttribute("communityList", communityList);
		request.setAttribute("commAttachmentList", commAttachmentList);
		
		request.getRequestDispatcher("/WEB-INF/views/shop/shopDetail.jsp").forward(request, response);
	}

	

}
