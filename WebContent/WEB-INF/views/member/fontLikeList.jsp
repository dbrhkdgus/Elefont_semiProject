<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>

     <!-- Portfolio Start -->
        <section id="font-likelist" class="font-likelist section-space-padding">
            <div class="container">
             
    
                <div class="likelist-inner">
                    <div class="test-shop-box">
    
                        <div class="likelist-tools">
                            <div class="tools likelist-change">
                                <table>
                                    <tr>
                                        <th rowspan="2"><i class="fas fa-user"></i></th>
                                        <th>유저 아이디</th>
                                        <th colspan="2">좋아요목록</th>
                                    </tr>
                                    <tr>
                                        <td>좋아요 카운트 수</td>
                                        <td><button id="member-font">Font</button></td>
                                        <td><button id="member-comm">Community</button></td>
                                    </tr>
                                </table>
                                
                            </div>
                            <div class="tools font-color-previewer">
                                <span id="color-title">색상 선택하기</span>
                                <form action="HTMLColorPicker.html">
                                    <input type="color" name="favcolor" id="color" >
                                </form>
                            </div>
                            <div class="tools font-size-range">
                                <span id="range-result"></span>
                                <input type="range" min="1.2" max="2.6" step=".2" id="range" />
                            </div>
                        </div>

                  
                        <div class="like-list">
                            <div class="like-item">
                                <div class="like-item-title"> 전체 폰트 적용 </div>
    
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                               
                            </div>
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div> 
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div>
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div>
                           
                        </div>
                           
                        <div class="like-list">
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div> 
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div> 
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div>
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div>
                           
                        </div>
                           
                        <div class="like-list">
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div> 
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div> 
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div>
                            <div class="like-item">
                                <a href=""><div class="like-item-title"> 광현체 </div></a>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                <div class="like-item-buttons"> 
                                    <i class="fas fa-heart"></i>
                                 </div>
                            </div>
                           
                        </div>
                           
                        </div>
    
    
                    </div>
                </div>
            </div>
        </section>
    
        <!-- Portfolio End -->
<script>
	$("#member-comm").click((e)=>{
		console.log("클릭");
		location.href = "<%=request.getContextPath()%>/member/commLikeList"
	});

	/* 폰트 사이즈 조절 바 px크기 입력*/
	$("#range-result").html($(range).val()+"px");
	$(range).change((e)=>{
		$("#range-result").html($(range).val()+"px");
	});
</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>