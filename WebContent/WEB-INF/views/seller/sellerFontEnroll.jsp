<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

<section id="font-enroll-page" class="section-space-padding">
<h3 id="fontEnroll-title"><%=loginMember.getMemberId() %>님의 폰트를 등록합니다.</h3>
<div id="font-enroll">
   <form action="<%=request.getContextPath() %>/seller/fontEnroll" method="POST" name="fontEnrollFrm" id="fontEnrollFrm" enctype="multipart/form-data">
   		<table>
   		<!-- 폰트 이름, 출처, 가격, 제작사, 제작자, 회사, 글꼴 스타일, 글꼴 언어, 출시년도 -->
   			<tr>
   				<th>폰트명<sup>*</sup></th>
   				<td><input type="text" name="font-name" id="font-name" placeholder="폰트명을 입력하세요"><i class="fas fa-check-circle"></i></td>
   			</tr>
   			<tr>
   				<th>희망 가격</th>
   				<td><input type="text" class="font-input" name="font-price" id="" placeholder="기본 가격은 200P입니다."/></td>
   			</tr>
   			<tr>
   				<th>폰트 출처(url)<sup>*</sup></th>
   				<td><input type="text" class="font-input" name="font-url" id="" placeholder="url 주소를 입력하세요."/></td>
   			</tr>
   			<tr>
   				<th>폰트 파일 업로드<sup>*</sup></th>
   				<td><input type="file" name="font-file" id="font-file" /></td>
   			</tr>
   			<tr>
   				<th>폰트 제작자명<sup>*</sup></th>
   				<td><input type="text" class="font-input" name="font-url" id="" placeholder="제작자명을 입력하세요"/></td>
   			</tr>
   			<tr>
   				<th>폰트 제작사명</th>
   				<td><input type="text" class="font-input" name="font-url" id="" placeholder="제작사명을 입력하세요"/></td>
   			</tr>
   			<tr>
   				<th>회사 홈페이지</th>
   				<td><input type="text" class="font-input" name="font-url" id="" placeholder="url 주소를 입력하세요."/></td>
   			</tr>
   			<tr>
   				<th>글꼴 스타일<sup>*</sup></th>
   				<td>
   					<input type="radio" name="font-style" id="font-style0" value="S" checked>
					<label for="font-style0">Serif</label>
					<input type="radio" name="font-style" id="font-style1" value="G">
					<label for="font-style1">Sans Serif</label><br />
					<input type="radio" name="font-style" id="font-style2" value="H">
					<label for="font-style2">Handwriting</label>
					<input type="radio" name="font-style" id="font-style3" value="M">
					<label for="font-style3">Monospace</label>
   				</td>
   			</tr>
   			<tr>
   				<th>글꼴 사용언어<sup>*</sup></th>
   				<td>
					<input type="radio" name="font-language" id="font-language0" value="KO" checked>
					<label for="font-language0">한글</label>
					<input type="radio" name="font-language" id="font-language1" value="EN">
					<label for="font-language1">영어</label>
					<input type="radio" name="font-language" id="font-language2" value="AL">
					<label for="font-language2">둘 다</label>
   				</td>
   			</tr>
   			<tr>
   				<th>글꼴 출시일</th>
   				<td>
   					<input type="date" name="font-release-year" id="font-release-year" >
   				</td>
   			</tr>
   			<tr>
	   			<td>
		   			<hr />
	   			</td>
   			</tr>
   			<tr>
   				<td colspan="2">
				  	<input type="button" id="font-x-btn" value="취소하기">
       				<input type="button" id="font-submit-btn" value="등록하기">
       				<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>">
				   	<input type="hidden" name="memberNo" value="<%= loginMember.getMemberNo() %>">
   				</td>
   			</tr>
   		</table>
   </form>
</div>
</section>

<script>
		
$("#font-submit-btn").click((e)=>{
	$(document.fontEnrollFrm).submit();
});
$("#font-x-btn").click((e)=>{
	window.history.back();
});
		
</script>
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>