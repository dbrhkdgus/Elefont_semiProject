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
double salePrice = 0;

	
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
        <span id="notice">----</span>
        <h3 id="coupon-total"></h3>

		<input type="hidden" name="couponCheckVaild" id="couponCheckVaild" value ="0"/>
		<input type="hidden" name="couponType" id="couponTypeInput" />
		<input type="hidden"  name="font-price" value="<%= fontPrice %>" />
		
		
        <input type="button" id="coupon-x-btn" value="취소하기">
        <input type="button" id="coupon-submit-btn" value="등록하기" onclick="LetsRegCoupon();">
        
    </form>
</div>
   <div class="purchase-container">
       
       <form action="<%= request.getContextPath()%>/font/fontPurchase" method="POST" name="fontPurchaseFrm" style="padding:70px; box-shadow: 6px 6px 16px #dfdfdf;">
           <h1 class="font_purchase_h1">폰트 구매</h1>
           <p class="purchase-menu"><span class="purchase-label">구매하신 폰트  </span><br><span><%=fontName %></span></p>               
           <p class="purchase-menu"><span class="purchase-label" >폰트가격  </span><br><span id="fontPrice"><%=fontPrice %></span></p>
           <span class="purchase-menu"><span class="purchase-label" id="couponReg">쿠폰 등록하기  </span></span>
          	<button type="button" id="purchase-coupon-btn">쿠폰등록</button><br>                
           <label for="purchase-email-address" style="background-color:#A0BFB5;  font-family: 'NanumSquareRound'; font-size: 20px; margin-right:5px;">받으실 이메일 주소</label>
           <input type="text" id="purchase-email" name="purchase-email" value="<%=memberEmail %>">
           <input type="hidden"  name="member-no" value="<%=loginMember.getMemberNo()%>"/>
           <input type="hidden"  name="font-no" value="<%= fontNo %>"/>
           <input type="hidden"  name="font-price" value="<%= fontPrice %>"/>
           <input type="hidden" name="coupon-no" id="coupon-no"/>
           <input type="hidden" name="finalPrice" id="finalPrice" value="<%= fontPrice %>" />
           
           <br />
           <div class="purchase-buttons">
	           <input type="button" id="purchase-btn" name ="btn-purchase" value = "구매하기" />
	           <input type="button" name ="btn-cancle" value = "취소" />
           </div>
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
	const fontPrice = $("[name=font-price]").val();
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
			
			let couponMemberId = $("#memberIdToReg").val();
			
			$.ajax({
				url : "<%=request.getContextPath()%>/coupon/isThisCouponVaild",
				data : $frmData.serialize(),
				method : "post",
				dataType : "json",
				success(data) {

					if(typeof data != "string" ) {
						//쿠폰타입 처리하기
						const couponType = data["couponType"];
						
						$("#couponTypeInput").val(couponType);
						
						if('P' === couponType){
							//포인트 얼마인지 보여주기
							const couponPAmount = data["couponPAmount"]; 
							document.getElementById("notice").innerHTML = "포인트";
							document.getElementById("coupon-total").innerHTML = `\${couponPAmount} p`;
							$("#couponCheckVaild").val(1);
	
						}else{
							const couponDiscountRate = data["couponDiscount"];
							document.getElementById("notice").innerHTML = "할인율";
							document.getElementById("coupon-total").innerHTML = `\${couponDiscountRate} %`;
							$("#couponCheckVaild").val(1);
						}
						
					}else{//유효기간 지난 쿠폰일 시
						alert(data);
					}
					
				},
				//유효하지 않은 쿠폰일 시 alert 띄우고 input 값 지우기
				error(xhr, textStatus, err){
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


function LetsRegCoupon(){
	const $vaild = $("#couponCheckVaild").val();
	const $userCouponEnrollFrm = $(userCouponEnrollFrm); 
	
	if($vaild !=1){
		alert("쿠폰 조회가 필요합니다");
	}
	if($vaild == 1){
		if(confirm("쿠폰을 등록하시겠습니까?")) {
			
			$.ajax({
				 
				
				url: "<%=request.getContextPath()%>/order/couponEnroll",
				method : "POST",
				dataType : "json",
				data : $userCouponEnrollFrm.serialize(),
				success(data) {
					const salePrice = data["salePrice"];
					const salePriceRound = parseFloat(salePrice).toFixed(1);
					const couponNo = data["couponNo"];
					
					$(".coupon-enroll").hide();
					
					$("#fontPrice").html(`<del><%= fontPrice %></del><span>[\${salePriceRound}]</span>`);
					$("#finalPrice").val(salePriceRound);
					$("[name=font-price]").val(salePrice);
					$("#couponReg").html(`등록된 쿠폰 : \${couponNo}`)
					
					$("#coupon-no").val(couponNo);
					$("#purchase-coupon-btn").html("쿠폰취소").attr("id","coupon-cancle-btn");
					
					$("#coupon-cancle-btn").click(()=>{
						$(".coupon-enroll").hide();
						location.reload();
					});
					
					
				},error(xhr, textStatus, err){
					alert("잘못된 접근입니다.");
					
				}				
			});
		}
		
	}
}

$("#coupon-no2").change(() => {
	$("#couponCheckVaild").val(0);
	
});

$("#coupon-no3").change(() => {
	$("#couponCheckVaild").val(0);
	
});



</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>