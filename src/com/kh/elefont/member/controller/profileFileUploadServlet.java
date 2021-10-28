package com.kh.elefont.member.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.common.ElefontFileRenamePolicy;
import com.kh.elefont.common.model.vo.Attachment;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class profileFileUploadServlet
 */
@WebServlet("/member/profileFileUpload")
public class profileFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * db update 요청!
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * 1. 파일업로드(사용자파일을 서버컴퓨터에 저장) : MultipartRequest객체 생성
		 * 	a. HttpServletRequest
		 * 	b. saveDirectory 파일이 저장될 directory
		 *  c. maxPostSize 업로드 최대용량(10mb)
		 *  d. encoding
		 *  e. FileRenamePolicy객체 : DefaultFileRenamePolicy객체 -> MvcFileRenamePolicy객체
		 * 	
		 * 
		 * 2. 저장된 파일정보를 db에 저장
		 * 
		 * 
		 * *MultipartRequest객체를 생성하면 기존의 HttpServletRequest객체로부터 사용자입력값을 가져올수 없다.
		*/
		
		
		//b. saveDirectory 파일이 저장될 directory
		String saveDirectory = getServletContext().getRealPath("/upload/profilephotos");
		
		//c.maxPostSize 업로드 최대용량(10mb)
		int maxPostSize = 1024* 1024* 10;
		
		//d. encoding
		String encoding = "utf-8";
		
		//e. FileRenamePolicy객체 : DefaultFileRenamePolicy객체 -> MvcFileRenamePolicy객체
		FileRenamePolicy policy = new ElefontFileRenamePolicy();
		
		MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		
		//1.사용자 입력값 
		String memberNo = multipartRequest.getParameter("memberNo");
		System.out.println("프로필사진 업로드를 위한 memberNo을 잘 받았나요? : " + memberNo);
		
		String originalFilename = multipartRequest.getParameter("profileimage");
		String renamedFilename = multipartRequest.getFilesystemName("profileimage");
		
		
		
	}

}
