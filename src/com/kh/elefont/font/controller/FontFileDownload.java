package com.kh.elefont.font.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.font.model.service.FontService;

/**
 * Servlet implementation class FontFileDownload
 */
@WebServlet("/font/fontDownload")
public class FontFileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FontService fontService = new FontService();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.
		String fontNo = (String)(request.getParameter("fontNo"));
		System.out.println("제가 fontNO을 JSP에서 잘 받아왔나요? ㅜㅜ" + fontNo);
		
		
		//2. 업무로직
		Attachment attach = fontService.selectOneFontAttachmentByFontNo(fontNo);
		System.out.println("attach@servlet = " + attach);
		
		
		
		//3.
		
		// 저장된 경로의 파일 입력스트림
		String saveDirectory = getServletContext().getRealPath("/upload/font");
		String filename = attach.getRenamedFilename();
		File downFile = new File(saveDirectory, filename);
		System.out.println("downFile@servlet = " + downFile);
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
		
		// 응답메세지 출력스트림
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		// 응답메세지 헤더작성
		response.setContentType("application/octet-stream; charset=utf-8");
		// 헤더부 한글깨짐 방지
		String responseFilename =  new String(attach.getOriginalFilename().getBytes("utf-8"), "iso-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename=" + responseFilename);
		
		// 입력 & 출력
		int read = -1;
		while((read = bis.read()) != -1) {
			bos.write(read);
		}
		
		// 자원반납
		bos.close();
		bis.close();
		
		
		
	}

}
