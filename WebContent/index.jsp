<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@include file = "/WEB-INF/views/common/header.jsp" %>
<%session.removeAttribute("categoryList"); session.removeAttribute("fontList"); %>
<script>
<%-- 	<div class="col-md-4 col-sm-6 col-xs-12 mix filter-like filter-sale">
<div class="test-item">
	<a href="<%= request.getContextPath()%>/shopDetail?fontNo=\${fontList.fontNO}"><div class="test-item-title"> \${fontlist.fontName} </div></a>
	<textarea name="" id="\${fontList.fontNo}" cols="30" rows="10" class="font-style" style="font-family: '\${fontList.fontFamily}';" ></textarea>
<\div>
</div>  --%>
$.ajax({
	url: "<%=request.getContextPath()%>/mainLanding",
	dataType: "json",
	type:"GET",
	success(data){
		const fontList = data["fontList"]; 
		const communityList = data["communityList"];
		console.log("fontList@jsp + ", fontList)
		for(let i = 0; i < fontList.length; i++){
			
			console.log(fontList[i].fontName);
			$("#fonts-box").append(`<a href="<%=request.getContextPath()%>/shopDetail?fontNo=\${fontList[i].fontNo}"><div class="test-item"> <div class="landing-fontName-textarea-box">\${fontList[i].fontName} </div><textarea name="" id="\${fontList[i].fontNo}" cols="30" rows="10" class="font-style" style="font-family: '\${fontList[i].fontFamily}</div>';" ></textarea><\div></div></a>`);
			$("#landing-community-box").append(``);
		}	
	},
	error: console.log
});
</script>

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
                        <h2>Shop</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit</p>
                    </div>
                </div>
            </div>

            <div class="row">
                <ul class="portfolio">
                    <li class="filter" data-filter="all">MD추천</li>
                    <li class="filter" data-filter=".filter-sale">Sale</li> <!-- 세일중인 애 -->
                    <li class="filter" data-filter=".filter-like">좋아요순</li>
                    <li class="filter" data-filter=".filter-view">조회순</li>
                </ul>
            </div>

            <div class="portfolio-inner">
                <div class="row" id="fonts-box">

                    

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
                    <h2>베스트 리뷰</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="testimonial-carousel-list margin-top-20" id="landing-community-box">

                
		
            </div>
        </div>
    </div>
 
</section>
<!-- 리뷰 End -->



<%@ include file = "/WEB-INF/views/common/footer.jsp" %>