<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%
	List<Community> communityList = (List<Community>)request.getAttribute("communityList");
	List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
	Member member = (Member) request.getAttribute("member");
	Attachment profileAttachment = (Attachment) request.getAttribute("profileAttachment");
%>
	
        <section id="comm-writer-collection" class="comm-writer-collection section-space-padding">
          
			<div class="container">
                
				<div class="writer-collection-profile" onclick="location.href='<%= request.getContextPath()%>/community/writerDetail?commWriter=<%= profileAttachment.getMemberNo() %>'">
					<div class= "writer-profile-photo-box">
					<img class="community-profile-photo" src="<%= request.getContextPath()%>/upload/profilephotos/<%=profileAttachment.getRenamedFilename()%>">
					</div>
					<h4><%= member.getMemberName() %></h4>
				</div>
				<div>
					<ul class="writer-collection-menu">
						<li class="writer-collection-list">커뮤니티 이력</li>
						<li class="writer-collection-list">좋아요한 폰트</li>
						<li class="writer-collection-list">좋아요한 커뮤니티</li>
					</ul>
				
				</div>
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
                               
			</div>
        </section>
<script>
	
</script>
        <!-- Portfolio End -->
		
	

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>