<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@include file = "/WEB-INF/views/common/header.jsp" %>
<%@include file = "/css/fontApply.jsp" %>
<%
session.removeAttribute("categoryList"); session.removeAttribute("fontList"); 
List<Community> communityList = (List<Community>) request.getAttribute("communityList");
List<Attachment> attachmentList = (List<Attachment>) request.getAttribute("attachmentList");
List<String> commLikeList = (List<String>) request.getAttribute("commLikeList");
%>

<form name="checkIdDuplicateFrm" action="<%= request.getContextPath() %>/member/checkIdDuplicate" method="POST">
<input type="hidden" name="memberId" />
</form>
<form name="checkEmailDuplicateFrm" action="<%= request.getContextPath() %>/member/checkEmailDuplicate" method="POST">
<input type="hidden" name="memberEmail" />
</form>

<div class="memberEnroll">
	<form action="<%= request.getContextPath()%>/member/memberEnroll" method="POST" name="memberEnrollFrm">
		<table class="enrollTbl">
			<tr>
				<th>ELEFONT</th>
				<td class="xBtn"><img id="enrollXBtn" src="<%=request.getContextPath()%>/images/xbutton.png" alt="" /></td>
			</tr>
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" placeholder="4글자이상" name="memberId" id="_memberId" required>
					<input type="button" value="중복검사" id="btnCheckId" />
					<input type="hidden" id="idValid" value="0"/>
				</td>
			</tr>
			<tr>
				<th>비밀번호<sup>*</sup></th>
				<td>
					<input type="password" name="password" id="_password" required><br>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인<sup>*</sup></th>
				<td>
					<input type="password" id="password2" required><br>
				</td>
			</tr>
			<tr>
				<th>이름<sup>*</sup></th>
				<td>
					<input type="text"  name="memberName" id="memberName" required><br>
				</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M" checked>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>회원 유형</th>
				<td>
					<input type="radio" name="memberRole" id="user" value="U" checked>
					<label for="gender0">일반회원</label>
					<input type="radio" name="memberRole" id="seller" value="S">
					<label for="gender1">판매회원</label>
				</td>
			</tr>
			<tr>
				<th>이메일<sup>*</sup></th>
				<td>
					<input type="email" placeholder="abc@xyz.com" name="email" class="_email">
					<input type="button" value="중복검사" id="btnCheckEmail" />
					<input type="hidden" class="emailValid" value="0"/>
					<br>
				</td>
			</tr>
			<tr>
				<th>전화번호<sup>*</sup></th>
				<td>
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>
					<input type="date" name="birthday" id="birthday" ><br />
				</td>
			</tr>
			<tr>
				<th>직업</th>
				<td>
					<select name="job" id="job">
					<option value="대답 안 함" selected>대답 안 함</option>
					<option value="학생">학생</option>
					<option value="디자이너">디자이너</option>
					<option value="개발자">개발자</option>
					<option value="기타">기타</option>
					</select><br />
				</td>
			</tr>
		</table>
			<input type="submit" value="가입" >
			<input type="button" value="취소" id="enrollXBtn2">
	</form>
	<script>

	</script>
</div>
        <div class="home-section-background" data-stellar-background-ratio="0.6">
            <div class="display-table">
                <div class="display-table-cell">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 text-center">
                                <div class="header-text">
                                    <hr  size="2px"  width="100px"  color="#fff"  align="center"/>
                                    <br />
                                    <h2><span class="typing"></span></h2>

                                    <div class="margin-top-60">
                                        <a class="button button-style button-style-icon fa fa-long-arrow-right smoth-scroll"
                                            href="<%= request.getContextPath() %>/shop">Shop으로 이동</a>
                                    </div>

                                </div>
                            </div>
                        </div> 
                    </div>
                </div>
            </div>
        </div>

    </header>
    <!-- Home & Menu Section End-->


    <!-- Portfolio Start -->
    <section id="portfolio" class="portfolio section-space-padding">
        <div class="container">
            <div class="row" >
                <div class="col-sm-12">
                    <div class="section-title">
                        <h2 class="landing-to-shop">Shop</h2>
                        <p>Elefont의 신상 폰트들을 만나보세요!</p>
                    </div>
                </div>
            </div>
            <div class="portfolio-inner">
				<div class="row" id="fonts-box">
 <%
