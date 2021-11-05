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
	int totalFontLikeByWriter = (int)request.getAttribute("totalFontLikeByWriter");
	List<Attachment> attachmentList = (List<Attachment>)request.getAttribute("attachmentList");
	List<Font> fontLikeList = (List<Font>)request.getAttribute("fontLikeList");
	List<Font> allFontList = (List<Font>)request.getAttribute("allFontList");
	System.out.println(fontLikeList);
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
<section id="portfolio" class="portfolio section-space-padding">
    <div class="container"> 
        <div class="comm-writer-detail">

            <div class="comm-detail-writer-info">
                <div class="comm-detail-writer-photo">
                <img src="<%= request.getContextPath()%>/upload/profilephotos/<%=profileAttachment.getRenamedFilename()%>" alt="" class="writer-profile-img">
                </div>
                <h2><%= writerMember.getMemberName() %></h2>
<%
boolean bool = ("대답 안 함".equals(writerMember.getMemberJob()) || "기타".equals(writerMember.getMemberJob())); 
if(!bool) {
%>
                <h4> 직업 : <span><%= writerMember.getMemberJob() %></span></h4>
<%} %>
            </div>
            
            <div class="comm-writer-history">
                <div class="comm-writer-comm-history">
                    <h4 id="shop-detail-rep">커뮤니티 게시글 이력(<%= totalCommunityByWriter %>)</h4>
                    <div class="comm-writer-comm-history-content">
 <%
 	int cnt = 0;
 	for(Attachment att : attachmentList){
 		if(cnt < 8) {
 %>
 						<div onclick="location.href='<%= request.getContextPath()%>/community/pictureDetail?commNo=<%= att.getCommNo() %>'">
 						<img class="comm-history-content-img" src="<%= request.getContextPath()%>/upload/community/<%=att.getRenamedFilename()%>" alt="" width=16px/>
 						</div>
 
 <%	
 			cnt++;
 		}
 	}
 %>
                    </div>
 
 <%
 	if(cnt >= 8) {
 %>
 		<div class="more-btn-align">
 			<span class="more-btn-text-algin" onclick="location.href='<%=request.getContextPath()%>/community/writerCollections?memberNo=<%= profileAttachment.getMemberNo() %>'">전체보기</span>
 		</div>
 <%
 	}
 %>                       
            
                   
                </div>
                <div class="comm-writer-font-history">
                    <h4 id="shop-detail-rep">폰트 좋아요 이력(<%= totalFontLikeByWriter %>)</h4>
                <div class="comm-writer-font-history-content">
<%
	int fontCnt = 0;
	String fontFamily = "";
	
	for(Font f : fontLikeList) {
		for(Font allFont : allFontList) {
			if(f.getFontNo().equals(allFont.getFontNo())) {
				f.setFontFamily(allFont.getFontFamily());
				fontFamily = f.getFontFamily();
				
			}
		}
		if(fontCnt < 4) {
%>
						<div class="user-like-font-box" onclick="location.href='<%= request.getContextPath()%>/shopDetail?fontNo=<%= f.getFontNo() %>'">
	                        <div class="like-item-comm">
	                            <p style="font-family: <%= fontFamily %>;"><%=f.getFontName() %></p>
	                        </div>
	                        <div class="user-like-font-name">
	                        	<p><%= f.getFontName() %></p>
	                        </div>
						</div>
<%
				fontCnt++;
		}
				System.out.println("전체보기cnt@jsp : " + fontCnt);
	}
%>
                    </div>
<%
	if(fontCnt >= 4) {
%>
					<div class="more-btn-align">
						<span class="more-btn-text-algin" onclick="location.href='<%=request.getContextPath()%>/community/writerCollections?memberNo=<%= profileAttachment.getMemberNo() %>'">전체보기</span>
					</div>
<%
	}
			
%>
            	</div>
            </div>
        </div>
            
    </div>
</section>
<%@ include file = "/WEB-INF/views/common/footer.jsp" %>

<!-- community user detail 끝 -->