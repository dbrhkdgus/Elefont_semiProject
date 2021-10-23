<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

     <!-- Portfolio Start -->
<%
	String memberRole = loginMember.getMemberRole();
%>
     <section id="member-mypage" class="member-mypage section-space-padding">
<%
	if("U".equals(memberRole)){
%>
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
<%
}else if("S".equals(memberRole)){
%>
	<div class="font-enroll">
        <form action="" method="POST" name="fontEnrollFrm">
            <h3>등록하실 폰트명</h3>
            <div class="font-wrpper">
            <input type="text" name="font-name" id="font-name" placeholder="폰트명을 입력하세요"><i class="fas fa-check-circle"></i>
            </div>
            <h3>폰트파일 업로드</h3>
            <input type="file" name="font-file" id="font-file" />
            <input type="button" id="font-x-btn" value="취소하기">
            <input type="button" id="font-submit-btn" value="등록하기">
            <input type="hidden" name="memberId">
        </form>
<%
}
%>
        </div>
        <div class="member-container">
         <div class="member-profile">
            <i class="fas fa-user"></i>
            <h3><%=loginMember.getMemberId() %></h3>
            <button id="btn-member-Info-Edit">설정</button>
            <hr>
            <div class="member-detail">
<%
	if("U".equals(memberRole)){
%>
                <a href="">
                    <span><%=memberRole %></span><br>
                    <span>회원등급</span>
                </a>
                <a href="<%=request.getContextPath()%>/cart/memberCart">
                    <span>3</span><br>
                    <span>장바구니</span>
                </a>
                <a href="<%=request.getContextPath()%>/member/fontLikeList">
                    <span>1</span><br>
                    <span>좋아요</span>
                </a>
<%
}else if("S".equals(memberRole)){
%>
                <div>
                    <span>폰트 등록</span><br />
                    <div class="font-audit">2</div>
                </div>
                <div>
                    <span>심사중</span><br />
                    <div class="font-audit">0</div>
                </div>
                <a href="<%=request.getContextPath()%>/seller/fontAudit">
                    <span>심사 완료</span><br />
                    <div class="font-audit">3</div>
                </a>
<%
}else{
%>
                <div>
                    <div class=""><%= memberRole %></div>
                    <span>회원 등급</span>
                </div>
                <div>
                    <div class="">0</div>
                    <span>답변 완료</span>
                </div>
                <a href="<%=request.getContextPath()%>/admin/answerQnA">
                    <div class="f">3</div>
                    <span>답변 미완료</span>
                </a>

<%
}
%>
            </div>
         </div>
         <div class="member-log">
            <div class="member-title">
<%
	if("U".equals(memberRole)){
%>
                <span><%=loginMember.getMemberId() %></span>님의 현재 포인트는 <span>15,000</span>점입니다.
                <button id="member-coupon">쿠폰 등록</button>
<%
}else if("A".equals(memberRole)){
%>
                <span><%=loginMember.getMemberId() %></span>님, 안녕하세요!
<%
}else if("S".equals(memberRole)){
%>
                <span><%=loginMember.getMemberId() %></span>님, 안녕하세요!
                <button id="member-font">폰트 등록</button>
<%
}
%>
            </div>
<%
	if("U".equals(memberRole)){
%>
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
<%
}else if("S".equals(memberRole)){
%>
			<div class="data-graphs">
				<div>
					<h4>내 폰트 판매 현황</h4>
					<div class="data-bars">
						<div class="data-bar">
							<div data-width="3">혜진체<span>3건</span></div>
						</div>
						<div class="data-bar">
							<div data-width="4">광현체<span>4건</span></div>
						</div>
						<div class="data-bar">
							<div data-width="5">윤희체<span>5건</span></div>
						</div>
					</div>
				</div>
				<div>
					<h4>내 폰트 좋아요 현황</h4>
					<div class="data-bars">
						<div class="data-bar">
							<div data-width="30">혜진체<span>30건</span></div>
						</div>
						<div class="data-bar">
							<div data-width="45">광현체<span>45건</span></div>
						</div>
						<div class="data-bar">
							<div data-width="57">윤희체<span>57건</span></div>
						</div>
					</div>
					
				</div>
			</div>
<%
}else{
%>
			<div class="admin-tab-bar">
			<ul>
				<li><a href="">회원 관리</a></li>
				<li><a href="">게시판 관리</a></li>
				<li><a href="">주문 관리</a></li>
				<li><a href="">쿠폰 관리</a></li>
			</ul>
			</div>
			<div id="tab-content">
				<div>
				</div>
				<div>
				</div>
				<div>
				</div>
				<div>
				</div>
			</div>
<%
}
%>
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
	
	$("#member-font").click((e)=>{
		const $fontEnroll = $(".font-enroll");
		if($fontEnroll.css("display","none")){
			$fontEnroll.show();
			
			$("#font-submit-btn").click((e)=>{
				$(document.fontEnrollFrm).submit();
			});
			$("#font-x-btn").click((e)=>{
				$fontEnroll.hide();
			});
			
		}
		else return;
	});
	
	
</script>

    <!-- Portfolio End -->

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>