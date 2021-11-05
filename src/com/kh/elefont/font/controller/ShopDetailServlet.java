package com.kh.elefont.font.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.ElefontUtils;
import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.font.model.service.FontCopyrightService;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.font.model.vo.FontCopyright;
import com.kh.elefont.member.model.vo.Member;
import com.kh.elefont.order.model.service.OrderService;
import com.kh.elefont.order.model.vo.Order;
import com.kh.elefont.rep.model.service.RepService;
import com.kh.elefont.rep.model.vo.Rep;


/**
 * Servlet implementation class ShopDetailServlet
 */
@WebServlet("/shopDetail")
public class ShopDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();
	private CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();
	private RepService repService = new RepService();
	private FontCopyrightService fontCopyrightService = new FontCopyrightService();
	private OrderService orderService = new OrderService();
	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("categoryList");
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String memberNo = "";
		if(loginMember != null) 
			memberNo = loginMember.getMemberNo();
		
		
	
		
		try {
			//1.파리미터 fontNo
			String fontNo = request.getParameter("fontNo");
			
			//2.비지니스로직 호출
			// 읽음여부 확인(cookie)
			Cookie[] cookies = request.getCookies();
			boolean hasRead = false;
			String fontViewValue = "";
			
			if(cookies != null) {
				for(Cookie c : cookies) {
					String name = c.getName();
					String value = c.getValue();
					System.out.println(name + " : " + value);
					
					if("fontView".equals(name)) {
						fontViewValue = value;
						// 현재 게시글 읽음여부
						if(value.contains("|" + fontNo + "|")) {
							hasRead = true;
						}
						break;
					}
				}
			}
			
			System.out.printf("hasRead = %b, boardValue = %s%n", hasRead, fontViewValue);
			
			// 게시글을 처음 읽는 경우
			if(!hasRead) {
				// 게시글 Cookie
				Cookie cookie = new Cookie("fontView", fontViewValue + "|" + fontNo + "|");
				cookie.setMaxAge(365 * 24 * 60 * 60);
				cookie.setPath(request.getContextPath() + "/shopDetail"); // 해당 요청시만 cookie전송
				response.addCookie(cookie);
				
				//조회수 증가
				int result = fontService.updateFontViewCount(fontNo);			
			}
			
			
			//업무 로직
			Font font = fontService.selectOneFontByFontNo(fontNo);
			FontCopyright fontCopyright = fontCopyrightService.selectOneFontCopyrightByFontNo(fontNo);
			
			List<Community> communityList =  communityService.selectCommunityListByFontNo(fontNo);
			
			List<Attachment> commAttachmentList = attachmentService.selectAllCommAttachmentList();
			
			List<Rep> repList = repService.selectFontRepListByFontNo(fontNo);
			
			List<Order> orderList = orderService.selectAllOrderListByMemberNo(memberNo);
			
			List<Attachment> profileAttachList = attachmentService.selectAllprofileAttachmentList();
			
			List<Font> fontList = fontService.selectAllFont();
			//게시글 가져오기에 실패한경우
			if(font == null){
				request.getSession().setAttribute("msg", "조회한 폰트가 존재하지 않습니다.");
				response.sendRedirect(request.getContextPath() + "/board/boardList");
				return;
			}
			
			for(Rep rep : repList) {
				String reply = ElefontUtils.escapeHtml(rep.getRepContent());
				reply = ElefontUtils.convertLineFeedToBr(reply);
				rep.setRepContent(reply);
			}
			
			Map<String,Object> param = new HashMap<>();
			param.put("fontNo", fontNo);
			param.put("memberNo", memberNo);
			
			int likeValid = fontService.selectFontLike(param);
			System.out.println("shopDetail@servlet " + likeValid);
			
			//3.view단 처리위임
			request.setAttribute("font", font);
			request.setAttribute("communityList", communityList);
			request.setAttribute("commAttachmentList", commAttachmentList);
			request.setAttribute("repList", repList);
			request.setAttribute("fontCopyright", fontCopyright);
			request.setAttribute("likeValid", likeValid);
			request.setAttribute("orderList", orderList);
			request.setAttribute("profileAttachList", profileAttachList);
			session.setAttribute("fontList", fontList);
			
			//request.setAttribute("commentList", commentList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/shop/shopDetail.jsp");
			reqDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
		


}
