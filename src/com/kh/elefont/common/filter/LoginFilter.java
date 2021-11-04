package com.kh.elefont.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({ 
	"/member/memberDelete", 
	"/member/memberUpdate", 
	"/member/updatePassword", 
	"/community/boardEnroll",
	"/community/communityUpdate",
	"/member/memberDetail",
	"/member/fontLikeList",
	"/member/commLikeList",
	"/member/memberCart",
	"/seller/*"
	})
public class LoginFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//login여부 확인
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null) {
			session.setAttribute("msg", "로그인 후 이용할 수 있습니다.");
			HttpServletResponse httpRes = (HttpServletResponse) response;
			httpRes.sendRedirect(httpReq.getContextPath()+"/");
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
