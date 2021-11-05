package com.kh.elefont.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.elefont.like_cart.model.service.LikeCartService;
import com.kh.elefont.like_cart.model.vo.MemberCartView;

import com.kh.elefont.member.model.vo.Member;

/**
 * Servlet implementation class MemberCartServlet
 */
@WebServlet("/member/memberCart")
public class MemberCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LikeCartService likeCartService = new LikeCartService();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 현재 loginMember가 좋아요를 눌렀는지 안 눌렀는지 확인 -> 안 눌렀으면 카운트 올리기/ 눌렀으면 카운트 빼기
				HttpSession session = request.getSession();
				Member member = (Member)session.getAttribute("loginMember");
				//1. 사용자 입력값 처리
				String fontNo = request.getParameter("fontNo");
				String PerCartType = request.getParameter("PerCartType");
				
				String cartNo = "cart-" + System.currentTimeMillis();
				String memberNo = member.getMemberNo();
				
				
				
				//System.out.println("fontNo, memberNo, PerCartType @ servlet = "+ fontNo + memberNo + PerCartType);
				//System.out.println("cart_no@servlet : "+ cartNo);
				
				
				//2. 업무 로직
				//like_font 테이블에서 조회. DQL이지만 존재 여부 확인 후, DML문 처리가 있을 예정이므로 int값으로 받는다.
				//json 변환할 데이터 객체 생성
				Map<String, Object> map = new HashMap<>();
				int insertCart = 0;
				int DeleteCart = 0;
				
				// 뷰테이블로 조회해서 해볼 것!!
				
				List<MemberCartView> memberCartViewList = likeCartService.selectAllMemberCartViewByMemberNo(memberNo);
				boolean flag = false;
				for(MemberCartView mcv : memberCartViewList) {
					if(mcv.getFontNo().equals(fontNo) ) {
						flag = true;
						break;
					}
				
				}
				if(!flag) {
					
					insertCart = likeCartService.insertCart(cartNo, fontNo);
					insertCart = likeCartService.insertMemberCart(memberNo, cartNo);					
				}
				
			
				map.put("insertCart", insertCart);
								
//				//json문자열로 변환
				Gson gson = new Gson();
				String jsonStr = gson.toJson(map);
				
				
				//3. view단 처리
				response.setContentType("application/json; charset = utf-8");
				response.getWriter().print(jsonStr);
				}

	
	//김은희가 만든부분........틀리면여기부터...보시오..
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			
			Member member = (Member)session.getAttribute("loginMember");
			
			
			String cartNo = request.getParameter("cartNo");
			String memberNo = member.getMemberNo();


			
			List<MemberCartView> memberCartList = new ArrayList<>();
			memberCartList = likeCartService.selectMemberCartList(memberNo);
			
			
				request.setAttribute("memberCartList", memberCartList);
				request
				.getRequestDispatcher("/WEB-INF/views/member/memberCart.jsp")
				.forward(request, response);

			}
		
	}


