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
	
	List<Community> list = (List<Community>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");

%>


		
	<section id="comm-likelist" class="comm-likelist section-space-padding">
		          
		<div class="container">
			<div class="enrollBtn">
			
			
			<div id ="comm-search-container">
			
				<select id="searchType">
				<option value="writerName"<%="writerName".equals(searchType) ? "selected":"" %>>작성자</option>		
	            <option value="title" <%="title".equals(searchType) ? "selected":"" %> >제목</option>
	            <option value="content" <%="content".equals(searchType) ? "selected":"" %> >내용</option>
	            
	        </select>
			
				<div id="search-writerName" class="comm-search-type">
					<form action="<%=request.getContextPath()%>/community/commFinder">
					<input type="hidden" name="searchType" value="writerName" />
					<input type="text"  name="searchKeyword" placeholder="작성자 이름으로 검색"/>
					<button type="submit">검색하기</button>
					</form>
				</div>
				
				<div id="search-title" class="comm-search-type">
					<form action="<%=request.getContextPath()%>/community/commFinder">
					<input type="hidden" name="searchType" value="title" />
					<input type="text"  name="searchKeyword" placeholder="제목으로 검색"/>
					<button type="submit">검색하기</button>
					</form>
				</div>
				
				<div id="search-content" class="comm-search-type">
					<form action="<%=request.getContextPath()%>/community/commFinder">
					<input type="hidden" name="searchType" value="content" />
					<input type="text"  name="searchKeyword" placeholder="내용으로 검색"/>
					<button type="submit">검색하기</button>
					</form>
				</div>
			
			
			</div>
			
	
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

	if(list==null){
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
						
					</div>
					<div class="like-comm-content">
						<p class="comm-user-title"><%= comm.getCommTitle() %></p>
					</div>
				</div> 




<%
	}
}else{
	String attachFilename = "";
	String memberNo = "";
	String commNo = "";
	String profileAttachFilename = "";
	
	for(Community comm : list){
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
}
%>
			</div>
		</div>
	</section>
	
	
<script>
//1. .search-type 감추기
$("#search-title").hide();
$("#search-content").hide();

	$("#searchType").change((e) => {
		// e.target 이벤트발생객체 -> #searchType
		const type = $(e.target).val();
		
		// 1. .search-type 감추기
		$(".comm-search-type").hide();
		
		// 2. #search-${type} 보여주기(display:inline-block)
		$(`#search-\${type}`).css("display", "inline-block");
	});	
	
	
	
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

	alert("로그인 후 이용 가능합니다.");
	$('.loginBox').show();
	$(loginId).select();
  $('#loginBtn').css("color","gold");
	
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
		
<%-- <style>
    div#search-writerName {display: <%= searchType == null || "writerName".equals(searchType) ? "inline-block" : "none" %>; }
    div#search-title {display: <%= "title".equals(searchType) ? "inline-block" : "none" %>;}
    div#search-content {display: <%= "content".equals(searchType) ? "inline-block" : "none" %>;}
</style> --%>		

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>