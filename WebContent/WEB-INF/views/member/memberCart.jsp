<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="com.kh.elefont.like_cart.model.vo.MemberCartView"%>

<%
	List<MemberCartView> memberCartList= (List<MemberCartView>)request.getAttribute("memberCartList");
	System.out.println("memberCartList@jsp"+ memberCartList );
%>
   <!-- 장바구니 기본 폼 시작-->
    <section id="portfolio" class="portfolio section-space-padding">
        <div class="cart_outter">

            <div class="container" id= "cart-container">
            
                <div class="cart_title">
                    <span>주문내역</span>
                    <hr>
                </div>
      
<%
for(MemberCartView mcv : memberCartList){
	int disresult = (int)(mcv.getFontPrice()-(mcv.getFontPrice() * mcv.getFontDiscountRate()));
%>
  				<%-- <form name="cartFrm"  
                  	 action="<%=request.getContextPath() %>/member/memberCartDelete"
                  	 method="post"
                        enctype="multipart/form-data"
                        > --%>
	                 <div class="cart_content">
	                 
	                        <input type="checkbox" name="font_no" id="" value=<%=mcv.getFontNo()%>>
	                
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
	                            <button class="btn-order-from-cart">상품 주문하기</button>  
	                        </div>
	                    </div>
       <!--        </form> -->

<%
}
%>                
      
              
                <div class="cart_buttons">
                    <div class="check_all_box">
                        <input type="checkbox" name="check_all" id="check_all">
                        <label for="check_all">전체 선택</label>
                    </div>
                    <div class= "cart_bottom_buttons">
                    	<input type="submit"  id="check_all_delete" value="전체 삭제하기"/>
                    	<input type="submit"  id="selected_cart_delete" value="선택한 상품 삭제하기"/>
                    	<input type="submit"  id="select_order_font" value="선택한 상품 주문하기"/>
	                  <!--   <button id="check_all_delete">전체 삭제하기</button> -->
	                 <!--    <button id="selected_cart_delete">선택한 상품 삭제하기</button> -->
	         		 <!--  <button id="select_order_font">선택한 상품 주문하기</button> -->
                    </div>
                </div>
                
                
                
            </div>
            
            
        </section>
    </div>

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
	$(".btn-order-from-cart").click((e) =>{
		$(".payment_window").show();
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
	
	/* 선택된걸 배열에 넣어보는거야 */
/* 	$("#check_all_delete").click((e)=>{
		var chk_arr = $("input[name='chk[]']"); 
		var chk_data = []; 
		for( var i=0; i<chk_arr.length; i++ ) { 
			if( chk_arr.eq(i).is(":checked") == true ) { 
				chk_data.push(chk_arr.eq(i).value);
				} 
			}
		console.log("chk_data@jsp = "+chk_data);
	}); */


	/* $("#selected_cart_delete").click((e)=>{-
		let checkedCart = $('')
	}); */
</script>




    <!-- 결제 팝업 끝-->

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>