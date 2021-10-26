package com.kh.elefont.seller.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.font.model.service.FontService;
import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class SellerFontAuditServlet
 */
@WebServlet("/seller/fontAudit")
public class SellerFontAuditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FontService fontService = new FontService();

	/**
	 * @author hyejin 폰트 심사 페이지로 들어갈 때 해당 아이디가 들어가 있는 폰트 정보들을 불러와 view단에 출력
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		//1. 사용자 입력값 처리 - 없음
		
		//2. 업무 로직
		List<Font> list = fontService.selectFontByMemberId(member.getMemberId());
		List<Font> approvalList = new ArrayList<>();
		List<Font> checkedList = new ArrayList<>();
		List<Font> auditList = new ArrayList<>();
		
		//	memberId로 조회한 폰트 목록들을 심사 대기중(audit)/심사 승인/심사 미승인/판매자 체크로 나누어 분리하고 분리한 리스트가 비어 있지 않은 경우 session에 저장
		for(Font f : list) {
			if("Y".equals(f.getFontApproval()) || "N".equals(f.getFontApproval())){
				approvalList.add(f);
			}
			else if("C".equals(f.getFontApproval())) {
				checkedList.add(f);
			}
			else {
				auditList.add(f);
			}
		}
		
		if(!approvalList.isEmpty()) {
			session.setAttribute("approvalList", approvalList);
		}
		if(!checkedList.isEmpty()) {
			session.setAttribute("checkedList", checkedList);
		}
		if(!auditList.isEmpty()) {
			session.setAttribute("auditList", auditList);
		}
		
		//3. view단 연결
		request.getRequestDispatcher("/WEB-INF/views/seller/sellerManagement.jsp").forward(request, response);
		
	}

}
