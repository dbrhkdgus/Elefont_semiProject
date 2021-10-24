<%@page import="java.util.List"%>
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
}else if("A".equals(memberRole)){
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
}else if("A".equals(memberRole)){
	List<Member> list = (List<Member>) session.getAttribute("list");
	System.out.println(list);
%>
		<div class="admin-container">
			<div class="admin-tab-bar">
				<ul>
					<li class="active"><a href="#">회원 관리</a></li>
					<li><a href="#">게시판 관리</a></li>
					<li><a href="#">주문 관리</a></li>
					<li><a href="#">쿠폰 관리</a></li>
				</ul>
			</div>
			<div id="tab-content">
				<div>
					<div class="memberLookup">
						<div class="user-search-bar">
							<input type="radio" name="memberRole" id="user" value="U" checked>
							<label for="memberRole0">일반 회원 보기</label>
							<input type="radio" name="memberRole" id="seller" value="S">
							<label for="memberRole1">판매 회원 보기</label>
							<input type="radio" name="memberRole" id="user" value="U" checked>
							<label for="memberRole2">관리 회원 보기</label>
							<input type="radio" name="memberRole" id="seller" value="S">
							<label for="memberRole3">휴면 회원 보기</label>
							<br />
							<select name="user-search" id="user-search">
								<option value="" selected>모든 회원 조회</option>
								<option value="id">Id로 검색</option>
								<option value="email">이메일로 검색</option>
								<option value="phone">연락처로 검색</option>
							</select>
							<input type="text" name="" id="" placeholder="검색할 내용을 입력하세요."/>
							<input type="button" value="검색" />
						</div>
						<table class="admin-tbl">
							<tr>
								<th>회원 구분</th>
								<th>회원 아이디</th>
								<th>회원 이름</th>
								<th>회원 이메일</th>
								<th>회원 포인트</th>
								<th>회원 연락처</th>
								<th>회원 탈퇴 여부</th>
							</tr>
<%
	if(list != null){
		for(Member m : list){
%>
							<tr>
								<td><%= m.getMemberRole() %></td>
								<td><%= m.getMemberId() %></td>
								<td><%= m.getMemberName() %></td>
								<td><%= m.getMemberEmail() %></td>
								<td><%= m.getMemberPoint() %></td>
								<td><%= m.getMemberPhone() %></td>
								<td><%= m.getMemberQuitYN() %></td>
							</tr>
<%
		}
	}
%>
						</table>
					</div>
				</div>
				<div>
					<table class="admin-tbl">
						<tr>
							<th>폰트 카테고리 관리</th>
							<th>커뮤니티 관리</th>
						</tr>
					</table>
				</div>
				<div>
					<div class="user-search-bar">
							<select name="user-search" id="user-search">
								<option value="" selected>모든 주문 조회</option>
								<option value="orderNo">주문 번호로 검색</option>
								<option value="orderDate">주문일로 검색</option>
								<option value="orderId">주문 회원으로 검색</option>
								<option value="orderFont">주문 상품으로 검색</option>
							</select>
							<input type="text" name="" id="" placeholder="검색할 내용을 입력하세요."/>
							<input type="button" value="검색" />
						</div>
					<table class="admin-tbl">
						<tr>
							<th>주문 번호</th>
							<th>주문일</th>
							<th>주문 회원</th>
							<th>주문 상품</th>
							<th>주문 가격</th>
						</tr>
					</table>
				</div>
				<div>
					<h3>사용자 쿠폰 관리</h3>
					<table class="admin-tbl">
						<tr>
							<th>쿠폰 조회</th>
							<th>쿠폰 발행</th>
							<th>쿠폰 충전</th>
						</tr>
						<tr>
							<td>
								<input type="text" placeholder="유저아이디로 검색" />
								<input type="button" value="검색" />
							</td>
							<td><button>신규 쿠폰 발행</button></td>
							<td>
								<input type="text" placeholder="유저아이디로 검색" />
								<input type="button" value="검색" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
<script>
/* 관리자 사용 메뉴 탭 클릭 시 다른 탭 보이도록 하는 효과 */
	let $tabBtn = $(".admin-tab-bar>ul>li");  
	let $tabContent = $("#tab-content>div"); 
	

	$tabContent.hide().eq(0).show();
	
	$tabBtn.click((e)=>{
	  const $target = $(e.target).parent();    
	  const index = $target.index(); 
	  $tabBtn.removeClass("active");
	  $target.addClass("active");   
	  $tabContent
		  .css("display","none")
		  .eq(index).css("display","block");
	});
</script>
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