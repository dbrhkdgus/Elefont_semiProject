<%@page import="java.util.List"%>
<%@page import="com.kh.elefont.member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/LandingHeader.jsp"%>

<%
Member member = (Member) request.getAttribute("member");
%>

<!-- 회원정보 수정 section 시작-->
<section id="portfolio" class="portfolio section-space-padding">

	<form name="withdrawalFrm" method="post"
		action="<%=request.getContextPath()%>/member/withdrawal">
		<input type="hidden" name="memberId" value="<%=member.getMemberId()%>" />
	</form>

	<div class="editCenter">
		<div id="editOuterDiv">
			<div id="titleDiv">
				<div class="editTitle" id="title">
					<h2>회원정보 수정</h2>
				</div>
				<div class="editTitle" id="withdrawButton">
<%
	if("U".equals(member.getMemberRole()) || "S".equals(member.getMemberRole()) ){
%>				<input type="button" id="memberwithdrawalBtn" value="탈퇴하기">
<%
	}
%>
				</div>
			</div>
			<hr>
			<div id="infoDiv">
				<div id="personalInfo">
					<div id="editPhoto">
						<div class="defaultphotobox">
							<img class="defaultPhoto"
								src="https://t1.daumcdn.net/cfile/tistory/243FE450575F82662D"
								alt="프로필기본사진">
						</div>
						<p>
							&nbsp;&nbsp;<%=member.getMemberRegDate()%>
							가입
						</p>
						<div id="photoEditButton">
							<input type="button" value="프로필 수정">
						</div>
					</div>
					<div id="infoEditInput">
						<div id="info1" class="info">
							<table id="editInfo1">
								<tr>
									<th>아이디<sup>*</sup></th>
									<td><input type="text" id="editId" readonly
										placeholder="<%=member.getMemberId()%>"></td>
								</tr>
								<tr>
									<th>비밀번호<sup>*</sup></th>
									<td><input type="text" id="editPwd" required></td>
								</tr>
								<tr>
									<th>비밀번호 확인<sup>*</sup>&nbsp;
									</th>
									<td><input type="text" name="" required></td>
								</tr>
								<tr>
									<th>생년월일</th>
									<td><input type="date" id="editBirthday"
										value="<%=member.getMemberBirthday()%>"></td>
								</tr>
							</table>
						</div>
						<div id="info2" class="info">
							<table id="editInfo2">
								<tr>
									<th>성별</th>
									<td><input type="radio" id="male" name="editGender"
										value="male"
										<%=MemberService.GENDER_MALE.equals(member.getMemberGender()) ? "checked" : "disabled"%>>
										<label for="male">남성</label> <input type="radio" id="female"
										name="eidtGender" value="female"
										<%=MemberService.GENDER_FEMALE.equals(member.getMemberGender()) ? "checked" : "disabled"%>>
										<label for="female">여성</label></td>
								</tr>
								<tr>
									<th>이메일&nbsp;</th>
									<td><input type="email" id="editEmail"
										placeholder="<%=member.getMemberEmail()%>"></td>
								</tr>
								<tr>
									<th>연락처&nbsp;</th>
									<td><input type="tel" id="editPhone"
										placeholder="<%=member.getMemberPhone()%>"></td>
								</tr>
								<tr>
									<%
									String student = "학생";
									String designer = "디자이너";
									String developer = "개발자";
									String etc = "기타";
									String none = "대답 안 함";
									%>
									<th>직업</th>
									<td><select name="job" id="editJob">
											<option value="student"
												<%=student.equals(member.getMemberJob()) ? "selected" : ""%>>학생</option>
											<option value="designer"
												<%=designer.equals(member.getMemberJob()) ? "selected" : ""%>>디자이너</option>
											<option value="developer"
												<%=developer.equals(member.getMemberJob()) ? "selected" : ""%>>개발자</option>
											<option value="etc"
												<%=etc.equals(member.getMemberJob()) ? "selected" : ""%>>기타</option>
											<option value="none"
												<%=none.equals(member.getMemberJob()) ? "selected" : ""%>>대답
												안 함</option>
									</select></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div id="editButton">
					<br>
					<button>회원정보수정</button>
				</div>
			</div>
		</div>
	</div>
</section>

<script>

$("#memberwithdrawalBtn").click((e)=>{
	if(confirm("탈퇴하시겠습니까?")){
		console.log("탈퇴?잘가요 ㅜㅜ")
		$(document.withdrawalFrm).submit();
	}
});
	

	

</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>