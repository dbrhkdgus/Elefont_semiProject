<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%
String fontName = (String)request.getAttribute("fontName");
String fontPrice = (String)request.getAttribute("fontPrice");
String memberEmail = (String)request.getAttribute("memberEmail");
String memberNo = (String)request.getAttribute("memberNo");
%>
	    <section id="portfolio" class="portfolio section-space-padding">
            
            <div class="purchase-container">
                <h1>폰트 구매</h1>
                <form>
                    <p class="purchase-menu">구매하신 폰트:<%=fontName %></p>
                    <p class="purchase-menu">폰트가격:<%=fontPrice %></p>
                    <p class="purchase-menu">쿠폰등록하기:</p>
                    <button>쿠폰등록</button><span>쿠폰 적용 금액</span><br>
                    <label for="purchase-email-address">받으실 이메일 주소<%=memberEmail %></label>
                    <input type="text" name="purchase-email">
                    <input type="hidden"  name="member-no" value="<%=loginMember.getMemberNo()%>"/>
            </div>
                </form>
         </section>
    

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>