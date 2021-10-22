<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

   <!-- 장바구니 기본 폼 시작-->
    <section id="portfolio" class="portfolio section-space-padding">
        <div class="cart_outter">

            <div class="container">
                <div class="cart_title">
                    <span>주문내역</span>
                    <hr>
                </div>
                
                <div class="cart_content_box">
                    <div class="cart_content">
                        <input type="checkbox" name="chose" id="" >
                        <img src="./images/shop_test_img.png" alt="" class="cart_content_img cart_content_margin">
                        <div class="cart_content_font_name cart_content_margin">
                            <h3>상품명</h3>
                            <div class="cart_font_name">
                                <p>고운돋움 Regular</p>
                            </div>
                        </div>
                        <div class="cart_content_font_price cart_content_margin">
                            <h3>판매가</h3>
                            <div class="cart_font_price">
                                <p>800P</p>
                                <p class="cart_price_explain">회원할인 20%</p>
                            </div>
                        </div>
                        
                        <div class="cart_content_pay_price">
                            <h3>주문금액</h3>
                            <p>640P</p>
                        </div>
                        <div class="cart_order  cart_content_margin">
                            <button>상품 주문하기</button>  
                        </div>
                    </div>
                    <div class="cart_content">
                        <input type="checkbox" name="chose" id="" >
                        <img src="./images/shop_test_img.png" alt="" class="cart_content_img cart_content_margin">
                        <div class="cart_content_font_name cart_content_margin">
                            <h3>상품명</h3>
                            <div class="cart_font_name">
                                <p>고운돋움 Regular</p>
                            </div>
                        </div>
                        <div class="cart_content_font_price cart_content_margin">
                            <h3>판매가</h3>
                            <div class="cart_font_price">
                                <p>800P</p>
                                <p class="cart_price_explain">회원할인 20%</p>
                            </div>
                        </div>
                        
                        <div class="cart_content_pay_price">
                            <h3>주문금액</h3>
                            <p>640P</p>
                        </div>
                        <div class="cart_order  cart_content_margin">
                            <button>상품 주문하기</button>  
                        </div>
                    </div>
                    <div class="cart_content">
                        <input type="checkbox" name="chose" id="" >
                        <img src="./images/shop_test_img.png" alt="" class="cart_content_img cart_content_margin">
                        <div class="cart_content_font_name cart_content_margin">
                            <h3>상품명</h3>
                            <div class="cart_font_name">
                                <p>고운돋움 Regular</p>
                            </div>
                        </div>
                        <div class="cart_content_font_price cart_content_margin">
                            <h3>판매가</h3>
                            <div class="cart_font_price">
                                <p>800P</p>
                                <p class="cart_price_explain">회원할인 20%</p>
                            </div>
                        </div>
                        
                        <div class="cart_content_pay_price">
                            <h3>주문금액</h3>
                            <p>640P</p>
                        </div>
                        <div class="cart_order  cart_content_margin">
                            <button>상품 주문하기</button>  
                        </div>
                    </div>
                    
                   
                    
                </div>
                <div class="cart_buttons">
                    <div class="check_all_box">
                        <input type="checkbox" name="check_all" id="check_all">
                        <label for="check_all">전체 선택</label>
                    </div>
                    <button>선택한 상품 삭제하기</button>
                    <button>선택한 상품 주문하기</button>
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
                <button>취소하기</button>
                <button>주문하기</button>


            </div>






        </div>





    <!-- 결제 팝업 끝-->

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>