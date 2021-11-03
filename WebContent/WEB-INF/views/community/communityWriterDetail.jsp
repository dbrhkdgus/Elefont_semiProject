<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="com.kh.elefont.like_cart.model.vo.LikeFont"%>
<%@page import="com.kh.elefont.common.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/views/common/LandingHeader.jsp" %>
    
    <!-- community writer detail 시작 -->
<%
	Member writerMember = (Member)request.getAttribute("writerMember");
	Attachment profileAttachment = (Attachment)request.getAttribute("profileAttachment");
	int totalCommunityByWriter = (int)request.getAttribute("totalCommunityByWriter");
	List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
	List<Font> fontList = (List<Font>)request.getAttribute("fontList");
	System.out.println(fontList);
%>
<section id="portfolio" class="portfolio section-space-padding">
    <div id="writer-detail"class="container"> 
        <div class="comm-writer-detail">

            <div class="comm-detail-writer-info">
                <div class="comm-detail-writer-photo">
                <img src="<%= request.getContextPath()%>/upload/profilephotos/<%=profileAttachment.getRenamedFilename()%>" alt="" class="writer-profile-img">
                </div>
                <h2><%= writerMember.getMemberName() %></h2>
                <h2>게시글 수 : <span><%= totalCommunityByWriter %></span></h2>
            </div>
            
            <div class="comm-writer-history">
                <div class="comm-writer-comm-history">
                    <h4 id="shop-detail-rep">커뮤니티 게시글 이력</h4>
                    <div class="comm-writer-comm-history-content">
 <%
 	int cnt = 0;
 	for(Attachment att : attachmentList){
 		if(cnt < 8) {
 %>
 						<img src="<%= request.getContextPath()%>/upload/community/<%=att.getRenamedFilename()%>" alt="" width=16px/>
 
 <%	
 			cnt++;
 		} else if(cnt >=8 ) {
 %>
 		<p>전체보기</p>
 <%
 		}
 	}
 %>                       
            
                    </div>
                   
                </div>
                <div class="comm-writer-font-history">
                    <h4 id="shop-detail-rep">폰트 좋아요 이력</h4>
                <div class="comm-writer-font-history-content">
<%
	int fontCnt = 0;
	for(Font font : fontList) {
		if(fontCnt < 4) {
%>
                        <div class="like-item-comm">
                            <p><%=font.getFontName() %></p>
                        </div>
<%
			
			fontCnt++;
		} else if(fontCnt >= 4) {
			
%>
                    </div>
			<p onclick="location.href='<%=request.getContextPath() %>/member/fontLikeList?memberNo=<%= writerMember.getMemberNo() %>'">전체보기</p>
<%
		}
	}
%>
                </div>
            </div>
            
        </div>
            
    </div>
</section>
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>

<!-- community user detail 끝 -->