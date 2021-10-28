<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@include file = "/WEB-INF/views/common/header.jsp" %>
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
            <div class="row">
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
                <div class="row">


                    <div class="col-md-4 col-sm-6 col-xs-12 mix filter-sale ">
                        <div class="item">
                            <a href="images/portfolio/1.jpg" class="portfolio-popup item-img" title="Project Title">
                                <img src="images/portfolio/1.jpg" alt="">
                            </a>
                            
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6 col-xs-12 mix filter-like filter-sale">
                        <div class="item">
                            <a href="images/portfolio/2.jpg" class="portfolio-popup item-img" title="Project Title">
                                <img src="images/portfolio/2.jpg" alt="">
                            </a>
                           
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6 col-xs-12 mix filter-sale">
                        <div class="item">
                            <a href="images/portfolio/3.jpg" class="portfolio-popup item-img" title="Project Title">
                                <img src="images/portfolio/3.jpg" alt="">
                            </a>
                        
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6 col-xs-12 mix filter-like filter-view">
                        <div class="item">
                            <a href="images/portfolio/4.jpg" class="portfolio-popup item-img" title="Project Title">
                                <img src="images/portfolio/4.jpg" alt="">
                            </a>
                           
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6 col-xs-12 mix filter-view filter-sale">
                        <div class="item">
                            <a href="images/portfolio/5.jpg" class="portfolio-popup item-img" title="Project Title">
                                <img src="images/portfolio/5.jpg" alt="">
                            </a>
                       
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6 col-xs-12 mix filter-sale filter-like filter-view" title="Project Title">
                        <div class="item">
                            <a href="images/portfolio/6.jpg" class="portfolio-popup item-img">
                                <img src="images/portfolio/6.jpg" alt="">
                            </a>
                           
                        </div>
                    </div>

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
            <div class="testimonial-carousel-list margin-top-20">

                <div class="testimonial-word text-center">
                    <div class="review-photo" style="background-image: url(https://cdn.crowdpic.net/list-thumb/thumb_l_6E3D0D96ADF1E2E821C86602AF03B960.jpg);"></div>
                    <div class="review-content">
                        <h2>BEST-REVIEW1</h2>
                        <p>quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                            Duisauteiruredolor in reprehenderit in voluptate.
                        </p>
                            <div class="like-button">
                                  <i class="heart-icon"></i>
                            </div>
                    </div>
                </div>

                <div class="testimonial-word text-center">
                	
                	<!-- 이미지에 호버시 마우스 커서 모양 변경처리하기 -->
                    <div class="review-photo"  style="background-image: url(http://image.kmib.co.kr/online_image/2020/0825/612212110014937570_1.jpg);" onclick = "location.href='<%= request.getContextPath()%>/review/?~';"></div>
                    
                    <div class="review-content">
                        <h2>BEST-REVIEW2</h2>
                        <p>quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                            Duisauteiruredolor in reprehenderit in voluptate.
                            </p>
                            <div class="like-button">
                                  <i class="heart-icon"></i>
                            </div>
                    </div>
                </div>

                <div class="testimonial-word text-center">
                    <div class="review-photo"  style="background-image: url(https://cdn.imweb.me/upload/S2017101359e025984d346/bff36a6d2ced4.jpg);"></div>
                    
                    <div class="review-content">
                        <h2>BEST-REVIEW3</h2>
                        <p>quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                            Duisauteiruredolor in reprehenderit in voluptate.</p>
                            <div class="like-button">
                                   <i class="heart-icon"></i>
                                   
                            </div>
                    </div>
                </div>
		
            </div>
        </div>
    </div>
 
</section>
<!-- 리뷰 End -->


<%@ include file = "/WEB-INF/views/common/footer.jsp" %>