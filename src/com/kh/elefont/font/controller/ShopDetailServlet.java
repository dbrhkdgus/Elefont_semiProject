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
		List<String> fontNoList = fontService.selectAllFontNo();
		String memberNo = "";
		if(loginMember != null) 
			memberNo = loginMember.getMemberNo();
		
		try {
			//1.???????????? fontNo
			
			String fontNo = request.getParameter("fontNo");
			if(fontNoList.contains(fontNo)) {
					
				//2.?????????????????? ??????
				// ???????????? ??????(cookie)
				Cookie[] cookies = request.getCookies();
				boolean hasRead = false;
				String fontViewValue = "";
				
				if(cookies != null) {
					for(Cookie c : cookies) {
						String name = c.getName();
						String value = c.getValue();

						
						if("fontView".equals(name)) {
							fontViewValue = value;
							// ?????? ????????? ????????????
							if(value.contains("|" + fontNo + "|")) {
								hasRead = true;
							}
							break;
						}
					}
				}
				
				
				
				// ???????????? ?????? ?????? ??????
				if(!hasRead) {
					// ????????? Cookie
					Cookie cookie = new Cookie("fontView", fontViewValue + "|" + fontNo + "|");
					cookie.setMaxAge(365 * 24 * 60 * 60);
					cookie.setPath(request.getContextPath() + "/shopDetail"); // ?????? ???????????? cookie??????
					response.addCookie(cookie);
					
					//????????? ??????
					int result = fontService.updateFontViewCount(fontNo);			
				}
				
				
				//?????? ??????
				Font font = fontService.selectOneFontByFontNo(fontNo);
				FontCopyright fontCopyright = fontCopyrightService.selectOneFontCopyrightByFontNo(fontNo);
				
				List<Community> communityList =  communityService.selectCommunityListByFontNo(fontNo);
				
				List<Attachment> commAttachmentList = attachmentService.selectAllCommAttachmentList();
				
				List<Rep> repList = repService.selectFontRepListByFontNo(fontNo);
				
				List<Order> orderList = orderService.selectAllOrderListByMemberNo(memberNo);
				
				List<Attachment> profileAttachList = attachmentService.selectAllprofileAttachmentList();
				
				List<Font> fontList = fontService.selectAllFont();
				//????????? ??????????????? ???????????????
				if(font == null){
					request.getSession().setAttribute("msg", "????????? ????????? ???????????? ????????????.");
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
				
				//3.view??? ????????????
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
			}else {
				request.getRequestDispatcher("/WEB-INF/common/404.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
		


}
