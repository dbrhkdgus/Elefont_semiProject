package com.kh.elefont.community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class CommunityPictureDetailServlet
 */
@WebServlet("/community/pictureDetail")
public class CommunityPictureDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CommunityService communityService = new CommunityService();
	AttachmentService attachmentService = new AttachmentService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		List<String> commLikeList = new ArrayList<>();
		
		
		try {
			//1.파리미터 fontNo
			String commNo = request.getParameter("commNo");
			
			
			//2.비지니스로직 호출
			// 읽음여부 확인(cookie)
			Cookie[] cookies = request.getCookies();
			boolean hasRead = false;
			String communityViewValue = "";
			
			if(cookies != null) {
				for(Cookie c : cookies) {
					String name = c.getName();
					String value = c.getValue();
					System.out.println(name + " : " + value);
					
					if("communityView".equals(name)) {
						communityViewValue = value;
						// 현재 게시글 읽음여부
						if(value.contains("|" + commNo + "|")) {
							hasRead = true;
						}
						break;
					}
				}
			}
			
			System.out.printf("hasRead = %b, boardValue = %s%n", hasRead, communityViewValue);
			
			// 게시글을 처음 읽는 경우
			if(!hasRead) {
				// 게시글 Cookie
				Cookie cookie = new Cookie("commView", communityViewValue + "|" + commNo + "|");
				cookie.setMaxAge(365 * 24 * 60 * 60);
				cookie.setPath(request.getContextPath() + "/community/pictureDetail"); // 해당 요청시만 cookie전송
				response.addCookie(cookie);
				
				//조회수 증가
				int result = communityService.updateCommunityViewCount(commNo);			
			}
			
			
			System.out.println("commNo@servlet : " + commNo);
			
			Community community = new Community();
			community = communityService.selectOneCommunity(commNo);
			
			Attachment attachment = new Attachment();
			attachment = attachmentService.selectOneAttachment(commNo);
			
			List<Attachment> attachmentList = attachmentService.selectAllCommAttachmentListByMemberNo(attachment.getMemberNo());
			
			
			//게시글 가져오기에 실패한경우
			if(community == null){
				request.getSession().setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
				response.sendRedirect(request.getContextPath() + "/community/");
				return;
			}
			
			// XSS공격대비 
			// cross-site script공격. 악성코드를 웹페이지삽입하여 클라이언트의 개인정보탈취하는 공격법
			//String content = MvcUtils.escapeHtml(board.getContent());
			
			// 개행문자 br태그 변환처리
			//content = MvcUtils.convertLineFeedToBr(content);
			//board.setContent(content);
			
			// 댓글목록 가져오기
			//List<BoardComment> commentList = boardService.selectCommentList(no);
			//System.out.println("commentList@servlet = " + commentList);
			
			
			request.setAttribute("commLikeList", commLikeList);
			request.setAttribute("community", community);
			request.setAttribute("attachment", attachment);
			request.setAttribute("attachmentList", attachmentList);
			request.getRequestDispatcher("/WEB-INF/views/community/communityPictureDetail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
		
		

	}


