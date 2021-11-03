<%@page import="com.kh.elefont.like_cart.model.vo.MemberCartView"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%
String fontNo = (String)request.getAttribute("fontNo");
String fontName = (String)request.getAttribute("fontName");
List<MemberCartView> mcvList = (List<MemberCartView>)request.getAttribute("mcvList");
System.out.println("mcvList @JSP : " + mcvList);
String fontPrice = (String)request.getAttribute("fontPrice");
String memberEmail = (String)request.getAttribute("memberEmail");
String memberNo = (String)request.getAttribute("memberNo");
%>
	    <section id="portfolio" class="portfolio section-space-padding">
            
            <div class="purchase-container">
                <h1>폰트 구매</h1>
                <form action="<%= request.getContextPath()%>/font/fontPurchase" method="POST" name="fontPurchaseFrm">
                
                    <p class="purchase-menu">
                    	<span> 구매하신 폰트 : </span>
<% 

%>                    	              
                    	<span><%=fontName %></span>
                    </p>
                    <p class="purchase-menu"><span>폰트가격 : </span><span><%=fontPrice %></span></p>
                    <p class="purchase-menu"><span>쿠폰등록하기 : </span> </p>
                    <button>쿠폰등록</button><span>쿠폰 적용 금액</span><br>
                    <label for="purchase-email-address">받으실 이메일 주소</label>
                    <input type="text" name="purchase-email" value="<%=memberEmail %>">
                    <input type="hidden"  name="member-no" value="<%=loginMember.getMemberNo()%>"/>
                    <input type="hidden"  name="font-no" value="<%= fontNo %>"/>
                    <input type="hidden"  name="font-price" value="<%= fontPrice %>"/>
                    
                    <br />
                    <input type="button" name ="btn-purchase" value = "구매하기" />
                    <input type="button" name ="btn-cancle" value = "취소" />
                </form>
            </div>
         </section>
         
    	 <script>
    	 /* 취소버튼 -> 이전페이지로 돌아가기 */
    	 $("input[name=btn-cancle]").click((e)=>{
    		 history.go(-1);
    	 });
    	 
    	 $("input[name=btn-purchase]").click((e)=>{
    		const memberPoint = <%= loginMember.getMemberPoint()%>;
    		const fontPrice = <%= fontPrice%>;
    		if(fontPrice > memberPoint){
    			alert(`보유하신 포인트가 부족하여 구매를 진행할 수 없습니다.
(현재 보유 포인트 : \${memberPoint}P)`);
    		}else{
    			if(confirm(`확인 버튼을 클릭 시 보유하신 포인트 (\${memberPoint}P)에서 
\${fontPrice}P 차감됩니다.`)){
    				$(document.fontPurchaseFrm).submit();
    			}
    			
    		}
    		
    		
    		 
    	 });
    	 
    	 </script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>