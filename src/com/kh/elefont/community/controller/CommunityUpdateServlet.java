package com.kh.elefont.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;

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
		System.out.println("community@servlet = " + community);
		
		// 3.view단 위임
		request.setAttribute("community", community);
		request.setAttribute("font", font);
		request
			.getRequestDispatcher("/WEB-INF/views/community/communityUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * db update 요청!
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 0. MultipartRequest 객체
//				String saveDirectory = getServletContext().getRealPath("/upload/board");
//				int maxPostSize = 1024 * 1024 * 10; 
//				String encoding = "utf-8";
//				FileRenamePolicy policy = new MvcFileRenamePolicy();
//				MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
//				
//				// 1. 사용자입력
//				int no = Integer.parseInt(multipartRequest.getParameter("no"));
//				String title = multipartRequest.getParameter("title");
//				String content = multipartRequest.getParameter("content");
//				
//				Board board = new Board(no, title, null, content, null, no, null);
//				
//				// 첨부파일
//				File f = multipartRequest.getFile("upFile");
//				if(f != null) {
//					Attachment attach = new Attachment();
//					attach.setBoardNo(no); 
//					attach.setOriginalFilename(multipartRequest.getOriginalFileName("upFile"));
//					attach.setRenamedFilename(multipartRequest.getFilesystemName("upFile"));
//					board.setAttach(attach);
//				}
//				
//				System.out.println("board@servlet = " + board);
//				
//				// 2. 업무로직 
//				int result = 0;
//				// 기존파일 삭제 (서버컴퓨터 파일 삭제 + db 레코드삭제)
//				String delFile = multipartRequest.getParameter("delFile");
//				if(delFile != null) {
//					int attachNo = Integer.parseInt(delFile);
//					Attachment attach = boardService.selectOneAttachment(attachNo);
//					// 서버컴퓨터 파일 삭제
//					File _delFile = new File(saveDirectory, attach.getRenamedFilename());
//					_delFile.delete();
//					// db 레코드삭제
//					result = boardService.deleteAttachment(attachNo);
//					System.out.println(result > 0 ? "첨부파일 삭제 성공!" : "첨부파일 삭제 실패!");
//				}
//				
//				// 게시물 수정 + 첨부파일 등록
//				result = boardService.updateBoard(board);
//				String msg = result > 0 ? "게시물 수정 성공!" : "게시물 수정 실패!";
//				
//				// 3. redirect
//				request.getSession().setAttribute("msg", msg);
//				String location = request.getContextPath() + "/board/boardView?no=" + no;
//				response.sendRedirect(location);
//	}

}
