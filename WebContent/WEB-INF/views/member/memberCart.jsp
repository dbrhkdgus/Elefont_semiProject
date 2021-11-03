<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="com.kh.elefont.like_cart.model.vo.MemberCartView"%>

<%
	List<MemberCartView> memberCartList= (List<MemberCartView>)request.getAttribute("memberCartList");
%>
   <!-- 장바구니 기본 폼 시작-->
    <section id="portfolio" class="portfolio section-space-padding">
        <div class="cart_outter">

            <div class="container" id= "cart-container">
<%
if(!memberCartList.isEmpty()){
%>
                <div class="cart_title">
                    <span>주문내역</span>
                    <hr>
                </div>
     	<form name="cartFrm"  action="<%=request.getContextPath() %>/member/memberCartDelete" method="post" id="cartFrm" >
<%
for(MemberCartView mcv : memberCartList){
	
	int disresult = (int)(mcv.getFontPrice()-(mcv.getFontPrice() * mcv.getFontDiscountRate()));
%>
  	
	                 <div class="cart_content" style="width:'100%';">
	                 
	                		<input type="hidden" name="cart_no" value=<%=mcv.getCartNo() %>>
	                		<input type="hidden" name="member_no" value=<%=mcv.getMemberNo()%>>
	                		<input type="hidden" name="font_no" value=<%=mcv.getFontNo()%>>
	                		<input type="hidden" name="font_name" value=<%=mcv.getFontName()%>>
	                        <input type="checkbox" name="chk_cart_no" class="check-box" value=<%=mcv.getCartNo()%>>
	                		<input type="hidden" name="font_price" value=<%=mcv.getFontPrice()%>>

	                        <img src="./images/shop_test_img.png" alt="" class="cart_content_img cart_content_margin">
	                        <div class="cart_content_font_name cart_content_margin">
	                            <h3>상품명</h3>
	                            <div class="cart_font_name">
	                                <p><%=mcv.getFontName() %></p>
	                            </div>
	                        </div>
	                        <div class="cart_content_font_price cart_content_margin">
	                            <h3>판매가</h3>
	                            <div class="cart_font_price">
	                                <p><%=mcv.getFontPrice() %></p>
	                                <p class="cart_price_explain">회원 할인<%=mcv.getFontDiscountRate() %> %</p>
	                            </div>
	                        </div>
	                        
	                        <div class="cart_content_pay_price">
	                            <h3>주문금액</h3>
	                            <p><%=disresult %>P</p>
	                        </div>
	                        <div class="cart_order  cart_content_margin">
	                        	<input type="button" class="btn-delete-from-cart" value="삭제하기"/>
	                            <!-- <button class="btn-delete-from-cart">삭제하기</button>   -->
	                        </div>
	                    </div>
	         
       
<%
}
%>              
	                <div class="cart_buttons">
	                    <div class="check_all_box">
	                        <input type="checkbox" name="check_all" id="check_all">
	                        <label for="check_all">전체 선택</label>
	                    </div>
	                    <div class= "cart_bottom_buttons">
	                    	<input type="button"  id="check_all_delete" value="전체 삭제하기">
	                    	<input type="button"  id="selected_cart_delete" value="선택한 상품 삭제하기">  
	                    	<input type="button"  id="select_order_font" value="주문하기">	  
	                    	<input type="hidden"  name="type" value="">
	                                  
	                    </div>
	                </div>
		 </form> 	
		
			            
       	      
        
      
	             
	        
                
                
            </div>
<%
}else{
%>    
			
		<div class="member_cart_null_box" >
			<img alt="우는코끼리" src="https://i.ibb.co/DzynHD7/image.png">
			<h1>장바구니가 텅~</h1>
		</div>
<%
}
%>
		        
            
    </div>
  </section>

    <!-- 장바구니 기본 폼 끝 -->


    <!-- 결제 팝업 시작-->

        <div class="payment_window">
            <div class="payment_infomation">

                <div class="payment_font_info">
                    <p class="payment_font_title">선택하신 상품명</p>
                    <h2>고운 돋움 Regular</h2>
                    <h2>티웨이항공체</h2>
                </div>
                <div class="payment_bar">
                    
                    

                </div>
                <div class="payment_coupon">
                    <p>적용가능한 쿠폰</p>
                    <select name="payment_coupon" id="payment_coupon">
                        
                        <option value="">쿠폰을 선택하세요.</option>
                        <option value="">할인 쿠폰</option>
                        <option value="">회원가입기념쿠폰</option>
                    </select>
                       
                    <p>주문하신 상품이 맞는지 <br> 다시 한번 확인해주세요.</p>
                    <p>총 주문 금액</p>
                    <span>1600P</span>
                </div>
                
            </div>
            <div class="payment_buttons">
                <button id="btn-payment-cancle">취소하기</button>
                <button>주문하기</button>
          </div>
        </div>
<script>
	$(".payment_window").hide();
	
	/* 선택한 단일 폰트 삭제 */
	$(".btn-delete-from-cart").click((e) =>{
		/* $(".payment_window").show(); */
		if(confirm("해당 상품을 삭제하시겠습니까?") ){
			/* console.log(`\${$(e.target).parent().parent().children("input").val()}`); */
			location.href= `<%=request.getContextPath() %>/member/memberCartDelete?cartNo=\${$(e.target).parent().parent().children("input").val()}`; 
		}
	});
	
	/* 선택한 상품 다중 삭제 */
	$("#selected_cart_delete").click((e)=>{
		$("input[name='type']").val('delete');		
		$(document.cartFrm).submit();
	});
	
	
	$("#btn-payment-cancle").click((e)=>{
		$(".payment_window").hide();
	});
	
	/* 전체선택해제버튼 */
	$("#check_all").click((e)=>{
		if($("input:checkbox[id='check_all']").prop("checked")){
			$("input[type=checkbox]").prop("checked" ,true);
		} else {
			$("input[type=checkbox]").prop("checked" ,false);
		}
	});
	
	
   
   
	/*전체삭제하기 버튼*/
	$("#check_all_delete").click((e)=>{		
		$("input[name='type']").val('delete');	
		$("input[type=checkbox]").prop("checked" ,true);			
		$(document.cartFrm).submit();		
	});
	
	
	/* 주문하기 버튼 */
	$("#select_order_font").click((e)=>{
		var chekObj = document.getElementsByClassName("check-box");
		console.log(chekObj);
		var lenth = chekObj.length;
		var memberPoint = <%=loginMember.getMemberPoint() %>;
		var fontName = [];
		var price = 0;
		
		  for (i = 0; i < lenth; i++) {
			    if (chekObj[i].checked === true) {
			      
			      
			      fontName.push(chekObj[i].previousElementSibling.getAttribute("value"));
			      price += Number(chekObj[i].nextElementSibling.getAttribute("value"));
			      
			    }
			  }
		  
		  console.log(price);
		
		
			if(confirm(`\${fontName} 폰트의 구매를 진행합니다.
총 \${price}P가 결제됩니다. (현재 보유 포인트 : \${memberPoint}P)`)){
				
				$("input[name='type']").val('purchase');
				$(document.cartFrm).submit();	
			}
		
	
				
				
			$("input[name='type']").val('purchase');
			$(document.cartFrm).submit();	
			
		
		
		
	});
	
	
	
</script>




    <!-- 결제 팝업 끝-->

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>