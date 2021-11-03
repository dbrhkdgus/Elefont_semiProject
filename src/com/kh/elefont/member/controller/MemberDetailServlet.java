package com.kh.elefont.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.kh.elefont.like_cart.model.service.LikeCartService;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;
import com.kh.elefont.order.model.service.OrderService;
import com.kh.elefont.order.model.vo.Order;

/**
 * Servlet implementation class MemberDetailServlet
 */
@WebServlet("/member/memberDetail")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private FontService fontService = new FontService();
	private AttachmentService attachmentService = new AttachmentService();
	private CouponService couponService = new CouponService();
	private OrderService orderService = new OrderService();
	private LikeCartService likeCartService = new LikeCartService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//memberRole이 관리자인 경우 전체 회원정보를 DB에서 불러와 함께 전달할 것
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberRole = loginMember.getMemberRole();
//		System.out.println(memberRole);
		
		//회원 프로필 사진 불러오기
		Attachment profile = attachmentService.selectProfileAttachment(loginMember.getMemberNo());
		request.setAttribute("profile", profile);
		
		if("A".equals(memberRole)) {
			List<Member> memberList = memberService.selectAllMember();
			List<Font> fontList = fontService.selectAllFont();
			List<Coupon> couponList = couponService.selectAllCoupon();
			List<Order> orderList = orderService.selectAllOrder();
			List<FontCategory> categoryList = fontService.selectAllFontCategory();
			List<Attachment> attachmentList = attachmentService.selectAllFontAttachmentList();
			
			request.setAttribute("attachmentList", attachmentList);
			request.setAttribute("memberList", memberList);
			request.setAttribute("fontList", fontList);
			request.setAttribute("couponList", couponList);
			request.setAttribute("orderList", orderList);
			request.setAttribute("categoryList", categoryList);
			session.setAttribute("tabIndex", 0);
		}else if("U".equals(memberRole)) {
			List<Attachment> commAttachmentList = attachmentService.selectAllCommAttachmentListByMemberNo(loginMember.getMemberNo());
			List<Font> fontLikeList = fontService.selectAllLikedFontByMemberNo(loginMember.getMemberNo());
			List<Font> fontPurchasedList = fontService.selectAllPurchasedFontByMemberNo(loginMember.getMemberNo());
			List<Coupon> coupounList = couponService.selectAllCouponByMemberNo(loginMember.getMemberNo());
			int cartCount = likeCartService.selectCartCountByMemberNo(loginMember.getMemberNo());
			request.setAttribute("commAttachmentList", commAttachmentList);
			request.setAttribute("fontLikeList", fontLikeList);
			request.setAttribute("fontPurchasedList", fontPurchasedList);
			request.setAttribute("couponList", coupounList);
			request.setAttribute("cartCount", cartCount);
		}else if("S".equals(memberRole)) {
			List<Font> list = fontService.selectFontByMemberId(loginMember.getMemberId());
			List<Font> approvalList = new ArrayList<>();
			List<Font> checkedList = new ArrayList<>();
			List<Font> auditList = new ArrayList<>();
			
			//	memberId로 조회한 폰트 목록들을 심사 대기중(audit)/심사 승인/심사 미승인/판매자 체크로 나누어 분리하고 분리한 리스트가 비어 있지 않은 경우 session에 저장
			for(Font f : list) {
				if("Y".equals(f.getFontApproval()) || "N".equals(f.getFontApproval())){
					approvalList.add(f);
				}
				else if("C".equals(f.getFontApproval())) {
					checkedList.add(f);
				}
				else {
					auditList.add(f);
				}
			}
			
			request.setAttribute("approvalList", approvalList);
			request.setAttribute("checkedList", checkedList);
			request.setAttribute("auditList", auditList);
			
			

		}
		
		// 회원의 커뮤니티 게시글 조회를 위해 전달할 것
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
			.forward(request, response);
	}

}
