package com.kh.elefont.like_cart.controller;

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
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.like_cart.model.service.LikeCartService;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;
import com.kh.elefont.order.model.service.OrderService;
import com.kh.elefont.order.model.vo.Order;

/**
 * Servlet implementation class MemberCartDelete
 */
@WebServlet("/member/memberCartDelete")
public class MemberCartPurchaseDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LikeCartService likeCartService = new LikeCartService();
	private OrderService orderService = new OrderService();
	private MemberService memberService = new MemberService();
	private FontService fontService = new FontService();
	private AttachmentService attachmentService = new AttachmentService();

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
		List<Order> orderList = new ArrayList<>();
		List<String> attachList = new ArrayList<>();
		List<String> fontNoList = new ArrayList<>();
		String[] cartNoArr = request.getParameterValues("chk_cart_no");
		String type = request.getParameter("type");
		
		
		String memberNo = request.getParameter("member_no");
		String fontPrice = request.getParameter("font_price");
		Member loginMember = memberService.selectOneMemberByMemberNo(memberNo); 	
		
		if("delete".equals(type)) {
			if (cartNoArr != null) {
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
			}
			
		}else if("purchase".equals(type)) {
			if (cartNoArr != null) {
				for (int i = 0; i < cartNoArr.length; i++) {
					cartNoList.add(cartNoArr[i]);
					
					}
				for (String cartNo : cartNoList) {
					//업무
					
					String fontNo = fontService.selectFontNoByCartNo(cartNo);
						
						int result = 0;
						
						Order order = new Order();
						order.setMemberNo(memberNo);
						order.setFontNo(fontNo);
						order.setOrderNo( "order-" + System.currentTimeMillis());
						
						
						
						
						fontNoList.add(fontNo);
						result = orderService.insertOrderFont(order);
						result = orderService.insertOrders(order);
						
						List<Order> oList = orderService.selectAllOrderListByOrderNo(order.getOrderNo());
						orderList.addAll(0,oList);
						
						result = memberService.updateMemberPoint(memberNo,fontPrice);
						
						
						session.removeAttribute("loginMember");
						session.setAttribute("loginMember", loginMember);
						
						result = likeCartService.deleteCart(cartNo);
						
						if (result < 0) {
							session.setAttribute("msg", "실패");
							break;
						}
				}
				
			}
			attachList = attachmentService.selectAllAttachByFontNo(fontNoList);
			String filepath = getServletContext().getRealPath("/upload/font");
			for(int i = 0; i < attachList.size(); i++) {
				String filename = attachList.get(i);
				attachList.set(i, filepath  + "/" + filename);
			}
			
				
			
				
			
			
			new MailSend().purchaseMailSend(orderList, attachList);
			session.setAttribute("msg", "구매가 완료되었습니다. 구매하신 폰트는 메일로 보내드렸습니당");
			
		}
		
		
		
		String location = request.getHeader("Referer");
		response.sendRedirect(location);
		
		
	}

}
