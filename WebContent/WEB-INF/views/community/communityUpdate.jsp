<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/views/common/LandingHeader.jsp"%>

<%
	Community community = (Community) request.getAttribute("community");
	Attachment attachment = (Attachment) request.getAttribute("attachment");
	Font font = (Font) request.getAttribute("font");
%>
    <!-- Community Board Enroll start -->
<section id="portfolio" class="portfolio section-space-padding">
    <div class="container">

        <script>
            /**
            * boardEnrollFrm 유효성 검사
            */
            function boardValidate(e){
                const $title = $("[name=title]");
                const $content = $("[name=content]");
                //제목을 작성하지 않은 경우 폼제출할 수 없음.
                if(!/^.+$/.test($title.val())){
                    alert("제목을 입력하세요.");
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
            </script>
            
            <section id="communityEnroll" class="community-enroll-section-space-padding">
            <div class="comm_container">
        
               
                    
                    <section id="board-container">
                    <h1>커뮤니티 게시글 수정</h1>
                    <div class="commenroll">
                    <form
                        name="boardEnrollFrm"
                        action="<%=request.getContextPath() %>/community/boardEnroll" 
                        method="post"
                        enctype="multipart/form-data" 
                        >
                         
                        <div id="tbl-board-view">
                       
                            <label for="title">제 목   </label>   
                            <input type="text" name="title" value="<%= community.getCommTitle() %>" required></td>
                     

							
                           <label for="writer">작성자   </label>
                                <input type="text" name="writer" value="<%= community.getCommWriter() %>" readonly/>
                         
                          
                            <label for="font">사용된 폰트  </label> 
                                <input type="text" name="font" value="<%= font.getFontName() %>"/>
                            

                           
                            
                            <label for="upFile">첨부파일</label>
                            
								<label class="btn btn-primary btn-file">
                            	  파일변경 <input type="file"  name="upFile" style="display: none;">
								    </label>
                            	 <span id="originalFname"><%= attachment.getOriginalFilename() %></span>	
                                 <img id="orginalAttachment" src="<%= request.getContextPath()%>/upload/community/<%=attachment.getRenamedFilename()%>" alt="">
								 <span id="changedFname"></span>
                            <label for="content">내용</label>
                            <textarea rows="15" cols="114" name="content" style="resize: none;"><%= community.getCommContent() %></textarea>
                       
                            
                            <input type="submit" value="수정하기" id="submitBtn"  >
                            <input type="button" value="취소하기" onclick="history.go(-1);"/>
                        </form>
                    </section>
                </div>
        
                
            </div>
        </section>
<script>
$("[name=upFile]").change((e) => {
	// 파일 선택여부 
	const $file = $(e.target);
	console.log($file.val());
	if($file.val() != ""){
		// 파일선택한 경우
		$(originalFname).hide();
		$(orginalAttachment)
			.hide()
			.next()
			.hide();
	}
	else {
		// 파일선택을 취소한 경우
		$(originalFname).show();
		$(orginalAttachment)
			.show()
			.next()
			.show();
	}
	
});

$(document.boardUpdateFrm).submit(function (){
	const $title = $("[name=title]");
	const $content = $("[name=content]");
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.+$/.test($title.val())){
		alert("제목을 입력하세요.");
		return false;
	}
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	// .(임의의 문자)에는 \n(개행문자)가 포함되지 않는다.
	if(!/^(.|\n)+$/.test($content.val())){
		alert("내용을 입력하세요.");
		return false;
	}
	return true;
});
</script>        


<!-- Community Board Enroll end -->
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>