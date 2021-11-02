<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%
	List<Community> communityList = (List<Community>)request.getAttribute("communityList");
	List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
	List<String> commLikeList = (List<String>) request.getAttribute("commLikeList");
	List<Attachment> allAttachmentList = (List<Attachment>)request.getAttribute("allAttachmentList");
%>
	
	<section id="comm-likelist" class="comm-likelist section-space-padding">
          
		<div class="container">
			<div class="enrollBtn">
<%
if(loginMember != null){
%>

				<button id="btn-comm-board-enroll">글 작성하기</button>
<%
	}
%>
			</div>
			<div class="comm-like-list">
<%
		String attachFilename = "";
		String memberNo = "";
		String commNo = "";
		String profileAttachFilename = "";
		
		for(Community comm : communityList){
			for(Attachment att : allAttachmentList){
				
				if(att.getCommNo() != null && att.getCommNo().equals(comm.getCommNo())){
					attachFilename = att.getRenamedFilename();
					memberNo = att.getMemberNo();
					commNo = att.getCommNo();
				}
				if(att.getCommNo() == null && att.getFontNo() == null && att.getMemberNo().equals(comm.getMemberNo())){
					profileAttachFilename = att.getRenamedFilename();
				}
			}
%>

				<div class="like-comm">
					<div class="community-profile-box">
						<div class="community-profile" onclick="location.href='<%= request.getContextPath()%>/community/writerDetail?commWriter=<%= memberNo %>'">
							<div class="community-profile-photo-box">
								<img class="community-profile-photo" src="<%= request.getContextPath()%>/upload/profilephotos/<%=profileAttachFilename%>"> 
							</div>
							<span><%= comm.getCommWriter() %></span>
						</div>
					</div>
					<div class="comm-img">
						<a href="<%= request.getContextPath()%>/community/pictureDetail?commNo=<%= commNo %>">
						<img src="<%= request.getContextPath()%>/upload/community/<%=attachFilename%>" alt="" />
						</a>
	                               		
					</div>
					<div class="like-comm-buttons"> 
<%
				if(loginMember != null && !commLikeList.isEmpty() && commLikeList.contains(comm.getCommNo())){
%>
						<i class="fas fa-heart" data-comm-no="<%=comm.getCommNo()%>"><span><%=comm.getCommLikeCount() %></span></i>
<%
				}else{
%>                                    
						<i class="far fa-heart" data-comm-no="<%=comm.getCommNo()%>"><span><%=comm.getCommLikeCount() %></span></i>
<%
				}
%>                                    
						<i class="fas fa-search-plus"></i>
					</div>
					<div class="like-comm-content">
						<span><%= comm.getCommTitle() %></span>
					</div>
				</div> 

<%
		}
	
%>
			</div>
		</div>
	</section>
	
	
<script>
	$(".comm-img").click((e) =>{
		<%-- location.href = "<%= request.getContextPath()%>/community/board?commNo="; --%>
		location.href = "<%= request.getContextPath()%>/community/board";
	});
	$("#btn-comm-board-enroll").click((e)=>{
		
		location.href = "<%= request.getContextPath()%>/community/boardEnroll";
	});
	
	/* 좋아요 버튼 클릭시 사용자 좋아요 여부에 따른 버튼 이벤트 */
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
			url: "<%= request.getContextPath()%>/community/commLike",
			dataType: "json",
			type: "GET",
			data: {'commNo' : $commNo},
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
</script>
        <!-- Portfolio End -->
		
	

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>