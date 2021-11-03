package com.kh.elefont.like_cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.like_cart.model.service.LikeCartService;

/**
 * Servlet implementation class MemberCartDelete
 */
@WebServlet("/member/memberCartDelete")
public class MemberCartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LikeCartService likeCartService = new LikeCartService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cartNo = request.getParameter("cartNo");
		int result = likeCartService.deleteCart(cartNo);
		HttpSession session = request.getSession();
		if (result > 0) {
			session.setAttribute("msg", "장바구니에서 삭제되었습니다.");
		} else {
			session.setAttribute("msg", "실패");
		}

		String location = request.getHeader("Referer");
		response.sendRedirect(location);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> cartNoList = new ArrayList<>();
		String[] cartNoArr = request.getParameterValues("chk_cart_no");
		String type = request.getParameter("type");
		System.out.println("type@servlet : " + type);
			
		
			if (cartNoArr != null) {
				if("delete".equals(type)) {
				for (int i = 0; i < cartNoArr.length; i++) {
					cartNoList.add(cartNoArr[i]);
					int result = 0;
					for (String cartNo : cartNoList) {
						result = likeCartService.deleteCart(cartNo);
						if (result < 0) {
							session.setAttribute("msg", "실패");
							break;
						}
					}
				}
			}else if("purchase".equals(type)) {
				
				
			}
			
		}
		String location = request.getHeader("Referer");
		response.sendRedirect(location);
		
		
	}

}
