<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/views/common/LandingHeader.jsp"%>
<%
List<String> fontNameList = (List<String>)request.getAttribute("fontNameList");
System.out.println("fontNameList@jsp : " + fontNameList);
%>
    <!-- Community Board Enroll start -->
<section id="portfolio" class="portfolio section-space-padding">
    <div class="container">

            <section id="communityEnroll" class="community-enroll-section-space-padding">
            <div class="comm_container">
        
               
                    
                    <section id="board-container">
                    <h1>커뮤니티 게시글 작성</h1>
                    <div class="commenroll">
                    <form
                        name="boardEnrollFrm"
                        action="<%=request.getContextPath() %>/community/boardEnroll" 
                        method="post"
                        enctype="multipart/form-data" 
                        >
                         
                        <div id="tbl-board-view">
                       
                            <label for="title">제 목   </label>   
                            <input type="text" name="title" required></td>
                     


                            
                           <label for="writer">작성자   </label>
                                <input type="text" name="writer" value="<%= loginMember.getMemberName()%>" readonly/>
                         
                          
                            <label for="font">사용된 폰트  </label> 
                                <input type="text" name="font" id="font_search"/>
                            

                           
                               <div class="image-container">
                            <label for="upFile">첨부파일</label>
                                <label class="btn btn-primary btn-file">
                            	  파일등록 <input type="file"  name="upFile" style="display: none;" id="input-image">
								    </label>
								   <p id="changedFname" ></p>	
								     <img style="width: 300px;" id="preview-image" src="">
								  	</div> 
								   
								   
						   <!-- <div class="image-container">
								    <img style="width: 500px;" id="preview-image" src="https://dummyimage.com/500x500/ffffff/000000.png&text=preview+image">
								    <input style="display: block;" type="file" id="input-image">
								</div> -->
                      
                            <label for="content">내용</label>
                            <textarea rows="15" cols="114" name="content" style="resize: none;"></textarea>
                       
                            
                            <input type="submit" value="등록하기" id="submitBtn"  >
                            
                        </form>
                    </section>
                </div>
        
                
            </div>
        </section>
        
<script>



function readImage(input) {
    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]) {
        // 이미지 파일인지 검사 (생략)
        // FileReader 인스턴스 생성
        const reader = new FileReader()
        // 이미지가 로드가 된 경우
        reader.onload = e => {
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0])
    }
}

// input file에 change 이벤트 부여
const inputImage = document.getElementById("input-image")
inputImage.addEventListener("change", e => {
    readImage(e.target)
})


$("[name=upFile]").change((e) => {
    // 파일 선택여부
    const $file = $(e.target);
     const newFnameSrc = $file.val().split("\\");
     const newFname  = newFnameSrc [newFnameSrc .length-1]; //마지막 화일명
    if($file.val() != ""){
         $(changedFname).text(newFname);
    }
});


/**
 * boardEnrollFrm 유효성 검사
 */
 function boardValidate(e){
     const $title = $("[name=title]");
     const $content = $("[name=content]");
     var fontDb = "";
	 	<%for(String fontName : fontNameList){ %>
	 		fontDb += "<%=fontName%> ";
	 	<% } %>
	 	console.log(fontDb);
     //제목을 작성하지 않은 경우 폼제출할 수 없음.
     if(!/^.+$/.test($title.val())){
         alert("제목을 입력하세요.");
         return false;
     }
     if(font_search.value == '' || font_search.value == null){
         alert('사용한 폰트는 필수 항목입니다.');
         return false;
     } else if(fontDb.indexOf(font_search.value) == -1){
    	 alert('Elefont에 등록된 폰트만 입력해주세요.');
         return false;
     } 
                        
     //내용을 작성하지 않은 경우 폼제출할 수 없음.
     // .(임의의 문자)에는 \n(개행문자)가 포함되지 않는다.
     if(!/^(.|\n)+$/.test($content.val())){
         alert("내용을 입력하세요.");
         return false;
     }
     return true;
 }
 
 $(() => {
     $(document.boardEnrollFrm).submit(boardValidate);
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
 
	
	 		

</script>

<!-- Community Board Enroll end -->
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>