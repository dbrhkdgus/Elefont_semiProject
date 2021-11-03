package com.kh.elefont.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.coupon.model.service.CouponService;
import com.kh.elefont.coupon.model.vo.Coupon;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.font.model.vo.FontCategory;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;
import com.kh.elefont.order.model.service.OrderService;
import com.kh.elefont.order.model.vo.Order;


/**
 * Servlet implementation class AdminFontFinderServlet
 */
@WebServlet("/admin/fontFinder")
public class AdminFontFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	private AttachmentService attachmentService = new AttachmentService();
	private CouponService couponService = new CouponService();
	private OrderService orderService = new OrderService();
	private MemberService memberService = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		System.out.println("searchType" + searchType);
		System.out.println("searchKeyword" + searchKeyword);
		List<Font> fontList = null;
		
		
		Map<String,Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		
		//2.업무로직!!
		if("all".equals(searchType)) {
			fontList = fontService.selectAllFont();
			
		}else {
			fontList = fontService.selectSerchFont(param);
		}
		System.out.println("fontList@servlet : " + fontList);
		
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		Attachment profile = attachmentService.selectProfileAttachment(loginMember.getMemberNo());
		request.setAttribute("profile", profile);
		
		List<Coupon> couponList = couponService.selectAllCoupon();
		List<Order> orderList = orderService.selectAllOrder();
		List<FontCategory> categoryList = fontService.selectAllFontCategory();
		List<Attachment> attachmentList = attachmentService.selectAllFontAttachmentList();
		List<Member> memberList = memberService.selectAllMember();
		
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("memberList", memberList);
		request.setAttribute("fontList", fontList);
		request.setAttribute("couponList", couponList);
		request.setAttribute("orderList", orderList);
		request.setAttribute("categoryList", categoryList);
		session.setAttribute("tabIndex", 0);
		
		request.setAttribute("memberList", memberList);
		request.setAttribute("tabIndex", 0);
		request.setAttribute("fontList", fontList);
		session.setAttribute("tabIndex", 4);

		//3. view단 처리
		request
		.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
		.forward(request, response);
	}

}
