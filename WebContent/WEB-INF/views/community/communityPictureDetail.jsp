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
	
%>
<section id="portfolio" class="portfolio section-space-padding">
    <div class="container">
        <div class="comm-pic-detail">

            <div class="comm-board-content">
            <h1><%=community.getCommTitle() %></h1>
                <img id="comm-user-attach-img" src="<%= request.getContextPath()%>/upload/community/<%=attachment.getRenamedFilename()%>" alt="">
                <div class="comm-user-content">
                   <%=community.getCommContent() %>
                </div>
            </div>
            
            <div class="comm-writer-info">
                <div class="comm-writer-info-buttons">
                    <i class="far fa-heart"></i>
                    <i class="fas fa-search-plus"></i>
                </div>
                <div class="comm-writer-img-name">
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


<!-- community user detail 끝 -->



<%@ include file = "/WEB-INF/views/common/footer.jsp" %>