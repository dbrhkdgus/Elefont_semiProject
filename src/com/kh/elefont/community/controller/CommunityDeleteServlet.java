package com.kh.elefont.community.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.common.model.service.AttachmentService;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.service.CommunityService;
import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.rep.model.service.RepService;

/**
 * Servlet implementation class CommunityDeleteServlet
 */
@WebServlet("/community/communityDelete")
public class CommunityDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommunityService communityService = new CommunityService();
	private AttachmentService attachmentService = new AttachmentService();
	private RepService repService = new RepService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.사용자 입력값
		String commNo = request.getParameter("no");
		System.out.println("commNo@servlet+delete" + commNo);
		//2.업무로직
		Community community = communityService.selectOneCommunity(commNo);
		Attachment attachment = attachmentService.selectOneAttachment(commNo);
		System.out.println("renamedFilename@servlet" + attachment.getRenamedFilename());
		
		ServletContext application = getServletContext(); 
		String saveDirectory = application.getRealPath("/upload/community");
		String filename = attachment.getRenamedFilename();
		File delFile = new File(saveDirectory, filename);
		boolean delBool = delFile.delete();
		System.out.printf("첨부파일[%s] 삭제여부 : %b%n", filename, delBool);
		if(delBool == true) {
			attachmentService.deleteAttachmentByCommNo(commNo);
		}
		int repResult = repService.deleteCommRep(commNo);
		int result = communityService.deleteCommunity(commNo);
		String msg = (repResult > 0 && result > 0) ? "게시물 삭제 성공!" : "게시물 삭제 실패!";
		
		//3.사용자메세지 및 redirect처리
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/community");
	}

}
