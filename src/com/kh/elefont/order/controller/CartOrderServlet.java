package com.kh.elefont.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.like_cart.model.service.LikeCartService;
import com.kh.elefont.like_cart.model.vo.MemberCartView;
import com.kh.elefont.order.model.service.OrderService;
import com.kh.elefont.order.model.vo.Order;

/**
 * Servlet implementation class FontOrderServlet
 */
@WebServlet("/cart/fontOrder")
public class CartOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();
	private LikeCartService likeCartService = new LikeCartService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> cartNoList = new ArrayList<>();
		String[] cartNoArr = request.getParameterValues("chk_cart_no");
		List<MemberCartView> mcvList = new ArrayList<MemberCartView>();
		
		String[] fontNoArr = request.getParameterValues("multi_font_no");
		System.out.println("fontNoArr@servlet : " + fontNoArr);
		String orderNo = "order-" + System.currentTimeMillis();
		//유일한 값을 위해서

//		Order order = new Order();
//		
//		order.setMemberNo(memberNo);
//		order.setFontNo(fontNo);
//		order.setOrderNo(orderNo);
		
//		
//		if (cartNoArr != null) {
//			for (int i = 0; i < cartNoArr.length; i++) {
//				cartNoList.add(cartNoArr[i]);
//				int result = 0;
//				for (String cartNo : cartNoList) {
//					result = likeCartService.deleteCart(cartNo);
//					if (result < 0) {
//						session.setAttribute("msg", "실패");
//						break;
//					}
//				}
//			}
//		}
		
		
		request.getRequestDispatcher("/WEB-INF/views/member/fontPurchaseView.jsp").forward(request, response);
	}

}
