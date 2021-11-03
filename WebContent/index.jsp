<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@include file = "/WEB-INF/views/common/header.jsp" %>

<%session.removeAttribute("categoryList"); session.removeAttribute("fontList"); %>
<script>
const commNo = [];
const renamedFilename = [];
const commTitle = [];
const commContent = [];
 $.ajax({
	url: "<%=request.getContextPath()%>/mainLanding",
	dataType: "json",
	type:"GET",
	success(data){
		const fontList = data["fontList"]; 
		const communityList = data["communityList"];
		const attachmentList = data["attachmentList"];
		console.log(communityList);
		
		for(let i = 0; i < fontList.length; i++){
			$("header").append(`<style>
					@font-face {
				    font-family: '\${fontList[i].fontFamily}';
				    src: url('\${fontList[i].fontUrl}') format('woff');
				    font-weight: normal;
				    font-style: normal;
				}
					#\${fontList[i].fontNo}{
						font-size : 40px;
					}
					
					
					</style>`);
			$("#fonts-box").append(`<div class="test-item"> <div class="landing-fontName-textarea-box"><a href="<%=request.getContextPath()%>/shopDetail?fontNo=\${fontList[i].fontNo}">\${fontList[i].fontName}</a> </div><textarea name="" id="\${fontList[i].fontNo}" cols="30" rows="10" class="font-style" style="font-family:'\${fontList[i].fontFamily}';" ></textarea><\div></div>`);
			
		};
		
		
		for(let i = 0; i < 3; i++){
			for(let j = 0; j <attachmentList.length; j++){
				if(communityList[i].commNo == attachmentList[j].commNo){
					commNo.push(communityList[`\${i}`].commNo);
					renamedFilename.push(attachmentList[j].renamedFilename);
					commTitle.push(communityList[i].commTitle);
					commContent.push(communityList[i].commContent);
					
	                    for(let i = 0; i < 3; i++){
	                    	console.log(`communityList[\${i}].commNo = \${communityList[i].commNo}`);
	                    	$(`#rc\${i+1}`).children("h2").text(`\${commTitle[i]}`);
	    	                $(`#rc\${i+1}`).children("p").text(`\${commContent[i]}`);
	    	                $("body").append(`<style>
	    	    					#rp\${i}{
	    	    						background-image : url(<%=request.getContextPath()%>/upload/community/\${renamedFilename[i]});
	    	    						
	    	    					}
	    	               
	                    }
	                
	                
						$("body").append(`<script>$("#\${communityList[i].commNo}").click((e)=>{location.href = "<%=request.getContextPath()%>/community/pictureDetail?commNo=\${communityList[i].commNo}";});`);
						
						/* Testimonial Carousel/Slider */

						$(".testimonial-carousel-list").owlCarousel({
						    items: 1,
						    autoPlay: true,
						    stopOnHover: false,
						    navigation: true,
						    navigationText: ["<i class='fa fa-long-arrow-left fa-2x owl-navi'></i>", "<i class='fa fa-long-arrow-right fa-2x owl-navi'></i>"],
						    itemsDesktop: [1199, 1],
						    itemsDesktopSmall: [980, 1],
						    itemsTablet: [768, 1],
						    itemsTabletSmall: false,
						    itemsMobile: [479, 1],
						    autoHeight: true,
						    pagination: false,
						    transitionStyle : "backSlide"
						});
						
						<\/script>
						`);
					
					
					
					
					</style>`);
					
				}
			
			}
		};
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

                <div class="testimonial-word text-center">
                    <div class="review-photo" id="rp1"></div>
                    <div class="review-content" id="rc1">
                        <h2></h2>
                        <p>
                        </p>
                            
                    </div>
                </div>

                <div class="testimonial-word text-center">
                	
                	<!-- 이미지에 호버시 마우스 커서 모양 변경처리하기 -->
                    <div class="review-photo"  id="rp2" ></div>
                    
                    <div class="review-content" id="rc2">
                        <h2></h2>
                        <p>
                            </p>
                            
                    </div>
                </div>

                <div class="testimonial-word text-center">
                    <div class="review-photo"  id="rp3"></div>
                    
                    <div class="review-content" id="rc3">
                        <h2></h2>
                        <p></p>
                           
                    </div>
                </div>
		
            </div>
        </div>
    </div>
 
</section>
<!-- 리뷰 End -->







<%@ include file = "/WEB-INF/views/common/footer.jsp" %>