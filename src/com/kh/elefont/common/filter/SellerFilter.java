package com.kh.elefont.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.member.model.service.MemberService;
import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet Filter implementation class SellerFilter
 */
@WebFilter("/seller/*")
public class SellerFilter implements Filter {

		/**
		 * 관리자가 아닌 부정요청에 대한 처리
		 */
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			HttpServletRequest httpReq = (HttpServletRequest)request;
			HttpServletResponse httpRes = (HttpServletResponse)response;
			HttpSession session = httpReq.getSession();
			
			Member loginMember = ((Member)session.getAttribute("loginMember"));
			//System.out.println("[관리자 권한 페이지 요청 @AdminFilter]");
			
			if(loginMember == null || !((MemberService.SELLER_ROLE).equals(loginMember.getMemberRole()))){
				session.setAttribute("msg", "판매자만 사용가능합니다.");
				httpRes.sendRedirect(httpReq.getContextPath() + "/");
				return;
			}
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

	}