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
	String oldRenamedFilename = attachment.getRenamedFilename();
%>
    <!-- Community Board Enroll start -->
<section id="portfolio" class="portfolio section-space-padding">
    <div class="container">
            <section id="communityEnroll" class="community-enroll-section-space-padding">
            <div class="comm_container">
                    <section id="board-container">
                    <h1>커뮤니티 게시글 수정</h1>
                    <div class="commenroll">
                    <form
                        name="boardUpdateFrm"
                        action="<%=request.getContextPath() %>/community/communityUpdate"
                        method="post"
                        enctype="multipart/form-data"
                        >
                        <div id="tbl-board-view">
                       		<input type="hidden" name="no" value="<%= community.getCommNo() %>" />
                            <label for="title">제 목   </label>
                            <input type="text" name="title" value="<%= community.getCommTitle() %>" required></td>
							
                           <label for="writer">작성자   </label>
                                <input type="text" name="writer" value="<%= community.getCommWriter() %>" readonly/>
                            <label for="font">사용된 폰트  </label>
                                <input type="text" name="font" value="<%= font.getFontName() %>"/>
                          
                       
							
                            <label for="upFile">첨부파일</label>
								<label class="btn btn-primary btn-file">
                            	  파일변경 <input type="file"  name="upFile" style="display: none;" id="input-image">
								    </label>
                            	 <span id="originalfname"><%= attachment.getOriginalFilename() %></span>	
                                     <img id="originalAttachment" style="width: 300px;" src="<%= request.getContextPath()%>/upload/community/<%=attachment.getRenamedFilename()%>"  value="<%= attachment.getAttNo() %>" alt="">
                            	 <p id="changedFname" ></p>
                            	  <img style="width: 300px;" id="preview-image" src="">
<%if(oldRenamedFilename.equals(attachment.getRenamedFilename())){ %>
								<input type="checkbox" name="delFile" id="delFile" style="display:none;"value="<%= attachment.getAttNo() %>"/>
<% } %>

                            <label for="content">내용</label>
                            <textarea rows="15" cols="114" name="content" style="resize: none;"><%= community.getCommContent() %></textarea>
                        	<div class="updateBtn"> 
                            <input type="submit" value="수정하기" id="submitBtn"  >
                            <input type="button" value="취소하기" id="submitBtn" onclick="history.go(-1);"/>
                        	</div>
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
    // 파일선택한 경우
        $(originalfname).hide();
        $(originalAttachment)
            .hide()
            .next()
            .show();
         $(changedFname).text(newFname);
    }
    else {
        // 파일선택을 취소한 경우
        $(originalfname).show();
        $(originalAttachment)
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