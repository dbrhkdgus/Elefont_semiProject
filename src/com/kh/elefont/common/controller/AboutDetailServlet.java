package com.kh.elefont.common.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AboutDetailServlet
 */
@WebServlet("/aboutDetail")
public class AboutDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//1.사용자입력값
				String name = request.getParameter("name");
				System.out.println("어바웃디테일서블릿"+name);
				
				
				//2.업무?
					
						
				//3.뷰단
				request.setAttribute("name",name);
				request.getRequestDispatcher("/WEB-INF/views/common/aboutDetail.jsp").forward(request, response);
	}

	

}
