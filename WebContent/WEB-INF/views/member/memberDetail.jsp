<%@page import="com.kh.elefont.coupon.model.vo.Coupon"%>
<%@page import="com.kh.elefont.font.model.vo.FontExt"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
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
}
%>
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
                <span><%=loginMember.getMemberId() %></span>님의 현재 포인트는 <span><%= loginMember.getMemberPoint() %></span>점입니다.
                <button id="member-coupon">쿠폰 등록</button>
<%
}else if("A".equals(memberRole)){
%>
                <span><%=loginMember.getMemberId() %></span>님, 안녕하세요!
<%
}else if("S".equals(memberRole)){
%>
                <span><%=loginMember.getMemberId() %></span>님, 안녕하세요!
                <button id="member-font" onclick="location.href='<%=request.getContextPath()%>/seller/fontEnroll'">폰트 등록</button>
<%
}
%>
            </div>
<%
if("U".equals(memberRole)){
List<Attachment> commAttachmentList = (List<Attachment>)request.getAttribute("commAttachmentList");
List<Font> fontLikeList = (List<Font>) request.getAttribute("fontLikeList");
List<Font> fontPurchasedList = (List<Font>) request.getAttribute("fontPurchasedList");
List<Coupon> couponList = (List<Coupon>) request.getAttribute("couponList");

%>
            <div class="member-comm">
                <h4>내가 쓴 커뮤니티</h4>
                <div class="member-list">
<% for(Attachment att : commAttachmentList){
	
%>
                    <a href="<%=request.getContextPath()%>/community/board"><div class="my-comm-img"><img src="<%=request.getContextPath()%>/upload/community/<%=att.getRenamedFilename()%>" alt="" /></div></a>

<%	
	}
%>
                    
                </div>
            </div>
            <div class="member-font-like">
                <h4>내 좋아요 리스트</h4>
                <div class="member-list">
<%
	for(Font f : fontLikeList){
%>
                    <a href="<%=request.getContextPath()%>/shopDetail?fontNo=<%=f.getFontNo()%>"><div class="my-font-img"><%=f.getFontName() %></div></a>
<%
	}
%>
                </div>
            </div>

            <div class="member-font-purchase">
                <h4>내 구매내역</h4>
                <div class="fix-head">
					<table class="member-tbl fix-tbl">
						<thead>
							<tr>
								<th width="80px">주문 일자</th>
								<th width="130px">주문 번호</th>
								<th width="200px">구매 폰트</th>
								<th width="130px">폰트 가격</th>
								<th width="90px">할인율</th>
								<th width="130px">결제 가격</th>
							</tr>
						</thead>
						<tbody>
<%
	if(!fontPurchasedList.isEmpty()){
		for(Font _fe : fontPurchasedList){
				FontExt fe = (FontExt) _fe;
%>
							<tr>
								<td><%=fe.getMemberOrderDate() %></td>
								<td><%=fe.getMemberOrderNo() %></td>
								<td><%=fe.getFontName() %></td>
								<td><%=fe.getFontPrice() %></td>
								<td><%=fe.getFontDiscountRate() %></td>
								<td><%=fe.getFontPrice()*fe.getFontDiscountRate() %></td>
							</tr>
<%
		}
	}else{
%>
							<tr>
								<td colspan="6">구매 내역이 없습니다.</td>
							</tr>
<%
	}
%>
						</tbody>
					</table>
                </div>
            </div>
            <div class="member-coupon-list">
                <h4>내 쿠폰 내역</h4>
                <div class="fix-head">
					<table class="member-tbl fix-tbl">
						<thead>
							<tr>
								<th width="80px">발급 일자</th>
								<th width="130px">쿠폰 종류</th>
								<th width="200px">쿠폰 번호</th>
								<th width="130px">할인율/포인트</th>
								<th width="130px">유효기간</th>
								<th width="90px">사용 여부</th>
							</tr>
						</thead>
						<tbody>
<%
	if(!couponList.isEmpty()){
		for(Coupon c : couponList){
%>
							<tr>
								<td><%=c.getCouponRegDate() %></td>
								<td><%="P".equals(c.getCouponType())? "포인트 쿠폰" : "할인 쿠폰" %></td>
								<td><%=c.getCouponNo() %></td>
								<td>
									<%="P".equals(c.getCouponType())? c.getCouponPAmount()+"p" : c.getCouponDiscount()+"%" %>
								</td>
								<td><%= c.getCouponExpDate() %>일 까지</td>
								<td><%= "Y".equals(c.getCouponUsed())? "사용 완료" : "미사용" %></td>
							</tr>
<%
		}
	}else{
%>
							<tr>
								<td colspan="6">사용 가능한 쿠폰이 없습니다</td>
							</tr>
<%
	}
%>
						</tbody>
					</table>
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
	List<Member> memberList = (List<Member>) session.getAttribute("memberList");
	List<Font> fontList = (List<Font>) session.getAttribute("fontList");
	List<Coupon> couponList = (List<Coupon>) session.getAttribute("couponList");
	int tabIndex = (int)session.getAttribute("tabIndex");
	System.out.println("tabIndex@jsp = " + tabIndex);
%>
		<div class="admin-container">
			<div class="admin-tab-bar">
				<ul>
					<li class="active"><a href="#">회원 관리</a></li>
					<li><a href="#">게시판 관리</a></li>
					<li><a href="#">주문 관리</a></li>
					<li><a href="#">쿠폰 관리</a></li>
					<li><a href="#">폰트 관리</a></li>
				</ul>
			</div>
			<div id="tab-content">
				<div>
					<div class="memberLookup">
						<div class="user-search-bar controlls">
							<select name="search-type" id="user-search">
								<option value="all" selected>모든 회원 조회</option>
								<option value="id">Id로 검색</option>
								<option value="email">이메일로 검색</option>
								<option value="phone">연락처로 검색</option>
							</select>
							<input type="text" name="search-keyword" id="" placeholder="검색할 내용을 입력하세요."/>
							<input type="button" value="검색" id="memberFinder"/>
							<br />
							<input type="radio" name="memberRole" id="allMember" value=""  checked>
							<label for="memberRole0">모든 회원 보기</label>
							<input type="radio" name="memberRole" id="user" value="M" >
							<label for="memberRole1">일반 회원 보기</label>
							<input type="radio" name="memberRole" id="seller" value="S">
							<label for="memberRole2">판매 회원 보기</label>
							<input type="radio" name="memberRole" id="admin" value="A">
							<label for="memberRole3">관리 회원 보기</label>
						<!-- 	<input type="radio" name="memberRole" id="" value="D">
							<label for="memberRole4">휴면 회원 보기</label> -->

						</div>
						<div class="fix-head">
						<table class="admin-tbl fix-tbl">
							<thead>
								<tr>
									<th width="80px">회원 구분</th>
									<th width="130px">회원 아이디</th>
									<th width="80px">회원 이름</th>
									<th width="200px">회원 이메일</th>
									<th width="90px">회원 포인트</th>
									<th width="130px">회원 연락처</th>
									<th width="80px">탈퇴 여부</th>
								</tr>
							</thead>
<%

	if(memberList != null){
		for(Member m : memberList){
%>
							<tr 
							class="allMember <%= "U".equals(m.getMemberRole())? "user": "S".equals(m.getMemberRole())? "seller":"admin" %>"
							>
							
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
	}else{
%>
							<tr>
								<td colspan="7">회원이 없습니다</td>
							</tr>
<%
	}
%>
						</table>
						</div>
					</div>
				</div>
				<div>
					<div id="admin-board-section">
						<div class="admin-board">
							<h4>폰트 카테고리 관리</h4>
							<table id="font-category-tbl">
								<tr>
									<th>No.</th>
									<th>분류명</th>
									<th>관리</th>
								</tr>

							</table>
						</div>
						<div class="admin-board">
							<h4>커뮤니티 관리</h4>
							<ul>
								<li><a href="<%= request.getContextPath()%>/admin/answerQnA">1:1 문의 관리</a></li>
								<li><a href="">삭제 게시글/댓글 관리</a></li>
								<li><a href="<%= request.getContextPath()%>/community">커뮤니티로 이동</a></li>
							</ul>
							
						</div>
					</div>
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
					<div class="fix-head">
						<table class="admin-tbl fix-tbl">
							<thead>
								<tr>
									<th>주문 번호</th>
									<th>주문일</th>
									<th>주문 회원</th>
									<th>주문 상품</th>
									<th>주문 가격</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div>
					<div class="coupon-enroll-wrapper">
						<form action="" method="POST" name="couponEnrollFrm">
							<table id="coupon-enroll-tbl">
								<tr>
									<th >쿠폰 발행</th>
									<td colspan="2">
										<input type="radio" name="couponType" id="point" value="P" checked>
										<label for="couponType0">포인트 쿠폰</label>
										<input type="radio" name="couponType" id="discount" value="D">
										<label for="couponType1">할인 쿠폰</label>
									</td>
								</tr>
								<tr>
									<td>
										<span>포인트/할인율</span>
										<input type="text" name="couponRate" id="couponRate" />
									</td>
									<td>
										<span>쿠폰 매수</span>
										<input type="number" name="couponCnt" id="couponCnt" value="1"/>
									</td>
									<td rowspan="2">
										<input type="button" value="발행" id="couponEnrollBtn"/>
									</td>
								</tr>
								<tr>
									<td>
										<span>쿠폰 유효기간</span>
										<input type="text" name="couponExpired" id="couponExpired" placeholder="발급일로부터 일"/>
									</td>
									<td>
										<span>회원 아이디 입력</span>
										<input type="text" name="memberId" id="memberId" placeholder="회원 아이디를 입력하세요" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
									</td>
								</tr>							
							</table>
							
						</form>
						<div id="coupon-result">
						<!-- 발행된 쿠폰 번호 출력할 div -->
						</div>
					</div>
					<div id="coupon-head" class="fix-head">
						<table id="coupon-tbl"class="fix-tbl">
							<thead>
								<tr>
									<th>쿠폰 발급일</th>
									<th>쿠폰 번호</th>
									<th>쿠폰 종류</th>
									<th>쿠폰 유효기간</th>
									<th>쿠폰 사용여부</th>
									<th>포인트 값/할인율</th>
									<th>회원 번호</th>
								</tr>
							</thead>
<%
	if(!couponList.isEmpty()){
		for(Coupon c : couponList){	
%>
								<tr>
									<td><%= c.getCouponRegDate() %></td>
									<td><%= c.getCouponNo() %></td>
									<td><%= "P".equals(c.getCouponType())? "포인트 쿠폰" : "할인 쿠폰" %></td>
									<td><%= c.getCouponRegDate() %>~<%= c.getCouponExpDate()%></td>
									<td><%= "Y".equals(c.getCouponUsed())? "사용 완료" : "미사용" %></td>
									<td><%= "P".equals(c.getCouponType())? c.getCouponPAmount()+"p":c.getCouponDiscount()+"%" %></td>
									<td><%= c.getMemberNo() == null? "사용자 없음" : c.getMemberNo() %></td>
								</tr>								
<%
		}
	}else{
%>
								<tr>
									<td colspan="7">쿠폰이 없습니다.</td>
								</tr>
<%
	}
%>
						</table>
					</div>
				</div>
				<div>
					<div class="fontLookup">
						<div class="font-search-bar">
							<input type="radio" name="fontAuditYN" id="font-a" value="A" checked>
							<label for="fontAuditYN0">모든 폰트 보기</label>
							<input type="radio" name="fontAuditYN" id="font-w" value="N">
							<label for="fontAuditYN1">심사 대기 폰트 보기</label>
							<input type="radio" name="fontAuditYN" id="font-n" value="N">
							<label for="fontAuditYN1">미승인 폰트 보기</label>
							<input type="radio" name="fontAuditYN" id="font-y" value="Y">
							<label for="fontAuditYN2">승인 폰트 보기</label>
							<br />
							<select name="font-search" id="font-search">
								<option value="all" selected>모든 폰트 조회</option>
								<option value="font-no">폰트번호로 검색</option>
								<option value="font-name">폰트명으로 검색</option>
								<option value="font-seller">폰트 판매자로 검색</option>
							</select>
							<input type="text" name="font-search-keyword" id="" placeholder="검색할 내용을 입력하세요."/>
							<input type="button"  id="fontFinder" value="검색" />
						</div>
						<form action="<%=request.getContextPath()%>/admin/fontUpdate" method="POST" name="adminFontUpdateFrm" id="adminFontupdateFrm">
							<div class="fix-head">
							<table id="font-tbl " class="fix-tbl">
								<thead>
									<tr>
										<th width="70px">폰트 승인</th>
										<th width="180px">폰트 번호</th>
										<th width="100px">폰트명</th>
										<th width="100px">폰트 가격</th>
										<th width="80px">폰트 할인율</th>
										<th width="100px">폰트 파일</th>
										<th width="100px">판매 회원</th>
										<th width="70px">회원 확인</th>
									</tr>
								</thead>
								<tbody>
<%
	if(fontList != null){
		for(Font f : fontList){
%>
								<tr class="font-a <%=f.getFontApproval() == null? "font-w": "N".equals(f.getFontApproval())? "font-n" :"font-y" %>">
									<td>
										<select class="font-approval">
											<option value="" <%= (f.getFontApproval() == null)? "selected":"" %>>심사 대기</option>
											<option value="N" <%= "N".equals(f.getFontApproval())?"selected":"" %>>미승인</option>
											<option value="Y" <%= ("Y".equals(f.getFontApproval()) || "C".equals(f.getFontApproval()))?"selected":"" %>>승인</option>
										</select>
										<input type="hidden" name="fontApproval" />
									</td>
									<td><%= f.getFontNo() %><input type="hidden" name="fontNo" value="<%= f.getFontNo() %>"/></td>
									<td><%= f.getFontName() %></td>
									<td>
										<input type="text" name="fontPrice" id="fPrice" placeholder="<%= f.getFontPrice() %>"/>
									</td>
									<td>
										<input type="text" name="fontDiscountRate" id="fDCRate" placeholder="<%= f.getFontDiscountRate() %>"/>
									</td>
									<td>
<% 
		if(f.getAttach() != null){
%>
										<input type="button" value="파일 다운로드" class="fontDownloadBtn"/>			
<%
		}
%>	
									</td>
									<td><%= f.getMemberId() %></td>
									<td>
									<%="C".equals(f.getFontApproval())? "확인 완료": "" %>
									</td>
								</tr>
<%
		}
	}else{
%>
								<tr>
									<td colspan="8">등록된 폰트가 없습니다</td>
								</tr>
<%
	}

%>
							</tbody>
						</table>
							
						</div>
							<input type="button" value="수정" id="fontUpdateBtn"/>
						</form>
					</div>
				</div>
			</div>
		</div>
<script>
/* 관리자 사용 메뉴 탭 클릭 시 다른 탭 보이도록 하는 효과 */
let $tabBtn = $(".admin-tab-bar>ul>li");  
let $tabContent = $("#tab-content>div"); 


$tabContent.hide().eq(<%=tabIndex%>).show();
$tabBtn
	.removeClass("active")
	.eq(<%=tabIndex%>).addClass("active"); 

$tabBtn.click((e)=>{
  const $target = $(e.target).parent();    
  const index = $target.index(); 
  $tabBtn.removeClass("active");
  $target.addClass("active");   
  $tabContent
	  .css("display","none")
	  .eq(index).css("display","block");
});
	
/* 회원 관리 - radio버튼 선택 시 해당 클래스명을 가진 tr 제외 display: none 처리 */
$("[name=memberRole]").change((e)=>{
	$filter = $(e.target).attr("id");
	$(".allMember").hide();
	$("."+$filter).show();
	
});
/* 회원 관리 - 회원 검색 버튼 클릭 시 search-type, search-keyword 전송 */
$(memberFinder).click((e)=>{
	const $searchType = $("[name=search-type]").val();
	const $searchKeyword = $("[name=search-keyword]").val();
	console.log($searchType);
	console.log($searchKeyword);
	location.href = "<%=request.getContextPath()%>/admin/memberFinder?searchType="+$searchType+"&searchKeyword="+$searchKeyword;
});

/* 폰트 관리 - 폰트 검색 버튼 클릭 시 search-type, search-keyword 전송 */
$(fontFinder).click((e)=>{
	const $searchType = $("[name=font-search]").val();
	const $searchKeyword = $("[name=font-search-keyword]").val();
	console.log($searchType);
	console.log($searchKeyword);
	location.href = "<%=request.getContextPath()%>/admin/fontFinder?searchType="+$searchType+"&searchKeyword="+$searchKeyword;
});

/* 폰트 관리 - radio버튼 선택 시 해당 클래스명을 가진 tr 제외 display: none 처리 */
$("[name=fontAuditYN]").change((e)=>{
	$filter = $(e.target).attr("id");
	$(".font-a").hide();
	$("."+$filter).show();
	
});

/* 폰트 관리 - 회원 폰트 다운로드 버튼 클릭 시 파일 다운로드 */
$(".fontDownloadBtn").click((e)=>{
    $fontNo = $(e.target).parent().prevAll().eq(2).html();
    console.log($fontNo);
    location.href = "<%=request.getContextPath()%>/font/fontDownload?fontNo=" + $fontNo; 
});

/* 폰트 업데이트 버튼 클릭 시, price와 discountRate에 변경사항이 없을 경우, 기존 값을 전달*/
	$(fontUpdateBtn).click((e)=>{
		const $fontPrice = $("[name = fontPrice]");
		const $fontDiscountRate = $("[name=fontDiscountRate]");
		const $fontApproval = $("[name=fontApproval]");
		
		$.each($fontApproval, function(index, item){
			$item = $(item);
			let $fontApprYN = $(".font-approval").eq(index).val();
			$item.val($fontApprYN);
			console.log($item.val());
		});	
		$.each($fontPrice, function(index, item){
			$item = $(item);
			if(!$item.val()){
				$item.val($item.attr('placeholder'));
			}
		});

		$.each($fontDiscountRate, function(index, item){
			$item = $(item);
			if(!$item.val()){
				$item.val($item.attr('placeholder'));
			}
		});
		
		$(document.adminFontUpdateFrm).submit();
		
	});

/* 쿠폰 발급 이벤트 */
$(couponEnrollBtn).click((e)=>{
	const $frmData = $(document.couponEnrollFrm);
	let couponMemberId = $("#memberId").val();
	
	$.ajax({
		url : "<%=request.getContextPath()%>/coupon/couponEnroll",
		data : $frmData.serialize(),
		type: "POST",
		dataType : "json",
		success(data){
			const $ol = $("<ol></ol>");
			const $couponResult = $("#coupon-result");
			$couponResult.html("");
			if(data.length == 1){
				if(data[0] === "Complete") alert(`\${couponMemberId}님에게 정상적으로 쿠폰이 발급되었습니다.`);
				else $ol.append("<li>"+ data[0] +"</li>");
			}
			else{
				data.forEach(element =>{
					$ol.append(`<li>\${element}</li>`);
				});
			}
			$couponResult.append($ol);
		},
		error: console.log
	});
});

/* 쿠폰 이벤트 - 회원 아이디 입력 시 해당하는 회원 아이디와 번호 보여주기 */
$(memberId).autocomplete({
	source(request, response){
		const {term : searchId} = request;
		$.ajax({
			url: "<%=request.getContextPath()%>/member/autocomplete",
			data: {searchId},
			success(data){
				let temp = $.map(data, (element)=>{
					return{
						label : element,
						value : element
					}
				});
				response(temp);
			},
			error : console.log
		});
	}
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
	location.href = "<%= request.getContextPath()%>/member/memberInfoEdit?memberId=<%=loginMember.getMemberId()%>&memberNo=<%=loginMember.getMemberNo()%>";
});

/* 조회 창 높이 행 입력량에 따라 조절*/
$(window).load((e)=>{
	const $fixHead = $(".fix-head");
	$.each($fixHead, function(index, item){
		let $item = $(item);
		let length = $(item).find('tr').length;
		console.log(length);
		
		if(length > 4){
			$fixHead.eq(index).css("height","100px");
		}
		else{
			if(index != 2){
				length = length*25 + 3;
				$fixHead.eq(index).css("height", length+"px");
			}
			else{
				length = length*27 + 3;
				$fixHead.eq(index).css("height", length+"px");
			}
		}
	});
});
	
	
</script>

    <!-- Portfolio End -->

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>