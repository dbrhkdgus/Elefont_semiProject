package com.kh.elefont.seller.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.font.model.service.FontCategoryService;
import com.kh.elefont.font.model.service.FontCopyrightService;
import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.font.model.vo.FontCategory;
import com.kh.elefont.font.model.vo.FontCopyright;
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
	private FontCategoryService fontCategoryService = new FontCategoryService();
	private FontCopyrightService fontCopyrightService = new FontCopyrightService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/seller/sellerFontEnroll.jsp").forward(request, response);
	}


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
		System.out.println("renamedFilename@servlet : " + renamedFilename);		
		
		//1. 사용자 입력값 받기
		String memberId = multipartRequest.getParameter("memberId");
		String fontName = multipartRequest.getParameter("font-name");
		System.out.println(multipartRequest.getParameter("font-price"));
		double fontPrice = Double.parseDouble(multipartRequest.getParameter("font-price"));
		String fontUrl = multipartRequest.getParameter("font-url");
		
		String memberNo = multipartRequest.getParameter("memberNo");
		
		Font font = new Font();
		font.setFontName(fontName);
		font.setFontPrice(fontPrice);
		font.setFontUrl(fontUrl);
		font.setMemberId(memberId);
		
		System.out.println("font@servlet = " + font);
		
		if(multipartRequest.getFile("font-file") != null) {
			Attachment attach = new Attachment();
			attach.setMemberNo(memberNo);
			attach.setOriginalFilename(originalFilename);
			attach.setRenamedFilename(renamedFilename);
			font.setAttach(attach);
		}
		
		// 카테고리 코드 부여해야함!
		String lang = multipartRequest.getParameter("font-language");
		String fontStyle = multipartRequest.getParameter("font-style");
		String fontCategoryCode = "";
		
		
			
			switch(lang) {
			case "KO" : fontCategoryCode += "KO-"; break;
			case "EN" : fontCategoryCode += "EN-"; break;
			case "AL" : fontCategoryCode += "AL-"; break;
			}
		
			switch(fontStyle) {
			case "S" :  fontCategoryCode += "S-"; break;
			case "G" :  fontCategoryCode += "G-"; break;
			case "H" :  fontCategoryCode += "H-"; break;
			case "M" :  fontCategoryCode += "M-"; break;
			}
		
		String _fontReleaseYear = multipartRequest.getParameter("font-release-year");
		Date fontReleaseYear = null;
		if(_fontReleaseYear != null && !"".equals(_fontReleaseYear))
			fontReleaseYear = Date.valueOf(_fontReleaseYear);
		
		fontCategoryCode += _fontReleaseYear.replace("-", "");
		
		System.out.println("fontCategoryCode@serlvet = " + fontCategoryCode);
		
		FontCategory fontCategory = new FontCategory();
		fontCategory.setCategoryCode(fontCategoryCode);
		fontCategory.setCategoryReleaseYear(fontReleaseYear);
		fontCategory.setFontNo(font.getFontNo());
		
		
		
		String fontPublisher = multipartRequest.getParameter("font-publisher");
		String fontDesigner = multipartRequest.getParameter("font-designer");
		String fontRootUrl = multipartRequest.getParameter("font-root-url");
		
		FontCopyright fontCopyright = new FontCopyright();
		fontCopyright.setFontNo(font.getFontNo());
		fontCopyright.setFontPublisher(fontPublisher);
		fontCopyright.setFontDesigner(fontDesigner);
		fontCopyright.setFontRootUrl(fontRootUrl);
		
		
		
		//2. 업무 로직
		int result = fontService.insertFont(font);
		String msg = result > 0? "등록되었습니다.":"등록 실패";
		
		Font fontForCheck = fontService.selectOneFontByFontnameNFontUrl(fontName, fontUrl);
		// 폰트 카테고리 테이블에 저장
		result = fontCategoryService.insertFontCategory(fontForCheck.getFontNo(),fontCategory);
				
		// 폰트 저작권정보 테이블에 저장
		result = fontCopyrightService.insertFontCopyright(fontForCheck.getFontNo(), fontCopyright);	
		//3. 뷰단 처리
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		String location = request.getHeader("Referer");
		response.sendRedirect(location);
	}

}