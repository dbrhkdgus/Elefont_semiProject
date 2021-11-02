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
                
				<div class="comm-like-list">
					<img src="<%= request.getContextPath()%>/upload/profilephotos/<%=profileAttachment.getRenamedFilename()%>">
					<h4><%= member.getMemberName() %></h4>
				</div>
 						<div class="like-comm">
<%--                            <a href="<%= request.getContextPath()%>/community/writerDetail?writer=<% userId%>"><i class="fas fa-user"></i><div class="like-comm-writer"> user id </div></a> --%>
                                <a href="#"><i class="fas fa-user"></i><div class="like-comm-writer"> 글쓴이 </div></a>
                                <div class="comm-img">
                               		<a href="#">
                               		<img src="https://cdn.discordapp.com/attachments/893374121449254916/904648543921401856/unknown.png" alt="" />
                               		</a>
                               		
                                </div>
                                
                            </div> 


              
                           

                    </div>
                </div>
            </div>
        </section>
<script>
	
</script>
        <!-- Portfolio End -->
		
	

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>