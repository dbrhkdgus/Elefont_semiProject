<%@page import="com.kh.elefont.like_cart.model.vo.CommLike"%>
<%@page import="com.kh.elefont.like_cart.model.vo.LikeFont"%>
<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%
	int tabIndex = (int)session.getAttribute("tabIndex");

	List<Community> communityList = (List<Community>)request.getAttribute("communityList");
	List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
	Member member = (Member) request.getAttribute("member");
	Attachment profileAttachment = (Attachment) request.getAttribute("profileAttachment");
	
	List<Font> likeFontList = (List<Font>)request.getAttribute("likeFontList");
	List<Font> allFontList = (List<Font>)request.getAttribute("allFontList");
	
	List<Community> commLikeList = (List<Community>)request.getAttribute("commLikeList");
	List<Community> allCommunityList = (List<Community>)request.getAttribute("allCommunityList");
	List<Attachment> allAttachmentList = (List<Attachment>)request.getAttribute("allAttachmentList");
%>
<style>
<% 
if(!allFontList.isEmpty()){
	for(Font font : allFontList){
		if(font.getFontFamily() != null){
%>


@font-face {
    font-family: '<%= font.getFontFamily()%>';
    src: url('<%= font.getFontUrl()%>') format('woff');
    font-weight: normal;
    font-style: normal;
}

<% 
		}
	} 
}
%>
</style>
	
        <section id="comm-writer-collection" class="comm-writer-collection section-space-padding">
          
			<div class="container">
                <div class="writer-collection-profile-menu">
					<div class="writer-collection-profile" onclick="location.href='<%= request.getContextPath()%>/community/writerDetail?commWriter=<%= profileAttachment.getMemberNo() %>'">
						<div class= "writer-profile-photo-box">
						<img class="community-profile-photo" src="<%= request.getContextPath()%>/upload/profilephotos/<%=profileAttachment.getRenamedFilename()%>">
						</div>
						<h4><%= member.getMemberName() %></h4>
					</div>
					<div class="collection-tab-bar">
						<ul class="writer-collection-menu">
							<a class="active" href="#"><li class="writer-collection-list">커뮤니티 이력</li></a>
							<a href="#"><li class="writer-collection-list">좋아요한 폰트</li></a>
							<a href="#"><li class="writer-collection-list">좋아요한 커뮤니티</li></a>
						</ul>
					</div>
                </div>
                <div id="tab-content">
                
					<div class="collection-list-center">
						<div class="writer-collection-comm-list">
<%
for(Attachment att : attachmentList) {
%>	
							<div class="collection-comm-img-box" onclick="location.href='<%= request.getContextPath()%>/community/pictureDetail?commNo=<%= att.getCommNo() %>'">
								<img class="collection-comm-img" src="<%= request.getContextPath()%>/upload/community/<%=att.getRenamedFilename()%>" alt="" />
							</div>
<%
}
%>
						</div>
					</div>
					<div class="like-font-collection-box">
	                	<div class="like-font-collection">
<%
String fontFamily = "";

for(Font f : likeFontList) {
	for(Font allFont : allFontList) {
		if(f.getFontNo().equals(allFont.getFontNo())) {
			f.setFontFamily(allFont.getFontFamily());
			fontFamily = f.getFontFamily();
			
		}
	}
%>
							<div class="collection-user-like-font-box" onclick="location.href='<%= request.getContextPath()%>/shopDetail?fontNo=<%= f.getFontNo() %>'">
								<div class="collection-like-font-box">
									<p class="like-font-name" style="font-family: <%= fontFamily %>;"><%= f.getFontName() %></p>
								</div>
								<div class="user-like-font-name">
		                        	<p><%= f.getFontName() %></p>
		                        </div>
							</div>
<%
}
%>
						</div>
	                </div>
	                <div class="collection-list-center">
	                	<div class="writer-collection-comm-list">
<%
String filename = "";

for(Community community : commLikeList) {
	for(Attachment attach : allAttachmentList) {
		if(community.getCommNo().equals(attach.getCommNo())) {
			filename = attach.getRenamedFilename();
		}
	}

%>
							<div class="collection-comm-img-box" onclick="location.href='<%= request.getContextPath()%>/community/pictureDetail?commNo=<%= community.getCommNo() %>'">
								<img class="collection-comm-img" src="<%= request.getContextPath()%>/upload/community/<%= filename %>" alt="" />
							</div>
<%
} 
%>                	
                		</div>
                	</div>
                </div>
			</div>
        </section>
<script>
let $tabBtn = $(".collection-tab-bar>ul>a");
let $tabContent = $("#tab-content>div");

$tabContent.hide().eq(<%=tabIndex%>).show();
$tabBtn
	.removeClass("active")
	.eq(<%=tabIndex%>).addClass("active"); 

$tabBtn.click((e)=>{
	  const $target = $(e.target).parent();    
	  const index = $target.index(); 
	  $tabBtn.removeClass("active");
	  $target.addClass("active");
	  $tabContent
		  .css("display","none")
		  .eq(index).css("display","flex");
});

</script>
        <!-- Portfolio End -->
		
	

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>