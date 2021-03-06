package com.kh.elefont.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/member/memberLogout")
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.업무로직
        // HttpSession javax.servlet.http.HttpServletRequest.getSession(boolean create)
        // create:boolean 
        // - true : 세션객체가 존재하면 해당세션객체를, 존재하지 않으면 새로운 session객체를 리턴.
        // - false : 세션객체가 존재하면 해당세션객체를, 존재하지 않으면 null을 리턴.
      HttpSession session = request.getSession(false);
      if(session != null) {
          session.invalidate();
      };
     
      try {
		if(request.getAttribute("categoryList") != null) {
			  request.removeAttribute("categoryList");    	  
		  }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
        // 2.redirect - url을 변경
        String location = request.getContextPath() + "/";
        response.sendRedirect(location);
	}
}
