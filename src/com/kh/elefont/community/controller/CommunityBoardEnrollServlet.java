package com.kh.elefont.community.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.ElefontFileRenamePolicy;
import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.member.model.service.MemberService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class CommunityBoardEnrollServlet
 */
@WebServlet("/community/boardEnroll")
public class CommunityBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommunityService communityService = new CommunityService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/community/communityBoardEnroll.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		
				// b. 파일저장경로
						// ServletContext객체로부터  /WebContent/upload/board 절대경로 참조
						ServletContext application = getServletContext();
						String saveDirectory = application.getRealPath("/upload/community");
						
						
						// c.최대파일용량 10mb = 1kb * 1000 * 10
						int maxPostSize = 1024 * 1024 * 10;
						
						// d.인코딩
						String encoding = "utf-8";
						
						// e.파일명 재지정 정책 객체
						FileRenamePolicy policy = new ElefontFileRenamePolicy();
						
						MultipartRequest multipartRequest =
								new MultipartRequest(
										request,
										saveDirectory,
										maxPostSize,
										encoding,
										policy
									);
						
						// 파일정보 가져오기
						String originalFilename = multipartRequest.getOriginalFileName("upFile");
						String renamedFilename = multipartRequest.getFilesystemName("upFile");
				
				
				
				// 2. 입력값 처리
				String title = multipartRequest.getParameter("title");
				String writer = multipartRequest.getParameter("writer");
				String content = multipartRequest.getParameter("content");
				String font = multipartRequest.getParameter("font");
				
				Community community = new Community();
				
				community.setCommTitle(title);
				community.setCommWriter(writer);
				community.setCommContent(content);
				
				
				
				FontService fontService = new FontService();
				community.setFontNo(fontService.selectFontNoByFontName(font));
				
				System.out.println("community@servlet : " + community);
				
				
				
				// 3. 업무로직
				int result = communityService.enrollBoard(community);
				
				
				if(multipartRequest.getFile("upFile") != null) {
					Attachment attach = new Attachment();
					attach.setOriginalFilename(originalFilename);
					attach.setRenamedFilename(renamedFilename);
					MemberService memberService = new MemberService();
					System.out.println(memberService.selectMemberNoByMemberName(writer));
					attach.setMemberNo(memberService.selectMemberNoByMemberName(writer));
					System.out.println(communityService.selectLastCommNo());
					attach.setCommNo(communityService.selectLastCommNo());
					// attachment insert sql
					AttachmentService attachmentService = new AttachmentService();
					result = attachmentService.insertAttachment(attach);
					
					
					community.setAttach(attach);
				}
				
				String msg = result > 0 ? "게시물 등록 성공" : "게시물 등록 실패";
				
				HttpSession session = request.getSession();
				session.setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath()+"/community");
}
}
