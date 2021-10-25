package com.kh.elefont.seller.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class SellerFontEnrollServlet
 */
@WebServlet("/seller/fontEnroll")
public class SellerFontEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 파일 업로드
		//	a. 파일 저장 경로
		ServletContext application = getServletContext();
		String saveDirectory = application.getRealPath("/upload/font");
		
		//	b. 최대 파일 용량 
		int maxPostSize = 1024 * 1024 * 20;
		
		//	c. 인코딩
		String encoding = "utf-8";
		
		//	d. 파일명 재지정 정책 객체
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest multipartRequest
				= new MultipartRequest(
						request,
						saveDirectory,
						maxPostSize,
						encoding,
						policy);
		
		//	파일 정보 가져오기
		String originalFilename = multipartRequest.getOriginalFileName("font-file");
		String renamedFilename = multipartRequest.getFilesystemName("font-file");
				
		
		//1. 사용자 입력값 받기
		String memberId = request.getParameter("memberId");
		String fontName = request.getParameter("font-name");
		int fontPrice = Integer.parseInt(request.getParameter("font-price"));
		String fontUrl = request.getParameter("font-url");
		
		Font font = new Font(0,fontName,fontUrl,fontPrice,0,0,0,0,null);
		//2. 업무 로직
		//3. 뷰단 처리
	}

}
