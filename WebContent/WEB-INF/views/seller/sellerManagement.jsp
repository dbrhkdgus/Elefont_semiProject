<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

     <!-- seller 심사완료1! -->
        <section id="portfolio" class="portfolio section-space-padding">
           <div class="seller-management">
                <div class="seller-management-title">
                    <h1>SELLER</h1>님의 폰트현황 | <span>폰트등록 0건</span><span> 폰트등록 3건</span> <span>  폰트등록 6건</span> </span><button id="sm-font-button">폰트등록</button>
                </div>
                
               	 <div class="font-enroll">
        <form action="" method="POST" name="fontEnrollFrm">
            <h3>등록하실 폰트명</h3>
            <div class="font-wrpper">
            <input type="text" name="font-name" id="font-name" placeholder="폰트명을 입력하세요"><i class="fas fa-check-circle"></i>
            </div>
            <h3>폰트파일 업로드</h3>
            <input type="file" name="font-file" id="font-file" />
            <input type="button" id="font-x-btn" value="취소하기">
            <input type="button" id="font-submit-btn" value="등록하기">
            <input type="hidden" name="memberId">
        </form>
        </div>
               	 
                <div class="seller-management-content">
                    
                    <div class="sm-enroll-font">
                        <h4>내가 등록한 폰트</h4>
                        <div class="sm-enroll-first">                      
                           <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">등록일자1</span><br>
                                <span>2021년 10월 25일</span>
                            </div>
    
                            <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">등록 폰트명</span><br>
                                <span>엘리폰트 체</span>
                            </div>
    
                            <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">등록 폰트파일</span><br>
                                <span>elefont.zip</span>
                            </div>
                        </div>
                        <hr class="liner">
                      
                        <h4>심사중인  폰트</h4>
                        <div class="sm-enroll-first">                          
                            <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">등록일자2</span><br>
                                <span>2021년 10월 25일</span>
                            </div>
    
                            <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">등록 폰트명</span><br>
                                <span>엘리폰트 체</span>
                            </div>
    
                            <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">등록 폰트파일</span><br>
                                <span>elefont.zip</span>
                            </div>

                            <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">심사결과</span><br>
                                <span>통과</span>
                            </div>
                        </div>

                   


                        <hr class="liner">
                        <h4>심사완료된 폰트</h4>
                        <div class="sm-enroll-first">
                            <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">등록일자3</span><br>
                                <span>2021년 10월 25일</span>
                            </div>
    
                            <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">등록 폰트명</span><br>
                                <span>엘리폰트 체</span>
                            </div>
    
                            <div class="sm-enroll-second">
                                <span id="sm-enroll-menu">등록 폰트파일</span><br>
                                <span>2021년 10월 25일</span>
                            </div>
                        </div>
                      
                        
                        
                    </div>
                
                </div>

            </div>








         </section>
   <script>
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
   
   </script>
        <!-- seller End -->
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>