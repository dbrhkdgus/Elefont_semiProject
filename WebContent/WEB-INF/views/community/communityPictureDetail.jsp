<%@page import="com.kh.elefont.rep.model.vo.Rep"%>
<%@page import="com.kh.elefont.member.model.service.MemberService"%>
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
	List<Rep> repList = (List<Rep>)request.getAttribute("repList");
	
	
	
	boolean editable = loginMember != null && (
			  loginMember.getMemberNo().equals(attachment.getMemberNo())
			  || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
			);
			
%>
<section id="portfolio" class="portfolio section-space-padding">
    <div class="container">
        <div class="comm-pic-detail">
            <div class="comm-board-content">
            	<div class="comm-board-title-button">
		            <h1><%=community.getCommTitle() %></h1>
		           
<% 	
if(editable){ 
%>
		            <div class="comm-board-button-box">
		            <input type="button" id="comm-board-button" value="수정하기" onclick="updateBoard()">
					<input type="button" id="comm-board-button" value="삭제하기" onclick="deleteBoard()">
		            </div>
<% 	
} 
%>
            	</div>
	            <div class="comm-board-img-user-content">
	                <img id="comm-user-attach-img" src="<%= request.getContextPath()%>/upload/community/<%=attachment.getRenamedFilename()%>" alt="">
	                <div class="comm-user-content">
	                   <%=community.getCommContent() %>
	                </div>
	            </div>
	            <div class="comm-detail-reply">
	            		<h4 id="shop-detail-rep">댓글</h4>
		            	<hr class="liner"/>
		            	
<%
if(loginMember!=null)
{ 
%>
	            <form action="<%= request.getContextPath() %>/rep/communityRepEnroll" method="POST"name ="commRepEnrollFrm">
		            <div class="comm-board-repEnroll">
						<input type="text" class="rep-enroll-area" name="rep-content" placeholder="댓글을 입력하세요." />
						
						<input type="submit" value="등록" />
						<input type="hidden" name="commNo" value="<%= community.getCommNo() %>" />		            	
						<input type="hidden" name="memberNo" value="<%= loginMember.getMemberNo() %>" />		            	
						<input type="hidden" name="repWriter" value="<%= loginMember.getMemberName() %>" />
						<input type="hidden" name="repLevel" value="1" />		            	
						<input type="hidden" name="repRef" value="0" />
		            </div>
	            </form>
	          
<%
} 

for(Rep rep : repList){
	if(rep.getRepLevel()==1) {
%>
			<form action="<%=request.getContextPath()%>/rep/DeleteUpdateRep" method="POST" name="commRepUpdateFrm" >
			<div class="comm-board-rep-comment">
				<div class="comm-reply reply-box">
	                 <img src="https://cdn1.vectorstock.com/i/1000x1000/10/05/user-icon-vector-22391005.jpg"  id="user-profile">
                      	<div class="reply-writer-content">
             	         	<span><%=rep.getRepWriter()%> : </span>
                    	  	<span><%=rep.getRepContent()%></span>
                    	</div>
				</div> 
            	<i class="fab fa-replyd" style="font-size:35px; color: #005A3C;"></i>
<%
		if(loginMember != null && (loginMember.getMemberNo().equals(rep.getMemberNo()) || "A".equals(loginMember.getMemberRole()))) {
%>			
					<input type="button" value="수정" class="btn-transform" />
					<input type="button" value="등록" class="btn-enroll"/>
					<input type="button" value="삭제" class="btn-delete"/>
					<input type="hidden" name="type" value=""/>
					<input type="hidden" name="rep_no" value="<%=rep.getRepNo()%>"/>
<% 				
		}
%>
					</div>
				</form>
			
<%
		if(loginMember!=null) {
%>	
				 <form action="<%= request.getContextPath() %>/rep/communityRepEnroll" method="POST" name ="commReRepEnrollFrm">
		            <div class="comm-board-re-repEnroll">
						<input type="text" name="rep-content" placeholder="댓글을 입력하세요." />
						
						<input type="submit" value="등록" />
						<input type="hidden" name="commNo" value="<%= community.getCommNo() %>" />		            	
						<input type="hidden" name="memberNo" value="<%= loginMember.getMemberNo() %>" />		            	
						<input type="hidden" name="repWriter" value="<%= loginMember.getMemberName() %>" />
						<input type="hidden" name="repLevel" value="2" />		            	
						<input type="hidden" name="repRef" value="<%= rep.getRepRef() == 0 ? rep.getRepNo() : rep.getRepRef() %>" />
		            </div>
	            </form>	
	            
<%
		}
	} else {
%>
					<form action="<%=request.getContextPath()%>/rep/DeleteUpdateRep" method="POST" name="commReRepUpdateFrm">
					<div class="comm-board-re-rep-comment">
						<div class="re-reply-box">
							<img src="https://i.ibb.co/chkD19T/image.png" alt="" />
			                 <img src="https://cdn1.vectorstock.com/i/1000x1000/10/05/user-icon-vector-22391005.jpg"  id="user-profile">
		                      	<div class="reply-writer-content">
		             	         	<span><%=rep.getRepWriter()%> : </span>
		                    	  	<span><%=rep.getRepContent()%></span>
		                    	</div>

<%
		if(loginMember != null && (loginMember.getMemberNo().equals(rep.getMemberNo()) || "A".equals(loginMember.getMemberRole()))) {
%>						
								<input type="button" value="수정" class="btn-transform" />
								<input type="button" value="등록" class="btn-enroll"/>
								<input type="button" value="삭제" class="btn-delete"/>
								<input type="hidden" name="type" value=""/>
								<input type="hidden" name="rep_no" value="<%=rep.getRepNo()%>"/>
<%			
		}
%>
						</div> 
					</div>						
					</form>

<%
	}
/*foreach문 끝남*/
}
%>
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
<% if(editable){ %>
<form action="<%= request.getContextPath() %>/community/communityDelete" name="deleteBoardFrm">
	<input type="hidden" name="no" value="<%= community.getCommNo() %>" />
</form>
<%}%>
<script>
$(".btn-enroll").hide();

$(".btn-enroll").click((e)=>{
	$("input[name=type]").val("update");
	
	 $(e.target).parent().submit(); 
});

$('.btn-transform').on('click', (e)=>{
	$(e.target).hide();
	$(e.target).next().show();
	 var oldContent = $(e.target).parent().children("div").children("div").children("div").children(':nth-child(2)').text();
	 $(e.target).parent().children("div").children("div").children("div").children(':nth-child(2)').html('');
	 $(e.target).parent().children("div").children("div").children("div").children(':nth-child(2)').html(`<input type="text"  name="update_rep_content" value = "\${oldContent}" />`); 
});

$(".btn-delete").click((e)=>{
	if(confirm("정말 삭제하시겠습니까?")){
		
	$("input[name=type]").val("delete");
	 $(e.target).parent().submit();  
	}
});
/* 숨김처리관련 스크립트 */
	 $(document.commReRepEnrollFrm).hide();
	  
	 $('.fa-replyd').on('click', (e)=>
 {
   console.log("click");
   
   $(e.target).parent().next().next().slideToggle(500);                            
 });
	 
	 
	 
	 
	 
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
		
<% if(editable){ %>
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
<% 	} %>
</script>
<!-- community user detail 끝 -->
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>