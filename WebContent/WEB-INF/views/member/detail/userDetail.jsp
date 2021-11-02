<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Attachment> commAttachmentList = (List<Attachment>)request.getAttribute("commAttachmentList");
List<Font> fontLikeList = (List<Font>) request.getAttribute("fontLikeList");
List<Font> fontPurchasedList = (List<Font>) request.getAttribute("fontPurchasedList");
List<Coupon> couponList = (List<Coupon>) request.getAttribute("couponList");
Attachment profile = (Attachment) request.getAttribute("profile");
%>
<div class="coupon-enroll">
    <form action="#" method="POST" name="userCouponEnrollFrm">
        <h2>쿠폰 등록 번호</h2>
        <input type="text" class="coupon-no" name="coupon-no1" id="coupon-no1" value="elpo" readonly>
        <span>-</span>
        <input type="text" class="coupon-no" name="coupon-no2" id="coupon-no2">
        <span>-</span>
        <input type="text" class="coupon-no" name="coupon-no3" id="coupon-no3">
        <input type="button" id="checkIfIHave" value="조회">
        <br>
        <input type="hidden" name="memberNoToReg" id="memberNoToReg" value="<%=loginMember.getMemberNo()%>"/>
        <span>총 금액</span>
        <h3 id="coupon-total"></h3>

		<input type="hidden" name="couponCheckVaild" id="couponCheckVaild" value ="0"/>
        <input type="button" id="coupon-x-btn" value="취소하기">
        <input type="button" id="coupon-submit-btn" value="등록하기" onclick="LetsRegCoupon();">
        <input type="hidden" name="memberIdToReg" id="memberIdToReg" value="<%=loginMember.getMemberId()%>">
    </form>
</div>
<div class="member-container">
   <div class="member-profile">
      <div id="my-profile-img">
      	<img id="my-profile-pic" src="<%= request.getContextPath()%>/upload/profilephotos/<%=profile.getRenamedFilename()%>" alt="" />
      </div>
      <h3><%=loginMember.getMemberId() %></h3>
      <button id="btn-member-Info-Edit">설정</button>
      <hr>
      <div class="member-detail">
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
      </div>
   </div>
   <div class="member-log">
    <div class="member-title">
     	<span><%=loginMember.getMemberId() %></span>님의 현재 포인트는 <span><%= loginMember.getMemberPoint() %></span>점입니다.
      <button id="member-coupon">쿠폰 등록</button>
    </div>
       <div class="member-comm">
           <h4><a href="<%=request.getContextPath()%>/community/writerCollections?memberNo=<%=loginMember.getMemberNo()%>">내가 쓴 커뮤니티</a></h4>
           <div class="member-list">
<% 
	if(commAttachmentList.size() < 4){
		for(Attachment att : commAttachmentList){
	
%>
              <a href="<%=request.getContextPath()%>/community/board"><div class="my-comm-img"><img src="<%=request.getContextPath()%>/upload/community/<%=att.getRenamedFilename()%>" alt="" /></div></a>

<%	
		}
	}else{
		for(int i = 0; i < 3; i++){
			Attachment att = commAttachmentList.get(i);
%>
              <a href="<%=request.getContextPath()%>/community/board"><div class="my-comm-img"><img src="<%=request.getContextPath()%>/upload/community/<%=att.getRenamedFilename()%>" alt="" /></div></a>
<%
		}
%>
              <a href="<%=request.getContextPath()%>/community/writerCollections?memberNo=<%=loginMember.getMemberNo()%>"><div class="my-comm-img">더보기</div></a>
<%
	}
%>
                    
           </div>
       </div>
       <div class="member-font-like">
           <h4><a href="<%=request.getContextPath()%>/member/fontLikeList?memberNo=<%=loginMember.getMemberNo()%>">내 좋아요 리스트</a></h4>
           <div class="member-list">
<%
	if(fontLikeList.size() < 4){	
		for(Font f : fontLikeList){
%>
              <a href="<%=request.getContextPath()%>/shopDetail?fontNo=<%=f.getFontNo()%>"><div class="my-font-img"><%=f.getFontName() %></div></a>
<%
		}
	}else{
		for(int i = 0; i < 3; i++){
			Font f = fontLikeList.get(i);
%>
              <a href="<%=request.getContextPath()%>/shopDetail?fontNo=<%=f.getFontNo()%>"><div class="my-font-img"><%=f.getFontName() %></div></a>
<%
		}
%>
              <a href="<%=request.getContextPath()%>/member/fontLikeList?memberNo=<%=loginMember.getMemberNo()%>"><div class="my-font-img">더보기</div></a>
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
    </div>
</div> 
<script>
$("#member-coupon").click((e)=>{
	const $couponEnroll = $(".coupon-enroll");
	if($couponEnroll.css("display","none")){
		$couponEnroll.show();
		
		/*User 쿠폰 사용 이벤트 - 다현 - */
		$("#checkIfIHave").click((e)=>{
			const $frmData = $(document.userCouponEnrollFrm);
			
			let couponMemberNo = $("#memberNoToReg").val();
			console.log(`쿠폰 사용할 memberId : \${couponMemberNo}`);
			
			let couponMemberId = $("#memberIdToReg").val();
			console.log(`쿠폰 사용할 memberId : \${couponMemberId}`);
			
			$.ajax({
				url : "<%=request.getContextPath()%>/coupon/isThisCouponVaild",
				data : $frmData.serialize(),
				method : "post",
				dataType : "json",
				success(data) {
					console.log(`데이터 받아왔나요? : \${data}`);
					
					//포인트 얼마인지 보여주기
					const couponPAmount = data["couponPAmount"]; 
					console.log(`포인트 얼마? \${couponPAmount}`);			
					document.getElementById("coupon-total").innerHTML = `\${couponPAmount} 원`;
					$("#couponCheckVaild").val(1);
					
				},
				//유효하지 않은 쿠폰일 시 alert 띄우고 input 값 지우기
				error(xhr, textStatus, err){
					console.log(xhr, textStatus, err);
					alert("유효하지 않은 쿠폰입니다");
					var coupons = document.getElementsByClassName("coupon-no");
					coupons[1].value = "";
					coupons[2].value = "";
					document.getElementById("coupon-total").innerHTML = "";
				}

				
				
			});
			
		});
		
		/*User 쿠폰 사용 이벤트 끝 - 다현 - */
		
		
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
	const $fixHead = $(".member-tbl").parent();
	$.each($fixHead, function(index, item){
		let $item = $(item);
		let length = $(item).find('tr').length;
		console.log(length);
		
		if(length > 4){
			$fixHead.eq(index).css("height","100px");
		}else if(length < 3){
			length = 2*25 + 3;
			$fixHead.eq(index).css("height", length+"px");
		}else{
			length = length*25 + 3;
			$fixHead.eq(index).css("height", length+"px");
		}
	});
});

	
	function LetsRegCoupon(){
		const $vaild = $("#couponCheckVaild").val();
		console.log("vaild 값은 : ")
		console.log($vaild);
		if($vaild !=1){
			alert("쿠폰 조회가 필요합니다");
		}
	}
	
	$("#coupon-no2").change(() => {
		$("#couponCheckVaild").val(0);
		
	});
	
	$("#coupon-no3").change(() => {
		$("#couponCheckVaild").val(0);
		
	});
</script>          
