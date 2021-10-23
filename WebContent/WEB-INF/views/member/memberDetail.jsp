<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

     <!-- Portfolio Start -->
<%
	String memberRole = loginMember.getMemberRole();
	if("U".equals(memberRole)){
%>
     <section id="member-mypage" class="member-mypage section-space-padding">
        <div class="coupon-enroll">
            <form action="" method="POST" name="couponEnrollFrm">
                <h5>쿠폰 등록</h5>
                <h2>쿠폰 등록 번호</h2>
                <input type="text" class="coupon-no" name="coupon-no1" id="coupon-no1">
                <span>-</span>
                <input type="text" class="coupon-no" name="coupon-no2" id="coupon-no2">
                <span>-</span>
                <input type="text" class="coupon-no" name="coupon-no3" id="coupon-no3">
                <br>
                <span>총 금액</span>
                <h3 class="coupon-total">0P</h3>

                <input type="button" id="coupon-x-btn" value="취소하기">
                <input type="button" id="coupon-submit-btn" value="등록하기">
                <input type="hidden" name="memberId">
        </form>
        </div>
        <div class="member-container">
         <div class="member-profile">
            <i class="fas fa-user"></i>
            <h3>USER</h3>
            <button id="btn-member-Info-Edit">설정</button>
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
                <a href="<%=request.getContextPath()%>/member/fontLikeList">
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
<script>
	$("#member-coupon").click((e)=>{
		const $couponEnroll = $(".coupon-enroll");
		if($couponEnroll.css("display","none")){
			$couponEnroll.show();
			
			$("#coupon-submit-btn").click((e)=>{
				$(document.couponEnrollFrm).submit();
			});
			$("#coupon-x-btn").click((e)=>{
				$couponEnroll.hide();
			});
			
		}
		else return;
	});
	$("#btn-member-Info-Edit").click((e)=>{
		location.href = "<%= request.getContextPath()%>/member/memberInfoEdit";
	});
</script>
<%
}
%>
    <!-- Portfolio End -->

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>