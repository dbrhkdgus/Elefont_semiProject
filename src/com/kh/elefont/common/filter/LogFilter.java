package com.kh.elefont.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter{

	/**
	 * 실행내용을 doFilter에 작성
	 * 
	 * @param ServletRequest request : HttpServletRequest의 부모 인터페이스
	 * @param ServletResponse response : HttpServletResponse의 부모 인터페이스
	 * @param FilterChain chain : Filter객체의 묶음
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. servlet 호출 전에 실행

		System.out.println("========================================");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		System.out.println(httpReq.getRequestURI()); // /mvc/member/memberDetail
		System.out.println("----------------------------------------");
		
		//chain객체의 동일한 이름의 doFilter메소드 반드시 호출
		// filter chain에 관리되는 다음 filter의 doFilter를 호출
		// 만약 마지막 filter라면 servlet 메소드를 호출
		chain.doFilter(request, response);
		
		// 2. servlet 호출 후 응답메시지 발송 전 실행
		System.out.println("________________________________________");
	}

}
