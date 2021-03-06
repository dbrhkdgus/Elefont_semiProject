package com.kh.elefont.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.ElefontFileRenamePolicy;
import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.member.model.service.MemberService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class proFileFileUploadServelt
 */
@WebServlet("/member/profileFileUpload")
public class ProFileFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService memberService = new MemberService();


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		// 0. MultipartRequest 객체
		String saveDirectory = getServletContext().getRealPath("/upload/profilephotos");
		int maxPostSize = 1024 * 1024 * 10; 
		String encoding = "utf-8";
		FileRenamePolicy policy = new ElefontFileRenamePolicy();
		MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		String memberNo = multipartRequest.getParameter("memberNo");

		
		
		int delResult = memberService.deletePrePhoto(memberNo); 
		
		File f = multipartRequest.getFile("profileimage");

		//파일 프젝 업로드 폴더에 저장된 거 확인함!
		
		
		Attachment attach = new Attachment();
		
		if(f != null) {
			attach.setMemberNo(memberNo);
			attach.setOriginalFilename(multipartRequest.getOriginalFileName("profileimage"));
			attach.setRenamedFilename(multipartRequest.getFilesystemName("profileimage"));
		}
		

		
		
		int result = memberService.insertProfileImage(attach);

		
		File profilePhotoAttach = new File(saveDirectory, attach.getRenamedFilename());

		
		if(attach != null ) {
			request.setAttribute("attach", attach);		
			session.setAttribute("profilePhotoAttach",profilePhotoAttach);
		}
		
		if(result>0) {
			request.getRequestDispatcher("/WEB-INF/views/member/popUpClose.jsp").forward(request, response);
		}
	}

}
