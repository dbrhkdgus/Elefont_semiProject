<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.question.model.vo.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Member> memberList = (List<Member>) request.getAttribute("memberList");
List<Font> fontList = (List<Font>) request.getAttribute("fontList");
List<Coupon> couponList = (List<Coupon>) request.getAttribute("couponList");
List<Order> orderList = (List<Order>) request.getAttribute("orderList");
List<FontCategory> categoryList = (List<FontCategory>) request.getAttribute("categoryList");
Attachment profile = (Attachment) request.getAttribute("profile");
int tabIndex = (int)request.getAttribute("tabIndex");
List<Attachment> attachmentList = (List<Attachment>) request.getAttribute("attachmentList");
int notAnsweredQuestionCnt = (int)request.getAttribute("notAnsweredQuestionCnt");
int answeredQuestionCnt = (int)request.getAttribute("answeredQuestionCnt");
%>

     <div class="member-container">
         <div class="member-profile">
            <div id="my-profile-img">
		      	<img id="my-profile-pic" src="<%= request.getContextPath()%>/upload/profilephotos/<%=profile.getRenamedFilename()%>" alt="" />
		     </div>
            <h3><%=loginMember.getMemberId() %></h3>
            <button id="btn-member-Info-Edit">설정</button>
            <hr>
            <div class="member-detail">
             <div>
                    <div class=""><%= memberRole %></div>
                    <span>회원 등급</span>
                </div>
                <div>
                    <div class=""><%=answeredQuestionCnt %></div>
                    <span>답변 완료</span>
                </div>
                <a href="<%=request.getContextPath()%>/admin/answerQnA">
                    <div class="f" style="color:red;"><%=notAnsweredQuestionCnt %></div>
                    <span>답변 미완료</span>
                </a>
                 </div>
         </div>
         <div class="member-log">
            <div class="member-title">
             <span><%=loginMember.getMemberId() %></span>님, 안녕하세요!
             </div>
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

	if(!memberList.isEmpty()){
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
						<div class="admin-board" >
							<h4>폰트 카테고리 관리</h4>
							<div class="fc-fix">
								<table id="font-category-tbl">
									<tr>
										<th>No.</th>
										<th>분류코드</th>
										<th>폰트번호</th>
									</tr>
<%
	if(!categoryList.isEmpty()){
		for(FontCategory fc : categoryList){
%>
									<tr>
										<td><%= categoryList.indexOf(fc) %></td>
										<td><%= fc.getCategoryCode() %></td>
										<td><%= fc.getFontNo() %></td>
									</tr>
<%
		}
	}else{
%>
									<tr>
										<td colspan="3">카테고리가 없습니다.</td>
									</tr>
<%
	}
%>
	
								</table>
							</div>
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
							<select name="order-search" id="user-search">
								<option value="all" selected>모든 주문 조회</option>
								<option value="orderNo">주문 번호로 검색</option>
								<option value="orderDate">주문일로 검색</option>
								<option value="orderId">주문 회원으로 검색</option>
								<option value="orderFont">주문 상품으로 검색</option>
							</select>
							<input type="text" name="order-search-keyword" id="" placeholder="검색할 내용을 입력하세요."/>
							<input type="button" value="검색" id="orderFinder"/>
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
<%
	if(!orderList.isEmpty()){
		for(Order _oe : orderList){
			OrderExt oe = (OrderExt) _oe; 
%>
								<tr>
									<td><%=oe.getOrderNo() %></td>
									<td><%=oe.getMemberOrderDate() %></td>
									<td><%=oe.getMemberId() %></td>
									<td><%=oe.getFontName() %></td>
									<td><%=oe.getFontPrice()*oe.getFontDiscoutRate() %></td>
								</tr>
<%
		}
	}else{
%>
								<tr>
									<td colspan="5">주문 내역이 없습니다.</td>
								</tr>
<%
	}
%>
						</table>
					</div>
				</div>
				<div>
					<div class="coupon-enroll-wrapper">
						<form action="" method="POST" name="couponEnrollFrm">
							<table id="coupon-enroll-tbl">
							<input type="hidden" name="index" value="4"/>
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
									<th width="80px">쿠폰 발급일</th>
									<th width="120px">쿠폰 번호</th>
									<th width="80px">쿠폰 종류</th>
									<th width="200px">쿠폰 유효기간</th>
									<th width="80px">쿠폰 사용여부</th>
									<th width="90px">포인트 값/할인율</th>
									<th width="150px">회원 번호</th>
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
	if(!fontList.isEmpty()){
		for(Font f : fontList){
			String targetFontNo = f.getFontNo();
%>
								<tr class="font-a <%="N".equals(f.getFontApproval())? "font-n" 
										: ("Y".equals(f.getFontApproval())||"C".equals(f.getFontApproval()))? 
												"font-y" : "font-w" %>">
									<td>
<%
			if("C".equals(f.getFontApproval())){
%>
										<select class="font-approval" disabled>
											<option value="C" selected>승인</option>
											<option value="">심사 대기</option>
											<option value="N">미승인</option>
										</select>
<%
			}else{
%>
										<select class="font-approval">
											<option value="" <%= (f.getFontApproval() == null)? "selected":"" %>>심사 대기</option>
											<option value="N" <%= "N".equals(f.getFontApproval())?"selected":"" %>>미승인</option>
											<option value="Y" <%="Y".equals(f.getFontApproval())?"selected":"" %>>승인</option>
										</select>
<%
			}
%>
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
	
	for(Attachment fontAtt :attachmentList){
		if(targetFontNo.equals(fontAtt.getFontNo()))
				
%>
										<input type="button" value="파일 다운로드" class="fontDownloadBtn"/>
										<input type="hidden" name="renamedFileName" value="<%= fontAtt.getRenamedFilename()%>"/>			
<%
		break;
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
$("#fontFinder").click((e)=>{
	const $searchType = $("[name=font-search]").val();
	const $searchKeyword = $("[name=font-search-keyword]").val();
	console.log(`searchType = \${$searchType}`);
	console.log(`searchKeyword = \${$searchKeyword}`);
	
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
    $fontNo = $(e.target).parent().prevAll().eq(3).text();
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
/* 주문관리 - 주문검색 클릭시 */
$(orderFinder).click((e)=>{
	const $searchType = $("[name=order-search]").val();
	const $searchKeyword = $("[name=order-search-keyword]").val();
	console.log(`searchType = \${$searchType}`);
	console.log(`searchKeyword = \${$searchKeyword}`);
	
	location.href = "<%=request.getContextPath()%>/admin/orderFinder?searchType="+$searchType+"&searchKeyword="+$searchKeyword;
});

</script>