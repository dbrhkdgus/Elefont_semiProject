package com.kh.elefont.rep.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.rep.model.service.RepService;

/**
 * Servlet implementation class DeleteUpdateRepServlet
 */
@WebServlet("/rep/DeleteUpdateRep")
public class DeleteUpdateRepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepService repService = new RepService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteUpdateRep 도착----------------------------");
		String type = request.getParameter("type");
		String repNo = request.getParameter("rep_no");
		
		
		System.out.println("---------------------------------------");
		System.out.println("type@servlet : " + type);
		System.out.println("repNo@servlet : " + repNo);
		
		System.out.println("---------------------------------------");
		// 업무로직
		int result = 0;
		switch(type) {
		case "update" : String updateRepContent = request.getParameter("update_rep_content");
		System.out.println("updateRepContent@Servlet = " + updateRepContent);
		result = repService.updateRep(repNo, updateRepContent); 
		break;
		case "delete" : result = repService.deleteRep(repNo); break;
 		}
		
		if(result < 0) {
			
			String msg = "실패!";
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
		}
		
		String location = request.getHeader("Referer"); 
		response.sendRedirect(location);
		
		
		
	}

}
