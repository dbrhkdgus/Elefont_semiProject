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

import com.kh.elefont.common.MailSend;
import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.coupon.model.service.CouponService;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.like_cart.model.service.LikeCartService;
import com.kh.elefont.like_cart.model.vo.MemberCartView;
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
	AttachmentService attachmentService = new AttachmentService();
	FontService fontService = new FontService();
	CouponService couponService = new CouponService();
	LikeCartService likeCartService = new LikeCartService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String couponNo = (String)request.getParameter("coupon-no");
		String _finalPrice = request.getParameter("finalPrice");
		double finalPrice = Double.parseDouble(_finalPrice);
		String memberNo = request.getParameter("member-no");
		String fontNo = request.getParameter("font-no");
		String fontPrice = request.getParameter("font-price");
		String orderNo = "order-" + System.currentTimeMillis();
		//????????? ?????? ?????????
		
		if(!(couponNo.isBlank())) {
			int result = couponService.deleteUsedCoupon(couponNo);
		}

		Order order = new Order();
		
		order.setMemberNo(memberNo);
		order.setFontNo(fontNo);
		order.setOrderNo(orderNo);
		
		
		//????????????
		//1. ???????????? ???????????????. view_member_orders
		int result = orderService.insertOrderFont(order,finalPrice);
		result = orderService.insertOrders(order);
		
		
		//2. ????????? ???????????? ???????????????. view_member_point
		result = memberService.updateMemberPoint(memberNo,fontPrice);
		
		Member loginMember = memberService.selectOneMemberByMemberNo(memberNo); 
		HttpSession session = request.getSession();
		session.removeAttribute("loginMember");
		session.setAttribute("loginMember", loginMember);
		
		
		List<Order> orderList = orderService.selectAllOrderListByOrderNo(orderNo);
		
		List<String> orderFonts = new ArrayList<>();
		for(Order o : orderList) {
			String fNo = o.getFontNo();
			orderFonts.add(fNo);
		}
		
		List<String> attachList = attachmentService.selectAllAttachByFontNo(orderFonts);
		String filepath = getServletContext().getRealPath("/upload/font");
		for(int i = 0; i < attachList.size(); i++) {
			String filename = attachList.get(i);
			attachList.set(i, filepath  + "/" + filename);
		}
		
		new MailSend().purchaseMailSend(orderList, attachList);
		
		// ????????? purchaseCount ?????????
		Font font = fontService.selectOneFontByFontNo(fontNo);
		font.setFontPurchasedCount(font.getFontPurchasedCount()+1);
		result = fontService.updateFontPurchaseCount(font);
		
		// ?????? ????????? ????????? ??????????????? ????????? ????????????, ??? ??????????????? ????????????.
		List<MemberCartView> memberCartViewList = likeCartService.selectMemberCartList(memberNo);
		for(MemberCartView mcv : memberCartViewList) {
			if(fontNo.equals(mcv.getFontNo())) {
				result = likeCartService.deleteCart(mcv.getCartNo());
				
			}
		}
		
		// view??? ??????
		
		response.sendRedirect(request.getContextPath()+"/shopDetail?fontNo="+fontNo);
		
		
	}

}
