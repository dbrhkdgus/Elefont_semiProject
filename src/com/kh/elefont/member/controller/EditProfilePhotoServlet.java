package com.kh.elefont.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditProfilePhoto
 */
@WebServlet("/member/editProfilePhoto")
public class EditProfilePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberNo = request.getParameter("memberNo");
		String profilePhotoPath = request.getParameter("profilePhotoPath");
		
		System.out.println("memberNO 확인해보자 : " + memberNo);
		System.out.println("profilePhotoPath확인해보자 : " + profilePhotoPath);
		
		request.setAttribute("profilePhotoPath", profilePhotoPath);
		
		request.getRequestDispatcher("/WEB-INF/views/member/editProfilePhoto.jsp").forward(request, response);

	}


}
