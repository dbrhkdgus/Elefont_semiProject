<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="com.kh.elefont.community.model.vo.Community"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
<%
	List<Community> communityList = (List<Community>)request.getAttribute("communityList");
	List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
	
	
	
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
		
		for(Community comm : communityList){
			for(Attachment att : attachmentList){
				
				if(att.getCommNo().equals(comm.getCommNo())){
					attachFilename = att.getRenamedFilename();
					memberNo = att.getMemberNo();
					commNo = att.getCommNo();
				}
			}
%>

 						<div class="like-comm">
<%--                            <a href="<%= request.getContextPath()%>/community/writerDetail?writer=<% userId%>"><i class="fas fa-user"></i><div class="like-comm-writer"> user id </div></a> --%>
                                <a href="<%= request.getContextPath()%>/community/writerDetail?commWriter=<%= memberNo %>"><i class="fas fa-user"></i><div class="like-comm-writer"> <%= comm.getCommWriter() %> </div></a>
                                <div class="comm-img">
                               		<a href="<%= request.getContextPath()%>/community/pictureDetail?commNo=<%= commNo %>">
                               		<img src="<%= request.getContextPath()%>/upload/community/<%=attachFilename%>" alt="" width=16px/>
                               		</a>
                               		
                                </div>
                                <div class="like-comm-buttons"> 
                                    <i class="fas fa-heart"></i>
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
</script>
        <!-- Portfolio End -->
		
	

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>