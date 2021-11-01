package com.kh.elefont.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.font.model.service.FontService;

/**
 * Servlet implementation class AutoCompleteServlet
 */
@WebServlet("/autocomplete")
public class AutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
    
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
		String searchName = request.getParameter("searchName");
		System.out.println("searchName@servlet = " + searchName);
		
		//2. 업무 로직
		List<String> fontNameList = fontService.selectAllFontName();
		System.out.println("fontNameList@Servlet : " + fontNameList);
		List<String> filteredList = new ArrayList<>();
		for(String name : fontNameList) {
			if(name.contains(searchName))
				filteredList.add(name);
		}
		
		//3. 응답 csv
		response.setContentType("text/csv; charset=utf-8");
		PrintWriter out = response.getWriter();
		for(int i = 0; i < filteredList.size(); i++) {
			out.print(filteredList.get(i));
			if(i != filteredList.size() - 1)
				out.print("\n");
		}
	}

	

}
