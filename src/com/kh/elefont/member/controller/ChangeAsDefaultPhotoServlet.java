package com.kh.elefont.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.member.model.service.MemberService;

/**
 * Servlet implementation class ChangeAsDefaultPhoto
 */
@WebServlet("/member/changeAsDefaultPhoto")
public class ChangeAsDefaultPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String memberNo = request.getParameter("memberNo");

		
		int result = memberService.updateDefaultImage(memberNo);
		
		String saveDirectory = getServletContext().getRealPath("/upload/profilephotos");
		Attachment attach = memberService.selectOneAttachmentByNo(memberNo);
		File profilePhotoAttach = new File(saveDirectory, attach.getRenamedFilename());

		
		
		if(result>0) {
			session.setAttribute("profilePhotoAttach",profilePhotoAttach);
			request.getRequestDispatcher("/WEB-INF/views/member/popUpClose.jsp").forward(request, response);
		}
		
		
	}

}
