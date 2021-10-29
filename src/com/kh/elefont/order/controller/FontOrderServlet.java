package com.kh.elefont.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.order.model.service.OrderService;
import com.kh.elefont.order.model.vo.Order;

/**
 * Servlet implementation class FontOrderServlet
 */
@WebServlet("/font/fontOrder")
public class FontOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String memberNo = request.getParameter("member-no");
		String fontName = request.getParameter("font-name");
		String fontPrice = request.getParameter("font-price");
		String memberEmail = request.getParameter("email");
		
		//2.업무로직
//		Order order = orderService.selectOneOrderByMemberNo(memberNo);
	 
//		System.out.println("order @Servlet:" + order);
		
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("fontName", fontName);
		request.setAttribute("fontPrice", fontPrice);
		request.setAttribute("memberEmail", memberEmail);
		
		request.getRequestDispatcher("/WEB-INF/views/member/FontPurchase.jsp").forward(request, response);
	}

}
