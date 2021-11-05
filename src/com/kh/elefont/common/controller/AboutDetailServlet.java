package com.kh.elefont.common.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
				String[] names = {"유광현","권혜진","김은희","김다현","백지영","이윤희"};
				String name = request.getParameter("name");
				if(Arrays.asList(names).contains(name)) {
					request.setAttribute("name",name);
					request.getRequestDispatcher("/WEB-INF/views/common/aboutDetail.jsp").forward(request, response);	
				}else {
					request.getRequestDispatcher("/WEB-INF/common/404.jsp").forward(request, response);
				}
				
				
				
				
	}

	

}
