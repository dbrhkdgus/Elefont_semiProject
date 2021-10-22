package com.kh.elefont.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

	/**
	 * Filter Chain에서 실행 순서
	 * 1. web.xml에 선언된 순서
	 * 2. @WebFilter annotation을 사용하는 경우. 필터 이름 순서로 처리
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//사용자 입력값에 대한 encoding처리
		request.setCharacterEncoding("utf-8");
		System.out.println("[utf-8 encoding 처리]");
		
		chain.doFilter(request, response);
	}

}
