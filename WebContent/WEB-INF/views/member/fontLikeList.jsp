<%@page import="com.kh.elefont.like_cart.model.vo.LikeFont"%>
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
	List<Attachment> allAttachmentList = (List<Attachment>)request.getAttribute("allAttachmentList");
	List<LikeFont> likeFontList = (List<LikeFont>)request.getAttribute("likeFontList");
	
	
	
	System.out.println(allAttachmentList);
	List<String> categoryList = (List<String>)session.getAttribute("categoryList");
	String str = "";
	for( String c : categoryList){
		str += c;
		
				
	};
	
	
%>
     <!-- Portfolio Start -->
        <section id="font-likelist" class="font-likelist section-space-padding">
            <div class="container">
             
    
                <div class="likelist-inner">
                    <div class="test-shop-box">
    
                        <div class="likelist-tools">
                            <div class="tools likelist-change">
                                <table>
                                    <tr>
<%
	String likeProfilepic ="";
	for(Attachment att : allAttachmentList ){
		if(att.getCommNo() == null && att.getFontNo() == null && att.getMemberNo().equals(loginMember.getMemberNo())){
			likeProfilepic = att.getRenamedFilename();
			System.out.println("likeProfilepic" + likeProfilepic);

%>                                
                                    <th rowspan="2">
                                     <div class="communitylike-profile-photo-box">
                                     <img src="<%= request.getContextPath()%>/upload/profilephotos/<%=likeProfilepic%>" alt="" />
                                     </div>
                                     </th>
                                    <th><%=loginMember.getMemberId() %>님 좋아요목록</th>
                                    <!-- th colspan="2">좋아요목록</th> -->
<%
	}
}
%>
                                </tr>
                                <tr>
                                        <!-- <td>좋아요 카운트 수</td> -->
                                        
                                        <td><button id="member-font">Font</button><button id="member-comm">Community</button></td>
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
                            <input type="range" min="10" max="50" step="2" value="20" id="range" />
                        </div>
        
                    </div>
                    <div class="test-shop">
                        <div class="test-item">
                            <div class="test-item-title"> 전체 폰트 적용 </div>

                            <textarea name="" id="allFontStyle" cols="30" rows="10"></textarea>
                           
                        </div>
                            
<%
    List<Attachment> fontAttchmentList = (List<Attachment>)request.getAttribute("fontAttchmentList");
    List<String> likeList = (List<String>) request.getAttribute("likeList");
   	String fontNo ="";
   	String fontName ="";
   	String fontFamily="";
   	int fontLikeCount = 0;
   	
   	for(LikeFont fontLike : likeFontList){
   		fontNo=fontLike.getFontNo();
   	for(Font font : fontList){
   		if(font.getFontNo().equals(fontNo))
    	for(Attachment att : fontAttchmentList){
    		fontLikeCount = font.getFontLikeCount();
 	   		fontName = font.getFontName();
    		fontFamily = font.getFontFamily();	
   		 	}
    	}
%>                       
						<div class="test-item">
                            <a href="<%= request.getContextPath()%>/shopDetail?fontNo=<%=fontNo %>"><div class="test-item-title"> <%=fontName %> </div></a>
                            <textarea name="" id="<%= fontNo%>" cols="30" rows="10" class="font-style" style="font-family: '<%= fontFamily %>';" ></textarea>
                           <div class="test-item-buttons"> 
<%
			if(loginMember != null && !likeList.isEmpty() && likeList.contains(fontNo)){
%>
                                <i class="fas fa-heart" data-font-no="<%=fontNo%>"><span><%=fontLikeCount%></span></i>  
<%
			}else{
%>
                                <i class="far fa-heart" data-font-no="<%=fontNo%>"><span><%=fontLikeCount%></span></i>  
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
       
        </section>
    
        <!-- Portfolio End -->
<script>
$("#range-result").html($(range).val()+"px");
$(".font-style").css("font-size", $(range).val()+"px");
$(".font-style").css("color", $(color).val());

	/* 샵 랜딩 스타일 체크박스 드롭다운 메뉴 */
	$("#style-button").click((e)=>{
		console.log("스타일 버튼 클릭");
	    $(".font-style-chkbox").toggle();
	 
	});
	
	
	/* 글씨체 체크박스  */
	
   		$("input[name='font-style']").change((e)=>{
   			
   	
        	if($(e.target).is(':checked')){
        	 console.log("check")
<%if(!categoryList.isEmpty()){%>
                 var categoryList = "<%=str %>";
                location.href = `<%=request.getContextPath()%>/shop?category=\${categoryList}&add=\${$(e.target).val()}`;  
<%}else{%>
  				 location.href = `<%=request.getContextPath()%>/shop?add=\${$(e.target).val()}`; 		
<%}%>                     
         }else{
        	  location.href = `<%=request.getContextPath()%>/shop?flag=\${$(e.target).val()}`;  
        	 console.log("uncheck")
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
		console.log($fontColor);
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
					console.log(data);
					let temp = data.split("\n");
					temp = $.map(temp, (name, index)=>{
						return {
							label : name,
							value : name
						}
					});
					console.log(temp);
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
		console.log($('#font-sort').val());
		location.href = `<%=request.getContextPath()%>/shop?sort=\${$('#font-sort').val()}`
	});
	
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