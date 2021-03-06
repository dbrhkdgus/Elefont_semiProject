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

import com.kh.elefont.common.ElefontUtils;
import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.DeletedCommunity;
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
import com.kh.elefont.question.model.service.QuestionService;
import com.kh.elefont.rep.model.service.RepService;
import com.kh.elefont.rep.model.vo.DeletedRep;


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
	private QuestionService questionService = new QuestionService();
	private CommunityService communityService = new CommunityService();
	private RepService repService = new RepService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//memberRole??? ???????????? ?????? ?????? ??????????????? DB?????? ????????? ?????? ????????? ???
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberRole = loginMember.getMemberRole();

		
		//?????? ????????? ?????? ????????????
		Attachment profile = attachmentService.selectProfileAttachment(loginMember.getMemberNo());
		request.setAttribute("profile", profile);
		
		if("A".equals(memberRole)) {
			int index = Integer.parseInt(request.getParameter("index"));
			List<Member> memberList = memberService.selectAllMember();
			List<Coupon> couponList = couponService.selectAllCoupon();
			List<Order> orderList = orderService.selectAllOrder();
			List<Font> fontList = fontService.selectAllFont();
			List<FontCategory> categoryList = fontService.selectAllFontCategory();
			List<Attachment> attachmentList = attachmentService.selectAllFontAttachmentList();
			List<DeletedCommunity> deletedCommList = communityService.selectAllDeletedCommList();
			List<DeletedRep> deletedRepList = repService.selectAllDeletedRepList();
			
			int notAnsweredQuestionCnt = questionService.selectNotAnseredQuestionCnt();
			int answeredQuestionCnt = questionService.selectAnsweredQuestionCnt();
			
			for(DeletedCommunity dc : deletedCommList) {
				String dcContent = ElefontUtils.escapeHtml(dc.getCommContent());
				dcContent = ElefontUtils.convertLineFeedToBr(dcContent);
				dc.setCommContent(dcContent);
			}
			for(DeletedRep dr : deletedRepList) {
				String drContent = ElefontUtils.escapeHtml(dr.getRepContent());
				drContent = ElefontUtils.convertLineFeedToBr(drContent);
				dr.setRepContent(drContent);
			}
			
			request.setAttribute("fontList", fontList);
			request.setAttribute("notAnsweredQuestionCnt", notAnsweredQuestionCnt); 
			request.setAttribute("answeredQuestionCnt", answeredQuestionCnt); 
			request.setAttribute("attachmentList", attachmentList);
			request.setAttribute("memberList", memberList);
			request.setAttribute("couponList", couponList);
			request.setAttribute("orderList", orderList);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("tabIndex", index);
			request.setAttribute("deletedCommList", deletedCommList);
			request.setAttribute("deletedRepList", deletedRepList);
		}else if("U".equals(memberRole)) {
			List<Attachment> commAttachmentList = attachmentService.selectAllCommAttachmentListByMemberNo(loginMember.getMemberNo());
			List<Font> fontLikeList = fontService.selectAllLikedFontByMemberNo(loginMember.getMemberNo());
			List<Font> fontPurchasedList = fontService.selectAllPurchasedFontByMemberNo(loginMember.getMemberNo());
			List<Coupon> coupounList = couponService.selectAllCouponByMemberNo(loginMember.getMemberNo());
			List<Font> fontList = fontService.selectAllApprovedFontOrderByDate();
			int cartCount = likeCartService.selectCartCountByMemberNo(loginMember.getMemberNo());
			
			session.setAttribute("fontList", fontList);
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
			
			List<Font> checkedListByLikeCnt = fontService.selectFontLikeCnt(loginMember.getMemberId());
			List<Font> checkedListByPurchasedCnt = fontService.selectFontPurchasedCnt(loginMember.getMemberId());
			
			//	memberId??? ????????? ?????? ???????????? ?????? ?????????(audit)/?????? ??????/?????? ?????????/????????? ????????? ????????? ???????????? ????????? ???????????? ?????? ?????? ?????? ?????? session??? ??????
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
			request.setAttribute("checkedListByLikeCnt", checkedListByLikeCnt);
			request.setAttribute("checkedListByPurchasedCnt", checkedListByPurchasedCnt);

		}
		
		// ????????? ???????????? ????????? ????????? ?????? ????????? ???
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
			.forward(request, response);
	}

}
