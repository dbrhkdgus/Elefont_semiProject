<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%@include file = "/css/fontApply.jsp" %>
<%
	String sort = request.getParameter("sort") == null? "newest" : request.getParameter("sort");	
	
	List<String> categoryList = (List<String>)session.getAttribute("categoryList");
	String str = "";
	for( String c : categoryList){
		str += c;
		
				
	};
	
	
%>
	<!-- Portfolio Start -->
    <section id="portfolio" class="portfolio section-space-padding">
        <div class="container">
         

            <div class="portfolio-inner">
                <div class="test-shop-box">

                    <div class="search-tools" id="search-tools">
                        <div class="tools font-name-finder">
                            <input type="text" name="font-search" id="font_search" placeholder="폰트명으로 검색">                     
                            <button id="btn-search"><i class="fas fa-search"></i></button>
                       
                            
                        </div>
                        <div class="tools font-color-previewer">
                            <span id="color-title">색상 선택하기</span>
                            <form action="HTMLColorPicker.html">
                                <input type="color" name="favcolor" id="color" >
                            </form>
                        </div>
                        <div class="tools font-size-range">
                            <span id="range-result"></span>
                            <input type="range" min="10" max="50" step="2" value="20" id="range" />
                        </div>
                        <div class="tools font-style-category">                   
                            <button id="style-button">폰트 형태</button>
                            <div class="font-style-chkbox">
                              <input type="checkbox" name="font-style" id="font-style1" value="S" <%= str.contains("S") ? "checked" : "" %>><label for="font-style1" >Serif</label><br>
                                <input type="checkbox" name="font-style" id="font-style2" value="G" <%= str.contains("G") ? "checked" : "" %>><label for="font-style2"  >Sans Serif</label><br>
                                <input type="checkbox" name="font-style" id="font-style3" value="H" <%= str.contains("H") ? "checked" : "" %>><label for="font-style3"  >Handwriting</label><br>
                                <input type="checkbox" name="font-style" id="font-style4" value="M"  <%= str.contains("M") ? "checked" : "" %>><label for="font-style4" >Monospace</label>
                                
                            </div>
                        </div>
                        <div class="tools font-sort-selector">
                            <select name="font-sort" id="font-sort">
                                <option value="popular" <%= "popular".equals(sort) ? "selected" : "" %>>인기순</option>
                                <option value="view" <%= "view".equals(sort) ? "selected" : "" %>>조회순</option>
                                <option value="order" <%= "order".equals(sort) ? "selected" : "" %>>판매순</option>
                                <option value="recommand" <%= "recommand".equals(sort) ? "selected" : "" %>>MD추천순</option>
                                <option value="newest" <%= "newest".equals(sort) ? "selected" : "" %>>최신순</option>
                            </select>
                        </div>
                    </div>

              
                    <div class="test-shop">
                        <div class="test-item">
                            <div class="test-item-title" style="font-family: 'NanumSquareRound'; font-size : 25px;"> 전체 폰트 적용 </div>

                            <textarea name="" id="allFontStyle" cols="30" rows="10" style="font-family: 'NanumSquareRound';"></textarea>
                           
                        </div>
                       
 <%
 	
    List<Attachment> fontAttchmentList = (List<Attachment>)request.getAttribute("fontAttchmentList");
    List<String> likeList = (List<String>) request.getAttribute("likeList");
    for(Font font : fontList){
    	for(Attachment att : fontAttchmentList){
    		
    	}
%>
                        <div class="test-item">
                            <a href="<%= request.getContextPath()%>/shopDetail?fontNo=<%= font.getFontNo()%>"><div class="test-item-title" style="font-family: '<%= font.getFontFamily() %>'; font-size: 20px;"> <%= font.getFontName()%></div></a>
                            <textarea name="" id="<%= font.getFontNo() %>" cols="30" rows="10" class="font-style" style="font-family: '<%= font.getFontFamily() %>';" ></textarea>
                            <div class="test-item-buttons"> 
<%
			if(loginMember != null && !likeList.isEmpty() && likeList.contains(font.getFontNo())){
%>
                                <i class="fas fa-heart" data-font-no="<%=font.getFontNo()%>"><span><%=font.getFontLikeCount()%></span></i>  
<%
			}else{
%>
                                <i class="far fa-heart" data-font-no="<%=font.getFontNo()%>"><span><%=font.getFontLikeCount()%></span></i>  
<%
			}
%>
                               
                             </div>
                        </div> 

<%
    }
 %>                    
                       
                    </div>


                </div>
            </div>

    </section>
