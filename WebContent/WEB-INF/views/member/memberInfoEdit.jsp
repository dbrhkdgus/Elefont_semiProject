<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.elefont.member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/LandingHeader.jsp"%>

<%
Member member = (Member) request.getAttribute("member");
File profilePhotoAttach = (File) request.getAttribute("profilePhotoAttach");
String photoPath = profilePhotoAttach.getPath();
System.out.println("경로가 궁금하느냐? : " + photoPath);
%>

<!-- 회원정보 수정 section 시작-->
<section id="portfolio" class="portfolio section-space-padding">

<form action="<%= request.getContextPath() %>/member/checkEmailDuplicate" name="checkEmailDuplicateFrm"  method="post">
	<input type="hidden" name="memberEmail" />
</form>

<form action="<%=request.getContextPath()%>/member/editProfilePhoto" name="profilephotosendFrm">
	<input type="hidden" name="memberNo" value="<%=loginMember.getMemberNo()%>"/>
</form>


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
								src="<%=photoPath %>"
								alt="프로필기본사진">
						</div>
						<p>
							&nbsp;&nbsp;<%=member.getMemberRegDate()%>
							가입
						</p>
						<div id="photoEditButton">
							<input type="button" value="프로필 수정" id="EditProfilePhoto">
						</div>
					</div>
					<div id="infoEditInput">
						<form name="editInfoFrm" action="<%=request.getContextPath()%>/member/memberInfoEdit" method="post">
							<div id="info1" class="info">
								<table id="editInfo1">
									<tr>
										<th>아이디<sup>*</sup></th>
										<td><input type="text" name ="editId" id="editId" value="<%=member.getMemberId()%>" readonly
											placeholder="<%=member.getMemberId()%>"></td>
									</tr>
									<tr>
									<th>이름<sup>*</sup></th>
									<td><input type="text" name="editName" id="editName" required
									value="<%=member.getMemberName()%>"  /></td>
									</tr>
									<tr>
										<th>비밀번호<sup>*</sup></th>
										<td><input type="password" name="editPwd" id="editPwd" onfocus="this.value=''" required></td>
									</tr>
									<tr>
										<th>비밀번호 확인<sup>*</sup>&nbsp;
										</th>
										<td><input type="password" id="editPwdDoubleCheck"  name="" required></td>
									</tr>
									<tr>
										<th>생년월일</th>
										<td><input type="date" name="editBirthday" id="editBirthday"
											value="<%=member.getMemberBirthday()%>"></td>
									</tr>
								</table>
							</div>
							<div id="info2" class="info">
								<table id="editInfo2">
									<tr>
										<th>성별</th>
										<td><input type="radio" id="male" name="eidtGender"
											value="M"
											<%=MemberService.GENDER_MALE.equals(member.getMemberGender()) ? "checked" : ""%>>
											<label for="male">남성</label> <input type="radio" id="female"
											name="eidtGender" value="F"
											<%=MemberService.GENDER_FEMALE.equals(member.getMemberGender()) ? "checked" : ""%>>
											<label for="female">여성</label></td>
									</tr>
									<tr>
										<th>이메일&nbsp;</th>
										<td><input type="email" class="_email" id="editEmailk"
											value="<%=member.getMemberEmail()%>" name="editEmailk" >
											<input id="emailDoubleCheck" type="button" value="중복검사"
											onclick ="checkEmailDuplicate();"/>
											<input type="hidden" class="emailValid" value="1"/>
											<script>
											$(editEmailk).change((e) => {
												console.log("이 칸을 건들였다아")
												$(".emailValid").val(0);
											});
											</script>
											</td>
									</tr>
									<tr>
										<th>연락처&nbsp;</th>
										<td><input type="tel" id="editPhone" name="editPhone" value="<%=member.getMemberPhone()%>"
											 onfocus="this.select()"></td>
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
												<option value="학생"
													<%=student.equals(member.getMemberJob()) ? "selected" : ""%>>학생</option>
												<option value="디자이너"
													<%=designer.equals(member.getMemberJob()) ? "selected" : ""%>>디자이너</option>
												<option value="개발자"
													<%=developer.equals(member.getMemberJob()) ? "selected" : ""%>>개발자</option>
												<option value="기타"
													<%=etc.equals(member.getMemberJob()) ? "selected" : ""%>>기타</option>
												<option value="대답 안 함"
													<%=none.equals(member.getMemberJob()) ? "selected" : ""%>>대답
													안 함</option>
										</select></td>
									</tr>
								</table>
							</div>
						</form>
					</div>
				</div>
				<div id="editButton">
					<br>
					<input type="button" id= "memberInfoEditBtn" value="회원정보수정"></button>
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


$("#memberInfoEditBtn").click(()=>{
	const $p1 = $("#editPwd");
	const $p2 = $("#editPwdDoubleCheck");
	const $phone = $("#editPhone");
	const $memberName =$("#editName");
	const $email =$("#editEmail");
	

		if(/^[가-힣]{2,6}$/.test($memberName.val()) == false){
			alert("이름은 띄어쓰기 없이 한글 2글자~6글자 사이어야 합니다.");
			$memberName.select();
			return;
		}
		
		if(/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test($p1.val()) == false){
			alert("유효한 패스워드를 입력하세요.");
			$p1.select();
			return ;
		}
		if($p1.val() != $p2.val()){
			alert("패스워드가 일치하지 않습니다.");
			$p1.select();
			return;
		}
		
		//email
/**	if(/^[\w]{4,}@[\w]+(.[\w]+){1,3}$/.test($email.val()) == false){
			alert("이메일 형식에 어긋납니다");
			return;
		}
*/
		$phone.val($phone.val().replace(/[^0-9]/g,""));//숫자만 남게
		if(/^010[0-9]{8}$/.test($phone.val()) == false){
			alert("연락처는 숫자로만 이뤄진 11개의 숫자를 입력해주세요");
			$phone.select();
			return;
		}
		const emailVaildVal = $(".emailValid").val();
		console.log(emailVaildVal);
		
		if(emailVaildVal < 1){
			alert("중복검사를 다시 해주세요"); 
			return;	
		}

		$(document.editInfoFrm).submit();


	
})

function checkEmailDuplicate() {
	const title = "popupToDublecheckEmail";
	const spec = "left=500px, top= 300px, width=500px, height = 200px";
	const popup = open("",title,spec);
	
	const $frm = $(document.checkEmailDuplicateFrm);
	$frm.find("[name=memberEmail]").val($("._email").val());
	$frm.attr("target", title) // form 제출을 popup에서 진행
		.submit();

}

$(EditProfilePhoto).click(() =>{
	const title = "Upload an image for the profile photo"
	const spec = "left=500px, top= 200px, width=500px, height = 450px";
	const popup = open ("",title,spec)
	
	const $frm = $(document.profilephotosendFrm);
	$frm.attr("target", title).submit();
})

</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>