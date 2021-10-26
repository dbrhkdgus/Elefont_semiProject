<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

<% Font font = (Font)request.getAttribute("font"); %>

 <section id="portfolio" class="portfolio section-space-padding">
           <div class="shop-detail">
                <div class= "shop-detail-top">
                    <div class="shop-detail-font-name"><h2><%= font.getFontName() %></h2></div>
                    <div class="shop-detail-buttons">
                    
                        <button id="purchase-button" name="button" type="button" onclick="location.href='<%= request.getContextPath() %>/member/memberCart';" >구매</button>
                        <button id="cart-button" name="button" type="button">장바구니</button>
                        <button id="like-button" name="button" type="button">좋아요</button>
                    </div>
                </div>

                <div class="shop-detail-middle">
                    <div class="sd-font-previewer">
                        <h4  id="font-previewer-title">폰트미리보기</h4 >
                        <hr class="liner">
                        <textarea id="shop-detail-textarea" name="shop-detail-previewer" rows="5" cols="60"></textarea>
                    </div>    
                    
                    <div class="shop-detail-reply">
                        <h4  id="shop-detail-rep">댓글</h4>
                        <hr class="liner">
                            <div class="shop-detail-reblybox">
                                <input type="text" id="detail-inputbox"name="reply-input" placeholder="댓글을 입력하세요">
                                <div class="reply-box"><img src="https://cdn1.vectorstock.com/i/1000x1000/10/05/user-icon-vector-22391005.jpg"  id="user-profile"><span>user: 폰트가 너무 예뻐요</span>
                                </div>
                            
                        
                        
                        
                            </div>

                    </div>
                </div>
                

                    <div class="sd-review-section">
                        <h4 id="shop-detail-review">폰트후기</h4>
                        <hr class="liner">
                        <div class="sd-review">
                            <img src="https://i.ibb.co/bmJDkqr/image.jpg "  id="sd-review-img" >   
                            <div class="sd-review-box">
                                <h2>리뷰제목</h2>
                                <span id="sd-review-text">Lorem ipsum dolor sit amet consectetur adipisicing elit. Pariatur veniam, praesentium labore nesciunt similique doloribus, cumque aperiam laborum ex perferendis delectus? Nesciunt soluta quasi eveniet dolor nisi? Nobis, unde aut!</span>  
                            </div>
                        </div> 
                    </div>
                
                    <div class="sd-copyright-section">
                        <h4 id="sd-copyright-title">저작권 정보</h4>
                        <hr class="liner">
                        <p id="sd-copyright-content">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ducimus totam tempora autem. Totam nemo, distinctio ipsam culpa vel excepturi fugit enim veritatis vitae tempore! Sed architecto voluptatem recusandae facilis dolorum.</p>
                        
                    </div>        
                
                
                
                
                </div>


            </div>








         </section>


<%@ include file = "/WEB-INF/views/common/footer.jsp" %>