<script>
$("#range-result").html($(range).val()+"px");
$(".font-style").css("font-size", $(range).val()+"px");
$(".font-style").css("color", $(color).val());

	/* 샵 랜딩 스타일 체크박스 드롭다운 메뉴 */
	$("#style-button").click((e)=>{
	    $(".font-style-chkbox").toggle();
	 
	});
	
	
	/* 글씨체 체크박스  */
	
   		$("input[name='font-style']").change((e)=>{
   			
   	
        	if($(e.target).is(':checked')){
<%if(!categoryList.isEmpty()){%>
                 var categoryList = "<%=str %>";
                location.href = `<%=request.getContextPath()%>/shop?category=\${categoryList}&add=\${$(e.target).val()}`;  
<%}else{%>
  				 location.href = `<%=request.getContextPath()%>/shop?add=\${$(e.target).val()}`; 		
<%}%>                     
         }else{
        	  location.href = `<%=request.getContextPath()%>/shop?flag=\${$(e.target).val()}`;  
         }
  }); 
  




    
    
     
	  

	

	/* 폰트 사이즈 조절 바 px크기 입력, textarea에 반영*/
	
	$(range).change((e)=>{
		let $fontSize = $(e.target).val();
		$("#range-result").html($(range).val()+"px");
		$(".font-style").css("font-size", $fontSize+"px");
    });
	
	/* 돋보기 아이콘 클릭시 넘어감*/
	$(".fa-search-plus").click((e)=>{
		location.href = "<%=request.getContextPath()%>/shopDetail";
	});
	
	/* 색상 변경 시 아래 textarea에 있는 글씨들에 반영 */
	$(color).change((e)=>{
		let $fontColor = $(e.target).val();
		$(".font-style").css("color",$fontColor);
	});
	
	/* 전체 폰트 적용에 내용 입력 시 모든 textarea에 적용 */
	$(allFontStyle).on("propertychange change keyup paste input", (e)=>{
		$(".font-style").val($(e.target).val());
	});
	
	/* 좋아요 버튼 클릭시 사용자 좋아요 여부에 따른 버튼 이벤트 */

 	$(".fa-heart").click((e)=>{
<%
	if(loginMember == null){
%>  
		alert("로그인 후 이용 가능합니다.");
		$('.loginBox').show();
    	$(loginId).select();
    	
    	var loginBox = setInterval(function(){
    		$('.loginBox').fadeOut(1000).fadeIn(1000);
    	},500);
    	
    	$('.loginBox').mouseover(function(){
    		clearInterval(loginBox);
    		});
    	/* $(".loginBox").css("background-color",'gold'); */
		return;
<%
	}else if("A".equals(loginMember.getMemberRole())){
%>
	
		alert("일반 회원만 사용 가능한 기능입니다.");
		return;
<%
 	}
%>
		let $target = $(e.target);
		let $fontNo = $target.data("fontNo"); 
		
		$.ajax({
			url: "<%=request.getContextPath()%>/font/fontLike",
			dataType: "json",
			type:"GET",
			data: {'fontNo' : $fontNo}, 
			success(data){
				const likeValid = data["likeValid"]; 
				const likeCnt = data["likeCnt"];
				//member 본인의 likeValid가 1이라면 속이 찬 하트, 0이면 속이 빈 하트
				if(likeValid == 1){
					$target
						.removeClass("far")
						.addClass("fas");
				}else{
					$target
						.removeClass("fas")
						.addClass("far");
				}
				//likeCnt값 적용
				$target.html(`<span>\${likeCnt}</span>`);
			},
			error: console.log
		});
	}); 
	/* 검색어 자동완성 */
	$("#font_search").autocomplete({

		source(request, response){
			//request : 사용자 입력값을 가진 객체
			//response : 검색어 목록 데이터를 처리할 콜백 함수
			//console.log(request, response);
			
			const {term : searchName} = request;
			$.ajax({
				url: "<%= request.getContextPath()%>/autocomplete",
				data: {searchName}, //?searchName=김
				success(data){
					let temp = data.split("\n");
					temp = $.map(temp, (name, index)=>{
						return {
							label : name,
							value : name
						}
					});
					response(temp);
				},
				error : console.log
			});
		}
	});
	
	$("#btn-search").click((e)=>{
		location.href = `<%=request.getContextPath()%>/shopSearch?fontName=\${$(font_search).val()}`;
	});
	$('#font-sort').change((e)=>{
		location.href = `<%=request.getContextPath()%>/shop?sort=\${$('#font-sort').val()}`
	});

	
</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>