for(Font font : fontList){
%>


		            <div class="test-item"> 
		            
		            	<div class="landing-fontName-textarea-box">
		            		<a href="<%=request.getContextPath()%>/shopDetail?fontNo=<%=font.getFontNo()%>"><%= font.getFontName() %></a> 
		            	</div>
		            	
		            	<textarea cols="30" rows="10" class="font-style" style="font-family:<%=font.getFontFamily() %>;" ></textarea>
		            </div>
<%	
}
%>           
				</div>
			</div>
		</div>

        <div class="text-center margin-top-50">
            <a class="button button-style button-style-dark button-style-icon fa fa-long-arrow-right smoth-scroll"
                href="<%= request.getContextPath() %>/shop">Shop 둘러보기</a>
        </div>

    </section>

    <!-- Portfolio End -->



 <!-- 리뷰 Start -->
  <section id="testimonials" class="testimonial-section section-space-padding">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="section-title">
                    <h2 class="landing-to-shop">베스트 리뷰</h2>
                    <p>다른 사람들은 폰트를 어떻게 활용하고 있을까요? <br />
                    Elefont의 커뮤니티 게시판에서 확인해보세요!</p>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="testimonial-carousel-list margin-top-20" id="landing-community-box">

<%
for(Community comm : communityList){
	for(Attachment att : attachmentList){
		if(comm.getCommNo().equals(att.getCommNo())){
			
%>
		 <div class="testimonial-word text-center">
	                <div class="review-photo" style="background-image: url(<%= request.getContextPath()%>/upload/community/<%= att.getRenamedFilename()%>);" onclick = "location.href='<%=request.getContextPath()%>/community/pictureDetail?commNo=<%= comm.getCommNo()%>'"></div>
	                    <div class="review-content">
	                        <h2><%= comm.getCommTitle() %></h2>
	                        <p><%= comm.getCommContent() %>
	                        </p>
	                            <div class="like-button">
<%
if(loginMember != null && !commLikeList.isEmpty() && commLikeList.contains(comm.getCommNo())){
%>
						<i class="fas fa-heart" data-comm-no="<%=comm.getCommNo()%>"><span><%=comm.getCommLikeCount() %></span></i>
<%
				}else{
%>                                    
						<i class="far fa-heart" data-comm-no="<%=comm.getCommNo()%>"><span><%=comm.getCommLikeCount() %></span></i>
<%
				}
%>  
	                            </div>
	                  </div>
	      </div>



<%
		}
	}
}
%>
               

		
            </div>
        </div>
    </div>
 
</section>
<!-- 리뷰 End -->

<script>
/* 좋아요 버튼 클릭시 사용자 좋아요 여부에 따른 버튼 이벤트 */
$(".fa-heart").click((e)=>{
	
<%
if(loginMember == null){
%>
	alert("로그인 후 사용 가능한 기능입니다.");
	return;
<%
}else if("A".equals(loginMember.getMemberRole())){
%>
	alert("일반 회원만 사용 가능합니다.");
	return;
<%
}
%>
	let $target = $(e.target);
	let $commNo = $target.data("commNo");
	
	$.ajax({
		url: "<%= request.getContextPath()%>/community/commLike",
		dataType: "json",
		type: "GET",
		data: {'commNo' : $commNo},
		success(jsonStr){
			const likeValid = jsonStr["likeValid"];
			const likeCnt = jsonStr["likeCnt"];
			
			if(likeValid == 1){
				$target
					.removeClass("far")
					.addClass("fas");
			}else{
				$target
					.removeClass("fas")
					.addClass("far");
			}
			$target.html(`<span>\${likeCnt}</span>`);
		},
		error: console.log
	});
});

</script>


<%@ include file = "/WEB-INF/views/common/footer.jsp" %>