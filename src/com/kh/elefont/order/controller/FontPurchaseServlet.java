package com.kh.elefont.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;
import com.kh.elefont.order.model.service.OrderService;
import com.kh.elefont.order.model.vo.Order;


/**
 * Servlet implementation class FontPerchaseServlet
 */
@WebServlet("/font/fontPurchase")
public class FontPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();
	MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberNo = request.getParameter("member-no");
		String fontNo = request.getParameter("font-no");
		String fontPrice = request.getParameter("font-price");
		//유일한 값을 위해서

		Order order = new Order();
		
		order.setMemberNo(memberNo);
		order.setFontNo(fontNo);
		order.setOrderNo("order-" + System.currentTimeMillis());
		
		
		//업무처리
		//1. 테이블에 인서트하기. view_member_orders
		int result = orderService.insertOrderFont(order);
		result = orderService.insertOrders(order);
		
		
		//2. 회원의 포인트를 차감시키기. view_member_point
		result = memberService.updateMemberPoint(memberNo,fontPrice);
		
		Member loginMember = memberService.selectOneMemberByMemberNo(memberNo); 
		HttpSession session = request.getSession();
		session.removeAttribute("loginMember");
		session.setAttribute("loginMember", loginMember);
		
		
		// view단 처리
		
		response.sendRedirect(request.getContextPath()+"/shopDetail?fontNo="+fontNo);
		
		
	}

}
