package com.kh.elefont.community.controller;

import java.io.File;
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
import com.kh.elefont.font.model.vo.Font;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class CommunityUpdateServlet
 */
@WebServlet("/community/communityUpdate")
public class CommunityUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();
	private FontService fontService = new FontService();

	/**
	 * update form 페이지 요청! 
	 * 
	 * 값이 2개 존재하고있음!
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 1.사용자입력값 처리
		String commNo = request.getParameter("no");


		// 2.업무로직 
		Community community = communityService.selectOneCommunity(commNo);
		String fontNo = community.getFontNo();
		Font font = fontService.selectOneFontByFontNo(fontNo);
		Attachment attachment = attachmentService.selectOneAttachment(commNo);
	
		
		// 3.view단 위임
		request.setAttribute("attachment", attachment);
		request.setAttribute("community", community);
		request.setAttribute("font", font);
		request.setAttribute("attachment", attachment);
		request
			.getRequestDispatcher("/WEB-INF/views/community/communityUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * db update 요청!
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. MultipartRequest 객체
		System.out.println("커뮤니티수정doPost서블릿들어옴");
			String saveDirectory = getServletContext().getRealPath("/upload/community");
			int maxPostSize = 1024 * 1024 * 10; 
			String encoding = "utf-8";
			FileRenamePolicy policy = new ElefontFileRenamePolicy();
			MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			// 1. 사용자입력
			String commNo = multipartRequest.getParameter("no");
			String title = multipartRequest.getParameter("title");
			String writer = multipartRequest.getParameter("writer");
			String content = multipartRequest.getParameter("content");
			String font = multipartRequest.getParameter("font");
						
			Community community = new Community();
			
			community.setCommNo(commNo);
			community.setCommTitle(title);
			community.setCommWriter(writer);
			community.setCommContent(content);
			
			FontService fontService = new FontService();
			community.setFontNo(fontService.selectFontNoByFontName(font));
			
			System.out.println(community);
			// 첨부파일
			File f = multipartRequest.getFile("upFile");
			if(f != null) {
				Attachment attach = new Attachment();
				attach.setCommNo(commNo); 
				attach.setOriginalFilename(multipartRequest.getOriginalFileName("upFile"));
				attach.setRenamedFilename(multipartRequest.getFilesystemName("upFile"));
				community.setAttach(attach);
			}
			
			System.out.println("community@servlet = " + community);
			
			// 2. 업무로직 
			int result = 0;
			// 기존파일 삭제 (서버컴퓨터 파일 삭제 + db 레코드삭제)
			Attachment attach = attachmentService.selectOneAttachment(commNo);
			// 서버컴퓨터 파일 삭제
			File _delFile = new File(saveDirectory, attach.getRenamedFilename());
			_delFile.delete();
			// db 레코드삭제
			result = attachmentService.deleteAttachmentByCommNo(commNo);
			System.out.println(result > 0 ? "첨부파일 삭제 성공!" : "첨부파일 삭제 실패!");
			
			
			// 게시물 수정 + 첨부파일 등록
			result = communityService.updateCommunity(community);
			String msg = result > 0 ? "게시물 수정 성공!" : "게시물 수정 실패!";
			
			// 3. redirect
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/community/pictureDetail?commNo=" + commNo;
			response.sendRedirect(location);
	}


}
