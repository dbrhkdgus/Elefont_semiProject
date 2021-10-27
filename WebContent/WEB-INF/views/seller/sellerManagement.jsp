<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

<%
	List<Font> approvalList = (List<Font>) session.getAttribute("approvalList");
	List<Font> checkedList = (List<Font>) session.getAttribute("checkedList");
	List<Font> auditList = (List<Font>) session.getAttribute("auditList");
%>
     <!-- seller 심사완료1! -->
<section id="portfolio" class="portfolio section-space-padding">
   <div class="seller-management">
        <div class="seller-management-title">
            <h3><%=loginMember.getMemberId() %></h3>님의 폰트현황 &nbsp;&nbsp;&nbsp;
            <span>폰트 등록<strong><%=checkedList.size() %></strong>건</span>&nbsp;&nbsp;
            <span>심사 중<strong><%=auditList.size() %></strong>건</span>&nbsp;&nbsp;
            <span>심사 완료<strong><%=approvalList.size() %></strong>건</span>&nbsp;
            <button id="sm-font-button">폰트등록</button>
        </div>
        
       	 <div class="font-enroll">
  			<form action="<%=request.getContextPath() %>/seller/fontEnroll" method="POST" name="fontEnrollFrm" enctype="multipart/form-data">
	            <h3>등록하실 폰트명</h3>
	            <div class="font-wrpper">
	            <input type="text" name="font-name" id="font-name" placeholder="폰트명을 입력하세요"><i class="fas fa-check-circle"></i>
	            <h4>희망 가격 입력</h4>
	            <input type="text" name="font-price" id="" value="기본 가격은 200P입니다."/><br />
	            <h4>폰트 원출처(url) 입력</h4>
	            <input type="text" name="font-url" id="" value="url 주소를 입력하세요."/><br />
	            <h3>폰트파일 업로드</h3>
	            <input type="file" name="font-file" id="font-file" />
	            </div>
	            <input type="button" id="font-x-btn" value="취소하기">
	            <input type="button" id="font-submit-btn" value="등록하기">
	            <input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>">
	            <input type="hidden" name="memberNo" value="<%= loginMember.getMemberNo() %>">
        	</form>
        </div>
               	 
        <div class="seller-management-content">
            
            <div class="sm-enroll-font">
                <h4>내가 등록한 폰트</h4>
                <div class="fix-head" id="check-font-head">                      
                    <table class="fix-tbl">
                    	<thead>
                    		<tr>
	                    		<th>등록 일자</th>
	                    		<th>폰트명</th>
	                    		<th>폰트 파일</th>
                    		</tr>
                    	</thead>
                    	<tbody>
<%
if(!checkedList.isEmpty()){
	for(Font f : checkedList){
%>
							<tr>
								<td><%= f.getFontRegDate() %></td>
								<td><%= f.getFontName() %></td>
								<td></td>
							</tr>
<%
	}
}else{
%>
							<tr>
								<td colspan="3">등록한 폰트가 없습니다.</td>
							</tr>
<%
}
%>
                    	</tbody>
                    </table>
                </div>
                <hr class="liner">
              
                <h4>심사중인 폰트</h4>
                <div class="fix-head" id="audit-font-head">                      
                    <table class="fix-tbl">
                    	<thead>
                    		<tr>
	                    		<th>등록 일자</th>
	                    		<th>폰트명</th>
	                    		<th>폰트 파일</th>
                    		</tr>
                    	</thead>
                    	<tbody>
<%
if(!auditList.isEmpty()){
	for(Font f : auditList){
%>
							<tr>
								<td><%= f.getFontRegDate() %></td>
								<td><%= f.getFontName() %></td>
								<td></td>
							</tr>
<%
	}
}else{
%>
							<tr>
								<td colspan="3">심사 중인 폰트가 없습니다.</td>
							</tr>
<%
}
%>
                    	</tbody>
                    </table>
                </div>

                <hr class="liner">
                <h4>심사 결과 확인</h4>
                <div class="fix-head" id="approval-font-head">                      
                    <table class="fix-tbl">
                    	<thead>
                    		<tr>
	                    		<th>등록 일자</th>
	                    		<th>폰트명</th>
	                    		<th>폰트 파일</th>
	                    		<th>심사 결과</th>
	                    		<th>확인 여부</th>
                    		</tr>
                    	</thead>
                    	<tbody>
<%
if(!approvalList.isEmpty()){
	for(Font f : approvalList){
%>
							<tr height="25px">
								<td><%= f.getFontRegDate() %></td>
								<td><%= f.getFontName() %></td>
								<td></td>
								<td><%= f.getFontApproval() %></td>
								<td>
									<input type="button" value="확인" id="auditCheckBtn" data-font-no = "<%= f.getFontNo()%>" data-font-approval="<%=f.getFontApproval()%>"/>
								</td>
							</tr>
<%
	}
}else{
%>
							<tr>
								<td colspan="5">심사 결과가 없습니다.</td>
							</tr>
<%
}
%>
                    	</tbody>
                    </table>
                    <form action="<%=request.getContextPath()%>/seller/fontAuditCheck" method="POST" name="fontAuditCheckFrm">
                    	<input type="hidden" name="fontNo" />
                    	<input type="hidden" name="fontApproval" />
                    </form>
                </div>
              
                
                
            </div>
        
        </div>

    </div>

</section>

<script>
/* 폰트 조회 창 높이 폰트 입력량에 따라 조절*/
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
/* 폰트 등록 모달에서 폰트 등록 시 font테이블에 insert 진행*/
   $("#sm-font-button").click((e)=>{
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

/* 폰트 심사 내용 확인 버튼 입력 시 update*/
	$(auditCheckBtn).click((e)=>{
		const $this = $(e.target);
		const $fontNo = $this.data("fontNo");
		const $fontApproval = $this.data("fontApproval");
		if(confirm("심사 결과를 확인했으며 이에 동의합니다")){
			const $frm = $(document.fontAuditCheckFrm);
			$frm.find("[name=fontNo]").val($fontNo);
			$frm.find("[name=fontApproval]").val($fontApproval);
			$frm.submit();
		}
	});

</script>
        <!-- seller End -->
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>