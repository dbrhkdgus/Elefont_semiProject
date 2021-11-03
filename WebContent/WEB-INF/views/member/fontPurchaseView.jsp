<%@page import="com.kh.elefont.like_cart.model.vo.MemberCartView"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%
String fontNo = (String)request.getAttribute("fontNo");
String fontName = (String)request.getAttribute("fontName");
List<MemberCartView> mcvList = (List<MemberCartView>)request.getAttribute("mcvList");
System.out.println("mcvList @JSP : " + mcvList);
String fontPrice = (String)request.getAttribute("fontPrice");
String memberEmail = (String)request.getAttribute("memberEmail");
String memberNo = (String)request.getAttribute("memberNo");
%>

<section id="portfolio" class="portfolio section-space-padding">
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
   <div class="purchase-container">
       <h1>폰트 구매</h1>
       <form action="<%= request.getContextPath()%>/font/fontPurchase" method="POST" name="fontPurchaseFrm">
           <p class="purchase-menu"><span> 구매하신 폰트 : </span><span><%=fontName %></span></p>
           <p class="purchase-menu"><span>폰트가격 : </span><span><%=fontPrice %></span></p>
           <p class="purchase-menu"><span>쿠폰등록하기 : </span> </p>
           <button type="button" id="purchase-coupon-btn">쿠폰등록</button><span>쿠폰 적용 금액</span><br>
           <label for="purchase-email-address">받으실 이메일 주소</label>
           <input type="text" name="purchase-email" value="<%=memberEmail %>">
           <input type="hidden"  name="member-no" value="<%=loginMember.getMemberNo()%>"/>
           <input type="hidden"  name="font-no" value="<%= fontNo %>"/>
           <input type="hidden"  name="font-price" value="<%= fontPrice %>"/>
           
           <br />
           <input type="button" name ="btn-purchase" value = "구매하기" />
           <input type="button" name ="btn-cancle" value = "취소" />
       </form>
   </div>
</section>

         
<script>
/* 취소버튼 -> 이전페이지로 돌아가기 */
$("input[name=btn-cancle]").click((e)=>{
	history.go(-1);
});

$("input[name=btn-purchase]").click((e)=>{
	const memberPoint = <%= loginMember.getMemberPoint()%>;
	const fontPrice = <%= fontPrice%>;
	  		if(fontPrice > memberPoint){
	  			alert(`보유하신 포인트가 부족하여 구매를 진행할 수 없습니다.
	(현재 보유 포인트 : \${memberPoint}P)`);
	}else{
		if(confirm(`확인 버튼을 클릭 시 보유하신 포인트 (\${memberPoint}P)에서 
	\${fontPrice}P 차감됩니다.`)){
			$(document.fontPurchaseFrm).submit();
		}
		
	}

    		
    		 
});
$("#purchase-coupon-btn").click((e)=>{
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

</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>