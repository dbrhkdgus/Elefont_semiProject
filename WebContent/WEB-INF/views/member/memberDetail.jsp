<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

  <!-- Portfolio Start -->
    <section id="member-mypage" class="member-mypage section-space-padding">
        <div class="member-container">
         <div class="member-profile">
            <i class="fas fa-user"></i>
            <h3>USER</h3>
            <button>설정</button>
            <hr>
            <div class="member-detail">
                <a href="">
                    <span>U1</span><br>
                    <span>구매등급</span>
                </a>
                <a href="<%=request.getContextPath()%>/cart/memberCart">
                    <span>3</span><br>
                    <span>장바구니</span>
                </a>
                <a href="<%=request.getContextPath()%>/member/memberLikeList">
                    <span>1</span><br>
                    <span>좋아요</span>
                </a>
            </div>
         </div>
         <div class="member-log">
            <div class="member-point">
                <span>USER</span>님의 현재 포인트는 <span>15,000</span>점입니다.
                <button id="member-coupon">쿠폰 등록</button>
            </div>
            <div class="member-comm">
                <h4>내가 쓴 커뮤니티</h4>
                <div class="member-list">
                    <a href=""><div class="my-comm-img"></div></a>
                    <a href=""><div class="my-comm-img"></div></a>
                    <a href=""><div class="my-comm-img"></div></a>
                    <a href=""><div class="my-comm-img"></div></a>
                </div>
            </div>
            <div class="member-font-like">
                <h4>내 좋아요 리스트</h4>
                <div class="member-list">
                    <a href=""><div class="my-font-img"></div></a>
                    <a href=""><div class="my-font-img"></div></a>
                   <a href=""><div class="my-font-img"></div></a>
                    <a href=""><div class="my-font-img"></div></a>
                </div>
            </div>
         </div>

        </div>
    </section>

    <!-- Portfolio End -->

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>