<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>


<%
	Community community = (Community)request.getAttribute("community");
	Attachment attachment = (Attachment)request.getAttribute("attachment");
	List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
	List<String> commLikeList = (List<String>) request.getAttribute("commLikeList");
%>



<section id="portfolio" class="portfolio section-space-padding">
    <div class="container">
        <div class="comm-pic-detail">

            <div class="comm-board-content">
            	<div class="comm-board-title-button">
		            <h1><%=community.getCommTitle() %></h1>
		            <div class="comm-board-button-box">
		            <input type="button" id="comm-board-button" value="수정하기" onclick="updateBoard()">
					<input type="button" id="comm-board-button" value="삭제하기" onclick="deleteBoard()">
		            </div>
            	</div>
	            <div class="comm-board-img-user-content">
	                <img id="comm-user-attach-img" src="<%= request.getContextPath()%>/upload/community/<%=attachment.getRenamedFilename()%>" alt="">
	                <div class="comm-user-content">
	                   <%=community.getCommContent() %>
	                </div>
	            </div>
	            <div class="comm-board-comment">
	            	<hr />
	            	<span>와 너무 예뻐요 !</span>
	            </div>
            </div>
            
            <div class="comm-writer-info" >
                <div class="comm-writer-info-buttons">
<%
				if(loginMember != null && !commLikeList.isEmpty() && commLikeList.contains(community.getCommNo())){
%>
              <i class="fas fa-heart" data-comm-no="<%=community.getCommNo()%>"><span><%=community.getCommLikeCount() %></span></i>
<%
				}else{
%> 
     <i class="far fa-heart" data-comm-no="<%=community.getCommNo()%>"><span><%=community.getCommLikeCount() %></span></i>
<%
				}
%>     
                    <i class="fas fa-search-plus" onclick="location.href='<%= request.getContextPath() %>/shopDetail?fontNo=<%=community.getFontNo()%>'"></i>
   
                </div>
                <div class="comm-writer-img-name" onclick="location.href='<%= request.getContextPath()%>/community/writerDetail?commWriter=<%= attachment.getMemberNo() %>'">
                    <img class="comm-pic-writer-profile-img" src="https://i.ibb.co/c6SYFNx/free-icon-male-user-74464.png" alt="">
                    <h4><%=community.getCommWriter() %></h4>
                </div>
                <div class="comm-writer-comm-history-content comm-pic-detail-history">
<%
int cnt = 0;
for(Attachment att : attachmentList){
	
	if(attachment.getMemberNo().equals(att.getMemberNo()) && !(community.getCommNo().equals(att.getCommNo())) && cnt < 5) {
				
%>               
					<a href="<%= request.getContextPath()%>/community/pictureDetail?commNo=<%= att.getCommNo() %>">
                    <img src="<%= request.getContextPath()%>/upload/community/<%=att.getRenamedFilename()%>" alt="">
                    </a>
<%
		cnt++;
	} else if(attachment.getMemberNo().equals(att.getMemberNo()) && !(community.getCommNo().equals(att.getCommNo())) && cnt >= 5) {
		System.out.println("오늘의집처럼 더보기 추가하기");
	}
}
 %>                    
                    
                </div>
            </div>
        </div>
        </div>
</section>
<form action="<%= request.getContextPath() %>/community/communityDelete" name="deleteBoardFrm">
	<input type="hidden" name="no" value="<%= community.getCommNo() %>" />
</form>
<script>

$(".fa-heart").click((e)=>{
	
	<%
	if(loginMember == null){
	%>
			alert("로그인 후 사용 가능한 기능입니다.");
			return;
	<%
	}else if("A".equals(loginMember.getMemberRole())){
	%>
			alert("일반 회원만 사용 가능합니다.");
			return;
	<%
	}
	%>
	let $target = $(e.target);
	let $commNo = $target.data("commNo");
	console.log($commNo);
	
	$.ajax({
		url:"<%=request.getContextPath()%>/community/commDetailLike",
		dataType :"json",
		type:"GET",
		data:{'commNo' : $commNo},
		success(jsonStr){
			console.log(jsonStr);
			const likeValid = jsonStr["likeValid"];
			const likeCnt = jsonStr["likeCnt"];
			if(likeValid == 1){
				$target
					.removeClass("far")
					.addClass("fas");
			}else{
				$target
					.removeClass("fas")
					.addClass("far");
			}
			$target.html(`<span>\${likeCnt}</span>`);
		},
		error: console.log
	});
});
		


const updateBoard = 
() => location.href = "<%= request.getContextPath() %>/community/communityUpdate?no=<%= community.getCommNo() %>";
/**
 * 삭제할 때 저장된 첨부파일이 있다면, 파일삭제!
 * - java.io.File API참조
 */
const deleteBoard = () => {
	if(confirm("정말 이 게시물을 삭제하시겠습니까?")){
		$(document.deleteBoardFrm).submit();
	}
};
</script>

<!-- community user detail 끝 -->




<%@ include file = "/WEB-INF/views/common/footer.jsp" %>